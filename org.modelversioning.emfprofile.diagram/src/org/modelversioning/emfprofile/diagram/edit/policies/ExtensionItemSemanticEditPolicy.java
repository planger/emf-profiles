package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class ExtensionItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ExtensionItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.Extension_4005);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
