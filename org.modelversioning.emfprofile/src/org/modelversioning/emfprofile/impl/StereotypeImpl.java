/**
 * Copyright (c) 2010 - 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import java.util.Collection;

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
		EList<EClass> allExtensibleClasses = getAllExtensibleClasses();
		for (EClass baseClass : allExtensibleClasses) {
			if (!metaBase) {
				// FIXME why name check and not equals?
				// FIXME support super types of eClass
				if (baseClass.getName().equals(eClass.getName())
						&& baseClass.getEPackage().getNsURI()
								.equals(eClass.getEPackage().getNsURI())) {
					return true;
				}
			} else {
				if (baseClass.getName().equals(eClass.eClass().getName())
						&& baseClass
								.getEPackage()
								.getNsURI()
								.equals(eClass.eClass().getEPackage()
										.getNsURI())) {
					return true;
				}
			}
		}
		return false;
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
			EList<Stereotype> appliedStereotypes) {
		if (isApplicable(eObject)) {
			// count stereotypes that are the same as me
			int currentApplicationCount = 0;
			for (Stereotype stereotype : appliedStereotypes) {
				if (this.equals(stereotype)
						|| getEAllSuperTypes().contains(stereotype)
						|| stereotype.getEAllSuperTypes().contains(this)) {
					currentApplicationCount++;
				}
			}
			int realUpperBound = isMetaBase() ? getUpperBound(eObject.eClass()
					.eClass()) : getUpperBound(eObject.eClass());
			if (realUpperBound < -1) {
				return false;
			} else {
				return realUpperBound == -1
						|| realUpperBound > currentApplicationCount;
			}
		} else {
			return true;
		}
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
	public int getLowerBound(EClass eClass) {
		Extension extension = getExtensionForClass(eClass);
		if (extension != null) {
			return extension.getLowerBound();
		} else {
			return -2;
		}
	}

	/**
	 * Returns the direct or indirect {@link Extension} of this stereotype
	 * having the specified <code>eClass</code> as target.
	 * 
	 * FIXME support super types of eClass
	 * 
	 * @param eClass
	 *            to search extension for.
	 * @return the found {@link Extension} or <code>null</code>.
	 */
	private Extension getExtensionForClass(EClass eClass) {
		// search for direct extension
		for (Extension extension : getExtensions()) {
			if (eClass.equals(extension.getTarget())) {
				return extension;
			}
		}
		// search in super stereotypes
		EList<Stereotype> superStereotypes = getSuperStereotypes();
		for (Stereotype superStereotype : superStereotypes) {
			Extension extension = getExtensionForClass(eClass, superStereotype);
			if (extension != null) {
				return extension;
			}
		}
		// search in super super** stereotypes
		EList<Stereotype> allSuperStereotypes = new BasicEList<Stereotype>(
				getAllSuperStereotypes());
		allSuperStereotypes.removeAll(superStereotypes);
		for (Stereotype superStereotype : allSuperStereotypes) {
			Extension extension = getExtensionForClass(eClass, superStereotype);
			if (extension != null) {
				return extension;
			}
		}
		return null;
	}

	/**
	 * Searches for an {@link Extension} having the specified
	 * <code>eClass</code> as {@link Extension#getTarget() target} in the
	 * specified <code>stereotype</code>.
	 * 
	 * @param eClass
	 *            target to search for.
	 * @param stereotype
	 *            to search in.
	 * 
	 * @return the found {@link Extension} or <code>null</code>.
	 */
	private Extension getExtensionForClass(EClass eClass, Stereotype stereotype) {
		for (Extension extension : stereotype.getExtensions()) {
			if (eClass.equals(extension.getTarget())) {
				return extension;
			}
		}
		return null;
	}

	/**
	 * Returns all <em>direct</em> super stereotypes.
	 * 
	 * @return all direct super stereotypes.
	 */
	private EList<Stereotype> getSuperStereotypes() {
		EList<Stereotype> superStereotypes = new BasicEList<Stereotype>();
		EList<EClass> superTypes = this.getESuperTypes();
		for (EClass eClass : superTypes) {
			if (eClass instanceof Stereotype) {
				superStereotypes.add((Stereotype) eClass);
			}
		}
		return ECollections.unmodifiableEList(superStereotypes);
	}

	/**
	 * Returns all <em>direct</em> and <em>indirect</em> super stereotypes.
	 * 
	 * @return all super stereotypes.
	 */
	private EList<Stereotype> getAllSuperStereotypes() {
		EList<Stereotype> superStereotypes = new BasicEList<Stereotype>();
		EList<EClass> superTypes = this.getEAllSuperTypes();
		for (EClass eClass : superTypes) {
			if (eClass instanceof Stereotype) {
				superStereotypes.add((Stereotype) eClass);
			}
		}
		return ECollections.unmodifiableEList(superStereotypes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getUpperBound(EClass eClass) {
		Extension extension = getExtensionForClass(eClass);
		if (extension != null) {
			return extension.getUpperBound();
		} else {
			return -2;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EClass> getAllExtensibleClasses() {
		EList<EClass> allExtensibleClasses = new BasicEList<EClass>();
		// add direct extensible classes
		allExtensibleClasses.addAll(getExtensibleClasses());
		// collect extensible classes of super stereotypes
		for (Stereotype superStereotype : this.getSuperStereotypes()) {
			allExtensibleClasses.addAll(superStereotype
					.getAllExtensibleClasses());
		}
		return ECollections.unmodifiableEList(allExtensibleClasses);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EClass> getExtensibleClasses() {
		EList<EClass> extensibleClasses = new BasicEList<EClass>();
		for (Extension extension : getExtensions()) {
			extensibleClasses.add(extension.getTarget());
		}
		return ECollections.unmodifiableEList(extensibleClasses);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EStructuralFeature> getTaggedValues() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getTaggedValue(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Boolean> getApplicableExtensions(EObject eObject, EList<Stereotype> appliedStereotypes) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
