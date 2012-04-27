package org.modelversioning.emfprofile.project.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModelFactory;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.build.WorkspaceBuildModel;
import org.eclipse.pde.internal.core.natures.PDE;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;
import org.eclipse.pde.internal.core.project.PDEProject;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.diagram.part.EMFProfileDiagramEditorUtil;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;

@SuppressWarnings("restriction")
public class NewEMFProfileProjectOperation extends WorkspaceModifyOperation {

	private static final String DEFAULT_VERSION = "1.0.0.qualifier";

	private EMFProfileProjectData projectData;
	private IProject project;
	private Resource profileDiagramResource;

	public NewEMFProfileProjectOperation(EMFProfileProjectData projectData) {
		super();
		this.projectData = projectData;
		this.project = projectData.getProjectHandle();
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		
		// TODO set task number

		monitor.subTask("Create EMF Profile project");
		createPDEProject(new SubProgressMonitor(monitor, 1));
		monitor.worked(1);

		monitor.subTask("Create EMF Profile project contents");
		createContents(new SubProgressMonitor(monitor, 1));
		monitor.worked(1);

		monitor.subTask("Create manifest");
		createManifest(new SubProgressMonitor(monitor, 1));
		monitor.worked(1);

		monitor.subTask("Open EMF Profile");
		openEMFProfileDiagram();
		monitor.worked(1);
	}

	private void createPDEProject(IProgressMonitor monitor)
			throws CoreException {
		if (!project.exists()) {
			if (!Platform.getLocation().equals(projectData.getLocationPath())) {
				IProjectDescription desc = project.getWorkspace()
						.newProjectDescription(project.getName());
				desc.setLocation(projectData.getLocationPath());
				project.create(desc, monitor);
			} else {
				project.create(monitor);
			}
			project.open(null);
		}
		configureNatures(project);
	}

	private void configureNatures(IProject project) throws CoreException {
		if (!project.hasNature(PDE.PLUGIN_NATURE))
			CoreUtility.addNatureToProject(project, PDE.PLUGIN_NATURE, null);
		if (!project.hasNature(EMFProfileProjectNature.NATURE_ID))
			EMFProfileProjectNatureUtil.addNature(project);
	}

	private void createManifest(IProgressMonitor monitor) throws CoreException {
		IFile pluginXml = PDEProject.getPluginXml(project);
		WorkspacePluginModel fModel = new WorkspacePluginModel(pluginXml, false);
		IPluginBase pluginBase = fModel.getPluginBase();
		pluginBase.setSchemaVersion(TargetPlatformHelper
				.getSchemaVersionForTargetVersion(null));
		pluginBase.setId(projectData.getProjectName());
		pluginBase.setVersion(DEFAULT_VERSION);
		pluginBase.setName(projectData.getProjectName());
		pluginBase.setProviderName("");
		// TODO add extension point for registry
		// TODO add dependency to emf profiles plug-ins
		fModel.save();

		// build properties
		// TODO also configure source build
		IFile file = PDEProject.getBuildProperties(project);
		if (!file.exists()) {
			WorkspaceBuildModel model = new WorkspaceBuildModel(file);
			IBuildModelFactory factory = model.getFactory();
			IBuildEntry binEntry = factory
					.createEntry(IBuildEntry.BIN_INCLUDES);
			binEntry.addToken(ICoreConstants.PLUGIN_FILENAME_DESCRIPTOR);
			binEntry.addToken(EMFProfileProjectNature.ICONS_FOLDER_NAME + "/"); //$NON-NLS-1$
			binEntry.addToken(EMFProfileProjectNature.PROFILE_DIAGRAM_FILE_NAME);
			model.getBuild().add(binEntry);
			model.save();
		}
	}

	private void openEMFProfileDiagram() {
		if (profileDiagramResource != null) {
			try {
				EMFProfileDiagramEditorUtil.openDiagram(profileDiagramResource);
			} catch (PartInitException e) {
				new InvocationTargetException(e);
			}
		}
	}

	protected void createContents(IProgressMonitor monitor)
			throws CoreException, JavaModelException,
			InvocationTargetException, InterruptedException {
		createIconFolder(monitor, project);
		try {
			createProfile(monitor, project);
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		}
	}

	private void createIconFolder(IProgressMonitor monitor, IProject project)
			throws CoreException {
		project.getFolder(EMFProfileProjectNature.ICONS_FOLDER_NAME).create(
				false, true, new SubProgressMonitor(monitor, 0));
	}

	private void createProfile(IProgressMonitor monitor, IProject project)
			throws IOException {
		profileDiagramResource = EMFProfileDiagramEditorUtil.createDiagram(
				EMFProfileProjectNatureUtil.getProfileDiagramURI(project),
				new SubProgressMonitor(monitor, 0));
		setProfileDiagramData();
		saveProfileDiagramResource();
	}

	private void saveProfileDiagramResource() throws IOException {
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(profileDiagramResource);
		profileDiagramResource.save(null);
		((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
	}

	private void setProfileDiagramData() {
		final Profile profile = getProfileFromResource();
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(profileDiagramResource);
		editingDomain.getCommandStack().execute(
				new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						profile.setName(projectData.getProfileName());
						profile.setNsURI(projectData.getProfileNamespace());
					}
				});
	}

	private Profile getProfileFromResource() {
		if (profileDiagramResource == null)
			return null;
		for (TreeIterator<EObject> contents = profileDiagramResource
				.getAllContents(); contents.hasNext();) {
			EObject next = contents.next();
			if (next instanceof Profile) {
				return (Profile) next;
			}
		}
		return null;
	}

}
