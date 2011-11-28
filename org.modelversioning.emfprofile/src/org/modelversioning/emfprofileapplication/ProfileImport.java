/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Profile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profile Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.ProfileImport#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.ProfileImport#getLocation <em>Location</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.ProfileImport#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileImport()
 * @model
 * @generated
 */
public interface ProfileImport extends EObject {
	/**
	 * Returns the value of the '<em><b>Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ns URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ns URI</em>' attribute.
	 * @see #setNsURI(String)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileImport_NsURI()
	 * @model required="true"
	 * @generated
	 */
	String getNsURI();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.ProfileImport#getNsURI <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ns URI</em>' attribute.
	 * @see #getNsURI()
	 * @generated
	 */
	void setNsURI(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileImport_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.ProfileImport#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Profile</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile</em>' reference.
	 * @see #setProfile(Profile)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getProfileImport_Profile()
	 * @model transient="true"
	 * @generated
	 */
	Profile getProfile();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.ProfileImport#getProfile <em>Profile</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile</em>' reference.
	 * @see #getProfile()
	 * @generated
	 */
	void setProfile(Profile value);

} // ProfileImport
