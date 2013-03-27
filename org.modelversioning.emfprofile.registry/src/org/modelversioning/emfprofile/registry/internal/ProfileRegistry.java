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
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.modelversioning.emfprofile.registry.IProfileRegistry;

public class ProfileRegistry extends Observable implements IProfileRegistry {

	private final ResourceSet resourceSet = new ResourceSetImpl();
	private final Map<String, IProfileProvider> registeredProfileProviders = new HashMap<String, IProfileProvider>();
	private final Collection<Profile> registeredProfiles = new ArrayList<Profile>();

	private ProfileProjectSynchronizer projectSynchronizer;

	public ProfileRegistry() {
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
		Profile profile = profileProvider.getProfile();
		if (isValidProfile(profile)) {
			registeredProfileProviders.put(profileProvider.getProfileNsURI(),
					profileProvider);
			registeredProfiles.add(profile);
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
	public IProfileProvider getProfileProvider(Profile profile) {
		IProfileProvider profileProvider = registeredProfileProviders
				.get(profile.getNsURI());
		return profileProvider;
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
