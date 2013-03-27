package org.modelversioning.emfprofile.registry.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileProvider;

public class ProjectProfileProvider implements IProfileProvider {

	private IProject project;
	private Resource profileResource;
	private Profile profile;
	private String profileName;
	private String profileNsURI;

	public ProjectProfileProvider(IProject project, Resource profileResource) {
		super();
		this.project = project;
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
		Profile profile = obtainProfile();
		if (profile != null) {
			initialize(profile);
		} else {
			throw new IllegalArgumentException("Resource contains no profile");
		}
	}

	private Profile obtainProfile() {
		return obtainProfileFromResource(profileResource);
	}

	private Profile obtainProfileFromResource(Resource resource) {
		for (EObject eObject : profileResource.getContents()) {
			if (eObject instanceof Profile) {
				return (Profile) eObject;
			}
		}
		return null;
	}

	private void initialize(Profile profile) {
		this.profile = profile;
		this.profileName = profile.getName();
		this.profileNsURI = profile.getNsURI();
	}

	public IProject getProject() {
		return project;
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
		return ProfileLocationType.WORKSPACE;
	}

	@Override
	public Profile loadProfile(ResourceSet resourceSet) {
		Resource resource = resourceSet.getResource(profile.eResource()
				.getURI(), true);
		return obtainProfileFromResource(resource);
	}

}
