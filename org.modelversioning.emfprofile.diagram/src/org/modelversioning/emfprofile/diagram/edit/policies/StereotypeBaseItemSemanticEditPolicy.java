package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class StereotypeBaseItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StereotypeBaseItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.StereotypeBase_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
