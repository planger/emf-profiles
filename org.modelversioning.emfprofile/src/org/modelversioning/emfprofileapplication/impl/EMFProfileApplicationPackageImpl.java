/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationFactory;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.util.EMFProfileApplicationValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EMFProfileApplicationPackageImpl extends EPackageImpl implements EMFProfileApplicationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileApplicationEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileImportEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeApplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeApplicabilityEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EMFProfileApplicationPackageImpl() {
		super(eNS_URI, EMFProfileApplicationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EMFProfileApplicationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EMFProfileApplicationPackage init() {
		if (isInited) return (EMFProfileApplicationPackage)EPackage.Registry.INSTANCE.getEPackage(EMFProfileApplicationPackage.eNS_URI);

		// Obtain or create and register package
		EMFProfileApplicationPackageImpl theEMFProfileApplicationPackage = (EMFProfileApplicationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EMFProfileApplicationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EMFProfileApplicationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EMFProfilePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEMFProfileApplicationPackage.createPackageContents();

		// Initialize created meta-data
		theEMFProfileApplicationPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theEMFProfileApplicationPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return EMFProfileApplicationValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theEMFProfileApplicationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EMFProfileApplicationPackage.eNS_URI, theEMFProfileApplicationPackage);
		return theEMFProfileApplicationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProfileApplication() {
		return profileApplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProfileApplication_StereotypeApplications() {
		return (EReference)profileApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProfileApplication_ImportedProfiles() {
		return (EReference)profileApplicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProfileImport() {
		return profileImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProfileImport_NsURI() {
		return (EAttribute)profileImportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProfileImport_Location() {
		return (EAttribute)profileImportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProfileImport_Profile() {
		return (EReference)profileImportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStereotypeApplication() {
		return stereotypeApplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotypeApplication_AppliedTo() {
		return (EReference)stereotypeApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotypeApplication_ProfileApplication() {
		return (EReference)stereotypeApplicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotypeApplication_Extension() {
		return (EReference)stereotypeApplicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStereotypeApplicability() {
		return stereotypeApplicabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotypeApplicability_Stereotype() {
		return (EReference)stereotypeApplicabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotypeApplicability_Extension() {
		return (EReference)stereotypeApplicabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFProfileApplicationFactory getEMFProfileApplicationFactory() {
		return (EMFProfileApplicationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		profileApplicationEClass = createEClass(PROFILE_APPLICATION);
		createEReference(profileApplicationEClass, PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS);
		createEReference(profileApplicationEClass, PROFILE_APPLICATION__IMPORTED_PROFILES);

		profileImportEClass = createEClass(PROFILE_IMPORT);
		createEAttribute(profileImportEClass, PROFILE_IMPORT__NS_URI);
		createEAttribute(profileImportEClass, PROFILE_IMPORT__LOCATION);
		createEReference(profileImportEClass, PROFILE_IMPORT__PROFILE);

		stereotypeApplicationEClass = createEClass(STEREOTYPE_APPLICATION);
		createEReference(stereotypeApplicationEClass, STEREOTYPE_APPLICATION__APPLIED_TO);
		createEReference(stereotypeApplicationEClass, STEREOTYPE_APPLICATION__PROFILE_APPLICATION);
		createEReference(stereotypeApplicationEClass, STEREOTYPE_APPLICATION__EXTENSION);

		stereotypeApplicabilityEClass = createEClass(STEREOTYPE_APPLICABILITY);
		createEReference(stereotypeApplicabilityEClass, STEREOTYPE_APPLICABILITY__STEREOTYPE);
		createEReference(stereotypeApplicabilityEClass, STEREOTYPE_APPLICABILITY__EXTENSION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage)EPackage.Registry.INSTANCE.getEPackage(EMFProfilePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(profileApplicationEClass, ProfileApplication.class, "ProfileApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProfileApplication_StereotypeApplications(), this.getStereotypeApplication(), this.getStereotypeApplication_ProfileApplication(), "stereotypeApplications", null, 0, -1, ProfileApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfileApplication_ImportedProfiles(), this.getProfileImport(), null, "importedProfiles", null, 0, -1, ProfileApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(profileApplicationEClass, this.getStereotypeApplication(), "getStereotypeApplications", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(profileApplicationEClass, this.getStereotypeApplication(), "getStereotypeApplications", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEMFProfilePackage.getStereotype(), "stereotype", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(profileApplicationEClass, theEcorePackage.getEObject(), "getAnnotatedObjects", 0, -1, IS_UNIQUE, !IS_ORDERED);

		initEClass(profileImportEClass, ProfileImport.class, "ProfileImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProfileImport_NsURI(), ecorePackage.getEString(), "nsURI", null, 1, 1, ProfileImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfileImport_Location(), ecorePackage.getEString(), "location", null, 0, 1, ProfileImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfileImport_Profile(), theEMFProfilePackage.getProfile(), null, "profile", null, 0, 1, ProfileImport.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stereotypeApplicationEClass, StereotypeApplication.class, "StereotypeApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStereotypeApplication_AppliedTo(), theEcorePackage.getEObject(), null, "appliedTo", null, 0, 1, StereotypeApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStereotypeApplication_ProfileApplication(), this.getProfileApplication(), this.getProfileApplication_StereotypeApplications(), "profileApplication", null, 0, 1, StereotypeApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStereotypeApplication_Extension(), theEMFProfilePackage.getExtension(), null, "extension", null, 1, 1, StereotypeApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(stereotypeApplicationEClass, theEMFProfilePackage.getStereotype(), "getStereotype", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stereotypeApplicabilityEClass, StereotypeApplicability.class, "StereotypeApplicability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStereotypeApplicability_Stereotype(), theEMFProfilePackage.getStereotype(), null, "stereotype", null, 1, 1, StereotypeApplicability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStereotypeApplicability_Extension(), theEMFProfilePackage.getExtension(), null, "extension", null, 1, 1, StereotypeApplicability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (profileApplicationEClass, 
		   source, 
		   new String[] {
			 "constraints", "violatedUpperBound violatedLowerBound"
		   });		
		addAnnotation
		  (stereotypeApplicationEClass, 
		   source, 
		   new String[] {
			 "constraints", "appliedInapplicableExtension"
		   });
	}

} //EMFProfileApplicationPackageImpl
