/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.EMFProfileApplicationRegistryUIPlugin;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class SaveAllProfileApplicationsHandler extends AbstractHandler
		implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(ActiveEditorObserver.INSTANCE.getLastActiveEditorPart() != null){
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					Iterator<ProfileApplicationDecorator> profileApplicationsIterator = ProfileApplicationRegistry.INSTANCE.getProfileApplications(ActiveEditorObserver.INSTANCE.getModelIdForWorkbenchPart(ActiveEditorObserver.INSTANCE.getLastActiveEditorPart())).iterator();
					while(profileApplicationsIterator.hasNext()){
						final ProfileApplicationDecorator profileApplication = profileApplicationsIterator.next();
						if(profileApplication.isDirty())
							saveProfileApplication(profileApplication);
					}
				}
			});
		}

//		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "Save All Profile Applications!");
		return null;
	}
	private void saveProfileApplication(final ProfileApplicationDecorator profileApplication){
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			public void execute(IProgressMonitor monitor) {
				try {
					profileApplication.save();
				} catch (IOException | CoreException e) {
					showError(
							"Error while saving profile application resource",
							e);
				}finally {
					ActiveEditorObserver.INSTANCE.updateViewer(profileApplication);
				}
			}
		};
		try {
			new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false,
					operation);
		} catch (Exception e) {
			e.printStackTrace();
			showError("Error while saving profile application resource", e);
		}
	}
	private void showError(String message, Throwable throwable) {
		ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileApplicationRegistryUIPlugin.PLUGIN_ID, throwable.getMessage(),
						throwable));
	}
}
