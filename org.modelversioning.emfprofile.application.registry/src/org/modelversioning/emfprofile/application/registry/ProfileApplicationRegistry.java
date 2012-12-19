/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.application.registry.internal.ProfileApplicationRegistryImpl;



/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public interface ProfileApplicationRegistry {
	
	
	/**
	 * Singleton instance of profile application registry
	 */ 
	public static final ProfileApplicationRegistry INSTANCE = ProfileApplicationRegistryImpl.INSTANCE;

	/**
	 * To apply profiles on a model
	 * @param modelId Generated Id of the opened model's resource in user interface editor.
	 * @param profileApplicationsFile file where profile applications are saved.
	 * @param profiles collection of profiles that are going to be applied on a model resource.
	 * @param resourceSet is optional. If it's not provided, the registry's default resource set will be used instead.
	 * @throws Exception
	 */
	void applyProfileToModel(String modelId, IFile profileApplicationsFile, Collection<Profile> profiles, ResourceSet resourceSet) throws Exception;
	
	/**
	 * Load profile application for model from a file.
	 * Returns <code>false</code> if this profile application was already loaded.
	 * @param modelId
	 * @param profileApplicationFile
	 * @param resourceSet is optional. If it's not provided, the registry's default resource set will be used instead.
	 * @return <code>null</code> if already loaded, otherwise the loaded profile application.
	 * @throws Exception
	 */
	ProfileApplicationDecorator loadProfileApplicationForModel(String modelId,	IFile profileApplicationFile, ResourceSet resourceSet) throws Exception;
	
	/**
	 * Unloads profile application for a model.
	 * @param modelId
	 * @param profileApplication
	 */
	void unloadProfileApplicationForModel(String modelId, ProfileApplicationDecorator profileApplication);
	
	/**
	 * Unloads all profile applications for a model.
	 * Removes tracking of model id from registry.
	 * @param modelId
	 */
	void unloadAllProfileApplicationsForModel(String modelId);
	
	/**
	 * Checks if there are any profile applications defined on the given modelled resource.
	 * @param modelID The string identification of the modelled resource.
	 * @return
	 */
	boolean hasProfileApplications(String modelId);
	
	/**
	 * Returns profile application elements.
	 * @param modelId
	 * @return
	 */
	Collection<ProfileApplicationDecorator> getProfileApplications(String modelId);
	
	/**
	 * Gets the instance of the @link {@link ProfileApplicationDecorator} that 
	 * is a parent of the provided {@link EObject} and it searches amongst all 
	 * profile application decorators for the given model id.
	 * 
	 * @param modelId The string identification of the modelled resource.
	 * @param eObject in question
	 * @return {@link ProfileApplicationDecorator} if everything OK, <code>null</code> if 
	 * could not find it or any of the parents was also <code>null</code> which would indicate
	 * that the eObject was removed together with parent.
	 */
	ProfileApplicationDecorator getProfileApplicationDecoratorOfContainedEObject(String modelId, EObject eObject);
	
}
