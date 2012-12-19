/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.commands.handlers.UnloadProfileApplicationHandler;
import org.modelversioning.emfprofile.application.registry.ui.commands.sourceprovider.ToolbarCommandEnabledState;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public final class DecoratableEditorPartListener implements IPartListener {
	
	private EMFProfileApplicationDecoratorHandler decoratorHandler;
	private Map<IWorkbenchPart, String> editorPartToModelIdMap;
	private IWorkbenchPart lastActiveEditorPart;
	private IWorkbenchPart cleaningUpForEditorPart = null;
	private TreeViewer viewer;
	private ToolbarCommandEnabledState toolbarCommandEnabeldStateService;
	
	private Map<IWorkbenchPart, ViewerState> editorPartToViewerStateMap;
	
	private boolean cleaningUpForClosedEditor = false;
	
	// Retrieve the corresponding Services
	IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
	ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

	// Retrieve commands
	Command unloadCmd = commandService.getCommand(UnloadProfileApplicationHandler.COMMAND_ID);
	
	public DecoratableEditorPartListener(
			EMFProfileApplicationDecoratorHandler decoratorHandler,
			Map<IWorkbenchPart, String> editorPartToModelIdMap,
			IWorkbenchPart lastActiveEditorPart, TreeViewer viewer,
			ToolbarCommandEnabledState toolbarCommandEnabeldStateService, Map<IWorkbenchPart, ViewerState> editorPartToViewerStateMap) {
		super();
		this.decoratorHandler = decoratorHandler;
		this.editorPartToModelIdMap = editorPartToModelIdMap;
		this.lastActiveEditorPart = lastActiveEditorPart;
		this.viewer = viewer;
		this.toolbarCommandEnabeldStateService = toolbarCommandEnabeldStateService;
		this.editorPartToViewerStateMap = editorPartToViewerStateMap;
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		// ignore
	}
	
	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// ignore
	}
	
	@Override
	public void partClosed(IWorkbenchPart part) {
		if(decoratorHandler.hasDecoratorForEditorPart(part)){
			// check if this part was registered in the map
			if(editorPartToModelIdMap.containsKey(part)){
				if(part.equals(lastActiveEditorPart)){
					setLastActiveEditorPart(null);
					try {
						viewer.setInput(Collections.emptyList());
					} catch (IllegalStateException e) {
						// viewer control is null or disposed
					}
				}
				cleaningUpForClosedEditor = true;
				cleanUp(part);
				cleaningUpForClosedEditor = false;
				editorPartToViewerStateMap.remove(part);
				editorPartToModelIdMap.remove(part);
			}
		}
	}
	
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// ignore
	}
	
	@Override
	public void partActivated(IWorkbenchPart part) {
		if(decoratorHandler.hasDecoratorForEditorPart(part)){
			if(editorPartToModelIdMap.containsKey(part)){
				// editor part is already known.
				// it is possible that the user was clicking around at view parts
				// so, check if last active editor part was this one.
				// if it was then no input model change on viewer is needed.
				if( ! part.equals(lastActiveEditorPart)){
					// Restore viewer state and save the viewer state of last active editor part
					// if last active editor part is not null then save it
					if(lastActiveEditorPart != null){
						editorPartToViewerStateMap.put(lastActiveEditorPart, new ViewerState(viewer));
					}
					// restore viewer part for already known part
					editorPartToViewerStateMap.get(part).restoreTreeViewerState(viewer);
					// remove viewer state from map for this part
					editorPartToViewerStateMap.remove(part);
				}
			}else{
				// editor part first time accessed or editor opened with double click on resource file,
				// Now, create an id for workbench part and put it into map
				editorPartToModelIdMap.put(part, UUID.randomUUID().toString());
				// Here we need to save last active editor part viewer state if it was not null and clear the view
				if(lastActiveEditorPart != null){
					// save last active editor part viewer state
					editorPartToViewerStateMap.put(lastActiveEditorPart, new ViewerState(viewer));
					viewer.setInput(Collections.emptyList());
				}
			}
			setLastActiveEditorPart((IEditorPart)part);
			
		}else{
			// if unsupported editor part was activated clear the view
			if(part instanceof IEditorPart){
				// unset last active editor part if it is not already
				if(lastActiveEditorPart != null){
					// save last active editor part viewer state
					editorPartToViewerStateMap.put(lastActiveEditorPart, new ViewerState(viewer));
					setLastActiveEditorPart(null);
					viewer.setInput(Collections.emptyList());
				}
			}
		}
	}
	
	private void setLastActiveEditorPart(IEditorPart editorPart){
		lastActiveEditorPart = editorPart;
		if(lastActiveEditorPart == null)
			toolbarCommandEnabeldStateService.setEnabled(false); 
		else
			toolbarCommandEnabeldStateService.setEnabled(true);
	}

	public IWorkbenchPart getLastActiveEditPart() {
		return this.lastActiveEditorPart;
	}
	/**
	 * It is needed when the decorations need to be refreshed.
	 * The {@link #cleanUp(IWorkbenchPart)}	sets the reference of editor part
	 * at the beginning of clean up and unsets it at the end.
	 * @return 
	 */
	public IWorkbenchPart getCleaningUpForEditorPart(){
		return cleaningUpForEditorPart;
	}
	
	public void cleanUpForAllEditorParts(){
		for (IWorkbenchPart editorPart : editorPartToModelIdMap.keySet()) {
			cleanUp(editorPart);
		}
		editorPartToModelIdMap.clear();
		editorPartToViewerStateMap.clear();			
	}
	
	private void cleanUp(IWorkbenchPart editorPart){
		cleaningUpForEditorPart = editorPart;
		ExecutionEvent executionEvent;
		String modelId = editorPartToModelIdMap.get(editorPart);
		Collection<ProfileApplicationDecorator> profileApplications = new ArrayList<>(ProfileApplicationRegistry.INSTANCE.getProfileApplications(modelId));
		for (ProfileApplicationDecorator profileApplication : profileApplications) {
			// Create an ExecutionEvent and specify the profile application associated
			executionEvent = handlerService.createExecutionEvent(unloadCmd, new Event());
			((IEvaluationContext) executionEvent.getApplicationContext()).addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(profileApplication));

			// Launch the command
			try {
				unloadCmd.executeWithChecks(executionEvent);
			} catch (ExecutionException | NotDefinedException
					| NotEnabledException | NotHandledException e) {
				System.err.println("Calling unload command");
				e.printStackTrace();
			}
		}
		ProfileApplicationRegistry.INSTANCE.unloadAllProfileApplicationsForModel(modelId);
		cleaningUpForEditorPart = null;
	}

	public boolean isCleaningUpForClosedEditorPart() {
		return cleaningUpForClosedEditor;
	}
}
