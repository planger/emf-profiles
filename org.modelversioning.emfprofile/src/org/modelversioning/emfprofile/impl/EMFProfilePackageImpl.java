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
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfileFactory;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.util.EMFProfileValidator;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extensionEClass = null;

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

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theEMFProfilePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return EMFProfileValidator.INSTANCE;
				 }
			 });

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
	public EClass getStereotype() {
		return stereotypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_IconPath() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStereotype_MetaBase() {
		return (EAttribute)stereotypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStereotype_Extensions() {
		return (EReference)stereotypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtension() {
		return extensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Source() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Target() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtension_LowerBound() {
		return (EAttribute)extensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtension_UpperBound() {
		return (EAttribute)extensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Redefined() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Subsetted() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Redefining() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtension_Subsetting() {
		return (EReference)extensionEClass.getEStructuralFeatures().get(7);
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

		stereotypeEClass = createEClass(STEREOTYPE);
		createEAttribute(stereotypeEClass, STEREOTYPE__ICON_PATH);
		createEAttribute(stereotypeEClass, STEREOTYPE__META_BASE);
		createEReference(stereotypeEClass, STEREOTYPE__EXTENSIONS);

		extensionEClass = createEClass(EXTENSION);
		createEReference(extensionEClass, EXTENSION__SOURCE);
		createEReference(extensionEClass, EXTENSION__TARGET);
		createEAttribute(extensionEClass, EXTENSION__LOWER_BOUND);
		createEAttribute(extensionEClass, EXTENSION__UPPER_BOUND);
		createEReference(extensionEClass, EXTENSION__REDEFINED);
		createEReference(extensionEClass, EXTENSION__SUBSETTED);
		createEReference(extensionEClass, EXTENSION__REDEFINING);
		createEReference(extensionEClass, EXTENSION__SUBSETTING);
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

		EOperation op = addEOperation(profileEClass, this.getStereotype(), "getApplicableStereotypes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(profileEClass, this.getStereotype(), "getStereotypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(profileEClass, this.getStereotype(), "getStereotype", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "stereotypeName", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stereotypeEClass, Stereotype.class, "Stereotype", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStereotype_IconPath(), ecorePackage.getEString(), "iconPath", null, 0, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStereotype_MetaBase(), theEcorePackage.getEBoolean(), "metaBase", "false", 1, 1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStereotype_Extensions(), this.getExtension(), this.getExtension_Source(), "extensions", null, 0, -1, Stereotype.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExtension(), "appliedExtensions", 0, -1, IS_UNIQUE, !IS_ORDERED);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExtension(), "extension", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExtension(), "appliedExtensions", 0, -1, IS_UNIQUE, !IS_ORDERED);

		addEOperation(stereotypeEClass, this.getProfile(), "getProfile", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stereotypeEClass, this.getExtension(), "getAllExtensions", 0, -1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(stereotypeEClass, theEcorePackage.getEStructuralFeature(), "getTaggedValues", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, theEcorePackage.getEStructuralFeature(), "getTaggedValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, this.getExtension(), "getApplicableExtensions", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExtension(), "appliedExtensions", 0, -1, IS_UNIQUE, !IS_ORDERED);

		op = addEOperation(stereotypeEClass, this.getExtension(), "getApplicableExtensions", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stereotypeEClass, this.getExtension(), "getApplicableExtensions", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "hasIcon", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(extensionEClass, Extension.class, "Extension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtension_Source(), this.getStereotype(), this.getStereotype_Extensions(), "source", null, 1, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtension_Target(), theEcorePackage.getEClass(), null, "target", null, 1, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtension_LowerBound(), ecorePackage.getEInt(), "lowerBound", "0", 0, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtension_UpperBound(), ecorePackage.getEInt(), "upperBound", "-1", 0, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtension_Redefined(), this.getExtension(), this.getExtension_Redefining(), "redefined", null, 0, -1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExtension_Subsetted(), this.getExtension(), this.getExtension_Subsetting(), "subsetted", null, 0, -1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtension_Redefining(), this.getExtension(), this.getExtension_Redefined(), "redefining", null, 0, -1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getExtension_Subsetting(), this.getExtension(), this.getExtension_Subsetted(), "subsetting", null, 0, -1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		addEOperation(extensionEClass, ecorePackage.getEBoolean(), "isRequired", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(extensionEClass, ecorePackage.getEBoolean(), "isApplicable", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL
		createOCLAnnotations();
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
		  (this, 
		   source, 
		   new String[] {
			 "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL"
		   });		
		addAnnotation
		  (stereotypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "uniqueExtensions"
		   });			
		addAnnotation
		  (extensionEClass, 
		   source, 
		   new String[] {
			 "constraints", "redefinedInSuperStereotype subsettedInSuperStereotype subsettedMustHaveHigherOrEqualUpperBound redefiningTargetMustBeSubclassOfRedefinedTarget subsettingTargetMustBeSubclassOfSubsettedTarget"
		   });	
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL";				
		addAnnotation
		  (stereotypeEClass, 
		   source, 
		   new String[] {
			 "uniqueExtensions", "self.extensions->size() > 1 implies self.extensions->forAll(ex1 : Extension, ex2 : Extension | ex1 <> ex2 implies ex1.target <> ex2.target)"
		   });			
		addAnnotation
		  (extensionEClass, 
		   source, 
		   new String[] {
			 "redefinedInSuperStereotype", "self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.redefined)",
			 "subsettedInSuperStereotype", "self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.subsetted)",
			 "subsettedMustHaveHigherOrEqualUpperBound", "self.subsetted->size() > 0 implies self.subsetted->forAll(subsetted : Extension | subsetted.upperBound >= self.upperBound or subsetted.upperBound = -1)",
			 "redefiningTargetMustBeSubclassOfRedefinedTarget", "self.redefined->notEmpty() implies self.redefined->forAll(redef : Extension | target.eAllSuperTypes->includes(redef.target))",
			 "subsettingTargetMustBeSubclassOfSubsettedTarget", "self.subsetted->notEmpty() implies self.subsetted->forAll(subsetted : Extension | target.eAllSuperTypes->includes(subsetted.target))"
		   });
	}

} //EMFProfilePackageImpl
