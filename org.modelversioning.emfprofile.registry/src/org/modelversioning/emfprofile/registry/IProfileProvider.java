package org.modelversioning.emfprofile.registry;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;

public interface IProfileProvider {

	public enum ProfileLocationType {
		WORKSPACE, BUNDLE;
	}

	/**
	 * Returns the provided profile.
	 * 
	 * @return the provided profile.
	 */
	Profile getProfile();

	/**
	 * Returns the namespace URI of the profile.
	 * 
	 * @return the namespace URI.
	 */
	String getProfileNsURI();

	/**
	 * Returns the name of the profile.
	 * 
	 * @return the name.
	 */
	String getProfileName();

	/**
	 * Returns the location type of the provided profile.
	 * 
	 * @return the location type.
	 */
	ProfileLocationType getProfileLocationType();

	/**
	 * Loads the provided profile using the specified {@code resourceSet}.
	 * 
	 * @param resourceSet
	 *            the {@link ResourceSet} to load the profile
	 * @return the loaded {@link Profile}
	 */
	Profile loadProfile(ResourceSet resourceSet);

}
