package org.modelversioning.emfprofile.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttributeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClass2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributes2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributesEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassESuperTypesEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassOperations2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassOperationsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EOperationEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageContentsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ProfileEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeTaggedValueCompEditPart;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EMFProfileDiagramUpdater {

	/**
	 * @generated
	 */
	public static boolean isShortcutOrphaned(View view) {
		return !view.isSetElement() || view.getElement() == null
				|| view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getSemanticChildren(View view) {
		switch (EMFProfileVisualIDRegistry.getVisualID(view)) {
		case ProfileEditPart.VISUAL_ID:
			return getProfile_1000SemanticChildren(view);
		case StereotypeTaggedValueCompEditPart.VISUAL_ID:
			return getStereotypeTaggedValueComp_7008SemanticChildren(view);
		case EClassAttributesEditPart.VISUAL_ID:
			return getEClassAttributes_7004SemanticChildren(view);
		case EClassOperationsEditPart.VISUAL_ID:
			return getEClassOperations_7005SemanticChildren(view);
		case EPackageContentsEditPart.VISUAL_ID:
			return getEPackageContents_7002SemanticChildren(view);
		case EClassAttributes2EditPart.VISUAL_ID:
			return getEClassAttributes_7006SemanticChildren(view);
		case EClassOperations2EditPart.VISUAL_ID:
			return getEClassOperations_7007SemanticChildren(view);
		case EEnumLiteralsEditPart.VISUAL_ID:
			return getEEnumLiterals_7003SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getProfile_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Profile modelElement = (Profile) view.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEClassifiers().iterator(); it
				.hasNext();) {
			EClassifier childElement = (EClassifier) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == StereotypeEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EClassEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EEnumEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EDataTypeEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getESubpackages().iterator(); it
				.hasNext();) {
			EPackage childElement = (EPackage) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EPackageEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getStereotypeTaggedValueComp_7008SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Stereotype modelElement = (Stereotype) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEStructuralFeatures().iterator(); it
				.hasNext();) {
			EStructuralFeature childElement = (EStructuralFeature) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EAttributeEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEClassAttributes_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EClass modelElement = (EClass) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEAttributes().iterator(); it
				.hasNext();) {
			EAttribute childElement = (EAttribute) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EAttribute2EditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEClassOperations_7005SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EClass modelElement = (EClass) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEOperations().iterator(); it
				.hasNext();) {
			EOperation childElement = (EOperation) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EOperationEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEPackageContents_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EPackage modelElement = (EPackage) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEClassifiers().iterator(); it
				.hasNext();) {
			EClassifier childElement = (EClassifier) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EClass2EditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEClassAttributes_7006SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EClass modelElement = (EClass) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEAttributes().iterator(); it
				.hasNext();) {
			EAttribute childElement = (EAttribute) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EAttribute2EditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEClassOperations_7007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EClass modelElement = (EClass) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEOperations().iterator(); it
				.hasNext();) {
			EOperation childElement = (EOperation) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EOperationEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileNodeDescriptor> getEEnumLiterals_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		EEnum modelElement = (EEnum) containerView.getElement();
		LinkedList<EMFProfileNodeDescriptor> result = new LinkedList<EMFProfileNodeDescriptor>();
		for (Iterator<?> it = modelElement.getELiterals().iterator(); it
				.hasNext();) {
			EEnumLiteral childElement = (EEnumLiteral) it.next();
			int visualID = EMFProfileVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EEnumLiteralEditPart.VISUAL_ID) {
				result.add(new EMFProfileNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getContainedLinks(View view) {
		switch (EMFProfileVisualIDRegistry.getVisualID(view)) {
		case ProfileEditPart.VISUAL_ID:
			return getProfile_1000ContainedLinks(view);
		case StereotypeEditPart.VISUAL_ID:
			return getStereotype_2006ContainedLinks(view);
		case EClassEditPart.VISUAL_ID:
			return getEClass_2002ContainedLinks(view);
		case EPackageEditPart.VISUAL_ID:
			return getEPackage_2003ContainedLinks(view);
		case EEnumEditPart.VISUAL_ID:
			return getEEnum_2004ContainedLinks(view);
		case EDataTypeEditPart.VISUAL_ID:
			return getEDataType_2005ContainedLinks(view);
		case EAttributeEditPart.VISUAL_ID:
			return getEAttribute_3001ContainedLinks(view);
		case EAttribute2EditPart.VISUAL_ID:
			return getEAttribute_3004ContainedLinks(view);
		case EOperationEditPart.VISUAL_ID:
			return getEOperation_3005ContainedLinks(view);
		case EClass2EditPart.VISUAL_ID:
			return getEClass_3002ContainedLinks(view);
		case EEnumLiteralEditPart.VISUAL_ID:
			return getEEnumLiteral_3003ContainedLinks(view);
		case ExtensionEditPart.VISUAL_ID:
			return getExtension_4005ContainedLinks(view);
		case EReferenceEditPart.VISUAL_ID:
			return getEReference_4003ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getIncomingLinks(View view) {
		switch (EMFProfileVisualIDRegistry.getVisualID(view)) {
		case StereotypeEditPart.VISUAL_ID:
			return getStereotype_2006IncomingLinks(view);
		case EClassEditPart.VISUAL_ID:
			return getEClass_2002IncomingLinks(view);
		case EPackageEditPart.VISUAL_ID:
			return getEPackage_2003IncomingLinks(view);
		case EEnumEditPart.VISUAL_ID:
			return getEEnum_2004IncomingLinks(view);
		case EDataTypeEditPart.VISUAL_ID:
			return getEDataType_2005IncomingLinks(view);
		case EAttributeEditPart.VISUAL_ID:
			return getEAttribute_3001IncomingLinks(view);
		case EAttribute2EditPart.VISUAL_ID:
			return getEAttribute_3004IncomingLinks(view);
		case EOperationEditPart.VISUAL_ID:
			return getEOperation_3005IncomingLinks(view);
		case EClass2EditPart.VISUAL_ID:
			return getEClass_3002IncomingLinks(view);
		case EEnumLiteralEditPart.VISUAL_ID:
			return getEEnumLiteral_3003IncomingLinks(view);
		case ExtensionEditPart.VISUAL_ID:
			return getExtension_4005IncomingLinks(view);
		case EReferenceEditPart.VISUAL_ID:
			return getEReference_4003IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getOutgoingLinks(View view) {
		switch (EMFProfileVisualIDRegistry.getVisualID(view)) {
		case StereotypeEditPart.VISUAL_ID:
			return getStereotype_2006OutgoingLinks(view);
		case EClassEditPart.VISUAL_ID:
			return getEClass_2002OutgoingLinks(view);
		case EPackageEditPart.VISUAL_ID:
			return getEPackage_2003OutgoingLinks(view);
		case EEnumEditPart.VISUAL_ID:
			return getEEnum_2004OutgoingLinks(view);
		case EDataTypeEditPart.VISUAL_ID:
			return getEDataType_2005OutgoingLinks(view);
		case EAttributeEditPart.VISUAL_ID:
			return getEAttribute_3001OutgoingLinks(view);
		case EAttribute2EditPart.VISUAL_ID:
			return getEAttribute_3004OutgoingLinks(view);
		case EOperationEditPart.VISUAL_ID:
			return getEOperation_3005OutgoingLinks(view);
		case EClass2EditPart.VISUAL_ID:
			return getEClass_3002OutgoingLinks(view);
		case EEnumLiteralEditPart.VISUAL_ID:
			return getEEnumLiteral_3003OutgoingLinks(view);
		case ExtensionEditPart.VISUAL_ID:
			return getExtension_4005OutgoingLinks(view);
		case EReferenceEditPart.VISUAL_ID:
			return getEReference_4003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getProfile_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getStereotype_2006ContainedLinks(
			View view) {
		Stereotype modelElement = (Stereotype) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Extension_4005(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_2002ContainedLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEPackage_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnum_2004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEDataType_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEOperation_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_3002ContainedLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnumLiteral_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getExtension_4005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEReference_4003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getStereotype_2006IncomingLinks(
			View view) {
		Stereotype modelElement = (Stereotype) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Extension_4005(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_EClass_ESuperTypes_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_EReference_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_2002IncomingLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Extension_4005(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_EClass_ESuperTypes_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_EReference_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEPackage_2003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnum_2004IncomingLinks(
			View view) {
		EEnum modelElement = (EEnum) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_EReference_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEDataType_2005IncomingLinks(
			View view) {
		EDataType modelElement = (EDataType) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_EReference_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEOperation_3005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_3002IncomingLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Extension_4005(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_EClass_ESuperTypes_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_EReference_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnumLiteral_3003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getExtension_4005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEReference_4003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getStereotype_2006OutgoingLinks(
			View view) {
		Stereotype modelElement = (Stereotype) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Extension_4005(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_2002OutgoingLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEPackage_2003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnum_2004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEDataType_2005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEAttribute_3004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEOperation_3005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEClass_3002OutgoingLinks(
			View view) {
		EClass modelElement = (EClass) view.getElement();
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_EReference_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEEnumLiteral_3003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getExtension_4005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EMFProfileLinkDescriptor> getEReference_4003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getContainedTypeModelFacetLinks_Extension_4005(
			Stereotype container) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		for (Iterator<?> links = container.getExtensions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Extension) {
				continue;
			}
			Extension link = (Extension) linkObject;
			if (ExtensionEditPart.VISUAL_ID != EMFProfileVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			EClass dst = link.getTarget();
			Stereotype src = link.getSource();
			result.add(new EMFProfileLinkDescriptor(src, dst, link,
					EMFProfileElementTypes.Extension_4005,
					ExtensionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getContainedTypeModelFacetLinks_EReference_4003(
			EClass container) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		for (Iterator<?> links = container.getEStructuralFeatures().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof EReference) {
				continue;
			}
			EReference link = (EReference) linkObject;
			if (EReferenceEditPart.VISUAL_ID != EMFProfileVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			EClassifier dst = link.getEType();
			result.add(new EMFProfileLinkDescriptor(container, dst, link,
					EMFProfileElementTypes.EReference_4003,
					EReferenceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getIncomingTypeModelFacetLinks_Extension_4005(
			EClass target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != EMFProfilePackage.eINSTANCE
					.getExtension_Target()
					|| false == setting.getEObject() instanceof Extension) {
				continue;
			}
			Extension link = (Extension) setting.getEObject();
			if (ExtensionEditPart.VISUAL_ID != EMFProfileVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Stereotype src = link.getSource();
			result.add(new EMFProfileLinkDescriptor(src, target, link,
					EMFProfileElementTypes.Extension_4005,
					ExtensionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getIncomingFeatureModelFacetLinks_EClass_ESuperTypes_4002(
			EClass target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == EcorePackage.eINSTANCE
					.getEClass_ESuperTypes()) {
				result.add(new EMFProfileLinkDescriptor(setting.getEObject(),
						target, EMFProfileElementTypes.EClassESuperTypes_4002,
						EClassESuperTypesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getIncomingTypeModelFacetLinks_EReference_4003(
			EClassifier target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != EcorePackage.eINSTANCE
					.getETypedElement_EType()
					|| false == setting.getEObject() instanceof EReference) {
				continue;
			}
			EReference link = (EReference) setting.getEObject();
			if (EReferenceEditPart.VISUAL_ID != EMFProfileVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof EClass) {
				continue;
			}
			EClass container = (EClass) link.eContainer();
			result.add(new EMFProfileLinkDescriptor(container, target, link,
					EMFProfileElementTypes.EReference_4003,
					EReferenceEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getOutgoingTypeModelFacetLinks_Extension_4005(
			Stereotype source) {
		Stereotype container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Stereotype) {
				container = (Stereotype) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		for (Iterator<?> links = container.getExtensions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Extension) {
				continue;
			}
			Extension link = (Extension) linkObject;
			if (ExtensionEditPart.VISUAL_ID != EMFProfileVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			EClass dst = link.getTarget();
			Stereotype src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new EMFProfileLinkDescriptor(src, dst, link,
					EMFProfileElementTypes.Extension_4005,
					ExtensionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EMFProfileLinkDescriptor> getOutgoingFeatureModelFacetLinks_EClass_ESuperTypes_4002(
			EClass source) {
		LinkedList<EMFProfileLinkDescriptor> result = new LinkedList<EMFProfileLinkDescriptor>();
		for (Iterator<?> destinations = source.getESuperTypes().iterator(); destinations
				.hasNext();) {
			EClass destination = (EClass) destinations.next();
			result.add(new EMFProfileLinkDescriptor(source, destination,
					EMFProfileElementTypes.EClassESuperTypes_4002,
					EClassESuperTypesEditPart.VISUAL_ID));
		}
		return result;
	}

}
