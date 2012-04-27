package org.modelversioning.emfprofile.project.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.eclipse.pde.internal.ui.wizards.IProjectProvider;
import org.eclipse.pde.internal.ui.wizards.plugin.NewProjectCreationOperation;
import org.eclipse.pde.internal.ui.wizards.plugin.PluginFieldData;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;

@SuppressWarnings("restriction")
public class NewEMFProfileProjectOperation extends NewProjectCreationOperation {

	private static final String DEFAULT_VERSION = "1.0.0.qualifier";

	private EMFProfileProjectData projectData;

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
		CoreUtility.addNatureToProject(projectData.getProjectHandle(),
				EMFProfileProjectNature.NATURE_ID, null);
		monitor.worked(1);

		monitor.subTask("Generate EMF Profile");
		// TODO generate empty profile
		// TODO create empty icons folder
		monitor.worked(1);

		monitor.subTask("Open EMF Profile");
		// TODO open profile diagram
		monitor.worked(1);
	}

	@Override
	protected int getNumberOfWorkUnits() {
		// we have three additional tasks
		return super.getNumberOfWorkUnits() + 3;
	}

	@Override
	protected void createContents(IProgressMonitor monitor, IProject project)
			throws CoreException, JavaModelException,
			InvocationTargetException, InterruptedException {
		super.createContents(monitor, project);
		createIconFolder(monitor, project);
	}

	private void createIconFolder(IProgressMonitor monitor, IProject project)
			throws CoreException {
		project.getFolder(EMFProfileProjectNature.ICONS_FOLDER_NAME).create(
				false, true, new SubProgressMonitor(monitor, 0));
	}

	@Override
	protected void fillBinIncludes(IProject project, IBuildEntry binEntry)
			throws CoreException {
		super.fillBinIncludes(project, binEntry);
		binEntry.addToken(EMFProfileProjectNature.ICONS_FOLDER_NAME + "/"); //$NON-NLS-1$
	}

}
