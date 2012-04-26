package org.modelversioning.emfprofile.project.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.internal.ui.wizards.IProjectProvider;
import org.eclipse.pde.internal.ui.wizards.plugin.NewProjectCreationOperation;
import org.eclipse.pde.internal.ui.wizards.plugin.PluginFieldData;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

@SuppressWarnings("restriction")
public class NewEMFProfileProjectOperation extends WorkspaceModifyOperation {

	private static final String DEFAULT_VERSION = "1.0.0.qualifier";

	private EMFProfileProjectData projectData;

	public NewEMFProfileProjectOperation(EMFProfileProjectData projectData) {
		super();
		this.projectData = projectData;
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		NewProjectCreationOperation projectCreationOperation = createNewProjectCreationOperation();
		projectCreationOperation.run(monitor);
		// TODO avoid opening the manifest editor?
		// TODO set profile nature
		// TODO generate empty profile
		// TODO create empty icons folder
		// TODO open profile diagram
	}

	private NewProjectCreationOperation createNewProjectCreationOperation() {
		return new NewProjectCreationOperation(createPluginData(),
				createProjectProvider(), createContentWizard());
	}

	private IFieldData createPluginData() {
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

	private IProjectProvider createProjectProvider() {
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

	private IPluginContentWizard createContentWizard() {
		return null;
	}

}
