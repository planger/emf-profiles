/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.reflective.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.decorator.reflective.EMFProfileApplicationDecoratorImpl;

/**
 * @author Philip Langer (langer@big.tuwien.ac.at)
 */
public class ApplyStereotypeHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (EMFProfileApplicationDecoratorImpl
				.getPluginExtensionOperationsListener() != null) {
			ISelection currentSelection = HandlerUtil
					.getCurrentSelection(event);
			if (currentSelection != null
					&& currentSelection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
				Object element = structuredSelection.getFirstElement();
				if (element instanceof EObject) {
					EObject selectedEObject = (EObject) element;
					EMFProfileApplicationDecoratorImpl
							.getPluginExtensionOperationsListener()
							.applyStereotype(selectedEObject);
				}
			}
		} else {
			MessageDialog
					.openError(HandlerUtil.getActiveShell(event),
							"Missing Component",
							"There is no Plugin Extension Operations Listener registered!");
			System.err
					.println("There is no Plugin Extension Operations Listener registered!");
		}
		return null;
	}

}
