/**
 * Copyright (c) ${year} modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.modelversioning.emfprofile.EMFProfileFactory;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EMFProfilePackageImpl extends EPackageImpl implements EMFProfilePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeEClass = null;

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
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EMFProfilePackageImpl() {
		super(eNS_URI, EMFProfileFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EMFProfilePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EMFProfilePackage init() {
		if (isInited) return (EMFProfilePackage)EPackage.Registry.INSTANCE.getEPackage(EMFProfilePackage.eNS_URI);

		// Obtain or create and register package
		EMFProfilePackageImpl theEMFProfilePackage = (EMFProfilePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EMFProfilePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EMFProfilePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEMFProfilePackage.createPackageContents();

		// Initialize created meta-data
		theEMFProfilePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEMFProfilePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EMFProfilePackage.eNS_URI, theEMFProfilePackage);
		return theEMFProfilePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProfile() {
		return profileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProfile_Base() {
		return (EReference)profileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStereotype() {
		return stereotypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotype_Base() {
		return (EReference)stereotypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_IconPath() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_MetaBase() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_LowerBound() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_UpperBound() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFProfileFactory getEMFProfileFactory() {
		return (EMFProfileFactory)getEFactoryInstance();
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
		profileEClass = createEClass(PROFILE);
		createEReference(profileEClass, PROFILE__BASE);

		stereotypeEClass = createEClass(STEREOTYPE);
		createEReference(stereotypeEClass, STEREOTYPE__BASE);
		createEAttribute(stereotypeEClass, STEREOTYPE__ICON_PATH);
		createEAttribute(stereotypeEClass, STEREOTYPE__META_BASE);
		createEAttribute(stereotypeEClass, STEREOTYPE__LOWER_BOUND);
		createEAttribute(stereotypeEClass, STEREOTYPE__UPPER_BOUND);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		profileEClass.getESuperTypes().add(theEcorePackage.getEPackage());
		stereotypeEClass.getESuperTypes().add(theEcorePackage.getEClass());

		// Initialize classes and features; add operations and parameters
		initEClass(profileEClass, Profile.class, "Profile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProfile_Base(), theEcorePackage.getEPackage(), null, "base", null, 1, 1, Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(profileEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(profileEClass, this.getStereotype(), "getApplicableStereotypes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(profileEClass, this.getStereotype(), "getStereotypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(profileEClass, this.getStereotype(), "getStereotype", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "stereotypeName", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stereotypeEClass, Stereotype.class, "Stereotype", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStereotype_Base(), theEcorePackage.getEClass(), null, "base", null, 0, -1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStereotype_IconPath(), ecorePackage.getEString(), "iconPath", null, 0, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStereotype_MetaBase(), theEcorePackage.getEBoolean(), "metaBase", "false", 1, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStereotype_LowerBound(), ecorePackage.getEInt(), "lowerBound", "0", 0, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStereotype_UpperBound(), ecorePackage.getEInt(), "upperBound", "-1", 0, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStereotype(), "appliedStereotypes", 0, -1, IS_UNIQUE, !IS_ORDERED);

		addEOperation(stereotypeEClass, this.getProfile(), "getProfile", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEInt(), "getLowerBound", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEInt(), "getUpperBound", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EMFProfilePackageImpl
