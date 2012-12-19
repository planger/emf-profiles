/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofileapplication.ProfileApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ProfileApplicationRegistryImpl implements
		ProfileApplicationRegistry {
	
	public static final ProfileApplicationRegistry INSTANCE = new ProfileApplicationRegistryImpl();
	
		// hides default constructor
	private ProfileApplicationRegistryImpl(){	}
	
	// every modelled resource (ID of a resource) has a profiles applications manager
	private Map<String, ProfileApplicationManager> modelledResourceProfilesApplicationsManagerMap = new HashMap<>();
	
	/**
	 * default resource set
	 */
	private ResourceSet resourceSet = new ResourceSetImpl();
	

	@Override
	public void applyProfileToModel(String modelId,
			IFile profileApplicationsFile, Collection<Profile> profiles, ResourceSet resourceSet) throws Exception {
		ProfileApplicationManager pam;
		if(! modelledResourceProfilesApplicationsManagerMap.containsKey(modelId)){
			if(resourceSet == null){
				pam = new ProfileApplicationManager(this.resourceSet);
			}else{
				pam = new ProfileApplicationManager(resourceSet);
			}
			modelledResourceProfilesApplicationsManagerMap.put(modelId, pam);
		}
			pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
			pam.createNewProfileApplication(profileApplicationsFile, profiles);
	}
	
	@Override
	public ProfileApplicationDecorator loadProfileApplicationForModel(String modelId,
			IFile profileApplicationFile, ResourceSet resourceSet) throws Exception {
		ProfileApplicationManager pam;
		if(! modelledResourceProfilesApplicationsManagerMap.containsKey(modelId)){
			if(resourceSet == null){
				pam = new ProfileApplicationManager(this.resourceSet);
			}else{
				pam = new ProfileApplicationManager(resourceSet);
			}
			modelledResourceProfilesApplicationsManagerMap.put(modelId, pam);
		}
	
		pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
		Collection<ProfileApplicationDecorator> elements = pam.getProfileApplications();
		// checks if profile application element is already loaded
		for (ProfileApplicationDecorator element : elements) {
			ProfileApplicationDecoratorImpl elementImpl = (ProfileApplicationDecoratorImpl) element;
			if(profileApplicationFile.getLocation().toString().equals(elementImpl.getProfileApplicationFile().getLocation().toString()))
				return null;
		}
		return pam.loadProfileApplication(profileApplicationFile);
	}

	@Override
	public void unloadProfileApplicationForModel(String modelId,
			ProfileApplicationDecorator profileApplication) {
		if(modelledResourceProfilesApplicationsManagerMap.containsKey(modelId)){
			ProfileApplicationManager pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
			pam.removeProfileApplication(profileApplication);
		}
		
	}	
	
	@Override
	public void unloadAllProfileApplicationsForModel(String modelId) {
		if(modelledResourceProfilesApplicationsManagerMap.containsKey(modelId)){
			ProfileApplicationManager pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
			pam.dispose();
			modelledResourceProfilesApplicationsManagerMap.remove(modelId);
		}
	}
	
	@Override
	public boolean hasProfileApplications(String modelId) {
		if(modelledResourceProfilesApplicationsManagerMap.containsKey(modelId)){
			ProfileApplicationManager pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
			return pam.hasProfileApplications();
		}
		
		return false;
	}

	@Override
	public Collection<ProfileApplicationDecorator> getProfileApplications(String modelId) {
		if(modelId == null || ! hasProfileApplications(modelId))
			return Collections.emptyList();
		ProfileApplicationManager pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
		return pam.getProfileApplications();
	}

	@Override
	public ProfileApplicationDecorator getProfileApplicationDecoratorOfContainedEObject(
			String modelId, EObject eObject) {
		ProfileApplication profileApplication = null;
		if(eObject instanceof ProfileApplication){
			profileApplication = (ProfileApplication) eObject;
		}else{
			EObject parent = eObject.eContainer();
			while(parent != null){
				if(parent instanceof ProfileApplication){
					profileApplication = (ProfileApplication) parent;
					break;
				}
				
				if(parent.eContainer() == null){
					// this means that the parent was maybe removed, and that this eObject is removed also, so retun null
					return null;
				}
				parent = parent.eContainer(); 
			}
		}
		ProfileApplicationManager pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
		for (ProfileApplicationDecorator pad : pam.getProfileApplications()) {
			if(((ProfileApplicationDecoratorImpl)pad).getProfileApplication().equals(profileApplication))
				return pad;
		}
		return null;
	}

}
