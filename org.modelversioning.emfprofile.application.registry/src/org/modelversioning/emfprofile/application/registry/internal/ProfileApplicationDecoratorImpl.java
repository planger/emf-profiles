/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.internal;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 * 
 */
public class ProfileApplicationDecoratorImpl implements
		ProfileApplicationDecorator {

	private final ResourceSet resourceSet;
	private final IProfileFacade facade;
	private final IFile profileApplicationFile;
	private final Collection<Profile> profiles;

	/**
	 * Creates new profiles application which will be saved into file. At the
	 * current state of implementation there will be only one profiles
	 * application file pro applied profiles.
	 * 
	 * @param profileApplicationFile
	 * @param profiles
	 * @param resourceSet
	 * @throws CoreException
	 * @throws IOException
	 */
	public ProfileApplicationDecoratorImpl(IFile profileApplicationFile,
			Collection<Profile> profiles, ResourceSet resourceSet)
			throws CoreException, IOException {
		this.profileApplicationFile = profileApplicationFile;
		this.resourceSet = resourceSet;
		this.facade = createAndInitializeProfileFacade(profileApplicationFile,
				profiles);
		this.profiles = facade.getLoadedProfiles();
	}

	/**
	 * Loads a profiles application from file.
	 * 
	 * @param profileApplicationFile
	 * @param resourceSet
	 * @throws CoreException
	 * @throws IOException
	 */
	public ProfileApplicationDecoratorImpl(IFile profileApplicationFile,
			ResourceSet resourceSet) throws CoreException, IOException {
		this.profileApplicationFile = profileApplicationFile;
		this.resourceSet = resourceSet;
		this.facade = loadProfileApplication(profileApplicationFile);
		if (facade.getProfileApplications().isEmpty())
			throw new IOException("The file: "
					+ profileApplicationFile.getName()
					+ ", does not contain any profile applications.");
		this.profiles = facade.getLoadedProfiles();
	}

	@Override
	public boolean isDirty() {
		return facade.getProfileApplicationResource().isModified();
	}

	public IFile getProfileApplicationFile() {
		return profileApplicationFile;
	}

	/**
	 * Creates new profiles application
	 * 
	 * @param profileApplicationFile
	 * @param profiles
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	private IProfileFacade createAndInitializeProfileFacade(
			IFile profileApplicationFile, Collection<Profile> profiles)
			throws CoreException, IOException {
		IProfileFacade facade = createNewProfileFacade(profileApplicationFile);
		facade.loadProfiles(profiles);
		profileApplicationFile.refreshLocal(IFile.DEPTH_ZERO,
				new NullProgressMonitor());
		return facade;
	}

	/**
	 * Loads an existing profiles application.
	 * 
	 * @param workbenchPart
	 *            to use.
	 * @param profileApplicationFile
	 *            to load.
	 */
	/**
	 * Loads an existing profiles application.
	 * 
	 * @param profileApplicationFile
	 *            to load.
	 * @return
	 * @throws CoreException
	 * @throws IOException
	 */
	private IProfileFacade loadProfileApplication(IFile profileApplicationFile)
			throws CoreException, IOException {
		profileApplicationFile.refreshLocal(IFile.DEPTH_ONE,
				new NullProgressMonitor());
		IProfileFacade facade = createNewProfileFacade(profileApplicationFile);
		return facade;
	}

	/**
	 * Creates new instance of {@link IProfileFacade}
	 * 
	 * @param profileApplicationFile
	 * @return
	 * @throws IOException
	 */
	private IProfileFacade createNewProfileFacade(IFile profileApplicationFile)
			throws IOException {
		IProfileFacade facade = new ProfileFacadeImpl();
		facade.loadProfileApplication(profileApplicationFile, resourceSet);
		return facade;
	}

	@Override
	public String getName() {
		String result = "";
		Collection<Profile> profiles = facade.getLoadedProfiles();
		Iterator<Profile> iter = profiles.iterator();
		while (iter.hasNext()) {
			result += iter.next().getName();
			if (iter.hasNext())
				result += ", ";
		}
		return result
				+ " - "
				+ profileApplicationFile
						.getLocation()
						.makeRelativeTo(
								ResourcesPlugin.getWorkspace().getRoot()
										.getLocation()).toString();
	}

	@Override
	public String getProfileName() {
		return profiles.iterator().next().getName();
	}

	public void unload() {
		facade.unload();
	}

	@Override
	public void save() throws IOException, CoreException {
		facade.save();
	}

	@Override
	public Collection<? extends StereotypeApplicability> getApplicableStereotypes(
			EObject eObject) {
		return facade.getApplicableStereotypes(eObject);
	}

	@Override
	public StereotypeApplication applyStereotype(
			StereotypeApplicability stereotypeApplicability, EObject eObject) {
		return facade.apply(stereotypeApplicability, eObject);
	}

	@Override
	public void addNestedEObject(EObject container, EReference eReference,
			EObject eObject) {
		facade.addNestedEObject(container, eReference, eObject);
	}

	@Override
	public void removeEObject(EObject eObject) {
		facade.removeEObject(eObject);
	}

	@Override
	public List<StereotypeApplication> getStereotypeApplications() {
		return facade.getStereotypeApplications();
	}

	@Override
	public List<StereotypeApplication> getStereotypeApplications(EObject eObject) {
		return facade.getAppliedStereotypes(eObject);
	}

	@Override
	public List<ProfileApplication> getProfileApplications() {
		return facade.getProfileApplications();
	}

}
