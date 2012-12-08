/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;
import org.modelversioning.emfprofile.application.registry.ui.wizards.LoadProfileApplicationWizard;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class LoadProfileApplicationHandler extends AbstractHandler implements
		IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor();
		if(activeEditor == null){
			MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "No model resource is opened!", "Before you can load profile application you have to open a model resource in an editor.");
		}else{
			if(EMFProfileApplicationDecoratorHandler.getInstance().hasDecoratorForEditorPart(activeEditor)){
				LoadProfileApplicationWizard wizard = new LoadProfileApplicationWizard();
				wizard.setWorkbenchPart(activeEditor);
				
//				wizard.setSelection(HandlerUtil.getCurrentSelection(event));
				
//				wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection)HandlerUtil.getActivePart(event).getSite().getSelectionProvider().getSelection());
				WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				wizardDialog.open();
			}else{
				MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Can not load a profile application!", 
						"You can not load a profile application to the resource opened in current active editor:\n" + activeEditor.getTitle());
			}
		}

		return null;
	}
}
