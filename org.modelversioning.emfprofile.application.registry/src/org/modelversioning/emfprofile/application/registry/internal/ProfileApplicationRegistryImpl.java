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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;

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
		// TODO remove comment
		System.out.println("Adding profile application to modelId: " + modelId 
				+ "\ninto pa file: " + profileApplicationsFile.getLocationURI().getRawPath()
				+ "and profiles amount: " + profiles.size());
			pam = modelledResourceProfilesApplicationsManagerMap.get(modelId);
			pam.createNewProfileApplication(profileApplicationsFile, profiles);
	}
	
	@Override
	public boolean loadProfileApplicationForModel(String modelId,
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
				return false;
		}
		pam.loadProfileApplication(profileApplicationFile);
		return true;
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

//	@Override
//	// notifications that the currently used file in eclipse editor has changed 
//	public void update(Observable o, Object notification) {
//		
//		if(notification == null){ // this means no file of interest is activated
//			notifyObservers(getProfileApplications(null));
//			// TODO remove
//			System.out.println("Clearing the View!");
//			return;
//		}
//		
//		if(notification instanceof ResourceOpenedNotification){
//			ResourceOpenedNotification ron = (ResourceOpenedNotification) notification;
//			ProfileApplicationManager pam;
//			if(ron.getResourceSet() == null){
//				pam = new ProfileApplicationManager(this.resourceSet);
//			}else{
//				pam = new ProfileApplicationManager(ron.getResourceSet());
//			}
//			modelledResourceProfilesApplicationsManagerMap.put(ron.getId(), pam);
//			notifyObservers(pam.getProfileApplicationElements());
//			
//			// TODO remove comment
//			System.out.println("Resource opened: " + ((ResourceOpenedNotification)notification).getId());
//		}else if(notification instanceof ResourceActivatedNotification){
//			ResourceActivatedNotification ran = (ResourceActivatedNotification) notification;
//			notifyObservers(modelledResourceProfilesApplicationsManagerMap.get(ran.getId()).getProfileApplicationElements());
//			// TODO remove comment
//			System.out.println("Resource activated: " + ((ResourceActivatedNotification)notification).getId());
//		} else if(notification instanceof ResourceClosedNotification){
//			// TODO maybe it is needed to clear resources before removing the reference from map?
//			ResourceClosedNotification rcn = (ResourceClosedNotification) notification;
//			modelledResourceProfilesApplicationsManagerMap.remove(rcn.getId());
//			// notify observers only if lastActiveModelId is same as the id of the notification
//			// TODO remove comment
//			System.out.println("Resource closed: " + ((ResourceClosedNotification)notification).getId());
//		}else{
//			throw new RuntimeException("Notification is of unknown type: " + notification);
//		}
//	}
//
//	/**
//	 * It sets that there is a change on this object and notifies observers.
//	 * @see java.util.Observable#notifyObservers(java.lang.Object)
//	 */
//	@Override
//	public void notifyObservers(Object arg) {
//		super.setChanged();
//		super.notifyObservers(arg);
//	}

}
