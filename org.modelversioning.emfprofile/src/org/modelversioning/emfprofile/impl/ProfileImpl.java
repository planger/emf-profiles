/**
 * Copyright (c) ${year} modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofile.impl.ProfileImpl#getBase <em>Base</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProfileImpl extends EPackageImpl implements Profile {
	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected EPackage base;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileImpl() {
		super();
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
	 * @generated
	 */
	public EPackage getBase() {
		if (base != null && base.eIsProxy()) {
			InternalEObject oldBase = (InternalEObject)base;
			base = (EPackage)eResolveProxy(oldBase);
			if (base != oldBase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EMFProfilePackage.PROFILE__BASE, oldBase, base));
			}
		}
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(EPackage newBase) {
		EPackage oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfilePackage.PROFILE__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isApplicable(EPackage ePackage) {
		return EcoreUtil.equals(ePackage, this.base);
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EMFProfilePackage.PROFILE__BASE:
				if (resolve) return getBase();
				return basicGetBase();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EMFProfilePackage.PROFILE__BASE:
				setBase((EPackage)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EMFProfilePackage.PROFILE__BASE:
				setBase((EPackage)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EMFProfilePackage.PROFILE__BASE:
				return base != null;
		}
		return super.eIsSet(featureID);
	}

} //ProfileImpl
