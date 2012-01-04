/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Profile Application</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl#getStereotypeApplications <em>Stereotype Applications</em>}</li>
 *   <li>{@link org.modelversioning.emfprofileapplication.impl.ProfileApplicationImpl#getImportedProfiles <em>Imported Profiles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProfileApplicationImpl extends EObjectImpl implements
		ProfileApplication {
	/**
	 * The cached value of the '{@link #getStereotypeApplications()
	 * <em>Stereotype Applications</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStereotypeApplications()
	 * @generated
	 * @ordered
	 */
	protected EList<StereotypeApplication> stereotypeApplications;

	/**
	 * The cached value of the '{@link #getImportedProfiles()
	 * <em>Imported Profiles</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getImportedProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ProfileImport> importedProfiles;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileApplicationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfileApplicationPackage.Literals.PROFILE_APPLICATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StereotypeApplication> getStereotypeApplications() {
		if (stereotypeApplications == null) {
			stereotypeApplications = new EObjectContainmentWithInverseEList<StereotypeApplication>(StereotypeApplication.class, this, EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS, EMFProfileApplicationPackage.STEREOTYPE_APPLICATION__PROFILE_APPLICATION);
		}
		return stereotypeApplications;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProfileImport> getImportedProfiles() {
		if (importedProfiles == null) {
			importedProfiles = new EObjectContainmentEList<ProfileImport>(ProfileImport.class, this, EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES);
		}
		return importedProfiles;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<StereotypeApplication> getStereotypeApplications(
			EObject eObject) {
		EList<StereotypeApplication> appliedStereotypes = new BasicEList<StereotypeApplication>();
		for (StereotypeApplication stereotypeApplication : getStereotypeApplications()) {
			if (eObject.equals(stereotypeApplication.getAppliedTo())) {
				appliedStereotypes.add(stereotypeApplication);
			}
		}
		return ECollections.unmodifiableEList(appliedStereotypes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<StereotypeApplication> getStereotypeApplications(
			EObject eObject, Stereotype stereotype) {
		EList<StereotypeApplication> appliedStereotypes = new BasicEList<StereotypeApplication>();
		for (StereotypeApplication stereotypeApplication : getStereotypeApplications(eObject)) {
			if (stereotypeApplication.eClass().equals(stereotype)) {
				appliedStereotypes.add(stereotypeApplication);
			}
		}
		return ECollections.unmodifiableEList(appliedStereotypes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EObject> getAnnotatedObjects() {
		EList<EObject> annotatedObjects = new BasicEList<EObject>();
		for (StereotypeApplication stereotypeApplication : getStereotypeApplications()) {
			if (!annotatedObjects.contains(stereotypeApplication.getAppliedTo())) {
				annotatedObjects.add(stereotypeApplication.getAppliedTo());
			}
		}
		return ECollections.unmodifiableEList(annotatedObjects);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStereotypeApplications()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				return ((InternalEList<?>)getStereotypeApplications()).basicRemove(otherEnd, msgs);
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES:
				return ((InternalEList<?>)getImportedProfiles()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				return getStereotypeApplications();
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES:
				return getImportedProfiles();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				getStereotypeApplications().clear();
				getStereotypeApplications().addAll((Collection<? extends StereotypeApplication>)newValue);
				return;
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES:
				getImportedProfiles().clear();
				getImportedProfiles().addAll((Collection<? extends ProfileImport>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				getStereotypeApplications().clear();
				return;
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES:
				getImportedProfiles().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__STEREOTYPE_APPLICATIONS:
				return stereotypeApplications != null && !stereotypeApplications.isEmpty();
			case EMFProfileApplicationPackage.PROFILE_APPLICATION__IMPORTED_PROFILES:
				return importedProfiles != null && !importedProfiles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // ProfileApplicationImpl
