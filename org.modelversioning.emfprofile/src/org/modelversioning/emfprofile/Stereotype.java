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
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stereotype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#isMetaBase <em>Meta Base</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.Stereotype#getExtensions <em>Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype()
 * @model
 * @generated
 */
public interface Stereotype extends EClass {
	/**
	 * Returns the value of the '<em><b>Icon Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * The path of to an icon for this stereotype.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon Path</em>' attribute.
	 * @see #setIconPath(String)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_IconPath()
	 * @model
	 * @generated NOT
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
	 * Returns the value of the '<em><b>Meta Base</b></em>' attribute. The
	 * default value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * Specifies whether this stereotype is a <em>meta stereotype</em> (i.e., is
	 * applicable to all instances of instances of {@link EClass}) or whether it
	 * is a usual stereotype.
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Meta Base</em>' attribute.
	 * @see #setMetaBase(boolean)
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_MetaBase()
	 * @model default="false" required="true"
	 * @generated NOT
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
	 * <!-- begin-user-doc -->
	 * Returns <code>true</code> if this stereotype is applicable to the specified
	 * <code>eClass</code>.
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated NOT
	 */
	boolean isApplicable(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * Returns <code>true</code> if this stereotype is applicable to the specified
	 * <code>eClass</code>.
	 * <!-- end-user-doc -->
	 * @model required="true" eObjectRequired="true"
	 * @generated NOT
	 */
	boolean isApplicable(EObject eObject);

	/**
	 * <!-- begin-user-doc -->
	 * Specifies whether this stereotype is applicable to <code>eObject</code>, given
	 * that the specified list of <code>appliedStereotypes</code> already is applied
	 * to <code>eObject</code>.
	 * <!-- end-user-doc -->
	 * @model required="true" eObjectRequired="true" appliedStereotypesMany="true" appliedStereotypesOrdered="false"
	 * @generated NOT
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
	 * @generated NOT
	 */
	int getLowerBound(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * Returns the upper bound of this stereotype for the specified <code>eClass</code>.
	 * If this stereotype is directly and indirectly not applicable to <code>eClass</code>,
	 * this method returns <code>-2</code>.
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated NOT
	 */
	int getUpperBound(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * All classes for which a this and all super stereotypes contain an {@link #getExtensions() extension}.
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	EList<EClass> getAllExtensibleClasses();

	/**
	 * <!-- begin-user-doc -->
	 * All classes for which a this stereotype contains an {@link #getExtensions() extension}.
	 * <!-- end-user-doc -->
	 * @model kind="operation" ordered="false"
	 * @generated
	 */
	EList<EClass> getExtensibleClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<EStructuralFeature> getTaggedValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model nameRequired="true"
	 * @generated
	 */
	EStructuralFeature getTaggedValue(String name);

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelversioning.emfprofile.Extension}.
	 * It is bidirectional and its opposite is '{@link org.modelversioning.emfprofile.Extension#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see org.modelversioning.emfprofile.EMFProfilePackage#getStereotype_Extensions()
	 * @see org.modelversioning.emfprofile.Extension#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Extension> getExtensions();

} // Stereotype
