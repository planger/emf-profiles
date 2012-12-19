/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * This object decorates the {@link ProfileApplication} with additional 
 * functionalities, e.g. the semantic name of the profile application 
 * (constructed of profile name and location of profile application resource), 
 * the status if profile application has changed and needs to be saved, 
 * or convenience methods to apply/remove stereotypes
 * or nested objects.  
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public interface ProfileApplicationDecorator extends ProfileApplication {

	/**
	 * Is this profile application changed
	 * and if it needs to be saved
	 * @return
	 */
	boolean isDirty();
	/**
	 * To set if profile application has changed or not
	 * @param dirty
	 */
	void setDirty(boolean dirty);

	/**
	 * Gets the Name of this profile application. <br />
	 * The name is constructed out of loaded profile name and the location of 
	 * this profile application resource in the workspace.
	 * @return
	 */
	String getName();
	
	/**
	 * To save this profile application
	 * @throws IOException
	 * @throws CoreException
	 */
	void save() throws IOException, CoreException;
	
	/**
	 * Returns the list of applicable stereotype for the specified type in
	 * <code>eClass</code>. <br />
	 * <b>Note:</b> The method is actually implemented in {@link IProfileFacade}, so
	 * this method forwards the call to the facade.
	 * 
	 * @param eClass
	 *            to get applicable stereotype for.
	 * @return the list of applicable {@link Stereotype}s.
	 */ 
	Collection<? extends StereotypeApplicability> getApplicableStereotypes(
			EObject eObject);
	
	/**
	 * Applies the specified <code>applicableStereotype</code>. <br />
	 * <b>Note:</b> The method is actually implemented in {@link IProfileFacade}, so
	 * this method forwards the call to the facade.
	 * <p>
	 * This method is a convenience method for
	 * {@link #apply(Stereotype, EObject, Extension)}.
	 * </p>
	 * 
	 * It also sets the state of this profile application to dirty.
	 * @param stereotypeApplicability
	 *            the applicable stereotype to be applied.
	 * @param eObject
	 *            to apply the <code>applicableStereotype</code> to.
	 * @return the created instance of the {@link Stereotype}.
	 */
	StereotypeApplication applyStereotype(
			StereotypeApplicability stereotypeApplicability, EObject eObject);
	
	/**
	 * Adds a nested eObject to the container and sets this
	 * profile application to dirty.
	 * @param container
	 * @param eReference
	 * @param eObject
	 */
	void addNestedEObject(EObject container, EReference eReference, EObject eObject);
	
	/**
	 * Removes the nested object from the profile application resource
	 * and sets it to dirty state.
	 * @param object
	 */
	void removeEObject(EObject object);
	
	/**
	 * Gets the name of the loaded profile.
	 * @return
	 */
	String getProfileName();
}
