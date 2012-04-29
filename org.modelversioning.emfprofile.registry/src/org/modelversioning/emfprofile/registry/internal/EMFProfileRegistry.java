package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.EMFProfilePlugin;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IEMFProfileRegistry;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.osgi.framework.Bundle;

// TODO synch with EMF Metamodel Registry

public class EMFProfileRegistry implements IEMFProfileRegistry {

	private final ResourceSet resourceSet = new ResourceSetImpl();
	private Map<String, IProfileProvider> registeredProfileProviders = new HashMap<String, IProfileProvider>();;
	private Collection<Profile> registeredProfiles = new ArrayList<Profile>();

	public EMFProfileRegistry() {
		loadProfileProvidersFromExtensionPoint();
	}

	private void loadProfileProvidersFromExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						IEMFProfileRegistry.PROFILE_EXTENSION_POINT_ID);
		for (IConfigurationElement configElement : config) {
			try {
				IProfileProvider profileProvider = loadProfileProvider(configElement);
				registerProfile(profileProvider);
			} catch (Exception e) {
				EMFProfilePlugin.getPlugin().log(
						new Status(IStatus.WARNING, EMFProfilePlugin.ID,
								"Could not load profile from extension point",
								e));
			}
		}
	}

	private IProfileProvider loadProfileProvider(
			IConfigurationElement configElement) {
		IContributor contributor = configElement.getContributor();
		String profileResourceName = configElement
				.getAttribute(PROFILE_EXTENSION_POINT_RESOURCE_NAME);
		Bundle bundle = Platform.getBundle(contributor.getName());
		Resource profileResource = resourceSet.getResource(
				createProfileURI(contributor, profileResourceName), true);
		return new BundleProfileProvider(bundle, profileResource);
	}

	private URI createProfileURI(IContributor contributor,
			String profileResourceName) {
		return URI.createURI("platform:/plugin/" + contributor.getName() //$NON-NLS-1$
				+ "/" + profileResourceName); //$NON-NLS-1$
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
	}

	@Override
	public void unregisterProfile(IProfileProvider profileProvider) {
		registeredProfileProviders.remove(profileProvider.getProfileNsURI());
		registeredProfiles.remove(profileProvider.getProfile());
	}

}
