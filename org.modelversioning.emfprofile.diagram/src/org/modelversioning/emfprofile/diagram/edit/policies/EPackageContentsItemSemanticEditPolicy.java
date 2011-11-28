package org.modelversioning.emfprofile.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.modelversioning.emfprofile.diagram.edit.commands.EClass2CreateCommand;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EPackageContentsItemSemanticEditPolicy extends
		EMFProfileBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EPackageContentsItemSemanticEditPolicy() {
		super(EMFProfileElementTypes.EPackage_2003);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EMFProfileElementTypes.EClass_3002 == req.getElementType()) {
			return getGEFWrapper(new EClass2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
