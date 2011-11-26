/**
 * Copyright (c) ${year} modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Stereotype</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getBase <em>
 * Base</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#isMetaBase <em>
 * Meta Base</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getLowerBound
 * <em>Lower Bound</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.StereotypeImpl#getUpperBound
 * <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StereotypeImpl extends EClassImpl implements Stereotype {
	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> base;

	/**
	 * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_PATH_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIconPath()
	 * @generated
	 * @ordered
	 */
	protected String iconPath = ICON_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isMetaBase() <em>Meta Base</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMetaBase()
	 * @generated
	 * @ordered
	 */
	protected static final boolean META_BASE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetaBase() <em>Meta Base</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMetaBase()
	 * @generated
	 * @ordered
	 */
	protected boolean metaBase = META_BASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StereotypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfilePackage.Literals.STEREOTYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<EClass> getBase() {
		if (base == null) {
			base = new EObjectResolvingEList<EClass>(EClass.class, this,
					EMFProfilePackage.STEREOTYPE__BASE);
		}
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIconPath(String newIconPath) {
		String oldIconPath = iconPath;
		iconPath = newIconPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.STEREOTYPE__ICON_PATH, oldIconPath,
					iconPath));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMetaBase() {
		return metaBase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMetaBase(boolean newMetaBase) {
		boolean oldMetaBase = metaBase;
		metaBase = newMetaBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.STEREOTYPE__META_BASE, oldMetaBase,
					metaBase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.STEREOTYPE__LOWER_BOUND, oldLowerBound,
					lowerBound));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.STEREOTYPE__UPPER_BOUND, oldUpperBound,
					upperBound));
	}

	/**
	 * {@inheritDoc}
	 */
	public EList<EClass> getAllBaseClasses() {
		EList<EClass> allBaseClasses = new BasicEList<EClass>();
		// collect direct super stereotypes
		for (EClass superClass : this.getEAllSuperTypes()) {
			if (superClass instanceof Stereotype) {
				Stereotype superStereotype = (Stereotype) superClass;
				allBaseClasses.addAll(superStereotype.getAllBaseClasses());
			}
		}
		// add direct base classes
		if (base != null) {
			allBaseClasses.addAll(base);
		}
		return ECollections.unmodifiableEList(allBaseClasses);
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
		EList<EClass> allBaseClasses = getAllBaseClasses();
		for (EClass baseClass : allBaseClasses) {
			if (!metaBase) {
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
			int realUpperBound = getUpperBound(eObject.eClass());
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
		if (getBase().contains(eClass)) {
			return getLowerBound();
		} else {
			EList<Stereotype> superStereotypes = getSuperStereotypes();
			if (superStereotypes.size() > 0) {
				for (Stereotype superStereotype : superStereotypes) {
					int bound = superStereotype.getLowerBound(eClass);
					if (bound != -2) {
						return bound;
					}
				}
			}
		}
		return -2;
	}

	private EList<Stereotype> getSuperStereotypes() {
		EList<Stereotype> superStereotypes = new BasicEList<Stereotype>();
		EList<EClass> superTypes = this.getESuperTypes();
		for (EClass eClass : superTypes) {
			if (eClass instanceof Stereotype) {
				superStereotypes.add((Stereotype) eClass);
			}
		}
		return superStereotypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getUpperBound(EClass eClass) {
		if (containsInBase(eClass)) {
			return getUpperBound();
		} else {
			EList<Stereotype> superStereotypes = getSuperStereotypes();
			if (superStereotypes.size() > 0) {
				for (Stereotype superStereotype : superStereotypes) {
					int bound = superStereotype.getUpperBound(eClass);
					if (bound != -2) {
						return bound;
					}
				}
			}
		}
		return -2;
	}

	private boolean containsInBase(EClass eClass) {
		if (getBase().contains(eClass)) {
			return true;
		} else {
			for (EClass superEClass : eClass.getEAllSuperTypes()) {
				if (getBase().contains(superEClass)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EMFProfilePackage.STEREOTYPE__BASE:
			return getBase();
		case EMFProfilePackage.STEREOTYPE__ICON_PATH:
			return getIconPath();
		case EMFProfilePackage.STEREOTYPE__META_BASE:
			return isMetaBase();
		case EMFProfilePackage.STEREOTYPE__LOWER_BOUND:
			return getLowerBound();
		case EMFProfilePackage.STEREOTYPE__UPPER_BOUND:
			return getUpperBound();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EMFProfilePackage.STEREOTYPE__BASE:
			getBase().clear();
			getBase().addAll((Collection<? extends EClass>) newValue);
			return;
		case EMFProfilePackage.STEREOTYPE__ICON_PATH:
			setIconPath((String) newValue);
			return;
		case EMFProfilePackage.STEREOTYPE__META_BASE:
			setMetaBase((Boolean) newValue);
			return;
		case EMFProfilePackage.STEREOTYPE__LOWER_BOUND:
			setLowerBound((Integer) newValue);
			return;
		case EMFProfilePackage.STEREOTYPE__UPPER_BOUND:
			setUpperBound((Integer) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case EMFProfilePackage.STEREOTYPE__BASE:
			getBase().clear();
			return;
		case EMFProfilePackage.STEREOTYPE__ICON_PATH:
			setIconPath(ICON_PATH_EDEFAULT);
			return;
		case EMFProfilePackage.STEREOTYPE__META_BASE:
			setMetaBase(META_BASE_EDEFAULT);
			return;
		case EMFProfilePackage.STEREOTYPE__LOWER_BOUND:
			setLowerBound(LOWER_BOUND_EDEFAULT);
			return;
		case EMFProfilePackage.STEREOTYPE__UPPER_BOUND:
			setUpperBound(UPPER_BOUND_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case EMFProfilePackage.STEREOTYPE__BASE:
			return base != null && !base.isEmpty();
		case EMFProfilePackage.STEREOTYPE__ICON_PATH:
			return ICON_PATH_EDEFAULT == null ? iconPath != null
					: !ICON_PATH_EDEFAULT.equals(iconPath);
		case EMFProfilePackage.STEREOTYPE__META_BASE:
			return metaBase != META_BASE_EDEFAULT;
		case EMFProfilePackage.STEREOTYPE__LOWER_BOUND:
			return lowerBound != LOWER_BOUND_EDEFAULT;
		case EMFProfilePackage.STEREOTYPE__UPPER_BOUND:
			return upperBound != UPPER_BOUND_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (iconPath: ");
		result.append(iconPath);
		result.append(", metaBase: ");
		result.append(metaBase);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(')');
		return result.toString();
	}

} // StereotypeImpl
