/**
 * Copyright (c) ${year} modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ProfileImpl extends EPackageImpl implements Profile {
	
	protected static final String NAME_EDEFAULT = "MyProfile"; //$NON-NLS-1$
	protected static final String NS_URI_EDEFAULT = "http://www.modelversioning.org/myprofile"; //$NON-NLS-1$
	protected static final String NS_PREFIX_EDEFAULT = "myprofile"; //$NON-NLS-1$
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ProfileImpl() {
		super();
		setDefaultValues();
	}
	
	private void setDefaultValues() {
		this.setName(NAME_EDEFAULT);
		this.setNsURI(NS_URI_EDEFAULT);
		this.setNsPrefix(NS_PREFIX_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfilePackage.Literals.PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Stereotype> getApplicableStereotypes(EClass eClass) {
		EList<Stereotype> applicableStereotypes = new BasicEList<Stereotype>();
		for (Stereotype stereotype : this.getStereotypes()) {
			if (stereotype.isApplicable(eClass)) {
				applicableStereotypes.add(stereotype);
			}
		}
		return ECollections.unmodifiableEList(applicableStereotypes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns all contained {@link Stereotype}s.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Stereotype> getStereotypes() {
		EList<Stereotype> stereotypes = new BasicEList<Stereotype>();
		for (EClassifier eClass : this.eClassifiers) {
			if (eClass instanceof Stereotype) {
				stereotypes.add((Stereotype)eClass);
			}
		}
		return ECollections.unmodifiableEList(stereotypes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the {@link Stereotype} with the specified <code>name</code>.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Stereotype getStereotype(String stereotypeName) {
		for (Stereotype stereotype : getStereotypes()) {
			if (stereotypeName.equals(stereotype.getName())) {
				return stereotype;
			}
		}
		return null;
	}

} //ProfileImpl
