/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.observer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.commands.Command;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.eclipse.ui.services.ISourceProviderService;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.EMFProfileApplicationRegistryUIPlugin;
import org.modelversioning.emfprofile.application.registry.ui.commands.handlers.StereotypeApplicationsOnSelectedElementHandler;
import org.modelversioning.emfprofile.application.registry.ui.commands.sourceprovider.ToolbarCommandEnabledState;
import org.modelversioning.emfprofile.application.registry.ui.dialogs.ApplyStereotypeOnEObjectDialog;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.PluginExtensionOperationsListener;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;
import org.modelversioning.emfprofile.application.registry.ui.views.filters.StereotypesOfEObjectViewerFilter;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;

/**
 * It manages mapping of opened editors of interest to 
 * the generated id for an opened model in editor.
 * It is also a {@link PluginExtensionOperationsListener}.
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ActiveEditorObserver implements PluginExtensionOperationsListener {
	
	public static ActiveEditorObserver INSTANCE = new ActiveEditorObserver();
	
	private Map<IWorkbenchPart, String> editorPartToModelIdMap = Collections.synchronizedMap(new HashMap<IWorkbenchPart, String>());
	private IWorkbenchPart lastActiveEditorPart = null;
	
	private IWorkbenchPage activePage;
	private EMFProfileApplicationDecoratorHandler decoratorHandler;
	private TreeViewer viewer;
	private ToolbarCommandEnabledState toolbarCommandEnabeldStateService;
	private StereotypesOfEObjectViewerFilter viewerFilter = new StereotypesOfEObjectViewerFilter(null);
	private boolean viewerFilterActivated = false;
	
	// hide default constructor
	private ActiveEditorObserver(){
	}
	
	public IWorkbenchPart getLastActiveEditorPart(){
		return lastActiveEditorPart;
	}
	
	/**
	 * To set the Tree Viewer from outside.
	 * After calling this method a part listener will be added on active page
	 * which registers activation of editors that can be decorated. </br>
	 * <b>Note:</b> without setting a tree viewer none of the services of this
	 * class implementation will work properly. 
	 * @param viewer
	 */
	public void setViewer(TreeViewer viewer){
		
		decoratorHandler = EMFProfileApplicationDecoratorHandler.getInstance();
		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window == null)
			throw new RuntimeException("could not locate workbench active window!");

		 // Get the source provider service
	    ISourceProviderService sourceProviderService = (ISourceProviderService) window.getService(ISourceProviderService.class);
	    // Now get the service for enabling/disenabling menu commands in viewer toolbar 
	    toolbarCommandEnabeldStateService = (ToolbarCommandEnabledState) sourceProviderService
	        .getSourceProvider(ToolbarCommandEnabledState.MY_STATE);
		activePage = window.getActivePage();
		if(activePage == null)
			throw new RuntimeException("could not locate active page for active window ");
		
		this.viewer = viewer;
				
		// getting the value of the view command for activating/deactivating view filter 
	    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
	    Command cmd = commandService.getCommand(StereotypeApplicationsOnSelectedElementHandler.COMMAND_ID);
	    setActivateViewFilter((Boolean)cmd.getState(RegistryToggleState.STATE_ID).getValue());
	    
//		When the plug-in starts, we should check if there is an active editor and if it can be decorated
		IEditorPart editorPart = activePage.getActiveEditor();
		if(editorPart != null){
			if(decoratorHandler.hasDecoratorForEditorPart(editorPart)){
				// Create an id for workbench part and put it into map
				editorPartToModelIdMap.put(editorPart, UUID.randomUUID().toString());
				lastActiveEditorPart = editorPart;
				// if there is editor of interest active, then enable commands of the view toolbar
				if(lastActiveEditorPart == null)
					toolbarCommandEnabeldStateService.setEnabled(false); 
				else
					toolbarCommandEnabeldStateService.setEnabled(true);
				// TODO remove
				System.out.println("part opened at the start, size: "+editorPartToModelIdMap.size());
			}
		}
		
		// listener that registers editors of interest
		activePage.addPartListener(new DecoratableEditorPartListener(decoratorHandler, editorPartToModelIdMap, lastActiveEditorPart, viewer, toolbarCommandEnabeldStateService));

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
		viewer.expandToLevel(2);
	}
	
	/**
	 * Collection can be provided as parameter.
	 * Refresh will be executed asynchronously for whole group of collection items in one runnable.
	 * @param object
	 */
	public void refreshViewer(final Object object){
		viewer.getTree().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if(object instanceof Collection<?>){
					Iterator<?> iterator = ((Collection<?>) object).iterator();
					while(iterator.hasNext())
						viewer.refresh(iterator.next());
				}else{
					viewer.refresh(object);
				}
				viewer.expandToLevel(2);
			}
		});
		
	}
	
	/**
	 * Updates the element of the viewer in a new UI thread.
	 * @param element
	 */
	public void updateViewer(final Object element){
		viewer.getTree().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				viewer.update(element, null);
			}
		});
		
	}
	
	public String getModelIdForWorkbenchPart(IWorkbenchPart part) {
		return editorPartToModelIdMap.get(part);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyStereotype(final EObject eObject) {
		Assert.isNotNull(eObject);
		// we are looking in all loaded profiles if there are any stereotypes applicable on eObject 
		final Map<ProfileApplicationDecorator, Collection<StereotypeApplicability>> profileToStereotypeApplicabilityForEObjectMap = new HashMap<>();
		for (ProfileApplicationDecorator profileApplication : ProfileApplicationRegistry.INSTANCE.getProfileApplications(editorPartToModelIdMap.get(lastActiveEditorPart))) {
			// TODO REmove comment
			System.out.println("Loaded Profile: "+profileApplication.getFirstProfileName() + ", applicable stereotypes count: " + profileApplication.getImportedProfiles().get(0).getProfile().getStereotypes());
			profileToStereotypeApplicabilityForEObjectMap.put(profileApplication, (Collection<StereotypeApplicability>) profileApplication.getApplicableStereotypes(eObject));
		}
		boolean mayApplyStereotype = false;
		for (Collection<?> stereotypesApplicabilities : profileToStereotypeApplicabilityForEObjectMap.values()) {
			if( ! stereotypesApplicabilities.isEmpty()){
				mayApplyStereotype = true;
				break;
			}
		}
		if (mayApplyStereotype) {
			ApplyStereotypeOnEObjectDialog helper = new ApplyStereotypeOnEObjectDialog(profileToStereotypeApplicabilityForEObjectMap);
			helper.openApplyStereotypeDialog(eObject);
		} else {
			MessageDialog.openInformation(viewer.getControl().getShell(), "Info", "Can not apply any stereotype to EObject with name: " + ((ENamedElement)eObject).getName() );
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eObjectSelected(EObject eObject) {	
		if(eObject == null){
			if(viewerFilter.getSelectedEObject() != null){
				viewerFilter.setSelectedEObject(eObject);
				if(viewerFilterActivated)
					refreshViewer();
			}
		}else if( ! eObject.equals(viewerFilter.getSelectedEObject())){
			viewerFilter.setSelectedEObject(eObject);
			if(viewerFilterActivated)
				refreshViewer();
		}
	}
	
	public void setActivateViewFilter(boolean activateFilter) {
		this.viewerFilterActivated = activateFilter;
		if(activateFilter)
			viewer.addFilter(viewerFilter);
		else
			viewer.removeFilter(viewerFilter);
		viewer.expandToLevel(2);
	}
	
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

}