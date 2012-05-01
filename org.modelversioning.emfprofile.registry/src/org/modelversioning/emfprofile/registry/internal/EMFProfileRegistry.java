package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IEMFProfileRegistry;
import org.modelversioning.emfprofile.registry.IProfileProvider;

public class EMFProfileRegistry extends Observable implements
		IEMFProfileRegistry {

	private final ResourceSet resourceSet = new ResourceSetImpl();
	private final Map<String, IProfileProvider> registeredProfileProviders = new HashMap<String, IProfileProvider>();
	private final Collection<Profile> registeredProfiles = new ArrayList<Profile>();

	private ProfileProjectSynchronizer projectSynchronizer;

	public EMFProfileRegistry() {
		loadProfileProvidersFromExtensionPoint();
		setUpProfileProjectSynchronizer();
		notifyObservers();
	}

	private void loadProfileProvidersFromExtensionPoint() {
		ExtensionPointReader extensionPointReader = new ExtensionPointReader(
				resourceSet);
		for (BundleProfileProvider provider : extensionPointReader
				.getProfileProvider()) {
			doRegisterProfile(provider);
		}
	}

	private void setUpProfileProjectSynchronizer() {
		projectSynchronizer = new ProfileProjectSynchronizer(this, resourceSet);
		projectSynchronizer.registerProfileProjects();
		projectSynchronizer.startSynchronizing();
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
		doRegisterProfile(profileProvider);
		notifyObservers();
	}

	protected void doRegisterProfile(IProfileProvider profileProvider) {
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

	protected void doUnregisterProfile(IProfileProvider profileProvider) {
		registeredProfileProviders.remove(profileProvider.getProfileNsURI());
		registeredProfiles.remove(profileProvider.getProfile());
		EPackage.Registry.INSTANCE.remove(profileProvider.getProfileNsURI());
		profileProvider.getProfile().eResource().unload();
		setChanged();
	}

	protected Collection<ProjectProfileProvider> getRegisteredProjectProfileProviders() {
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

}
