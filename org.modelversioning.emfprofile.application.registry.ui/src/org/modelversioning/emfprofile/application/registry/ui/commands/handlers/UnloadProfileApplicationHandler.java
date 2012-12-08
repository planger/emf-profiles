/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class UnloadProfileApplicationHandler extends AbstractHandler implements	IHandler {

	public static final String COMMAND_ID = "org.modelversioning.emfprofile.application.registry.ui.commands.unloadprofileapplication";
	// Retrieve the corresponding Services
		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

		// Retrieve commands
		Command saveCmd = commandService.getCommand(SaveProfileApplicationHandler.COMMAND_ID);
		
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
				ifProfileApplicationDirty_AskToSave(profileApplication);
				EList<EObject> eObjects = new BasicEList<>();
				for (StereotypeApplication stereotypeApplication : profileApplication.getStereotypeApplications()) {
					eObjects.add(stereotypeApplication.getAppliedTo());
				}
				IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor();
				ProfileApplicationRegistry.INSTANCE.unloadProfileApplicationForModel(ActiveEditorObserver.INSTANCE.getModelIdForWorkbenchPart(activeEditor), profileApplication);
				ActiveEditorObserver.INSTANCE.refreshViewer();
				ActiveEditorObserver.INSTANCE.refreshDecorations(eObjects);
			}else {
				MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Not a profile application selected", "selection: " + currentSelection.toString());
			}
		}else {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "no selection in view");
		}

		return null;
	}

	private final void ifProfileApplicationDirty_AskToSave(ProfileApplicationDecorator profileApplication){
		if(profileApplication.isDirty()){
			if(MessageDialog.openQuestion(null, "Save Profile Application", "Unloading unsaved Profile Application: " 
					+ profileApplication.getName() + "\nDo you wish to save it?")){
				// Create an ExecutionEvent and specify the profile application associated
				ExecutionEvent executionEvent = handlerService.createExecutionEvent(saveCmd, new Event());
				((IEvaluationContext) executionEvent.getApplicationContext()).addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(profileApplication));

				// Launch the command
				try {
					saveCmd.executeWithChecks(executionEvent);
				} catch (ExecutionException | NotDefinedException
						| NotEnabledException | NotHandledException e) {
					System.err.println("Calling save command");
					e.printStackTrace();
				}
			}
		
		}
	}
}
