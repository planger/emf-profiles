/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ProfileApplicationDecoratorImpl extends ProfileApplicationImpl implements
		ProfileApplicationDecorator {
	
	private ResourceSet resourceSet;
	private IProfileFacade facade;
	private ProfileApplication profileApplication;
	private IFile profileApplicationFile;
	private boolean dirty = false;
	
	/**
	 * Creates new profile application which will be saved into file.
	 * @param profileApplicationFile
	 * @param profiles
	 * @param resourceSet
	 * @throws CoreException
	 * @throws IOException
	 */
	public ProfileApplicationDecoratorImpl(IFile profileApplicationFile,
			Collection<Profile> profiles, ResourceSet resourceSet) throws CoreException, IOException {
		this.profileApplicationFile = profileApplicationFile;
		this.resourceSet = resourceSet; 
		this.facade = createAndInitializeProfileFacade(profileApplicationFile, profiles);
		this.dirty = true;
		if(!facade.getProfileApplications().isEmpty())
			this.profileApplication = facade.getProfileApplications().get(0);
	}


	/**
	 * Loads a profile application from file.
	 * @param profileApplicationFile
	 * @param resourceSet
	 * @throws CoreException
	 * @throws IOException
	 */
	public ProfileApplicationDecoratorImpl(IFile profileApplicationFile,
			ResourceSet resourceSet) throws CoreException, IOException{
		this.profileApplicationFile = profileApplicationFile;
		this.resourceSet = resourceSet;
		this.facade = loadProfileApplication(profileApplicationFile);
		this.dirty = false;
		if(!facade.getProfileApplications().isEmpty())
			this.profileApplication = facade.getProfileApplications().get(0);
	}

	private ProfileApplication getProfileaApplication(){
		if(this.profileApplication == null){
			if(!facade.getProfileApplications().isEmpty()){
				this.profileApplication = facade.getProfileApplications().get(0);
				return this.profileApplication;
			}
			return this;
		}
		return this.profileApplication;
	}
	
	@Override
	public boolean isDirty() {
		return this.dirty;
	}
	
	public IFile getProfileApplicationFile() {
		return profileApplicationFile;
	}
	
	/**
	 * Creates new profile application
	 * @param profileApplicationFile
	 * @param profiles
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	private IProfileFacade createAndInitializeProfileFacade(IFile profileApplicationFile, Collection<Profile> profiles) throws CoreException, IOException{

		IProfileFacade facade = createNewProfileFacade(profileApplicationFile);
		for (Profile profile : profiles) {
			facade.loadProfile(profile);
		}
		profileApplicationFile.getParent().refreshLocal(1, new NullProgressMonitor());
		return facade;
	}
	
	/**
	 * Loads an existing profile application.
	 * 
	 * @param workbenchPart
	 *            to use.
	 * @param profileApplicationFile
	 *            to load.
	 */
	/**
	 * Loads an existing profile application.
	 * 
	 * @param profileApplicationFile to load.
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	private IProfileFacade loadProfileApplication(IFile profileApplicationFile) throws CoreException, IOException{
		IProfileFacade facade = createNewProfileFacade(profileApplicationFile);
		profileApplicationFile.getParent().refreshLocal(1, new NullProgressMonitor());
		return facade;
	}
	
	
	
	/**
	 * Creates new instance of {@link IProfileFacade}
	 * @param profileApplicationFile
	 * @return
	 * @throws IOException
	 */
	private IProfileFacade createNewProfileFacade(IFile profileApplicationFile) throws IOException {
		Resource profileApplicationResource = createProfileApplicationResource(
				profileApplicationFile, resourceSet);
		IProfileFacade facade = new ProfileFacadeImpl();
		facade.setProfileApplicationResource(profileApplicationResource);
		return facade;
	}
	
	/**
	 * Creates the profile application resource.
	 * 
	 * @param profileApplicationFile
	 *            specifying the location.
	 * @param resourceSet
	 *            {@link ResourceSet} to use.
	 * @return the created resource.
	 * @throws IOException
	 *             if location not writable.
	 */
	private Resource createProfileApplicationResource(
			IFile profileApplicationFile, ResourceSet resourceSet)
			throws IOException {
		Resource profileApplicationResource = resourceSet
				.createResource(URI.createFileURI(profileApplicationFile
						.getLocation().toString()));
		if (!profileApplicationFile.exists()) {
			profileApplicationResource.save(Collections.emptyMap());
		}
		profileApplicationResource.load(Collections.emptyMap());
		return profileApplicationResource;
	}


	@Override
	public String getName() {
		String result = dirty ? "*": "" + "Profile Application - ";
		Collection<Profile> profiles = facade.getLoadedProfiles();
		Iterator<Profile> iter = profiles.iterator();
		while(iter.hasNext()){
			result += iter.next().getName();
			if(iter.hasNext())
				result += ", ";
		}
		return result + " - " + ResourcesPlugin.getWorkspace().getRoot().getFile(profileApplicationFile.getLocation()).getProjectRelativePath().toString();
	}

	public void unload() {
		facade.unloadProfile(null);
		facade.unload();
	}


	@Override
	public void save() throws IOException, CoreException {
		facade.save();
		dirty = false;
		profileApplicationFile.getParent().refreshLocal(1, new NullProgressMonitor());
	}


	@Override
	public Collection<? extends StereotypeApplicability> getApplicableStereotypes(
			EObject eObject) {
		
		return facade.getApplicableStereotypes(eObject.eClass());
	}


	@Override
	public StereotypeApplication apply(
			StereotypeApplicability stereotypeApplicability, EObject eObject) {
		try {
			StereotypeApplication result = facade.apply(stereotypeApplicability, eObject);
			dirty = true;
			return result;
		} catch (Exception e) {
			System.out.println("Stereotype not applicable: " + stereotypeApplicability.getStereotype().getName() + ", facade : " + facade);
		}
		return null;
	}

	@Override
	public EList<StereotypeApplication> getStereotypeApplications() {
		if(profileApplication == null)
			return super.getStereotypeApplications();
		return getProfileaApplication().getStereotypeApplications();
	}

	@Override
	public EList<ProfileImport> getImportedProfiles() {
		if(profileApplication == null)
			return super.getImportedProfiles();
		return getProfileaApplication().getImportedProfiles();
	}

	@Override
	public EList<StereotypeApplication> getStereotypeApplications(
			EObject eObject) {
		if(profileApplication == null)
			return super.getStereotypeApplications(eObject);
		return getProfileaApplication().getStereotypeApplications(eObject);
	}

	@Override
	public EList<StereotypeApplication> getStereotypeApplications(
			EObject eObject, Stereotype stereotype) {
		if(profileApplication == null)
			return super.getStereotypeApplications(eObject, stereotype);
		return getProfileaApplication().getStereotypeApplications(eObject, stereotype);
	}

	@Override
	public EList<EObject> getAnnotatedObjects() {
		if(profileApplication == null)
			return super.getAnnotatedObjects();
		return getProfileaApplication().getAnnotatedObjects();
	}


	@Override
	public Resource getProfileApplicationResource() {
		return facade.getProfileApplicationResource();
	}


	@Override
	public EClass eClass() {
		if(profileApplication == null)
			return super.eClass();
		return getProfileaApplication().eClass();
	}


	@Override
	public Resource eResource() {
		if(profileApplication == null)
			return super.eResource();
		return getProfileaApplication().eResource();
	}


	@Override
	public EObject eContainer() {
		if(profileApplication == null)
			return super.eContainer();
		return getProfileaApplication().eContainer();
	}


	@Override
	public EStructuralFeature eContainingFeature() {
		if(profileApplication == null)
			return super.eContainingFeature();
		return getProfileaApplication().eContainingFeature();
	}


	@Override
	public EReference eContainmentFeature() {
		if(profileApplication == null)
			return super.eContainmentFeature();
		return getProfileaApplication().eContainmentFeature();
	}


	@Override
	public EList<EObject> eContents() {
		if(profileApplication == null)
			return super.eContents();
		return getProfileaApplication().eContents();
	}


	@Override
	public TreeIterator<EObject> eAllContents() {
		if(profileApplication == null)
			return super.eAllContents();
		return getProfileaApplication().eAllContents();
	}


	@Override
	public boolean eIsProxy() {
		if(profileApplication == null)
			return super.eIsProxy();
		return getProfileaApplication().eIsProxy();
	}


	@Override
	public EList<EObject> eCrossReferences() {
		if(profileApplication == null)
			return super.eCrossReferences();
		return getProfileaApplication().eCrossReferences();
	}


	@Override
	public Object eGet(EStructuralFeature feature) {
		if(profileApplication == null)
			return super.eGet(feature);
		return getProfileaApplication().eGet(feature);
	}


	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		if(profileApplication == null)
			return super.eGet(feature, resolve);
		return getProfileaApplication().eGet(feature, resolve);
	}


	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		if(profileApplication == null)
			super.eSet(feature, newValue);
		getProfileaApplication().eSet(feature, newValue);
	}


	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		if(profileApplication == null)
			return super.eIsSet(feature);
		return getProfileaApplication().eIsSet(feature);
	}


	@Override
	public void eUnset(EStructuralFeature feature) {
		if(profileApplication == null)
			super.eUnset(feature);
		getProfileaApplication().eUnset(feature);
	}


	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments)
			throws InvocationTargetException {
		if(profileApplication == null)
			return super.eInvoke(operation, arguments);
		return getProfileaApplication().eInvoke(operation, arguments);
	}


	@Override
	public EList<Adapter> eAdapters() {
		if(profileApplication == null)
			return super.eAdapters();
		return getProfileaApplication().eAdapters();
	}


	@Override
	public boolean eDeliver() {
		if(profileApplication == null)
			return super.eDeliver();
		return getProfileaApplication().eDeliver();
	}


	@Override
	public void eSetDeliver(boolean deliver) {
		if(profileApplication == null)
			super.eSetDeliver(deliver);
		getProfileaApplication().eSetDeliver(deliver);
	}


	@Override
	public void eNotify(Notification notification) {
		if(profileApplication == null)
			super.eNotify(notification);
		getProfileaApplication().eNotify(notification);
	}

}
