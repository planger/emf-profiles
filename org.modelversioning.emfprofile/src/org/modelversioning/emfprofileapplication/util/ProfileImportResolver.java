/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.emfprofileapplication.util;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofileapplication.ProfileImport;

/**
 * Utility class to resolve {@link ProfileImport profile imports}.
 * 
 * TODO write to global persistent registry.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ProfileImportResolver {

	/**
	 * Tries to resolve the supplied <code>profileImport</code> using the global
	 * {@link Registry}.
	 * 
	 * <p>
	 * If the {@link Profile} could be resolved, it will be
	 * {@link ProfileImport#setProfile(Profile) set} to the {@link Profile}.
	 * </p>
	 * 
	 * @param profileImport
	 *            to resolve.
	 * @return resolved {@link Profile}.
	 */
	public static Profile resolve(ProfileImport profileImport) {
		if (profileImport.getProfile() != null) {
			return profileImport.getProfile();
		} else {
			Profile profile = tryToFindProfile(profileImport,
					EPackage.Registry.INSTANCE);
			if (profile != null) {
				profileImport.setProfile(profile);
			}
			return profileImport.getProfile();
		}
	}

	/**
	 * Tries to resolve the supplied <code>profileImport</code> using the global
	 * {@link Registry} and the local registry of the supplied
	 * <code>resourceSet</code>.
	 * 
	 * <p>
	 * If the {@link Profile} could be resolved, it will be
	 * {@link ProfileImport#setProfile(Profile) set} to the {@link Profile}.
	 * </p>
	 * 
	 * @param profileImport
	 *            to resolve.
	 * @param resourceSet
	 *            used for resolution.
	 * @return the resolved {@link Profile} or <code>null</code>.
	 */
	public static Profile resolve(ProfileImport profileImport,
			ResourceSet resourceSet) {
		if (profileImport.getProfile() != null) {
			return profileImport.getProfile();
		} else {
			Profile profile = tryToFindProfile(profileImport,
					resourceSet.getPackageRegistry());
			if (profile != null) {
				profileImport.setProfile(profile);
			} else {
				profile = resolve(profileImport);
				if (profile != null) {
					profileImport.setProfile(profile);
				}
			}
			return profileImport.getProfile();
		}
	}

	/**
	 * Persists the specified <code>profileImport</code> so that it can be
	 * resolved later. This method may change
	 * {@link ProfileImport#setLocation(String) the location} for a later
	 * resolution.
	 * 
	 * @param profileImport
	 *            to persist.
	 * @throws IllegalArgumentException
	 *             if <code>profileImport</code> has no {@link Profile}
	 *             {@link ProfileImport#setProfile(Profile) set}.
	 */
	public static void persistProfileImport(ProfileImport profileImport) {
		// TODO implement
	}

	private static Profile tryToFindProfile(ProfileImport profileImport,
			Registry registry) {
		EPackage ePackage = registry.getEPackage(profileImport.getNsURI());
		if (ePackage != null && ePackage instanceof Profile) {
			return (Profile) ePackage;
		} else {
			return null;
		}
	}

}
