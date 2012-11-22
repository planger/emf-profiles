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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.EMFProfileApplicationRegistryUIPlugin;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.ApplyStereotypeListener;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.provider.EMFProfileApplicationItemProviderAdapterFactory;

/**
 * It manages mapping of opened editors of interest to 
 * the generated id for an opened model in editor.
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ActiveEditorObserver implements ApplyStereotypeListener {
	
	public static ActiveEditorObserver INSTANCE = new ActiveEditorObserver();
	
	private Map<IWorkbenchPart, String> editorPartToModelIdMap = new HashMap<>();
	private Map<IWorkbenchPart, ViewerState> editorPartToViewerStateMap = new HashMap<>();
	private IWorkbenchPart lastActiveEditorPart = null;
	
	private IWorkbenchPage activePage;
	private EMFProfileApplicationDecoratorHandler decoratorHandler;
	private ProfileApplicationRegistry registry = ProfileApplicationRegistry.INSTANCE;
	private TreeViewer viewer;
	
	private ActiveEditorObserver(){
		decoratorHandler = EMFProfileApplicationDecoratorHandler.getInstance();
		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window == null)
			throw new RuntimeException("could not locate workbench active window!");
		activePage = window.getActivePage();
		if(activePage == null)
			throw new RuntimeException("could not locate active page for active window ");

//		When the plug-in starts, we should check if there is an active editor and if it can be decorated
		IEditorPart editorPart = activePage.getActiveEditor();
		if(editorPart != null){
			if(decoratorHandler.hasDecoratorForEditorPart(editorPart)){
				// Create an id for workbench part and put it into map
				editorPartToModelIdMap.put(editorPart, UUID.randomUUID().toString());
				lastActiveEditorPart = editorPart;
				// TODO remove
				System.out.println("part opened at the start, size: "+editorPartToModelIdMap.size());
			}
		}
	}
	
	/**
	 * To set the Tree Viewer from outside.
	 * After calling this method a part listener will be added on active page
	 * which registers activation of editors that can be decorated.  
	 * @param viewer
	 */
	public void setViewer(TreeViewer viewer){
		this.viewer = viewer;
		activePage.addPartListener(new DecoratableEditorPartListener());
	}
	
	public void refreshViewer() {
		if(viewer.getInput().equals(Collections.emptyList())){
			// TODO remove comment
			System.out.println("VIEWER INPUT WAS EMPTY");
			viewer.setInput(ProfileApplicationRegistry.INSTANCE.getProfileApplications(editorPartToModelIdMap.get(lastActiveEditorPart)));
		}else{
			// TODO remove comment
			System.out.println("VIEWER REFRESHES INPUT");
			viewer.refresh();
		}
	}
	
	public String getModelIdForWorkbenchPart(IWorkbenchPart part) {
		return editorPartToModelIdMap.get(part);
	}
	
	final class DecoratableEditorPartListener implements IPartListener {
		@Override
		public void partOpened(IWorkbenchPart part) {
			// noop
		}
		
		@Override
		public void partDeactivated(IWorkbenchPart part) {
			// noop
		}
		
		@Override
		public void partClosed(IWorkbenchPart part) {
			if(decoratorHandler.hasDecoratorForEditorPart(part)){
				// check if this part was registered in the map
				if(editorPartToModelIdMap.containsKey(part)){
					registry.unloadAllProfileApplicationsForModel(editorPartToModelIdMap.get(part));
					// TODO remove
					System.out.println("part id: " + editorPartToModelIdMap.get(part));
					editorPartToViewerStateMap.remove(part);
					editorPartToModelIdMap.remove(part);
					if(part.equals(lastActiveEditorPart)){
						lastActiveEditorPart = null;
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
			// noop
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
				lastActiveEditorPart = part;
				
			}else{
				// if unsupported editor part was activated clear the view
				if(part instanceof IEditorPart){
					// unset last active editor part if it is not already
					if(lastActiveEditorPart != null){
						// save last active editor part viewer state
						editorPartToViewerStateMap.put(lastActiveEditorPart, new ViewerState(viewer));
						lastActiveEditorPart = null;
						viewer.setInput(Collections.emptyList());
					}
				}
			}
		}
	}
	
	@Override
	public void applyStereotype(EObject eObject) {
		// TODO REMOVE COMMENT
		System.out.println("APPLYING STEREOTYPE");
		Assert.isNotNull(eObject);
		if (mayApplyStereotype(eObject)) {
			ElementListSelectionDialog dialog = new ElementListSelectionDialog(
					EMFProfileApplicationRegistryUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),
					new AdapterFactoryLabelProvider(getAdapterFactory()));
			dialog.setTitle("Stereotype Selection");
			dialog.setMessage("Select a Stereotype (* = any string, ? = any char):");
			EList<StereotypeApplicability> applicableStereotypes = new BasicEList<>();
			for (ProfileApplicationDecorator profileApplication : ProfileApplicationRegistry.INSTANCE.getProfileApplications(editorPartToModelIdMap.get(lastActiveEditorPart))) {
				applicableStereotypes.addAll(profileApplication.getApplicableStereotypes(eObject));
			}
			dialog.setElements(applicableStereotypes.toArray());
			int result = dialog.open();
			if (Dialog.OK == result) {
				Object firstResult = dialog.getFirstResult();
				if (firstResult instanceof StereotypeApplicability) {
					for (ProfileApplicationDecorator profileApplication : ProfileApplicationRegistry.INSTANCE.getProfileApplications(editorPartToModelIdMap.get(lastActiveEditorPart))) {
						profileApplication.apply((StereotypeApplicability) firstResult, eObject);
					}

					viewer.refresh();
				}
			}
		} else {
			showError("Cannot apply any stereotype to " + eObject);
		}
	}
	
	private ComposedAdapterFactory getAdapterFactory(){
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new EMFProfileItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		return adapterFactory;
	}
	
	/**
	 * Specifies whether we may apply a stereotype to the supplied
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            in question.
	 * @param workbenchPart
	 *            to identify the profile facade ({@link RootEditPart} or
	 *            {@link IWorkbenchPart}).
	 * @return the resource set.
	 */
	public boolean mayApplyStereotype(EObject eObject) {
		for (ProfileApplicationDecorator profileApplication : ProfileApplicationRegistry.INSTANCE.getProfileApplications(editorPartToModelIdMap.get(lastActiveEditorPart))) {
			if(profileApplication.getApplicableStereotypes(eObject).size() > 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns the applied stereotypes for the <code>eObject</code>.
	 * 
	 * @param eObject
	 *            to get {@link StereotypeApplication StereotypeApplications}
	 *            for.
	 * @param keyPart
	 *            to identify facade.
	 * @return the list.
	 */
//	public EList<StereotypeApplication> getAppliedStereotypes(EObject eObject,
//			IAdaptable keyPart) {
//		if (partProfileMapping.containsKey(keyPart)) {
//			IProfileFacade iProfileFacade = partProfileMapping.get(keyPart);
//			return iProfileFacade.getAppliedStereotypes(eObject);
//		}
//		return ECollections.emptyEList();
//	}
	
	private void showError(String message) {
		ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileApplicationRegistryUIPlugin.PLUGIN_ID, message));
	}

	private void showError(String message, Throwable throwable) {
		ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileApplicationRegistryUIPlugin.PLUGIN_ID, throwable.getMessage(),
						throwable));
	}
	
	
	final class ViewerState {
		private final Object input;
		private final Object[] viewerExpandedElements;
		private final ISelection selection;

		public ViewerState(TreeViewer viewer) {
			super();
			this.input = viewer.getInput();
			this.viewerExpandedElements = viewer.getExpandedElements();
			this.selection = viewer.getSelection();
		}
		
		public void restoreTreeViewerState(TreeViewer viewer){
			viewer.setInput(input);
			viewer.setExpandedElements(viewerExpandedElements);
			viewer.setSelection(selection, true);
		}
	}
}