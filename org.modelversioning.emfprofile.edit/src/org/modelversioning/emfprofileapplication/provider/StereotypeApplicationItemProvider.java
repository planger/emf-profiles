/**
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * This is the item provider adapter for a {@link org.modelversioning.emfprofileapplication.StereotypeApplication} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class StereotypeApplicationItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StereotypeApplicationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addAppliedToPropertyDescriptor(object);
			addExtensionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Applied To feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAppliedToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StereotypeApplication_appliedTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StereotypeApplication_appliedTo_feature", "_UI_StereotypeApplication_type"),
				 EMFProfileApplicationPackage.Literals.STEREOTYPE_APPLICATION__APPLIED_TO,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Extension feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StereotypeApplication_extension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StereotypeApplication_extension_feature", "_UI_StereotypeApplication_type"),
				 EMFProfileApplicationPackage.Literals.STEREOTYPE_APPLICATION__EXTENSION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns Stereotype.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/Stereotype"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		if (object instanceof StereotypeApplication) {
			StereotypeApplication stereotypeApplication = (StereotypeApplication) object;
			Stereotype stereotype = (stereotypeApplication).getStereotype();
			EObject eObject = stereotypeApplication.getAppliedTo();
			if(eObject instanceof ENamedElement){
				ENamedElement namedElement = (ENamedElement) eObject;
				return stereotype.getName() + " -> " + namedElement.getName();
			}
			return getString("_UI_Stereotype_Prefix")
			+ stereotypeApplication.getStereotype().getName()
			+ getString("_UI_Stereotype_Postfix")
			+ " ("
			+ stereotypeApplication.getExtension().getSource()
					.getName()
			+ " -> "
			+ stereotypeApplication.getExtension().getTarget()
					.getName() + ")";
		} else {
			return getString("_UI_StereotypeApplication_type");
		}
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return EMFProfileApplicationEditPlugin.INSTANCE;
	}

	 /**
	  * @generated NOT
	   */
	  @Override
	  protected Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	  {
	    // if (childrenFeatures == null)
	    {
	      childrenFeatures = new ArrayList<EStructuralFeature>();
	      EObject eObject = (EObject)object;
	      EClass eClass = eObject.eClass();
	      if (ExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.MIXED_CONTENT)
	      {
	        for (EReference eReference : eClass.getEAllReferences())
	        {
	          if (eReference.isContainment() && ExtendedMetaData.INSTANCE.getGroup(eReference) == null)
	          {
	            childrenFeatures.add(eReference);
	          }
	        }
	      }
	      for (EAttribute eAttribute : eClass.getEAllAttributes())
	      {
	        if (ExtendedMetaData.INSTANCE.getGroup(eAttribute) == null && 
	              eAttribute.getEType().getInstanceClass() == FeatureMap.Entry.class &&
	              !eAttribute.isDerived())
	        {
	          childrenFeatures.add(eAttribute);
	        }
	      }
	    }
	    return childrenFeatures;
	  }
}
