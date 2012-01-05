/**
 * Copyright (c) 2010 - 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.modelversioning.emfprofile.EMFProfilePackage
 * @generated
 */
public class EMFProfileValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final EMFProfileValidator INSTANCE = new EMFProfileValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.modelversioning.emfprofile";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EcoreValidator ecoreValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMFProfileValidator() {
		super();
		ecoreValidator = EcoreValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return EMFProfilePackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case EMFProfilePackage.PROFILE:
				return validateProfile((Profile)value, diagnostics, context);
			case EMFProfilePackage.STEREOTYPE:
				return validateStereotype((Stereotype)value, diagnostics, context);
			case EMFProfilePackage.EXTENSION:
				return validateExtension((Extension)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProfile(Profile profile, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(profile, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(profile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateENamedElement_WellFormedName(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEPackage_WellFormedNsURI(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEPackage_WellFormedNsPrefix(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEPackage_UniqueSubpackageNames(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEPackage_UniqueClassifierNames(profile, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEPackage_UniqueNsURIs(profile, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStereotype(Stereotype stereotype, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stereotype, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateENamedElement_WellFormedName(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClassifier_WellFormedInstanceTypeName(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClassifier_UniqueTypeParameterNames(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_InterfaceIsAbstract(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_AtMostOneID(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_UniqueFeatureNames(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_UniqueOperationSignatures(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_NoCircularSuperTypes(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_WellFormedMapEntryClass(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_ConsistentSuperTypes(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= ecoreValidator.validateEClass_DisjointFeatureAndOperationSignatures(stereotype, diagnostics, context);
		if (result || diagnostics != null) result &= validateStereotype_uniqueExtensions(stereotype, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the uniqueExtensions constraint of '<em>Stereotype</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String STEREOTYPE__UNIQUE_EXTENSIONS__EEXPRESSION = "self.extensions->size() > 1 implies self.extensions->forAll(ex1 : Extension, ex2 : Extension | ex1 <> ex2 implies ex1.target <> ex2.target)";

	/**
	 * Validates the uniqueExtensions constraint of '<em>Stereotype</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStereotype_uniqueExtensions(Stereotype stereotype, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.STEREOTYPE,
				 stereotype,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "uniqueExtensions",
				 STEREOTYPE__UNIQUE_EXTENSIONS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(extension, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validateExtension_redefinedInSuperStereotype(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validateExtension_subsettedInSuperStereotype(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validateExtension_subsettedMustHaveHigherOrEqualUpperBound(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validateExtension_redefiningTargetMustBeSubclassOfRedefinedTarget(extension, diagnostics, context);
		if (result || diagnostics != null) result &= validateExtension_subsettingTargetMustBeSubclassOfSubsettedTarget(extension, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the redefinedInSuperStereotype constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String EXTENSION__REDEFINED_IN_SUPER_STEREOTYPE__EEXPRESSION = "self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.redefined)";

	/**
	 * Validates the redefinedInSuperStereotype constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension_redefinedInSuperStereotype(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.EXTENSION,
				 extension,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "redefinedInSuperStereotype",
				 EXTENSION__REDEFINED_IN_SUPER_STEREOTYPE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the subsettedInSuperStereotype constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String EXTENSION__SUBSETTED_IN_SUPER_STEREOTYPE__EEXPRESSION = "self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.subsetted)";

	/**
	 * Validates the subsettedInSuperStereotype constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension_subsettedInSuperStereotype(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.EXTENSION,
				 extension,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "subsettedInSuperStereotype",
				 EXTENSION__SUBSETTED_IN_SUPER_STEREOTYPE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the subsettedMustHaveHigherOrEqualUpperBound constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String EXTENSION__SUBSETTED_MUST_HAVE_HIGHER_OR_EQUAL_UPPER_BOUND__EEXPRESSION = "self.subsetted->size() > 0 implies self.subsetted->forAll(subsetted : Extension | subsetted.upperBound >= self.upperBound or subsetted.upperBound = -1)";

	/**
	 * Validates the subsettedMustHaveHigherOrEqualUpperBound constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension_subsettedMustHaveHigherOrEqualUpperBound(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.EXTENSION,
				 extension,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "subsettedMustHaveHigherOrEqualUpperBound",
				 EXTENSION__SUBSETTED_MUST_HAVE_HIGHER_OR_EQUAL_UPPER_BOUND__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the redefiningTargetMustBeSubclassOfRedefinedTarget constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String EXTENSION__REDEFINING_TARGET_MUST_BE_SUBCLASS_OF_REDEFINED_TARGET__EEXPRESSION = "self.redefined->notEmpty() implies self.redefined->forAll(redef : Extension | target.eAllSuperTypes->includes(redef.target))";

	/**
	 * Validates the redefiningTargetMustBeSubclassOfRedefinedTarget constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension_redefiningTargetMustBeSubclassOfRedefinedTarget(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.EXTENSION,
				 extension,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "redefiningTargetMustBeSubclassOfRedefinedTarget",
				 EXTENSION__REDEFINING_TARGET_MUST_BE_SUBCLASS_OF_REDEFINED_TARGET__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the subsettingTargetMustBeSubclassOfSubsettedTarget constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String EXTENSION__SUBSETTING_TARGET_MUST_BE_SUBCLASS_OF_SUBSETTED_TARGET__EEXPRESSION = "self.subsetted->notEmpty() implies self.subsetted->forAll(subsetted : Extension | target.eAllSuperTypes->includes(subsetted.target))";

	/**
	 * Validates the subsettingTargetMustBeSubclassOfSubsettedTarget constraint of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtension_subsettingTargetMustBeSubclassOfSubsettedTarget(Extension extension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(EMFProfilePackage.Literals.EXTENSION,
				 extension,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 "subsettingTargetMustBeSubclassOfSubsettedTarget",
				 EXTENSION__SUBSETTING_TARGET_MUST_BE_SUBCLASS_OF_SUBSETTED_TARGET__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //EMFProfileValidator
