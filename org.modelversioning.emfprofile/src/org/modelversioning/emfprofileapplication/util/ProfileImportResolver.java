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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofileapplication.ProfileImport;

/**
 * Utility class to resolve {@link ProfileImport profile imports} and
 * {@link Profile profiles}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ProfileImportResolver {

	/**
	 * Tries to resolve the speciifed <code>profileImport</code> using the
	 * {@link ResourceSet} the {@link ProfileImport} resides in and, if not
	 * successful, using the global {@link Registry}.
	 * 
	 * <p>
	 * If the {@link Profile} could be resolved, it will be
	 * {@link ProfileImport#setProfile(Profile) set} to the
	 * {@link ProfileImport}.
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
			if (profileImport.eResource() != null) {
				return resolve(profileImport, profileImport.eResource()
						.getResourceSet());
			} else {
				return resolve(profileImport, null);
			}
		}
	}

	/**
	 * Tries to resolve the specified <code>profileImport</code> using the
	 * specified {@code resourceSet}'s registry, if not successful, using the
	 * global {@link Registry}, and finally, it is tried to be loaded using the
	 * {@link ProfileImport#getLocation() location} of the specified
	 * <code>profileImport</code>.
	 * 
	 * <p>
	 * If the {@link Profile} could be resolved, it will be
	 * {@link ProfileImport#setProfile(Profile) set} to the
	 * {@link ProfileImport}.
	 * </p>
	 * 
	 * @param profileImport
	 *            to resolve.
	 * @param resourceSet
	 *            used for resolving.
	 * @return the resolved {@link Profile} or <code>null</code>.
	 */
	public static Profile resolve(ProfileImport profileImport,
			ResourceSet resourceSet) {
		return doResolve(profileImport, resourceSet);
	}

	private static Profile doResolve(ProfileImport profileImport,
			ResourceSet resourceSet) {
		Profile profile = profileImport.getProfile();

		if (profile != null)
			if (isProfileLoadedInResourceSet(profile, resourceSet))
				return profile;

		profile = loadProfile(profileImport, resourceSet);
		if (profile != null)
			return profile;

		profile = tryToFindProfile(profileImport, EPackage.Registry.INSTANCE);

		return profile;
	}

	private static boolean isProfileLoadedInResourceSet(Profile profile,
			ResourceSet resourceSet) {
		return resourceSet != null && profile.eResource() != null
				&& resourceSet.equals(profile.eResource().getResourceSet());
	}

	private static Profile loadProfile(ProfileImport profileImport,
			ResourceSet resourceSet) {
		Resource resource = resourceSet.getResource(
				URI.createURI(profileImport.getLocation()), true);
		return getProfile(profileImport.getNsURI(), resource);
	}

	public static Profile getProfile(String profileNsUri, Resource resource) {
		if (resource == null)
			return null;
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof Profile) {
				Profile profile = (Profile) eObject;
				if (isImportedProfile(profile, profileNsUri)) {
					return profile;
				} else {
					for (EPackage subPackage : profile.getESubpackages()) {
						if (subPackage instanceof Profile
								&& isImportedProfile((Profile) subPackage,
										profileNsUri)) {
							return (Profile) subPackage;
						}
					}
				}
			}
		}
		return null;
	}

	private static boolean isImportedProfile(Profile profile,
			String profileNsUri) {
		return profileNsUri.equals(profile.getNsURI());
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
