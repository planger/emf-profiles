package org.modelversioning.emfprofile.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.diagram.edit.commands.EClassESuperTypesCreateCommand;
import org.modelversioning.emfprofile.diagram.edit.commands.EClassESuperTypesReorientCommand;
import org.modelversioning.emfprofile.diagram.edit.commands.EReferenceCreateCommand;
import org.modelversioning.emfprofile.diagram.edit.commands.EReferenceReorientCommand;
import org.modelversioning.emfprofile.diagram.edit.commands.ExtensionCreateCommand;
import org.modelversioning.emfprofile.diagram.edit.commands.ExtensionReorientCommand;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassAttributes2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassESuperTypesEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassOperations2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EOperationEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionEditPart;
import org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EClass2ItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EClass2ItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.EClass_3002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (EMFProfileVisualIDRegistry.getVisualID(incomingLink) == ExtensionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (EMFProfileVisualIDRegistry.getVisualID(incomingLink) == EClassESuperTypesEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (EMFProfileVisualIDRegistry.getVisualID(incomingLink) == EReferenceEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (EMFProfileVisualIDRegistry.getVisualID(outgoingLink) == EClassESuperTypesEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (EMFProfileVisualIDRegistry.getVisualID(outgoingLink) == EReferenceEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (EMFProfileVisualIDRegistry.getVisualID(node)) {
			case EClassAttributes2EditPart.VISUAL_ID:
				for (Iterator<?> cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (EMFProfileVisualIDRegistry.getVisualID(cnode)) {
					case EAttribute2EditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			case EClassOperations2EditPart.VISUAL_ID:
				for (Iterator<?> cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (EMFProfileVisualIDRegistry.getVisualID(cnode)) {
					case EOperationEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (EMFProfileElementTypes.Extension_4005 == req.getElementType()) {
			return null;
		}
		if (EMFProfileElementTypes.EClassESuperTypes_4002 == req
				.getElementType()) {
			return getGEFWrapper(new EClassESuperTypesCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (EMFProfileElementTypes.EReference_4003 == req.getElementType()) {
			return getGEFWrapper(new EReferenceCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (EMFProfileElementTypes.Extension_4005 == req.getElementType()) {
			return getGEFWrapper(new ExtensionCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (EMFProfileElementTypes.EClassESuperTypes_4002 == req
				.getElementType()) {
			return getGEFWrapper(new EClassESuperTypesCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (EMFProfileElementTypes.EReference_4003 == req.getElementType()) {
			return getGEFWrapper(new EReferenceCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ExtensionEditPart.VISUAL_ID:
			return getGEFWrapper(new ExtensionReorientCommand(req));
		case EReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new EReferenceReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case EClassESuperTypesEditPart.VISUAL_ID:
			return getGEFWrapper(new EClassESuperTypesReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
