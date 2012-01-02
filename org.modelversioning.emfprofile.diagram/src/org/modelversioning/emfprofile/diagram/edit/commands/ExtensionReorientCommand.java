package org.modelversioning.emfprofile.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.diagram.edit.policies.EMFProfileBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class ExtensionReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ExtensionReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Extension) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof Stereotype && newEnd instanceof Stereotype)) {
			return false;
		}
		EClass target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Stereotype)) {
			return false;
		}
		Stereotype container = (Stereotype) getLink().eContainer();
		return EMFProfileBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistExtension_4005(container, getLink(), getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof EClass && newEnd instanceof EClass)) {
			return false;
		}
		Stereotype source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Stereotype)) {
			return false;
		}
		Stereotype container = (Stereotype) getLink().eContainer();
		return EMFProfileBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistExtension_4005(container, getLink(), source,
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected Extension getLink() {
		return (Extension) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Stereotype getOldSource() {
		return (Stereotype) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Stereotype getNewSource() {
		return (Stereotype) newEnd;
	}

	/**
	 * @generated
	 */
	protected EClass getOldTarget() {
		return (EClass) oldEnd;
	}

	/**
	 * @generated
	 */
	protected EClass getNewTarget() {
		return (EClass) newEnd;
	}
}
