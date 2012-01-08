/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stereotype Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getAppliedTo <em>Applied To</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication <em>Profile Application</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplication()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='appliedInapplicableExtension'"
 * @generated
 */
public interface StereotypeApplication extends EObject {
	/**
	 * Returns the value of the '<em><b>Applied To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applied To</em>' reference.
	 * @see #setAppliedTo(EObject)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplication_AppliedTo()
	 * @model
	 * @generated
	 */
	EObject getAppliedTo();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getAppliedTo <em>Applied To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied To</em>' reference.
	 * @see #getAppliedTo()
	 * @generated
	 */
	void setAppliedTo(EObject value);

	/**
	 * Returns the value of the '<em><b>Profile Application</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofileapplication.ProfileApplication#getStereotypeApplications <em>Stereotype Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile Application</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Application</em>' container reference.
	 * @see #setProfileApplication(ProfileApplication)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplication_ProfileApplication()
	 * @see org.modelversioning.emfprofileapplication.ProfileApplication#getStereotypeApplications
	 * @model opposite="stereotypeApplications" transient="false"
	 * @generated
	 */
	ProfileApplication getProfileApplication();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication <em>Profile Application</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile Application</em>' container reference.
	 * @see #getProfileApplication()
	 * @generated
	 */
	void setProfileApplication(ProfileApplication value);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' reference.
	 * @see #setExtension(Extension)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplication_Extension()
	 * @model required="true"
	 * @generated
	 */
	Extension getExtension();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getExtension <em>Extension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension</em>' reference.
	 * @see #getExtension()
	 * @generated
	 */
	void setExtension(Extension value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	Stereotype getStereotype();

} // StereotypeApplication
