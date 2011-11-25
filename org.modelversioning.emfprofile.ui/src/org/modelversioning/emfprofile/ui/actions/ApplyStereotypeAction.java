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

package org.modelversioning.emfprofile.ui.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;

/**
 * @author <a href="mailto:wieland@big.tuwien.ac.at">Konrad Wieland</a>
 * 
 */
public class ApplyStereotypeAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;
	private EObject selectedEObject;

	/**
	 * Constructor for Action1.
	 */
	public ApplyStereotypeAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		EMFProfileUIPlugin.getDefault().addNewStereotypeApplication(
				selectedEObject, targetPart);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (targetPart == null) {
			action.setEnabled(false);
			return;
		}
		ISelection realSelection = targetPart.getSite().getSelectionProvider()
				.getSelection();
		if (realSelection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) realSelection;
			Object selectedObject = structuredSelection.getFirstElement();
			if (selectedObject instanceof EObject) {
				this.selectedEObject = (EObject) selectedObject;
				if (EMFProfileUIPlugin.getDefault().mayApplyStereotype(
						this.selectedEObject, targetPart)) {
					action.setEnabled(true);
					return;
				}
			} else if (selectedObject instanceof EditPart) {
				EditPart editPart = (EditPart) selectedObject;
				Object model = editPart.getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					this.selectedEObject = node.getElement();
					if (EMFProfileUIPlugin.getDefault().mayApplyStereotype(
							this.selectedEObject, targetPart)) {
						action.setEnabled(true);
						return;
					}
				}
			}

		}
		action.setEnabled(false);
	}
}
