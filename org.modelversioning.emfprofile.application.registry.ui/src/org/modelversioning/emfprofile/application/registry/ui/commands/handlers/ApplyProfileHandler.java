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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;
import org.modelversioning.emfprofile.application.registry.ui.wizards.ApplyProfileWizard;


/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ApplyProfileHandler extends AbstractHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.commands.IHandler#execute(java.util.Map)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor();
		if(activeEditor == null){
			MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "No model resource is opened!", "Before you can apply profiles you have to open a model resource in editor.");
		}else{
			if(EMFProfileApplicationDecoratorHandler.getInstance().hasDecoratorForEditorPart(activeEditor)){
				ApplyProfileWizard wizard = new ApplyProfileWizard();
				wizard.setWorkbenchPart(activeEditor);
				wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection)HandlerUtil.getActivePart(event).getSite().getSelectionProvider().getSelection());
				WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				wizardDialog.open();
			}else{
				MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Can not applay a profile to this resource!", 
						"You can not apply a profile to the resource opened in current active editor:\n" + activeEditor.getTitle());
			}
		}

		return null;
	}
	
}
