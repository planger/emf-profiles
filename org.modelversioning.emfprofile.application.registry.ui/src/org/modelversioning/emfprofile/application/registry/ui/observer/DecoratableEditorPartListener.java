/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.observer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
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
	private TreeViewer viewer;
	private ToolbarCommandEnabledState toolbarCommandEnabeldStateService;
	
	private Map<IWorkbenchPart, ViewerState> editorPartToViewerStateMap = new HashMap<>();
	
	public DecoratableEditorPartListener(
			EMFProfileApplicationDecoratorHandler decoratorHandler,
			Map<IWorkbenchPart, String> editorPartToModelIdMap,
			IWorkbenchPart lastActiveEditorPart, TreeViewer viewer,
			ToolbarCommandEnabledState toolbarCommandEnabeldStateService) {
		super();
		this.decoratorHandler = decoratorHandler;
		this.editorPartToModelIdMap = editorPartToModelIdMap;
		this.lastActiveEditorPart = lastActiveEditorPart;
		this.viewer = viewer;
		this.toolbarCommandEnabeldStateService = toolbarCommandEnabeldStateService;
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
				ProfileApplicationRegistry.INSTANCE.unloadAllProfileApplicationsForModel(editorPartToModelIdMap.get(part));
				// TODO remove
				System.out.println("part id: " + editorPartToModelIdMap.get(part));
				editorPartToViewerStateMap.remove(part);
				editorPartToModelIdMap.remove(part);
				if(part.equals(lastActiveEditorPart)){
					setLastActiveEditorPart(null);
					try {
						viewer.setInput(Collections.emptyList());
					} catch (IllegalStateException e) {
						// viewer control is null or disposed
					}
				}
				// TODO remove						
				System.out.println("part closed, editor parts size: "+editorPartToModelIdMap.size() + ", viewer state size: " + editorPartToViewerStateMap.size());
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
					// TODO remove
					System.out.println("part accessed, size: "+editorPartToModelIdMap.size() + ", viewer state size: " + editorPartToViewerStateMap.size());
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
}
