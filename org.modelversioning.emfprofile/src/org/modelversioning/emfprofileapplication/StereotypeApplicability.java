/**
 * Copyright (c) 2010 - 2012 modelversioning.org
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
 * A representation of the model object '<em><b>Stereotype Applicability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplicability()
 * @model
 * @generated
 */
public interface StereotypeApplicability extends EObject {
	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype</em>' reference.
	 * @see #setStereotype(Stereotype)
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplicability_Stereotype()
	 * @model required="true"
	 * @generated
	 */
	Stereotype getStereotype();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getStereotype <em>Stereotype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' reference.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(Stereotype value);

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
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#getStereotypeApplicability_Extension()
	 * @model required="true"
	 * @generated
	 */
	Extension getExtension();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getExtension <em>Extension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension</em>' reference.
	 * @see #getExtension()
	 * @generated
	 */
	void setExtension(Extension value);

} // StereotypeApplicability
