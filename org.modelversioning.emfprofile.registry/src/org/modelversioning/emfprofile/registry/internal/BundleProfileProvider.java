package org.modelversioning.emfprofile.registry.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.osgi.framework.Bundle;

public class BundleProfileProvider implements IProfileProvider {

	private Bundle bundle;
	private Resource profileResource;
	private Profile profile;
	private String profileName;
	private String profileNsURI;

	public BundleProfileProvider(Bundle bundle, Resource profileResource) {
		super();
		this.bundle = bundle;
		this.profileResource = profileResource;
		initialize();
	}

	private void initialize() {
		if (profileResource == null) {
			throw new IllegalArgumentException("Resource is null");
		}
		if (profileResource.getContents().size() < 0) {
			throw new IllegalArgumentException("Resource is emtpy");
		}
		EObject eObject = profileResource.getContents().get(0);
		if (eObject instanceof Profile) {
			initialize((Profile) eObject);
		} else {
			throw new IllegalArgumentException("Resource contains no profile");
		}
	}

	private void initialize(Profile profile) {
		this.profile = profile;
		this.profileName = profile.getName();
		this.profileNsURI = profile.getNsURI();
	}

	public Bundle getBundle() {
		return bundle;
	}

	@Override
	public Profile getProfile() {
		return profile;
	}

	@Override
	public String getProfileNsURI() {
		return profileNsURI;
	}

	@Override
	public String getProfileName() {
		return profileName;
	}

	@Override
	public ProfileLocationType getProfileLocationType() {
		return ProfileLocationType.BUNDLE;
	}

}
