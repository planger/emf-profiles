/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationFactory;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EMFProfileApplicationFactoryImpl extends EFactoryImpl implements EMFProfileApplicationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EMFProfileApplicationFactory init() {
		try {
			EMFProfileApplicationFactory theEMFProfileApplicationFactory = (EMFProfileApplicationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.modelversioning.org/emfprofile/application/1.1"); 
			if (theEMFProfileApplicationFactory != null) {
				return theEMFProfileApplicationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EMFProfileApplicationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFProfileApplicationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION: return createProfileApplication();
			case EMFProfileApplicationPackage.PROFILE_IMPORT: return createProfileImport();
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION: return createStereotypeApplication();
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICABILITY: return createStereotypeApplicability();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProfileApplication createProfileApplication() {
		ProfileApplicationImpl profileApplication = new ProfileApplicationImpl();
		return profileApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProfileImport createProfileImport() {
		ProfileImportImpl profileImport = new ProfileImportImpl();
		return profileImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StereotypeApplication createStereotypeApplication() {
		StereotypeApplicationImpl stereotypeApplication = new StereotypeApplicationImpl();
		return stereotypeApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StereotypeApplicability createStereotypeApplicability() {
		StereotypeApplicabilityImpl stereotypeApplicability = new StereotypeApplicabilityImpl();
		return stereotypeApplicability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFProfileApplicationPackage getEMFProfileApplicationPackage() {
		return (EMFProfileApplicationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EMFProfileApplicationPackage getPackage() {
		return EMFProfileApplicationPackage.eINSTANCE;
	}

} //EMFProfileApplicationFactoryImpl
