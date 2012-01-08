/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage
 * @generated
 */
public interface EMFProfileApplicationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EMFProfileApplicationFactory eINSTANCE = org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Profile Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Profile Application</em>'.
	 * @generated
	 */
	ProfileApplication createProfileApplication();

	/**
	 * Returns a new object of class '<em>Profile Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Profile Import</em>'.
	 * @generated
	 */
	ProfileImport createProfileImport();

	/**
	 * Returns a new object of class '<em>Stereotype Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stereotype Application</em>'.
	 * @generated
	 */
	StereotypeApplication createStereotypeApplication();

	/**
	 * Returns a new object of class '<em>Stereotype Applicability</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stereotype Applicability</em>'.
	 * @generated
	 */
	StereotypeApplicability createStereotypeApplicability();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EMFProfileApplicationPackage getEMFProfileApplicationPackage();

} //EMFProfileApplicationFactory
