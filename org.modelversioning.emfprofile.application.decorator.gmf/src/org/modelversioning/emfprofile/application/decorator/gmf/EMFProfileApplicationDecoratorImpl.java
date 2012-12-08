/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.gmf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.notation.Node;
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
import org.modelversioning.emfprofile.application.decorator.gmf.decoration.EMFProfileDecorator;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.PluginExtensionOperationsListener;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class EMFProfileApplicationDecoratorImpl implements EMFProfileApplicationDecorator, ISelectionListener {

//	private static final String ECORE_REFLECTIVE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID"; 
//	private static final String ECORE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID"; 
	private static final String ECORE_DIAGRAM_EDITOR_ID = "org.eclipse.emf.ecoretools.diagram.part.EcoreDiagramEditorID";
	
	private static final String[] CAN_DECORATE_EDITORS = new String[]{ECORE_DIAGRAM_EDITOR_ID};
	
	private static PluginExtensionOperationsListener pluginExtensionOperationListener;
	private IEditorPart activeEditor = null;
	
	/** {@link EObject} to {@link IDecorator} map. */
	private static Map<EObject, IDecorator> eObjectToDecoratorMap = new HashMap<EObject, IDecorator>();
	
	public static PluginExtensionOperationsListener getPluginExtensionOperationsListener(){
		return EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener;
	}
	
	public IWorkbenchPage getActivePage() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(window == null)
			throw new RuntimeException("could not locate workbench active window!");
		IWorkbenchPage activePage = window.getActivePage();
		if(activePage == null)
			throw new RuntimeException("could not locate active page for active window ");
		return activePage;
	}
	/**
	 * default constructor
	 */
	public EMFProfileApplicationDecoratorImpl() {
		// Initialize active editor selection pluginExtensionOperationListener for notification of selection to extended plug-in.
		// This is needed for tree view filter, when clicking around to know which element is selected.
		IWorkbenchPage activePage = getActivePage();
		IEditorPart editorPart = activePage.getActiveEditor();
		if(editorPart != null){
			if(editorPart.getSite().getId().equals(EMFProfileApplicationDecoratorImpl.ECORE_DIAGRAM_EDITOR_ID)){
				activeEditor = editorPart;
				activeEditor.getSite().getPage().addSelectionListener(EMFProfileApplicationDecoratorImpl.ECORE_DIAGRAM_EDITOR_ID, this);
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
				if(part instanceof IEditorPart && part.equals(activeEditor)){
					activeEditor.getSite().getPage().removeSelectionListener(EMFProfileApplicationDecoratorImpl.this);
				}
			}
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				// ignore
			}
			@Override
			public void partActivated(IWorkbenchPart part) {
				if(part instanceof IEditorPart && part.getSite().getId().equals(EMFProfileApplicationDecoratorImpl.ECORE_DIAGRAM_EDITOR_ID)){
					if(activeEditor != null){
						activeEditor.getSite().getPage().removeSelectionListener(EMFProfileApplicationDecoratorImpl.this);
					}
					activeEditor = (IEditorPart) part;
					activeEditor.getSite().getPage().addSelectionListener(EMFProfileApplicationDecoratorImpl.this);
				}
			}
		});
	}

	@Override
	public String[] canDecorateEditorParts() {
		return EMFProfileApplicationDecoratorImpl.CAN_DECORATE_EDITORS;
	}

	@Override
	public void setPluginExtensionOperationsListener(PluginExtensionOperationsListener listener) {
		EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener = listener;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(EMFProfileApplicationDecoratorImpl.pluginExtensionOperationListener != null 
				&& part instanceof IEditorPart && part.equals(activeEditor)){
			if(selection != null && selection instanceof IStructuredSelection){
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				EditPart editPart = (EditPart) structuredSelection.getFirstElement();
				Object model = editPart.getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					EObject selectedEObject = node.getElement();
					EMFProfileApplicationDecoratorImpl.getPluginExtensionOperationsListener().eObjectSelected(selectedEObject);
				}else{
					EMFProfileApplicationDecoratorImpl.getPluginExtensionOperationsListener().eObjectSelected(null);
				}
			}
		}
	}

	public static void registerDecoratorForEObject(EObject eObject, IDecorator decorator){
		// TODO remove comment
				System.out.println("Activate decorator for eObject: " + eObject);
		EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap.put(eObject, decorator);
	}
	
	public static void unregisterDecoratorForEObject(EObject eObject){
		// TODO remove comment
				System.out.println("Deactivate decorator for eObject: " + eObject);
		EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap.remove(eObject);
	}

	@Override
	public void decorate(EObject eObject, List<Image> images,
			List<String> toolTipTexts) {
		if(eObject == null || images == null || toolTipTexts == null)
			throw new IllegalArgumentException("Parametrs can not have null value.");
		if(EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap.containsKey(eObject)){
			// TODO remove comment
			System.out.println("Decorating eObject: " + eObject);
			EMFProfileDecorator decorator = (EMFProfileDecorator) EMFProfileApplicationDecoratorImpl.eObjectToDecoratorMap.get(eObject);
			decorator.refresh(images, toolTipTexts);
		}
	}
}
