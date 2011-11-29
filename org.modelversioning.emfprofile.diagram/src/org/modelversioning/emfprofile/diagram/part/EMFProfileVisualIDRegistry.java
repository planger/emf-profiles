package org.modelversioning.emfprofile.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttributeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClass2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributes2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributesEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassName2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassOperations2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassOperationsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeInstanceClassNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EOperationEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageContentsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceLowerBoundUpperBoundEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionLowerBoundUpperBoundEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ProfileEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeTaggedValueCompEditPart;
import org.modelversioning.emfprofile.diagram.expressions.EMFProfileOCLFactory;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class EMFProfileVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.modelversioning.emfprofile.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ProfileEditPart.MODEL_ID.equals(view.getType())) {
				return ProfileEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				EMFProfileDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (EMFProfilePackage.eINSTANCE.getProfile().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Profile) domainElement)) {
			return ProfileEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry
				.getModelID(containerView);
		if (!ProfileEditPart.MODEL_ID.equals(containerModelID)
				&& !"ecore".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (ProfileEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ProfileEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case ProfileEditPart.VISUAL_ID:
			if (EMFProfilePackage.eINSTANCE.getStereotype().isSuperTypeOf(
					domainElement.eClass())) {
				return StereotypeEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEClass().isSuperTypeOf(
					domainElement.eClass())) {
				return EClassEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEPackage().isSuperTypeOf(
					domainElement.eClass())) {
				return EPackageEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEEnum().isSuperTypeOf(
					domainElement.eClass())) {
				return EEnumEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEDataType().isSuperTypeOf(
					domainElement.eClass())
					&& isEDataType_2005((EDataType) domainElement)) {
				return EDataTypeEditPart.VISUAL_ID;
			}
			break;
		case StereotypeTaggedValueCompEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAttribute().isSuperTypeOf(
					domainElement.eClass())) {
				return EAttributeEditPart.VISUAL_ID;
			}
			break;
		case EClassAttributesEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAttribute().isSuperTypeOf(
					domainElement.eClass())) {
				return EAttribute2EditPart.VISUAL_ID;
			}
			break;
		case EClassOperationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEOperation().isSuperTypeOf(
					domainElement.eClass())) {
				return EOperationEditPart.VISUAL_ID;
			}
			break;
		case EPackageContentsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEClass().isSuperTypeOf(
					domainElement.eClass())) {
				return EClass2EditPart.VISUAL_ID;
			}
			break;
		case EClassAttributes2EditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAttribute().isSuperTypeOf(
					domainElement.eClass())) {
				return EAttribute2EditPart.VISUAL_ID;
			}
			break;
		case EClassOperations2EditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEOperation().isSuperTypeOf(
					domainElement.eClass())) {
				return EOperationEditPart.VISUAL_ID;
			}
			break;
		case EEnumLiteralsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEEnumLiteral().isSuperTypeOf(
					domainElement.eClass())) {
				return EEnumLiteralEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry
				.getModelID(containerView);
		if (!ProfileEditPart.MODEL_ID.equals(containerModelID)
				&& !"ecore".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (ProfileEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ProfileEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ProfileEditPart.VISUAL_ID:
			if (StereotypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackageEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EEnumEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EDataTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StereotypeEditPart.VISUAL_ID:
			if (StereotypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StereotypeTaggedValueCompEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassEditPart.VISUAL_ID:
			if (EClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassAttributesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassOperationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackageEditPart.VISUAL_ID:
			if (EPackageNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackageContentsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EEnumEditPart.VISUAL_ID:
			if (EEnumNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EEnumLiteralsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EDataTypeEditPart.VISUAL_ID:
			if (EDataTypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EDataTypeInstanceClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClass2EditPart.VISUAL_ID:
			if (EClassName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassAttributes2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassOperations2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StereotypeTaggedValueCompEditPart.VISUAL_ID:
			if (EAttributeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassAttributesEditPart.VISUAL_ID:
			if (EAttribute2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassOperationsEditPart.VISUAL_ID:
			if (EOperationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackageContentsEditPart.VISUAL_ID:
			if (EClass2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassAttributes2EditPart.VISUAL_ID:
			if (EAttribute2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassOperations2EditPart.VISUAL_ID:
			if (EOperationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EEnumLiteralsEditPart.VISUAL_ID:
			if (EEnumLiteralEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ExtensionEditPart.VISUAL_ID:
			if (ExtensionLowerBoundUpperBoundEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EReferenceEditPart.VISUAL_ID:
			if (EReferenceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EReferenceLowerBoundUpperBoundEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (EMFProfilePackage.eINSTANCE.getExtension().isSuperTypeOf(
				domainElement.eClass())) {
			return ExtensionEditPart.VISUAL_ID;
		}
		if (EcorePackage.eINSTANCE.getEReference().isSuperTypeOf(
				domainElement.eClass())) {
			return EReferenceEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Profile element) {
		return true;
	}

	/**
	 * @generated
	 */
	private static boolean isEDataType_2005(EDataType domainElement) {
		Object result = EMFProfileOCLFactory.getExpression(0,
				EcorePackage.eINSTANCE.getEDataType(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

}
