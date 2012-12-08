/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;

/**
 * Profile application manager manages profiles' applications of modelled ecore resource. <br />
 * 
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ProfileApplicationManager {
	
	
	private ResourceSet resourceSet;
	private Collection<ProfileApplicationDecorator> profileApplications = new ArrayList<>();
	
	public ProfileApplicationManager(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	public Collection<ProfileApplicationDecorator> getProfileApplications(){
		return profileApplications;
	}
	
	public boolean hasProfileApplications() {
		return ! profileApplications.isEmpty();
	}
	
	/**
	 * Creates new profile application
	 * @param profileApplicationFile
	 * @param profiles
	 * @throws CoreException
	 * @throws IOException
	 */
	public void createNewProfileApplication(IFile profileApplicationFile, Collection<Profile> profiles) throws CoreException, IOException{
		ProfileApplicationDecorator element = new ProfileApplicationDecoratorImpl(profileApplicationFile, profiles, resourceSet);
		profileApplications.add(element);
	}
	
	/**
	 * Loads an existing profile application.
	 * 
	 * @param workbenchPart
	 *            to use.
	 * @param profileApplicationFile
	 *            to load.
	 * @return profile application decorator
	 */
	public ProfileApplicationDecorator loadProfileApplication(IFile profileApplicationFile) throws CoreException, IOException{
		ProfileApplicationDecorator profileApplication = new ProfileApplicationDecoratorImpl(profileApplicationFile, resourceSet);
		profileApplications.add(profileApplication);
		return profileApplication;
	}

	public void removeProfileApplication(
			ProfileApplicationDecorator profileApplication) {
		ProfileApplicationDecoratorImpl profileApplicationImpl = (ProfileApplicationDecoratorImpl) profileApplication;
		profileApplicationImpl.unload();
		profileApplications.remove(profileApplication);
		
	}

	public void dispose() {
		for (ProfileApplicationDecorator profileApplication : profileApplications) {
			((ProfileApplicationDecoratorImpl)profileApplication).unload();
		}
		profileApplications.clear();
	}
	
	
}
