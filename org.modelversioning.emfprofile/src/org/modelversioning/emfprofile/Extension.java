/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getSource <em>Source</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getTarget <em>Target</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getRedefined <em>Redefined</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getSubsetted <em>Subsetted</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getRedefining <em>Redefining</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Extension#getSubsetting <em>Subsetting</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='redefinedInSuperStereotype subsettedInSuperStereotype subsettedMustHaveHigherOrEqualUpperBound redefiningTargetMustBeSubclassOfRedefinedTarget subsettingTargetMustBeSubclassOfSubsettedTarget'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL redefinedInSuperStereotype='self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.redefined)' subsettedInSuperStereotype='self.source.eAllSuperTypes->select(s | s.oclIsKindOf(Stereotype))->collect(s  | s.oclAsType(Stereotype).extensions)->includesAll(self.subsetted)' subsettedMustHaveHigherOrEqualUpperBound='self.subsetted->size() > 0 implies self.subsetted->forAll(subsetted : Extension | subsetted.upperBound >= self.upperBound or subsetted.upperBound = -1)' redefiningTargetMustBeSubclassOfRedefinedTarget='self.redefined->notEmpty() implies self.redefined->forAll(redef : Extension | target.eAllSuperTypes->includes(redef.target))' subsettingTargetMustBeSubclassOfSubsettedTarget='self.subsetted->notEmpty() implies self.subsetted->forAll(subsetted : Extension | target.eAllSuperTypes->includes(subsetted.target))'"
 * @generated
 */
public interface Extension extends EObject {

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Stereotype#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Stereotype)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Source()
	 * @see org.modelversioning.emfprofile.Stereotype#getExtensions
	 * @model opposite="extensions" required="true" transient="false"
	 * @generated
	 */
	Stereotype getSource();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Extension#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Stereotype value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(EClass)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Target()
	 * @model required="true"
	 * @generated
	 */
	EClass getTarget();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Extension#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(EClass value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_LowerBound()
	 * @model default="0"
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Extension#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_UpperBound()
	 * @model default="-1"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Extension#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Redefined</b></em>' reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofile.Extension}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Extension#getRedefining <em>Redefining</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefined</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefined</em>' reference list.
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Redefined()
	 * @see org.modelversioning.emfprofile.Extension#getRedefining
	 * @model opposite="redefining" ordered="false"
	 * @generated
	 */
	EList<Extension> getRedefined();

	/**
	 * Returns the value of the '<em><b>Subsetted</b></em>' reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofile.Extension}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Extension#getSubsetting <em>Subsetting</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subsetted</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subsetted</em>' reference list.
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Subsetted()
	 * @see org.modelversioning.emfprofile.Extension#getSubsetting
	 * @model opposite="subsetting" resolveProxies="false"
	 * @generated
	 */
	EList<Extension> getSubsetted();

	/**
	 * Returns the value of the '<em><b>Redefining</b></em>' reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofile.Extension}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Extension#getRedefined <em>Redefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefining</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefining</em>' reference list.
	 * @see #isSetRedefining()
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Redefining()
	 * @see org.modelversioning.emfprofile.Extension#getRedefined
	 * @model opposite="redefined" unsettable="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Extension> getRedefining();

	/**
	 * Returns whether the value of the '{@link org.modelversioning.emfprofile.Extension#getRedefining <em>Redefining</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Redefining</em>' reference list is set.
	 * @see #getRedefining()
	 * @generated
	 */
	boolean isSetRedefining();

	/**
	 * Returns the value of the '<em><b>Subsetting</b></em>' reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofile.Extension}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Extension#getSubsetted <em>Subsetted</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subsetting</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subsetting</em>' reference list.
	 * @see #isSetSubsetting()
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getExtension_Subsetting()
	 * @see org.modelversioning.emfprofile.Extension#getSubsetted
	 * @model opposite="subsetted" unsettable="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Extension> getSubsetting();

	/**
	 * Returns whether the value of the '{@link org.modelversioning.emfprofile.Extension#getSubsetting <em>Subsetting</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Subsetting</em>' reference list is set.
	 * @see #getSubsetting()
	 * @generated
	 */
	boolean isSetSubsetting();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" eClassRequired="true"
	 * @generated
	 */
	boolean isApplicable(EClass eClass);
} // Extension
