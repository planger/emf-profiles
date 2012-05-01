package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.modelversioning.emfprofile.EMFProfilePlugin;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;
import org.modelversioning.emfprofile.registry.IEMFProfileRegistry;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.osgi.framework.Bundle;

public class EMFProfileRegistry extends Observable implements
		IEMFProfileRegistry, IResourceChangeListener {

	private final ResourceSet resourceSet = new ResourceSetImpl();
	private Map<String, IProfileProvider> registeredProfileProviders = new HashMap<String, IProfileProvider>();
	private Collection<Profile> registeredProfiles = new ArrayList<Profile>();

	public EMFProfileRegistry() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		loadProfileProvidersFromExtensionPoint();
		loadProfileProvidersFromWorkspace();
	}

	// ***************** LOAD PROFILES FROM EXTENSION POINTS

	private void loadProfileProvidersFromExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						IEMFProfileRegistry.PROFILE_EXTENSION_POINT_ID);
		registerProfiles(config);
		notifyObservers();
	}

	private void registerProfiles(IConfigurationElement[] config) {
		for (IConfigurationElement configElement : config) {
			try {
				registerProfile(configElement);
			} catch (Exception e) {
				logWarning(e, "Could not load profile from extension point");
			}
		}
	}

	private void registerProfile(IConfigurationElement configElement) {
		IContributor contributor = configElement.getContributor();
		String profileResourceName = configElement
				.getAttribute(PROFILE_EXTENSION_POINT_RESOURCE_NAME);
		Bundle bundle = Platform.getBundle(contributor.getName());
		Resource profileResource = resourceSet.getResource(
				createProfileURI(contributor, profileResourceName), true);
		BundleProfileProvider bundleProfileProvider = new BundleProfileProvider(
				bundle, profileResource);
		doRegisterProfile(bundleProfileProvider);
	}

	private URI createProfileURI(IContributor contributor,
			String profileResourceName) {
		return URI.createURI("platform:/plugin/" + contributor.getName() //$NON-NLS-1$
				+ "/" + profileResourceName); //$NON-NLS-1$
	}

	// ***************** /LOAD PROFILES FROM EXTENSION POINTS

	// ***************** Resource listening
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Collection<IProject> affectedProjects = getAffectedProfileProjects(event);
		for (IProject project : affectedProjects) {
			unregisterProfiles(project);
			if (!isDeleteOrClose(event)) {
				registerProfiles(project);
			}
		}
		if (affectedProjects.size() > 0)
			notifyObservers();
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

	private boolean isDeleteOrClose(IResourceChangeEvent event) {
		return IResourceChangeEvent.PRE_CLOSE == event.getType()
				|| IResourceChangeEvent.PRE_DELETE == event.getType();
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

	private void unregisterProfiles(IProject project) {
		for (ProjectProfileProvider profileProvider : getRegisteredProjectProfileProviders()) {
			if (project.equals(profileProvider.getProject())) {
				doUnregisterProfile(profileProvider);
			}
		}
	}

	private Collection<ProjectProfileProvider> getRegisteredProjectProfileProviders() {
		Collection<ProjectProfileProvider> projectProfileProviders = new ArrayList<ProjectProfileProvider>();
		for (IProfileProvider profileProvider : registeredProfileProviders
				.values()) {
			if (profileProvider instanceof ProjectProfileProvider) {
				ProjectProfileProvider projectProfileProvider = (ProjectProfileProvider) profileProvider;
				projectProfileProviders.add(projectProfileProvider);
			}
		}
		return projectProfileProviders;
	}

	// ***************** Resource listening

	private void logWarning(Exception exception, String msg) {
		EMFProfilePlugin.getPlugin()
				.log(new Status(IStatus.WARNING, EMFProfilePlugin.ID, msg,
						exception));
	}

	// ***************** LOAD PROFILES FROM WORKSPACE

	private void loadProfileProvidersFromWorkspace() {
		IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		registerProfiles(allProjects);
		notifyObservers();
	}

	private void registerProfiles(IProject[] allProjects) {
		for (IProject project : allProjects) {
			try {
				if (project.hasNature(EMFProfileProjectNature.NATURE_ID)) {
					registerProfiles(project);
				}
			} catch (CoreException e) {
				logWarning(e, "Could not load profile project from workspace");
			}
		}
	}

	private void registerProfiles(IProject project) {
		Collection<IFile> profileDiagramFiles = EMFProfileProjectNatureUtil
				.getProfileDiagramFiles(project);
		for (IFile profileDiagramFile : profileDiagramFiles) {
			Resource profileResource = resourceSet.getResource(
					createProfileURI(profileDiagramFile), true);
			ProjectProfileProvider profileProvider = new ProjectProfileProvider(
					project, profileResource);
			doRegisterProfile(profileProvider);
		}
	}

	private URI createProfileURI(IFile profileDiagramFile) {
		return URI.createURI("platform:/resource/" //$NON-NLS-1$
				+ profileDiagramFile.getProject().getName()
				+ "/" //$NON-NLS-1$
				+ profileDiagramFile.getProjectRelativePath()
						.toPortableString());
	}

	// ***************** /LOAD PROFILES FROM WORKSPACE

	@Override
	public Collection<Profile> getRegisteredProfiles() {
		return Collections.unmodifiableCollection(registeredProfiles);
	}

	@Override
	public Collection<IProfileProvider> getRegisteredProfileProviders() {
		return Collections.unmodifiableCollection(registeredProfileProviders
				.values());
	}

	@Override
	public void registerProfile(IProfileProvider profileProvider) {
		doRegisterProfile(profileProvider);
		notifyObservers();
	}

	private void doRegisterProfile(IProfileProvider profileProvider) {
		if (isValidProfile(profileProvider.getProfile())) {
			registeredProfileProviders.put(profileProvider.getProfileNsURI(),
					profileProvider);
			registeredProfiles.add(profileProvider.getProfile());
			EPackage.Registry.INSTANCE
					.remove(profileProvider.getProfileNsURI());
			EPackage.Registry.INSTANCE.put(profileProvider.getProfileNsURI(),
					profileProvider.getProfile());
			setChanged();
		}
	}

	private boolean isValidProfile(Profile profile) {
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(profile,
				Collections.emptyMap());
		if (Diagnostic.OK == diagnostic.getSeverity()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void unregisterProfile(IProfileProvider profileProvider) {
		doUnregisterProfile(profileProvider);
		notifyObservers();
	}

	private void doUnregisterProfile(IProfileProvider profileProvider) {
		registeredProfileProviders.remove(profileProvider.getProfileNsURI());
		registeredProfiles.remove(profileProvider.getProfile());
		EPackage.Registry.INSTANCE.remove(profileProvider.getProfileNsURI());
		profileProvider.getProfile().eResource().unload();
		setChanged();
	}

}
