/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.views;

import java.util.Collections;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofile.application.registry.ui.providers.ProfileProviderContentAdapter;
import org.modelversioning.emfprofile.application.registry.ui.providers.ProfileProviderLabelAdapter;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class EMFProfileApplicationsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.modelversioning.emfprofile.application.registry.ui.views.EMFProfileApplicationsView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private static LocalResourceManager resourceManager;
	
	/**
	 * The constructor.
	 */
	public EMFProfileApplicationsView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		// TODO remove comment
		System.out.println("CEATING VIEW");
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setLabelProvider(new ProfileProviderLabelAdapter());
//		viewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));
		viewer.setContentProvider(new ProfileProviderContentAdapter());
		
		viewer.setSorter(new ViewerSorter());
		viewer.setAutoExpandLevel(2);
		getSite().setSelectionProvider(viewer);
		EMFProfileApplicationsView.resourceManager = new LocalResourceManager(JFaceResources.getResources());
		viewer.setInput(Collections.emptyList());
		ActiveEditorObserver.INSTANCE.setViewer(viewer);
		
		// Create the help context id for the viewer's control
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.modelversioning.emfprofile.application.registry.ui.viewer");
//		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
//		contributeToActionBars();
		
		
		MenuManager menuManager = new MenuManager("profileApplicationsPopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EMFProfileApplicationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		
		getSite().registerContextMenu(menuManager, viewer);
		// Other plug-ins can contribute there actions here
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		drillDownAdapter.addNavigationActions(menuManager);
		drillDownAdapter.addNavigationActions(getViewSite().getActionBars().getToolBarManager());
		getViewSite().getActionBars().getToolBarManager().add(new Separator());
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeViewer viewer = (TreeViewer) event.getViewer();
			    IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection(); 
			    Object selectedNode = thisSelection.getFirstElement(); 
			    viewer.setExpandedState(selectedNode,
			        !viewer.getExpandedState(selectedNode));
			}
		});
		// To enable the key binding we need to activate context
		// The reason why, is because this context overrides the
		// default key binding of workbench, e.g. key DEL
		IContextService contextService = (IContextService) getSite()
				  .getService(IContextService.class);
		IContextActivation contextActivation = contextService.activateContext("org.modelversioning.emfprofile.application.registry.ui.keybindingcontext");
		
		// registers it self for notification when this view is closed
		// so that the clean-up may be done
//		getSite().getPage().addPartListener(this); TODO remove
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EMFProfileApplicationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
//	}

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(action1);
//		manager.add(new Separator());
//		manager.add(action2);
//	}

	private void fillContextMenu(IMenuManager manager) {
//		manager.add(action1);
//		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(action1);
//		manager.add(action2);
//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
//	}
//
//	private void makeActions() {
//		action1 = new Action() {
//			public void run() {
//				showMessage("Action 1 executed");
//			}
//		};
//		action1.setText("Action 1");
//		action1.setToolTipText("Action 1 tooltip");
//		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		
//		action2 = new Action() {
//			public void run() {
//				showMessage("Action 2 executed");
//			}
//		};
//		action2.setText("Action 2");
//		action2.setToolTipText("Action 2 tooltip");
//		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		doubleClickAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
//			}
//		};
//	}
//
//	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
//			}
//		});
//	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"EMF Profile Applications View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	/**
	 * After the view has been initialized this method can be used to 
	 * create an image for the descriptor.
	 * Images created in this way do not need to be extra disposed in the code.
	 * Images are created with usage of {@link LocalResourceManager} which takes care of disposal
	 * when the UI part is disposed. 
	 * @param imageDescriptor
	 * @return image or null if the image could not be located.
	 */
	public static Image createImage(ImageDescriptor imageDescriptor){
		try {
			if(EMFProfileApplicationsView.resourceManager != null)
				return EMFProfileApplicationsView.resourceManager.createImage(imageDescriptor);
		} catch (DeviceResourceException e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void dispose() {
		ActiveEditorObserver.INSTANCE.cleanUp();
		// dispose our resource manager for images
		EMFProfileApplicationsView.resourceManager.dispose();
		super.dispose();
	}

		
//	// when view is closed we have to do a clean-up before 
//	// SWT widgets are disposed
//	@Override
//	public void partClosed(IWorkbenchPart part) {
//		if(part.getSite().getId().equals(EMFProfileApplicationsView.ID)){
//			ActiveEditorObserver.INSTANCE.cleanUp();
//			getSite().getPage().removePartListener(this);
//		}
//	}
//	// ignore other events
//	@Override
//	public void partActivated(IWorkbenchPart part) {}
//	@Override
//	public void partBroughtToTop(IWorkbenchPart part) {}
//	@Override
//	public void partDeactivated(IWorkbenchPart part) {}
//	@Override
//	public void partOpened(IWorkbenchPart part) {}
}