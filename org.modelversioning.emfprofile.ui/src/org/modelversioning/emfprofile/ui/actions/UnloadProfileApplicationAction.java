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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;

public class UnloadProfileApplicationAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart = null;

	/**
	 * Constructor for Action1.
	 */
	public UnloadProfileApplicationAction() {
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
		MessageDialog.openInformation(targetPart.getSite().getShell(),
				"EMF Extensions Reflective Editor",
				"Unload Extensions was executed.");
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (targetPart != null) {
			if (EMFProfileUIPlugin.getDefault().isProfileApplicationLoaded(
					targetPart)) {
				action.setEnabled(true);
				return;
			}
		}
		action.setEnabled(false);
	}

}