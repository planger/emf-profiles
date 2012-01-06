/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profile Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.ProfileApplication#getStereotypeApplications <em>Stereotype Applications</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.ProfileApplication#getImportedProfiles <em>Imported Profiles</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileApplication()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='violatedUpperBound violatedLowerBound'"
 * @generated
 */
public interface ProfileApplication extends EObject {
	/**
	 * Returns the value of the '<em><b>Stereotype Applications</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofileapplication.StereotypeApplication}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication <em>Profile Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype Applications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype Applications</em>' containment reference list.
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileApplication_StereotypeApplications()
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication
	 * @model opposite="profileApplication" containment="true"
	 * @generated
	 */
	EList<StereotypeApplication> getStereotypeApplications();

	/**
	 * Returns the value of the '<em><b>Imported Profiles</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofileapplication.ProfileImport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Profiles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Profiles</em>' containment reference list.
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileApplication_ImportedProfiles()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProfileImport> getImportedProfiles();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false" eObjectRequired="true"
	 * @generated
	 */
	EList<StereotypeApplication> getStereotypeApplications(EObject eObject);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false" eObjectRequired="true" stereotypeRequired="true"
	 * @generated
	 */
	EList<StereotypeApplication> getStereotypeApplications(EObject eObject, Stereotype stereotype);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<EObject> getAnnotatedObjects();

} // ProfileApplication
