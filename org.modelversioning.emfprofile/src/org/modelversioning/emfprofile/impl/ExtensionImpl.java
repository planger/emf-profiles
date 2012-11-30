/**
 * Copyright (c) 2010 modelversioning.org
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getSource <em>
 * Source</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getTarget <em>
 * Target</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getLowerBound
 * <em>Lower Bound</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getUpperBound
 * <em>Upper Bound</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getRedefined
 * <em>Redefined</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getSubsetted
 * <em>Subsetted</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getRedefining
 * <em>Redefining</em>}</li>
 * <li>{@link org.modelversioning.emfprofile.impl.ExtensionImpl#getSubsetting
 * <em>Subsetting</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ExtensionImpl extends EObjectImpl implements Extension {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected EClass target;
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
	 * The cached value of the '{@link #getRedefined() <em>Redefined</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRedefined()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> redefined;
	/**
	 * The cached value of the '{@link #getSubsetted() <em>Subsetted</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSubsetted()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> subsetted;
	/**
	 * The cached value of the '{@link #getRedefining() <em>Redefining</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRedefining()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> redefining;
	/**
	 * The cached value of the '{@link #getSubsetting() <em>Subsetting</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSubsetting()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> subsetting;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EMFProfilePackage.Literals.EXTENSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Stereotype getSource() {
		if (eContainerFeatureID() != EMFProfilePackage.EXTENSION__SOURCE)
			return null;
		return (Stereotype) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSource(Stereotype newSource,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newSource,
				EMFProfilePackage.EXTENSION__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSource(Stereotype newSource) {
		if (newSource != eInternalContainer()
				|| (eContainerFeatureID() != EMFProfilePackage.EXTENSION__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject) newSource).eInverseAdd(this,
						EMFProfilePackage.STEREOTYPE__EXTENSIONS,
						Stereotype.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.EXTENSION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject) target;
			target = (EClass) eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							EMFProfilePackage.EXTENSION__TARGET, oldTarget,
							target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTarget(EClass newTarget) {
		EClass oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					EMFProfilePackage.EXTENSION__TARGET, oldTarget, target));
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
					EMFProfilePackage.EXTENSION__LOWER_BOUND, oldLowerBound,
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
					EMFProfilePackage.EXTENSION__UPPER_BOUND, oldUpperBound,
					upperBound));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getRedefined() {
		if (redefined == null) {
			redefined = new EObjectWithInverseResolvingEList.ManyInverse<Extension>(
					Extension.class, this,
					EMFProfilePackage.EXTENSION__REDEFINED,
					EMFProfilePackage.EXTENSION__REDEFINING);
		}
		return redefined;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getSubsetted() {
		if (subsetted == null) {
			subsetted = new EObjectWithInverseEList.ManyInverse<Extension>(
					Extension.class, this,
					EMFProfilePackage.EXTENSION__SUBSETTED,
					EMFProfilePackage.EXTENSION__SUBSETTING);
		}
		return subsetted;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getRedefining() {
		if (redefining == null) {
			redefining = new EObjectWithInverseResolvingEList.Unsettable.ManyInverse<Extension>(
					Extension.class, this,
					EMFProfilePackage.EXTENSION__REDEFINING,
					EMFProfilePackage.EXTENSION__REDEFINED);
		}
		return redefining;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetRedefining() {
		return redefining != null
				&& ((InternalEList.Unsettable<?>) redefining).isSet();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getSubsetting() {
		if (subsetting == null) {
			subsetting = new EObjectWithInverseResolvingEList.Unsettable.ManyInverse<Extension>(
					Extension.class, this,
					EMFProfilePackage.EXTENSION__SUBSETTING,
					EMFProfilePackage.EXTENSION__SUBSETTED);
		}
		return subsetting;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetSubsetting() {
		return subsetting != null
				&& ((InternalEList.Unsettable<?>) subsetting).isSet();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isRequired() {
		return getLowerBound() > 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isApplicable(EClass eClass) {
		return getSource().isMetaBase() || isApplicableNonMetaBase(eClass);
	}

	private boolean isApplicableNonMetaBase(EClass eClass) {
		return isApplicableWithoutConsideringRedefinition(eClass)
				&& isTypeOrItsSuperTypeUnredefined(eClass);
	}

	private boolean isApplicableWithoutConsideringRedefinition(EClass eClass) {
		return equalsWithTarget(eClass)
				|| isSubTypeOfTarget(eClass);
	}

	private boolean isSubTypeOfTarget(EClass eClass) {
		for (EClass superType : eClass.getEAllSuperTypes()) {
			if (equals(superType, getTarget())) {
				return true;
			}
		}
		return false;
	}

	private boolean equalsWithTarget(EClass eClass) {
		return equals(eClass, getTarget());
	}

	private boolean equals(EClass eClass1, EClass eClass2) {
		return eClass1.getName().equals(eClass2.getName())
				&& eClass1.getEPackage().getNsURI()
						.equals(eClass2.getEPackage().getNsURI());
	}

	private boolean isTypeOrItsSuperTypeUnredefined(EClass type) {
		Collection<EClass> refinedTypes = getRefinedTypesOfRefiningExtensions();
		if (refinedTypes.contains(type)) {
			return false;
		}
		for (EClass superType : type.getEAllSuperTypes()) {
			if (refinedTypes.contains(superType)) {
				return false;
			}
		}
		return true;
	}

	private Collection<EClass> getRefinedTypesOfRefiningExtensions() {
		EList<EClass> refinedTypes = new BasicEList<EClass>();
		for (Extension refiningExtensions : getRedefining()) {
			refinedTypes.add(refiningExtensions.getTarget());
		}
		return refinedTypes;
	}

	protected boolean isRedefinedByExtensions(EList<Extension> extensions) {
		for (Extension extension : extensions) {
			if (getRedefining().contains(extension)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isSubsettedByExtensions(EList<Extension> extensions) {
		for (Extension extension : extensions) {
			if (getSubsetting().contains(extension)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EMFProfilePackage.EXTENSION__SOURCE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetSource((Stereotype) otherEnd, msgs);
		case EMFProfilePackage.EXTENSION__REDEFINED:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getRedefined())
					.basicAdd(otherEnd, msgs);
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getSubsetted())
					.basicAdd(otherEnd, msgs);
		case EMFProfilePackage.EXTENSION__REDEFINING:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getRedefining())
					.basicAdd(otherEnd, msgs);
		case EMFProfilePackage.EXTENSION__SUBSETTING:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getSubsetting())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EMFProfilePackage.EXTENSION__SOURCE:
			return basicSetSource(null, msgs);
		case EMFProfilePackage.EXTENSION__REDEFINED:
			return ((InternalEList<?>) getRedefined()).basicRemove(otherEnd,
					msgs);
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			return ((InternalEList<?>) getSubsetted()).basicRemove(otherEnd,
					msgs);
		case EMFProfilePackage.EXTENSION__REDEFINING:
			return ((InternalEList<?>) getRedefining()).basicRemove(otherEnd,
					msgs);
		case EMFProfilePackage.EXTENSION__SUBSETTING:
			return ((InternalEList<?>) getSubsetting()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case EMFProfilePackage.EXTENSION__SOURCE:
			return eInternalContainer().eInverseRemove(this,
					EMFProfilePackage.STEREOTYPE__EXTENSIONS, Stereotype.class,
					msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EMFProfilePackage.EXTENSION__SOURCE:
			return getSource();
		case EMFProfilePackage.EXTENSION__TARGET:
			if (resolve)
				return getTarget();
			return basicGetTarget();
		case EMFProfilePackage.EXTENSION__LOWER_BOUND:
			return getLowerBound();
		case EMFProfilePackage.EXTENSION__UPPER_BOUND:
			return getUpperBound();
		case EMFProfilePackage.EXTENSION__REDEFINED:
			return getRedefined();
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			return getSubsetted();
		case EMFProfilePackage.EXTENSION__REDEFINING:
			return getRedefining();
		case EMFProfilePackage.EXTENSION__SUBSETTING:
			return getSubsetting();
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
		case EMFProfilePackage.EXTENSION__SOURCE:
			setSource((Stereotype) newValue);
			return;
		case EMFProfilePackage.EXTENSION__TARGET:
			setTarget((EClass) newValue);
			return;
		case EMFProfilePackage.EXTENSION__LOWER_BOUND:
			setLowerBound((Integer) newValue);
			return;
		case EMFProfilePackage.EXTENSION__UPPER_BOUND:
			setUpperBound((Integer) newValue);
			return;
		case EMFProfilePackage.EXTENSION__REDEFINED:
			getRedefined().clear();
			getRedefined().addAll((Collection<? extends Extension>) newValue);
			return;
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			getSubsetted().clear();
			getSubsetted().addAll((Collection<? extends Extension>) newValue);
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
		case EMFProfilePackage.EXTENSION__SOURCE:
			setSource((Stereotype) null);
			return;
		case EMFProfilePackage.EXTENSION__TARGET:
			setTarget((EClass) null);
			return;
		case EMFProfilePackage.EXTENSION__LOWER_BOUND:
			setLowerBound(LOWER_BOUND_EDEFAULT);
			return;
		case EMFProfilePackage.EXTENSION__UPPER_BOUND:
			setUpperBound(UPPER_BOUND_EDEFAULT);
			return;
		case EMFProfilePackage.EXTENSION__REDEFINED:
			getRedefined().clear();
			return;
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			getSubsetted().clear();
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
		case EMFProfilePackage.EXTENSION__SOURCE:
			return getSource() != null;
		case EMFProfilePackage.EXTENSION__TARGET:
			return target != null;
		case EMFProfilePackage.EXTENSION__LOWER_BOUND:
			return lowerBound != LOWER_BOUND_EDEFAULT;
		case EMFProfilePackage.EXTENSION__UPPER_BOUND:
			return upperBound != UPPER_BOUND_EDEFAULT;
		case EMFProfilePackage.EXTENSION__REDEFINED:
			return redefined != null && !redefined.isEmpty();
		case EMFProfilePackage.EXTENSION__SUBSETTED:
			return subsetted != null && !subsetted.isEmpty();
		case EMFProfilePackage.EXTENSION__REDEFINING:
			return isSetRedefining();
		case EMFProfilePackage.EXTENSION__SUBSETTING:
			return isSetSubsetting();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(getSource().getName());
		result.append(" -> ");
		result.append(getTarget().getName());
		return result.toString();
	}

} // ExtensionImpl
