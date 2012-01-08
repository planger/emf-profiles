/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stereotype Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl#getAppliedTo <em>Applied To</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl#getProfileApplication <em>Profile Application</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.impl.StereotypeApplicationImpl#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StereotypeApplicationImpl extends EObjectImpl implements StereotypeApplication {
	/**
	 * The cached value of the '{@link #getAppliedTo() <em>Applied To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppliedTo()
	 * @generated
	 * @ordered
	 */
	protected EObject appliedTo;

	/**
	 * The cached value of the '{@link #getExtension() <em>Extension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtension()
	 * @generated
	 * @ordered
	 */
	protected Extension extension;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StereotypeApplicationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfileApplicationPackage.Literals.STEREOTYPE_APPLICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getAppliedTo() {
		if (appliedTo != null && appliedTo.eIsProxy()) {
			InternalEObject oldAppliedTo = (InternalEObject)appliedTo;
			appliedTo = eResolveProxy(oldAppliedTo);
			if (appliedTo != oldAppliedTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO, oldAppliedTo, appliedTo));
			}
		}
		return appliedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetAppliedTo() {
		return appliedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppliedTo(EObject newAppliedTo) {
		EObject oldAppliedTo = appliedTo;
		appliedTo = newAppliedTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO, oldAppliedTo, appliedTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProfileApplication getProfileApplication() {
		if (eContainerFeatureID() != EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION) return null;
		return (ProfileApplication)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProfileApplication(ProfileApplication newProfileApplication, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProfileApplication, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProfileApplication(ProfileApplication newProfileApplication) {
		if (newProfileApplication != eInternalContainer() || (eContainerFeatureID() != EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION && newProfileApplication != null)) {
			if (EcoreUtil.isAncestor(this, newProfileApplication))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProfileApplication != null)
				msgs = ((InternalEObject)newProfileApplication).eInverseAdd(this, EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS, ProfileApplication.class, msgs);
			msgs = basicSetProfileApplication(newProfileApplication, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION, newProfileApplication, newProfileApplication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Extension getExtension() {
		if (extension != null && extension.eIsProxy()) {
			InternalEObject oldExtension = (InternalEObject)extension;
			extension = (Extension)eResolveProxy(oldExtension);
			if (extension != oldExtension) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION, oldExtension, extension));
			}
		}
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Extension basicGetExtension() {
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtension(Extension newExtension) {
		Extension oldExtension = extension;
		extension = newExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION, oldExtension, extension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Stereotype getStereotype() {
		EClass eClass = this.eClass();
		if (eClass instanceof Stereotype) {
			return (Stereotype) eClass;
		} else {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProfileApplication((ProfileApplication)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				return basicSetProfileApplication(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				return eInternalContainer().eInverseRemove(this, EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS, ProfileApplication.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO:
				if (resolve) return getAppliedTo();
				return basicGetAppliedTo();
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				return getProfileApplication();
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION:
				if (resolve) return getExtension();
				return basicGetExtension();
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
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO:
				setAppliedTo((EObject)newValue);
				return;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				setProfileApplication((ProfileApplication)newValue);
				return;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION:
				setExtension((Extension)newValue);
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
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO:
				setAppliedTo((EObject)null);
				return;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				setProfileApplication((ProfileApplication)null);
				return;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION:
				setExtension((Extension)null);
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
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__APPLIED_TO:
				return appliedTo != null;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION:
				return getProfileApplication() != null;
			case EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__EXTENSION:
				return extension != null;
		}
		return super.eIsSet(featureID);
	}

} //StereotypeApplicationImpl
