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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stereotype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getBase <em>Base</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#isMetaBase <em>Meta Base</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype()
 * @model
 * @generated
 */
public interface Stereotype extends EClass {
	/**
	 * Returns the value of the '<em><b>Base</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base</em>' reference list.
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_Base()
	 * @model
	 * @generated
	 */
	EList<EClass> getBase();

	/**
	 * Returns the value of the '<em><b>Icon Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon Path</em>' attribute.
	 * @see #setIconPath(String)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_IconPath()
	 * @model
	 * @generated
	 */
	String getIconPath();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Stereotype#getIconPath <em>Icon Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon Path</em>' attribute.
	 * @see #getIconPath()
	 * @generated
	 */
	void setIconPath(String value);

	/**
	 * Returns the value of the '<em><b>Meta Base</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Base</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Base</em>' attribute.
	 * @see #setMetaBase(boolean)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_MetaBase()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isMetaBase();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Stereotype#isMetaBase <em>Meta Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meta Base</em>' attribute.
	 * @see #isMetaBase()
	 * @generated
	 */
	void setMetaBase(boolean value);

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
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_LowerBound()
	 * @model default="0"
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Stereotype#getLowerBound <em>Lower Bound</em>}' attribute.
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
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_UpperBound()
	 * @model default="-1"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link org.modelversioning.emfprofile.Stereotype#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * <!-- begin-user-doc -->
	 * Returns <code>true</code> if this stereotype is applicable to the specified
	 * <code>eClass</code>.
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	boolean isApplicable(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * Returns <code>true</code> if this stereotype is applicable to the specified
	 * <code>eClass</code>.
	 * <!-- end-user-doc -->
	 * @model required="true" eObjectRequired="true"
	 * @generated
	 */
	boolean isApplicable(EObject eObject);

	/**
	 * <!-- begin-user-doc -->
	 * Specifies whether this stereotype is applicable to <code>eObject</code>, given
	 * that the specified list of <code>appliedStereotypes</code> already is applied
	 * to <code>eObject</code>.
	 * <!-- end-user-doc -->
	 * @model required="true" eObjectRequired="true" appliedStereotypesMany="true" appliedStereotypesOrdered="false"
	 * @generated
	 */
	boolean isApplicable(EObject eObject, EList<Stereotype> appliedStereotypes);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	Profile getProfile();
	
	/**
	 * <!-- begin-user-doc -->
	 * Returns the upper bound of this stereotype for the specified <code>eClass</code>.
	 * If this stereotype is directly and indirectly not applicable to <code>eClass</code>,
	 * this method returns <code>-2</code>.
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	int getLowerBound(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * Returns the upper bound of this stereotype for the specified <code>eClass</code>.
	 * If this stereotype is directly and indirectly not applicable to <code>eClass</code>,
	 * this method returns <code>-2</code>.
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	int getUpperBound(EClass eClass);

	/**
	 * Returns a list of all (inherited and direct) base classes.
	 * 
	 * @return a list of all (inherited and direct) base classes.
	 */
	EList<EClass> getAllBaseClasses();

} // Stereotype
