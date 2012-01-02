package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.modelversioning.emfprofile.diagram.edit.commands.EAttributeCreateCommand;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class StereotypeTaggedValueCompItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StereotypeTaggedValueCompItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.Stereotype_2006);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EMFProfileElementTypes.EAttribute_3001 == req.getElementType()) {
			return getGEFWrapper(new EAttributeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
