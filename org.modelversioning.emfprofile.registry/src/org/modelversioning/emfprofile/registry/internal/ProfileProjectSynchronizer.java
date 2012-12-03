package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.EMFProfilePlugin;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;

public class ProfileProjectSynchronizer implements IResourceChangeListener {

	private ResourceSet resourceSet;
	private ProfileRegistry registry;

	public ProfileProjectSynchronizer(ProfileRegistry registry,
			ResourceSet resourceSet) {
		this.registry = registry;
		this.resourceSet = resourceSet;
	}

	public void registerProfileProjects() {
		registerProfiles(getAllWorkspaceProjects());
	}

	private IProject[] getAllWorkspaceProjects() {
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

	private void registerProfiles(IProject[] allProjects) {
		for (IProject project : allProjects) {
			try {
				if (isOpenProfileProject(project)) {
					registerProfilesFromProject(project);
				}
			} catch (Exception e) {
				logWarning(e, "Errors while loading profiles from project "
						+ project.getName());
			}
		}
	}

	private boolean isOpenProfileProject(IProject project) {
		return project.isOpen() && isProfileProject(project);
	}

	private boolean isProfileProject(IResource resource) {
		if (resource instanceof IProject) {
			IProject project = (IProject) resource;
			try {
				if (project.hasNature(EMFProfileProjectNature.NATURE_ID)) {
					return true;
				}
			} catch (CoreException e) {
				return false;
			}
		}
		return false;
	}

	private void registerProfilesFromProject(IProject project) {
		Collection<IFile> profileDiagramFiles = EMFProfileProjectNatureUtil
				.getProfileDiagramFiles(project);
		for (IFile profileDiagramFile : profileDiagramFiles) {
			Resource profileResource = resourceSet.getResource(
					createProfileURI(profileDiagramFile), true);
			ProjectProfileProvider profileProvider = new ProjectProfileProvider(
					project, profileResource);
			registry.doRegisterProfile(profileProvider);
		}
	}

	private URI createProfileURI(IFile profileDiagramFile) {
		return URI.createPlatformResourceURI(profileDiagramFile.getFullPath()
				.toString(), true);
	}

	public void startSynchronizing() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	public void stopSynchronizing() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Collection<IProject> affectedProjects = getAffectedProfileProjects(event);
		for (IProject project : affectedProjects) {
			unregisterProfilesFromProject(project);
			if (!isDeleteOrCloseEvent(event)) {
				try {
					registerProfilesFromProject(project);
				} catch (Exception e) {
					logWarning(e, "Errors while loading profiles from project "
							+ project.getName());
				}
			}
		}
		if (affectedProjects.size() > 0)
			registry.notifyObservers();
	}

	private Collection<IProject> getAffectedProfileProjects(
			IResourceChangeEvent event) {
		Collection<IProject> affectedProjects = new ArrayList<IProject>();
		if (event.getDelta() != null) {
			for (IResourceDelta affectedChildren : event.getDelta()
					.getAffectedChildren()) {
				if (isProfileProject(affectedChildren.getResource())) {
					affectedProjects.add((IProject) affectedChildren
							.getResource());
				}
			}
		} else if (event.getResource() != null
				&& event.getResource() instanceof IProject) {
			if (isProfileProject(event.getResource())) {
				affectedProjects.add((IProject) event.getResource());
			}
		}
		return affectedProjects;
	}

	private boolean isDeleteOrCloseEvent(IResourceChangeEvent event) {
		return IResourceChangeEvent.PRE_CLOSE == event.getType()
				|| IResourceChangeEvent.PRE_DELETE == event.getType();
	}

	private void unregisterProfilesFromProject(IProject project) {
		for (ProjectProfileProvider profileProvider : registry
				.getRegisteredProjectProfileProviders()) {
			if (project.equals(profileProvider.getProject())) {
				registry.doUnregisterProfile(profileProvider);
			}
		}
	}

	private void logWarning(Exception exception, String msg) {
		EMFProfilePlugin.getPlugin()
				.log(new Status(IStatus.WARNING, EMFProfilePlugin.ID, msg,
						exception));
	}

}
