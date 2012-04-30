package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.EMFProfilePlugin;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.project.EMFProfileProjectNature;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;
import org.modelversioning.emfprofile.registry.IEMFProfileRegistry;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.osgi.framework.Bundle;

public class EMFProfileRegistry extends Observable implements
		IEMFProfileRegistry {

	private final ResourceSet resourceSet = new ResourceSetImpl();
	private Map<String, IProfileProvider> registeredProfileProviders = new HashMap<String, IProfileProvider>();;
	private Collection<Profile> registeredProfiles = new ArrayList<Profile>();

	public EMFProfileRegistry() {
		loadProfileProvidersFromExtensionPoint();
		loadProfileProvidersFromWorkspace();
	}

	private void loadProfileProvidersFromExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						IEMFProfileRegistry.PROFILE_EXTENSION_POINT_ID);
		for (IConfigurationElement configElement : config) {
			try {
				registerProfile(configElement);
			} catch (Exception e) {
				EMFProfilePlugin.getPlugin().log(
						new Status(IStatus.WARNING, EMFProfilePlugin.ID,
								"Could not load profile from extension point",
								e));
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
		registerProfile(bundleProfileProvider);
	}

	private URI createProfileURI(IContributor contributor,
			String profileResourceName) {
		return URI.createURI("platform:/plugin/" + contributor.getName() //$NON-NLS-1$
				+ "/" + profileResourceName); //$NON-NLS-1$
	}

	private void loadProfileProvidersFromWorkspace() {
		IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (IProject project : allProjects) {
			try {
				if (project.hasNature(EMFProfileProjectNature.NATURE_ID)) {
					registerProfiles(project);
				}
			} catch (CoreException e) {
				EMFProfilePlugin
						.getPlugin()
						.log(new Status(
								IStatus.WARNING,
								EMFProfilePlugin.ID,
								"Could not load profile project from workspace",
								e));
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
			registerProfile(profileProvider);
		}
	}

	private URI createProfileURI(IFile profileDiagramFile) {
		return URI.createURI("platform:/resource/" //$NON-NLS-1$
				+ profileDiagramFile.getProject().getName()
				+ "/" //$NON-NLS-1$
				+ profileDiagramFile.getProjectRelativePath()
						.toPortableString());
	}

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
		registeredProfileProviders.put(profileProvider.getProfileNsURI(),
				profileProvider);
		registeredProfiles.add(profileProvider.getProfile());
		EPackage.Registry.INSTANCE.remove(profileProvider.getProfileNsURI());
		EPackage.Registry.INSTANCE.put(profileProvider.getProfileNsURI(),
				profileProvider.getProfile());
		notifyObservers();
	}

	@Override
	public void unregisterProfile(IProfileProvider profileProvider) {
		registeredProfileProviders.remove(profileProvider.getProfileNsURI());
		registeredProfiles.remove(profileProvider.getProfile());
		EPackage.Registry.INSTANCE.remove(profileProvider.getProfileNsURI());
		notifyObservers();
	}

	public void update(Observable o, Object arg) {
		o.notifyObservers();
	}

}
