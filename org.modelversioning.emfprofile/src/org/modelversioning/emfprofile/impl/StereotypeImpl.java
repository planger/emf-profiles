/**
 * Copyright (c) 2010 - 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Stereotype</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#isMetaBase <em>Meta Base</em>}</li>
 *   <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getExtensions <em>Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StereotypeImpl extends EClassImpl implements Stereotype {
	/**
	 * The default value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_PATH_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected String iconPath = ICON_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isMetaBase() <em>Meta Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isMetaBase()
	 * @generated
	 * @ordered
	 */
	protected static final boolean META_BASE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetaBase() <em>Meta Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isMetaBase()
	 * @generated
	 * @ordered
	 */
	protected boolean metaBase = META_BASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> extensions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected StereotypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfilePackage.Literals.STEREOTYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIconPath(String newIconPath) {
		String oldIconPath = iconPath;
		iconPath = newIconPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfilePackage.STEREOTYPE__ICON_PATH, oldIconPath, iconPath));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMetaBase() {
		return metaBase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaBase(boolean newMetaBase) {
		boolean oldMetaBase = metaBase;
		metaBase = newMetaBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EMFProfilePackage.STEREOTYPE__META_BASE, oldMetaBase, metaBase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isApplicable(EClass eClass) {
		if (this.isAbstract()) {
			return false;
		}
		if (this.isMetaBase()) {
			return true;
		}

		return getApplicableExtensions(eClass).size() > 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isApplicable(EObject eObject) {
		return this.isApplicable(eObject.eClass());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isApplicable(EObject eObject,
			EList<Extension> appliedExtensions) {
		return isApplicable(eObject)
				&& getApplicableExtensions(eObject, appliedExtensions).size() > 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isApplicable(EObject eObject, Extension extension,
			EList<Extension> appliedExtensions) {
		return isApplicable(eObject)
				&& getApplicableExtensions(eObject, appliedExtensions)
						.contains(extension);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Profile getProfile() {
		if (!(eContainer() instanceof Profile)) {
			throw new IllegalStateException(
					"Container of stereotype is no profile");
		}
		return (Profile) this.eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Extension> getAllExtensions() {
		EList<Extension> allExtensions = new BasicEList<Extension>(
				this.extensions);
		allExtensions.addAll(getAllInheritedExtensions());
		return allExtensions;
	}

	private EList<Extension> getAllInheritedExtensions() {
		EList<Stereotype> allSuperStereotypes = new BasicEList<Stereotype>(
				getAllSuperStereotypes());

		EList<Extension> indirectlyInheritedExtensions = new BasicEList<Extension>();
		for (Stereotype superStereotype : allSuperStereotypes) {
			indirectlyInheritedExtensions.addAll(superStereotype
					.getExtensions());
		}

		return indirectlyInheritedExtensions;
	}

	private EList<Stereotype> getAllSuperStereotypes() {
		EList<EClass> superTypes = this.getEAllSuperTypes();
		return extractStereotypes(superTypes);
	}

	private EList<Stereotype> extractStereotypes(EList<EClass> classes) {
		EList<Stereotype> stereotypes = new BasicEList<Stereotype>();
		for (EClass eClass : classes) {
			if (eClass instanceof Stereotype) {
				stereotypes.add((Stereotype) eClass);
			}
		}
		return ECollections.unmodifiableEList(stereotypes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EStructuralFeature> getTaggedValues() {
		return this.eStructuralFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EStructuralFeature getTaggedValue(String name) {
		for (EStructuralFeature taggedValue : getTaggedValues()) {
			if (name.equals(taggedValue.getName())) {
				return taggedValue;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Extension> getApplicableExtensions(EObject eObject,
			EList<Extension> appliedExtensions) {
		ExtensionApplicabilityAdvisor applicabilityAdvisor = new ExtensionApplicabilityAdvisor(
				getApplicableExtensions(eObject), appliedExtensions);
		return applicabilityAdvisor.getApplicableExtensions();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Extension> getApplicableExtensions(EObject eObject) {
		return getApplicableExtensions(eObject.eClass());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasIcon() {
		return getIconPath() != null && getIconPath().length() > 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Extension> getApplicableExtensions(EClass eClass) {
		EList<Extension> extensions = getAllUnredefinedAndUnsubsettedExtensions();
		EList<Extension> applicableExtensions = new BasicEList<Extension>();
		for (Extension extension : extensions) {
			if (extension.isApplicable(eClass)) {
				applicableExtensions.add(extension);
			}
		}
		return ECollections.unmodifiableEList(applicableExtensions);
	}

	private EList<Extension> getAllUnredefinedAndUnsubsettedExtensions() {
		EList<Extension> allExtensions = getAllExtensions();
		EList<Extension> unredefinedAndUnsubsettedExtensions = new BasicEList<Extension>();
		for (Extension extension : allExtensions) {
			if (notRedefinedOrSubsetted(extension, allExtensions)) {
				unredefinedAndUnsubsettedExtensions.add(extension);
			}
		}
		return unredefinedAndUnsubsettedExtensions;
	}

	private boolean notRedefinedOrSubsetted(Extension extension,
			EList<Extension> allExtensions) {
		Assert.isTrue(extension instanceof ExtensionImpl);
		ExtensionImpl extensionImpl = (ExtensionImpl) extension;
		return !extensionImpl.isRedefinedByExtensions(allExtensions)
				&& !extensionImpl.isSubsettedByExtensions(allExtensions);
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
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
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
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Extension> getExtensions() {
		if (extensions == null) {
			extensions = new EObjectContainmentWithInverseEList<Extension>(Extension.class, this, EMFProfilePackage.STEREOTYPE__EXTENSIONS, EMFProfilePackage.EXTENSION__SOURCE);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EMFProfilePackage.STEREOTYPE__ICON_PATH:
				return getIconPath();
			case EMFProfilePackage.STEREOTYPE__META_BASE:
				return isMetaBase();
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				return getExtensions();
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
			case EMFProfilePackage.STEREOTYPE__ICON_PATH:
				setIconPath((String)newValue);
				return;
			case EMFProfilePackage.STEREOTYPE__META_BASE:
				setMetaBase((Boolean)newValue);
				return;
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				getExtensions().clear();
				getExtensions().addAll((Collection<? extends Extension>)newValue);
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
			case EMFProfilePackage.STEREOTYPE__ICON_PATH:
				setIconPath(ICON_PATH_EDEFAULT);
				return;
			case EMFProfilePackage.STEREOTYPE__META_BASE:
				setMetaBase(META_BASE_EDEFAULT);
				return;
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				getExtensions().clear();
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
			case EMFProfilePackage.STEREOTYPE__ICON_PATH:
				return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
			case EMFProfilePackage.STEREOTYPE__META_BASE:
				return metaBase != META_BASE_EDEFAULT;
			case EMFProfilePackage.STEREOTYPE__EXTENSIONS:
				return extensions != null && !extensions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (iconPath: ");
		result.append(iconPath);
		result.append(", metaBase: ");
		result.append(metaBase);
		result.append(')');
		return result.toString();
	}

} // StereotypeImpl
