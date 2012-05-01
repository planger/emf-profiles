package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.EMFProfilePlugin;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.osgi.framework.Bundle;

public class ExtensionPointReader {

	private final ResourceSet resourceSet;
	private Collection<BundleProfileProvider> bundleProfileProviders;

	public ExtensionPointReader(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		bundleProfileProviders = new ArrayList<BundleProfileProvider>();
		loadBundleProfileProvidersFromExtensionPoint();
	}

	private void loadBundleProfileProvidersFromExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						IProfileRegistry.PROFILE_EXTENSION_POINT_ID);
		loadProfiles(config);
	}

	private void loadProfiles(IConfigurationElement[] config) {
		for (IConfigurationElement configElement : config) {
			try {
				BundleProfileProvider bundleProfileProvider = loadProfile(configElement);
				bundleProfileProviders.add(bundleProfileProvider);
			} catch (Exception e) {
				logWarning(e, "Could not load profile from extension point");
			}
		}
	}

	private BundleProfileProvider loadProfile(
			IConfigurationElement configElement) {
		IContributor contributor = configElement.getContributor();
		String profileResourceName = configElement
				.getAttribute(IProfileRegistry.PROFILE_EXTENSION_POINT_RESOURCE_NAME);
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

	public Collection<BundleProfileProvider> getProfileProvider() {
		return Collections.unmodifiableCollection(bundleProfileProviders);
	}

	private void logWarning(Exception exception, String msg) {
		EMFProfilePlugin.getPlugin()
				.log(new Status(IStatus.WARNING, EMFProfilePlugin.ID, msg,
						exception));
	}
}
