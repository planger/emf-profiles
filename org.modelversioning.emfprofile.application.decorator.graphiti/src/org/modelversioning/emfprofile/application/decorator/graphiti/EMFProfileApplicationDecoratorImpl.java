/**
 * Copyright (c) 2013 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.graphiti;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.PluginExtensionOperationsListener;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class EMFProfileApplicationDecoratorImpl implements
		EMFProfileApplicationDecorator, ISelectionListener {

	private final List<String> decorateableEditors = new ArrayList<String>();

	private static PluginExtensionOperationsListener pluginExtensionOperationListener;
	private IEditorPart activeEditor = null;

	/**
	 * 
	 */
	public EMFProfileApplicationDecoratorImpl() {
		decorateableEditors.add("org.eclipse.graphiti.ui.editor.DiagramEditor");
//		obtainRegisteredGMFEditors();
//		IConfigurationElement[] config = Platform.getExtensionRegistry()
//				.getConfigurationElementsFor("org.eclipse.ui.editors"); //$NON-NLS-1$
//		// TODO remove
//		System.out.println("Getting all registered editors: ");
//		for (IConfigurationElement e : config) {
//			System.out.println(e.getDeclaringExtension().getSimpleIdentifier());
////			if (declaresGMFBasedEditor(e)) {
////				String editorID = e.getAttribute("id"); //$NON-NLS-1$
////				if (editorID != null)
////					decorateableEditors.add(editorID);
////			}
//		}
//		
		getActiveEditorAndCheckWhetherToDecorate();
	}

	/* (non-Javadoc)
	 * @see org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator#canDecorateEditorParts()
	 */
	@Override
	public String[] canDecorateEditorParts() {
		return decorateableEditors.toArray(new String[decorateableEditors.size()]);
	}

	/* (non-Javadoc)
	 * @see org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator#setPluginExtensionOperationsListener(org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.PluginExtensionOperationsListener)
	 */
	@Override
	public void setPluginExtensionOperationsListener(
			PluginExtensionOperationsListener listener) {
		EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener = listener;
	}
	
	public static PluginExtensionOperationsListener getPluginExtensionOperationsListener() {
		return EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener;
	}

	/* (non-Javadoc)
	 * @see org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator#decorate(org.eclipse.emf.ecore.EObject, java.util.List, java.util.List)
	 */
	@Override
	public void decorate(EObject eObject, List<Image> images,
			List<String> toolTipTexts) {
		// TODO have to implement decoration for graphiti editor elements
		if (eObject == null || images == null || toolTipTexts == null)
			throw new IllegalArgumentException(
					"Parametrs can not have null value.");
//		if (EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap
//				.containsKey(eObject)) {
//			EMFProfileDecorator decorator = (EMFProfileDecorator) EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap
//					.get(eObject);
//			decorator.refresh(images, toolTipTexts);
//		}
		System.out.println("Decorating: " + eObject + ", with " + images.size() +" image(s)");
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener != null
				&& part instanceof IEditorPart && part.equals(activeEditor)) {
			if (selection != null && selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Iterator<?> selectionElements = ((IStructuredSelection) selection).iterator();
				while (selectionElements.hasNext()) {
					Object se = selectionElements.next();
					if(se instanceof EditPart){
						Object object = ((EditPart)se).getModel();
						EObject robj = (EObject) object;
						if (robj instanceof PictogramElement) {
							PictogramElement pe = (PictogramElement) robj;
							EObject businessObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
							if (businessObject == null) {
								EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener.eObjectSelected(null);
							} else {
								System.out.println("Found EObject: " + businessObject.toString());
								EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener.eObjectSelected(businessObject);
							
							}
						}
					}
//					if (se instanceof GraphitiShapeEditPart && !selectionElements.hasNext()) {					
//						PictogramElement pe = ((GraphitiShapeEditPart) se).getPictogramElement();
//						Object bo = MyFeatureProvider.getbusinessobject(pe);
//						if (bo instanceof MybusinessClass) {
//							// whatever
//						}
							
//					}
				}
//				if(activeEditor instanceof DiagramEditor){
//					DiagramEditor de = (DiagramEditor) activeEditor;
//					de.getDiagramTypeProvider().
//					de.getDiagramTypeProvider().getFeatureProvider().getb
//				}
				System.out.println(structuredSelection);
//				EditPart editPart = (EditPart) structuredSelection
//						.getFirstElement();
//				Object model = editPart.getModel();
//				if (model instanceof Node) {
//					Node node = (Node) model;
//					EObject selectedEObject = node.getElement();
//					EMFProfileApplicationDecoratorImpl
//							.getPluginExtensionOperationsListener()
//							.eObjectSelected(selectedEObject);
//				} else {
//					EMFProfileApplicationDecoratorImpl
//							.getPluginExtensionOperationsListener()
//							.eObjectSelected(null);
//				}
			}
		}
	}
	
	private void getActiveEditorAndCheckWhetherToDecorate() {
		// Initialize active editor selection pluginExtensionOperationListener
		// for notification of selection to extended plug-in.
		// This is needed for tree view filter, when clicking around to know
		// which element is selected.
		IWorkbenchPage activePage = getActivePage();
		IEditorPart editorPart = activePage.getActiveEditor();
		if (editorPart != null) {
			String editorId = getEditorId(editorPart);
			if (isDecorateable(editorId)) {
				activeEditor = editorPart;
				activeEditor.getSite().getPage()
						.addSelectionListener(editorId, this);
			}
		}
		activePage.addPartListener(new IPartListener() {
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
				if (part instanceof IEditorPart && part.equals(activeEditor)) {
					activeEditor
							.getSite()
							.getPage()
							.removeSelectionListener(
									EMFProfileApplicationDecoratorImpl.this);
				}
			}

			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				// ignore
			}

			@Override
			public void partActivated(IWorkbenchPart part) {
				if (part instanceof IEditorPart) {
					IEditorPart editorPart = (IEditorPart) part;
					if (isDecorateable(getEditorId(editorPart))) {
						if (activeEditor != null) {
							activeEditor
									.getSite()
									.getPage()
									.removeSelectionListener(
											EMFProfileApplicationDecoratorImpl.this);
						}
						activeEditor = (IEditorPart) part;
						activeEditor
								.getSite()
								.getPage()
								.addSelectionListener(
										EMFProfileApplicationDecoratorImpl.this);
					}
				}
			}
		});
	}
	
	private IWorkbenchPage getActivePage() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window == null)
			throw new RuntimeException(
					"could not locate workbench active window!");
		IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null)
			throw new RuntimeException(
					"could not locate active page for active window ");
		return activePage;
	}
	
	private String getEditorId(IEditorPart editorPart) {
		return editorPart.getSite().getId();
	}

	private boolean isDecorateable(String editorId) {
		return decorateableEditors.contains(editorId);
	}
}
