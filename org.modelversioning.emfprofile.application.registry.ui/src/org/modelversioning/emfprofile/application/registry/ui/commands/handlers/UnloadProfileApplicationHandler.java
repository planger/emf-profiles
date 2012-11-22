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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.EMFProfileApplicationRegistryUIPlugin;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class UnloadProfileApplicationHandler extends AbstractHandler implements
		IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection != null && currentSelection instanceof IStructuredSelection){
			IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
			Object element = structuredSelection.getFirstElement();
			if(element instanceof ProfileApplicationDecorator){
				final ProfileApplicationDecorator profileApplication = (ProfileApplicationDecorator) element;
				IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor();
				ProfileApplicationRegistry.INSTANCE.unloadProfileApplicationForModel(ActiveEditorObserver.INSTANCE.getModelIdForWorkbenchPart(activeEditor), profileApplication);
				ActiveEditorObserver.INSTANCE.refreshViewer();
				
				// TODO check if this code is needed, if not remove 
//				WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
//					@Override
//					public void execute(IProgressMonitor monitor) {
//						try {
//							profileApplication.save();
////							refreshProfileApplicationFile(profileApplication);
//						} catch (IOException e) {
//							showError(
//									"Error while saving profile application resource",
//									e);
//						}
//					}
//				};
//				try {
//					new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false,
//							operation);
//				} catch (Exception e) {
//					showError("Error while saving profile application resource", e);
//				}
			}else {
				MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Not a profile facade selected", "selection: " + currentSelection.toString());
			}
		}else {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "no selection in view");
		}

		return null;
	}

	private void showError(String message, Throwable throwable) {
		ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileApplicationRegistryUIPlugin.PLUGIN_ID, throwable.getMessage(),
						throwable));
	}

}
