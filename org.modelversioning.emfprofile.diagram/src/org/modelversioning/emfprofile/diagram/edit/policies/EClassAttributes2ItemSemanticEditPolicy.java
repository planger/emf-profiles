package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.modelversioning.emfprofile.diagram.edit.commands.EAttribute2CreateCommand;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EClassAttributes2ItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EClassAttributes2ItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.EClass_3002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EMFProfileElementTypes.EAttribute_3004 == req.getElementType()) {
			return getGEFWrapper(new EAttribute2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
