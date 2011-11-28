package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.modelversioning.emfprofile.diagram.edit.commands.EEnumLiteralCreateCommand;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EEnumLiteralsItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EEnumLiteralsItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.EEnum_2004);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EMFProfileElementTypes.EEnumLiteral_3003 == req.getElementType()) {
			return getGEFWrapper(new EEnumLiteralCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
