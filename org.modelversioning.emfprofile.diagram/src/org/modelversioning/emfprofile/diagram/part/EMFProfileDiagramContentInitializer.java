package org.modelversioning.emfprofile.diagram.part;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttributeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClass2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributes2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributesEditPart;
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

/**
 * @generated
 */
public class EMFProfileDiagramContentInitializer {

	/**
	 * @generated
	 */
	private Map myDomain2NotationMap = new HashMap();

	/**
	 * @generated
	 */
	private Collection myLinkDescriptors = new LinkedList();

	/**
	 * @generated
	 */
	public void initDiagramContent(Diagram diagram) {
		if (!ProfileEditPart.MODEL_ID.equals(diagram.getType())) {
			EMFProfileDiagramEditorPlugin.getInstance().logError(
					"Incorrect diagram passed as a parameter: "
							+ diagram.getType());
			return;
		}
		if (false == diagram.getElement() instanceof Profile) {
			EMFProfileDiagramEditorPlugin.getInstance().logError(
					"Incorrect diagram element specified: "
							+ diagram.getElement() + " instead of Profile");
			return;
		}
		createProfile_1000Children(diagram);
		createLinks(diagram);
	}

	/**
	 * @generated
	 */
	private void createProfile_1000Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getProfile_1000SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createStereotype_2006Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getStereotype_2006OutgoingLinks(view));
		createStereotypeTaggedValueComp_7008Children(getCompartment(view,
				StereotypeTaggedValueCompEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createEClass_2002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEClass_2002OutgoingLinks(view));
		createEClassAttributes_7004Children(getCompartment(view,
				EClassAttributesEditPart.VISUAL_ID));
		createEClassOperations_7005Children(getCompartment(view,
				EClassOperationsEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createEPackage_2003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEPackage_2003OutgoingLinks(view));
		createEPackageContents_7002Children(getCompartment(view,
				EPackageContentsEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createEEnum_2004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEEnum_2004OutgoingLinks(view));
		createEEnumLiterals_7003Children(getCompartment(view,
				EEnumLiteralsEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createEDataType_2005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEDataType_2005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createEAttribute_3001Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEAttribute_3001OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createEAttribute_3004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEAttribute_3004OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createEOperation_3005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEOperation_3005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createEClass_3002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEClass_3002OutgoingLinks(view));
		createEClassAttributes_7006Children(getCompartment(view,
				EClassAttributes2EditPart.VISUAL_ID));
		createEClassOperations_7007Children(getCompartment(view,
				EClassOperations2EditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createEEnumLiteral_3003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(EMFProfileDiagramUpdater
				.getEEnumLiteral_3003OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createStereotypeTaggedValueComp_7008Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getStereotypeTaggedValueComp_7008SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEClassAttributes_7004Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEClassAttributes_7004SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEClassOperations_7005Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEClassOperations_7005SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEPackageContents_7002Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEPackageContents_7002SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEClassAttributes_7006Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEClassAttributes_7006SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEClassOperations_7007Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEClassOperations_7007SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createEEnumLiterals_7003Children(View view) {
		Collection childNodeDescriptors = EMFProfileDiagramUpdater
				.getEEnumLiterals_7003SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (EMFProfileNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createNode(View parentView,
			EMFProfileNodeDescriptor nodeDescriptor) {
		final String nodeType = EMFProfileVisualIDRegistry
				.getType(nodeDescriptor.getVisualID());
		Node node = ViewService.createNode(parentView,
				nodeDescriptor.getModelElement(), nodeType,
				EMFProfileDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		switch (nodeDescriptor.getVisualID()) {
		case StereotypeEditPart.VISUAL_ID:
			createStereotype_2006Children(node);
			return;
		case EClassEditPart.VISUAL_ID:
			createEClass_2002Children(node);
			return;
		case EPackageEditPart.VISUAL_ID:
			createEPackage_2003Children(node);
			return;
		case EEnumEditPart.VISUAL_ID:
			createEEnum_2004Children(node);
			return;
		case EDataTypeEditPart.VISUAL_ID:
			createEDataType_2005Children(node);
			return;
		case EAttributeEditPart.VISUAL_ID:
			createEAttribute_3001Children(node);
			return;
		case EAttribute2EditPart.VISUAL_ID:
			createEAttribute_3004Children(node);
			return;
		case EOperationEditPart.VISUAL_ID:
			createEOperation_3005Children(node);
			return;
		case EClass2EditPart.VISUAL_ID:
			createEClass_3002Children(node);
			return;
		case EEnumLiteralEditPart.VISUAL_ID:
			createEEnumLiteral_3003Children(node);
			return;
		}
	}

	/**
	 * @generated
	 */
	private void createLinks(Diagram diagram) {
		for (boolean continueLinkCreation = true; continueLinkCreation;) {
			continueLinkCreation = false;
			Collection additionalDescriptors = new LinkedList();
			for (Iterator it = myLinkDescriptors.iterator(); it.hasNext();) {
				EMFProfileLinkDescriptor nextLinkDescriptor = (EMFProfileLinkDescriptor) it
						.next();
				if (!myDomain2NotationMap.containsKey(nextLinkDescriptor
						.getSource())
						|| !myDomain2NotationMap.containsKey(nextLinkDescriptor
								.getDestination())) {
					continue;
				}
				final String linkType = EMFProfileVisualIDRegistry
						.getType(nextLinkDescriptor.getVisualID());
				Edge edge = ViewService.getInstance().createEdge(
						nextLinkDescriptor.getSemanticAdapter(), diagram,
						linkType, ViewUtil.APPEND, true,
						EMFProfileDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (edge != null) {
					edge.setSource((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getSource()));
					edge.setTarget((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getDestination()));
					it.remove();
					if (nextLinkDescriptor.getModelElement() != null) {
						myDomain2NotationMap.put(
								nextLinkDescriptor.getModelElement(), edge);
					}
					continueLinkCreation = true;
					switch (nextLinkDescriptor.getVisualID()) {
					case ExtensionEditPart.VISUAL_ID:
						additionalDescriptors.addAll(EMFProfileDiagramUpdater
								.getExtension_4005OutgoingLinks(edge));
						break;
					case EReferenceEditPart.VISUAL_ID:
						additionalDescriptors.addAll(EMFProfileDiagramUpdater
								.getEReference_4003OutgoingLinks(edge));
						break;
					}
				}
			}
			myLinkDescriptors.addAll(additionalDescriptors);
		}
	}

	/**
	 * @generated
	 */
	private Node getCompartment(View node, int visualID) {
		String type = EMFProfileVisualIDRegistry.getType(visualID);
		for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (nextView instanceof Node && type.equals(nextView.getType())) {
				return (Node) nextView;
			}
		}
		return null;
	}

}
