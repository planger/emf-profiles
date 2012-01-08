/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationFactory
 * @model kind="package"
 * @generated
 */
public interface EMFProfileApplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "emfprofileapplication";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelversioning.org/emfprofile/application/1.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "emfprofileapplication";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EMFProfileApplicationPackage eINSTANCE = org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl <em>Profile Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl
	 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getProfileApplication()
	 * @generated
	 */
	int PROFILE_APPLICATION = 0;

	/**
	 * The feature id for the '<em><b>Stereotype Applications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS = 0;

	/**
	 * The feature id for the '<em><b>Imported Profiles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_APPLICATION__IMPORTED_PROFILES = 1;

	/**
	 * The number of structural features of the '<em>Profile Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_APPLICATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelversioning.emfprofileapplication.impl.ProfileImportImpl <em>Profile Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelversioning.emfprofileapplication.impl.ProfileImportImpl
	 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getProfileImport()
	 * @generated
	 */
	int PROFILE_IMPORT = 1;

	/**
	 * The feature id for the '<em><b>Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_IMPORT__NS_URI = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_IMPORT__LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Profile</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_IMPORT__PROFILE = 2;

	/**
	 * The number of structural features of the '<em>Profile Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_IMPORT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl <em>Stereotype Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl
	 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getStereotypeApplication()
	 * @generated
	 */
	int STEREOTYPE_APPLICATION = 2;

	/**
	 * The feature id for the '<em><b>Applied To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICATION__APPLIED_TO = 0;

	/**
	 * The feature id for the '<em><b>Profile Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICATION__PROFILE_APPLICATION = 1;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICATION__EXTENSION = 2;

	/**
	 * The number of structural features of the '<em>Stereotype Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICATION_FEATURE_COUNT = 3;


	/**
	 * The meta object id for the '{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicabilityImpl <em>Stereotype Applicability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelversioning.emfprofileapplication.impl.StereotypeApplicabilityImpl
	 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getStereotypeApplicability()
	 * @generated
	 */
	int STEREOTYPE_APPLICABILITY = 3;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICABILITY__STEREOTYPE = 0;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICABILITY__EXTENSION = 1;

	/**
	 * The number of structural features of the '<em>Stereotype Applicability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_APPLICABILITY_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.modelversioning.emfprofileapplication.ProfileApplication <em>Profile Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile Application</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileApplication
	 * @generated
	 */
	EClass getProfileApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelversioning.emfprofileapplication.ProfileApplication#getStereotypeApplications <em>Stereotype Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stereotype Applications</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileApplication#getStereotypeApplications()
	 * @see #getProfileApplication()
	 * @generated
	 */
	EReference getProfileApplication_StereotypeApplications();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelversioning.emfprofileapplication.ProfileApplication#getImportedProfiles <em>Imported Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imported Profiles</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileApplication#getImportedProfiles()
	 * @see #getProfileApplication()
	 * @generated
	 */
	EReference getProfileApplication_ImportedProfiles();

	/**
	 * Returns the meta object for class '{@link org.modelversioning.emfprofileapplication.ProfileImport <em>Profile Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile Import</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileImport
	 * @generated
	 */
	EClass getProfileImport();

	/**
	 * Returns the meta object for the attribute '{@link org.modelversioning.emfprofileapplication.ProfileImport#getNsURI <em>Ns URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ns URI</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileImport#getNsURI()
	 * @see #getProfileImport()
	 * @generated
	 */
	EAttribute getProfileImport_NsURI();

	/**
	 * Returns the meta object for the attribute '{@link org.modelversioning.emfprofileapplication.ProfileImport#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileImport#getLocation()
	 * @see #getProfileImport()
	 * @generated
	 */
	EAttribute getProfileImport_Location();

	/**
	 * Returns the meta object for the reference '{@link org.modelversioning.emfprofileapplication.ProfileImport#getProfile <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Profile</em>'.
	 * @see org.modelversioning.emfprofileapplication.ProfileImport#getProfile()
	 * @see #getProfileImport()
	 * @generated
	 */
	EReference getProfileImport_Profile();

	/**
	 * Returns the meta object for class '{@link org.modelversioning.emfprofileapplication.StereotypeApplication <em>Stereotype Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype Application</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplication
	 * @generated
	 */
	EClass getStereotypeApplication();

	/**
	 * Returns the meta object for the reference '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getAppliedTo <em>Applied To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Applied To</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplication#getAppliedTo()
	 * @see #getStereotypeApplication()
	 * @generated
	 */
	EReference getStereotypeApplication_AppliedTo();

	/**
	 * Returns the meta object for the container reference '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication <em>Profile Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Profile Application</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplication#getProfileApplication()
	 * @see #getStereotypeApplication()
	 * @generated
	 */
	EReference getStereotypeApplication_ProfileApplication();

	/**
	 * Returns the meta object for the reference '{@link org.modelversioning.emfprofileapplication.StereotypeApplication#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extension</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplication#getExtension()
	 * @see #getStereotypeApplication()
	 * @generated
	 */
	EReference getStereotypeApplication_Extension();

	/**
	 * Returns the meta object for class '{@link org.modelversioning.emfprofileapplication.StereotypeApplicability <em>Stereotype Applicability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype Applicability</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplicability
	 * @generated
	 */
	EClass getStereotypeApplicability();

	/**
	 * Returns the meta object for the reference '{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getStereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Stereotype</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplicability#getStereotype()
	 * @see #getStereotypeApplicability()
	 * @generated
	 */
	EReference getStereotypeApplicability_Stereotype();

	/**
	 * Returns the meta object for the reference '{@link org.modelversioning.emfprofileapplication.StereotypeApplicability#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extension</em>'.
	 * @see org.modelversioning.emfprofileapplication.StereotypeApplicability#getExtension()
	 * @see #getStereotypeApplicability()
	 * @generated
	 */
	EReference getStereotypeApplicability_Extension();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EMFProfileApplicationFactory getEMFProfileApplicationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl <em>Profile Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl
		 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getProfileApplication()
		 * @generated
		 */
		EClass PROFILE_APPLICATION = eINSTANCE.getProfileApplication();

		/**
		 * The meta object literal for the '<em><b>Stereotype Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS = eINSTANCE.getProfileApplication_StereotypeApplications();

		/**
		 * The meta object literal for the '<em><b>Imported Profiles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_APPLICATION__IMPORTED_PROFILES = eINSTANCE.getProfileApplication_ImportedProfiles();

		/**
		 * The meta object literal for the '{@link org.modelversioning.emfprofileapplication.impl.ProfileImportImpl <em>Profile Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelversioning.emfprofileapplication.impl.ProfileImportImpl
		 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getProfileImport()
		 * @generated
		 */
		EClass PROFILE_IMPORT = eINSTANCE.getProfileImport();

		/**
		 * The meta object literal for the '<em><b>Ns URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE_IMPORT__NS_URI = eINSTANCE.getProfileImport_NsURI();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE_IMPORT__LOCATION = eINSTANCE.getProfileImport_Location();

		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_IMPORT__PROFILE = eINSTANCE.getProfileImport_Profile();

		/**
		 * The meta object literal for the '{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl <em>Stereotype Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl
		 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getStereotypeApplication()
		 * @generated
		 */
		EClass STEREOTYPE_APPLICATION = eINSTANCE.getStereotypeApplication();

		/**
		 * The meta object literal for the '<em><b>Applied To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_APPLICATION__APPLIED_TO = eINSTANCE.getStereotypeApplication_AppliedTo();

		/**
		 * The meta object literal for the '<em><b>Profile Application</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_APPLICATION__PROFILE_APPLICATION = eINSTANCE.getStereotypeApplication_ProfileApplication();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_APPLICATION__EXTENSION = eINSTANCE.getStereotypeApplication_Extension();

		/**
		 * The meta object literal for the '{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicabilityImpl <em>Stereotype Applicability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelversioning.emfprofileapplication.impl.StereotypeApplicabilityImpl
		 * @see org.modelversioning.emfprofileapplication.impl.EMFProfileApplicationPackageImpl#getStereotypeApplicability()
		 * @generated
		 */
		EClass STEREOTYPE_APPLICABILITY = eINSTANCE.getStereotypeApplicability();

		/**
		 * The meta object literal for the '<em><b>Stereotype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_APPLICABILITY__STEREOTYPE = eINSTANCE.getStereotypeApplicability_Stereotype();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_APPLICABILITY__EXTENSION = eINSTANCE.getStereotypeApplicability_Extension();

	}

} //EMFProfileApplicationPackage
