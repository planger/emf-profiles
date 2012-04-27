package org.modelversioning.emfprofile.project.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.pde.internal.ui.wizards.IProjectProvider;
import org.eclipse.pde.internal.ui.wizards.plugin.NewProjectCreationOperation;
import org.eclipse.pde.internal.ui.wizards.plugin.PluginFieldData;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.eclipse.ui.PartInitException;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.diagram.part.EMFProfileDiagramEditorUtil;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;

@SuppressWarnings("restriction")
public class NewEMFProfileProjectOperation extends NewProjectCreationOperation {

	private static final String DEFAULT_VERSION = "1.0.0.qualifier";

	private EMFProfileProjectData projectData;

	private Resource profileDiagramResource;

	public NewEMFProfileProjectOperation(EMFProfileProjectData projectData) {
		super(createPluginData(projectData),
				createProjectProvider(projectData), createContentWizard());
		this.projectData = projectData;
	}

	private static IFieldData createPluginData(EMFProfileProjectData projectData) {
		PluginFieldData pluginFieldData = new PluginFieldData();
		pluginFieldData.setDoGenerateClass(false);
		pluginFieldData.setEnableAPITooling(false);
		pluginFieldData.setId(projectData.getProjectName());
		pluginFieldData.setLegacy(false);
		pluginFieldData.setName(projectData.getProjectName());
		pluginFieldData.setRCPApplicationPlugin(false);
		pluginFieldData.setUIPlugin(false);
		pluginFieldData.setVersion(DEFAULT_VERSION);
		return pluginFieldData;
	}

	private static IProjectProvider createProjectProvider(
			final EMFProfileProjectData projectData) {
		return new IProjectProvider() {
			@Override
			public String getProjectName() {
				return projectData.getProfileName();
			}

			@Override
			public IProject getProject() {
				return projectData.getProjectHandle();
			}

			@Override
			public IPath getLocationPath() {
				return projectData.getLocationPath();
			}
		};
	}

	private static IPluginContentWizard createContentWizard() {
		return null;
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		super.execute(monitor);

		monitor.subTask("Configure EMF Profile project");
		addEMFProfileProjectNature();
		monitor.worked(1);

		monitor.subTask("Open EMF Profile");
		openEMFProfileDiagram();
		monitor.worked(1);
	}

	private void addEMFProfileProjectNature() throws CoreException {
		EMFProfileProjectNatureUtil.addNature(projectData.getProjectHandle());
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

	@Override
	protected int getNumberOfWorkUnits() {
		// we have two additional tasks
		return super.getNumberOfWorkUnits() + 2;
	}

	@Override
	protected void createContents(IProgressMonitor monitor, IProject project)
			throws CoreException, JavaModelException,
			InvocationTargetException, InterruptedException {
		super.createContents(monitor, project);
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

	@Override
	protected void fillBinIncludes(IProject project, IBuildEntry binEntry)
			throws CoreException {
		super.fillBinIncludes(project, binEntry);
		binEntry.addToken(EMFProfileProjectNature.ICONS_FOLDER_NAME + "/"); //$NON-NLS-1$
		binEntry.addToken(EMFProfileProjectNature.PROFILE_DIAGRAM_FILE_NAME);
	}

}
