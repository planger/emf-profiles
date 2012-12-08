/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.emfprofile.application.decorator.gmf.decoration.provider;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.application.decorator.gmf.decoration.EMFProfileDecorator;

/**
 * GMF {@link IDecoratorProvider} for stereotype applications.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class GMFProfileDecoratorProvider extends AbstractProvider implements
		IDecoratorProvider {

	/** The key used for the profile decoration */
	public static final String PROFILE = "EMFProfile_Decorator"; //$NON-NLS-1$

	/**
	 * Utility method to retrieve {@link Node} from the supplied
	 * <code>decoratorTarget</code>.
	 * 
	 * @param decoratorTarget
	 *            {@link IDecoratorTarget} to retrieve {@link Node} from.
	 * @return node {@link Node} from the <code>decoratorElement</code> or
	 *         <code>null</code> if not retrievable.
	 */
	public static Node getDecoratorTargetNode(IDecoratorTarget decoratorTarget) {
		View node = (View) decoratorTarget.getAdapter(View.class);
		if (node != null && node instanceof Node) {
			return (Node) node;
		}
		return null;
	}

	/**
	 * Utility method to retrieve the {@link EObject} from the supplied
	 * <code>decoratorTarget</code>.
	 * 
	 * @param decoratorTarget
	 *            {@link IDecoratorTarget} to retrieve {@link EObject} from.
	 * @return eObject {@link EObject} from the <code>decoratorElement</code> or
	 *         <code>null</code> if not retrievable.
	 */
	public static EObject getDecoratorTargetElement(
			IDecoratorTarget decoratorTarget) {
		Node node = getDecoratorTargetNode(decoratorTarget);
		if (node != null && node.getElement() != null) {
			return node.getElement();
		} else {
			return null;
		}
	}

	/**
	 * Utility method to retrieve the root {@link EditPartViewer} from the
	 * supplied <code>decoratorTarget</code>.
	 * 
	 * @param decoratorTarget
	 *            {@link IDecoratorTarget} to retrieve {@link EditPartViewer}
	 *            from.
	 * @return editPart {@link EditPartViewer} from the
	 *         <code>decoratorElement</code> or <code>null</code> if not
	 *         retrievable.
	 */
	public static EditPartViewer getEditPartViewer(
			IDecoratorTarget decoratorTarget) {
		Object adapter = decoratorTarget.getAdapter(EditPart.class);
		if (adapter != null) {
			return ((EditPart) adapter).getRoot().getViewer();
		}
		return null;
	}

	/**
	 * Utility method to retrieve the root {@link RootEditPart} from the
	 * supplied <code>decoratorTarget</code>.
	 * 
	 * @param decoratorTarget
	 *            {@link IDecoratorTarget} to retrieve {@link RootEditPart}
	 *            from.
	 * @return editPart {@link RootEditPart} from the
	 *         <code>decoratorElement</code> or <code>null</code> if not
	 *         retrievable.
	 */
	public static RootEditPart getRootEditPart(IDecoratorTarget decoratorTarget) {
		Object adapter = decoratorTarget.getAdapter(EditPart.class);
		if (adapter != null) {
			return ((EditPart) adapter).getRoot();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDecorators(IDecoratorTarget decoratorTarget) {
		EObject eObject = getDecoratorTargetElement(decoratorTarget);
		if (eObject != null) {
			IDecorator decorator = new EMFProfileDecorator(decoratorTarget);
			decoratorTarget.installDecorator(PROFILE, decorator);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean provides(IOperation operation) {
		Assert.isNotNull(operation);
		if (!(operation instanceof CreateDecoratorsOperation)) {
			return false;
		}
		IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation)
				.getDecoratorTarget();
		EObject eObject = getDecoratorTargetElement(decoratorTarget);
		EditPart editPart = (EditPart) decoratorTarget
				.getAdapter(EditPart.class);
		if (eObject != null) {
			// only provide decorator for root nodes of an element - not for
			// compartments of the same element
			if (editPart instanceof CompartmentEditPart) {
				EditPart parentEditPart = editPart.getParent();
				if (parentEditPart.getModel() instanceof Node) {
					Node node = (Node) parentEditPart.getModel();
					EObject parentEObject = node.getElement();
					return !parentEObject.equals(eObject);
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
