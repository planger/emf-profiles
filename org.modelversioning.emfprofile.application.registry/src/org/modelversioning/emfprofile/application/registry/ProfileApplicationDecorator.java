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
import org.eclipse.emf.ecore.resource.Resource;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
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

	String getName();
//	Collection<StereotypeApplication> getStereotypeApplications();
	/**
	 * To save this profile application
	 * @throws IOException
	 * @throws CoreException
	 */
	void save() throws IOException, CoreException;
	Collection<? extends StereotypeApplicability> getApplicableStereotypes(
			EObject eObject);
	StereotypeApplication applyStereotype(
			StereotypeApplicability stereotypeApplicability, EObject eObject);
//	ProfileApplication getProfileApplication();
//	EList<StereotypeApplication> getStereotypeApplications();
//	EList<ProfileImport> getImportedProfiles();
//	EList<StereotypeApplication> getStereotypeApplications(EObject eObject);
//	EList<StereotypeApplication> getStereotypeApplications(EObject eObject,
//			Stereotype stereotype);
//	EList<EObject> getAnnotatedObjects();
	Resource getProfileApplicationResource();
	void removeStereotypeApplication(EObject object);
	String getFirstProfileName();
}
