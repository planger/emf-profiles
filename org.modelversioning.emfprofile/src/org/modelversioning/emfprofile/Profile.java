/**
 * Copyright (c) ${year} modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.modelversioning.emfprofile.EMFProfilePackage#getProfile()
 * @model
 * @generated
 */
public interface Profile extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * Returns a list of all applicable stereotypes for the specified
	 * <code>eClass</code>.
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<Stereotype> getApplicableStereotypes(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Stereotype> getStereotypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model stereotypeNameRequired="true"
	 * @generated
	 */
	Stereotype getStereotype(String stereotypeName);

} // Profile
