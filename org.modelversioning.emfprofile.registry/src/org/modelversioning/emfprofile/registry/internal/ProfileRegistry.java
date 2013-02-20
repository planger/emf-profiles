package org.modelversioning.emfprofile.registry.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
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
			EPackage.Registry.INSTANCE
					.remove(profileProvider.getProfileNsURI());
			EPackage.Registry.INSTANCE.put(profileProvider.getProfileNsURI(),
					profile);
			registerExtendedPackages(profile);
			setChanged();
		}
	}

	private void registerExtendedPackages(Profile profile) {
		List<EPackage> registeredPackages = new ArrayList<EPackage>();
		for (Stereotype stereotype : profile.getStereotypes()) {
			for (Extension extension : stereotype.getAllExtensions()) {
				EPackage ePackage = extension.getTarget().getEPackage();
				if (!registeredPackages.contains(ePackage)) {
					EPackage.Registry.INSTANCE.put(ePackage.getNsURI(),
							ePackage);
					registeredPackages.add(ePackage);
				}
			}
		}
		for (EPackage subPackage : profile.getESubpackages()) {
			if (subPackage instanceof Profile) {
				registerExtendedPackages((Profile) subPackage);
			}
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
