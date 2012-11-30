/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.gmf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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
public class EcoreDiagramEditorDecorator implements EMFProfileApplicationDecorator, ISelectionListener {

//	private static final String ECORE_REFLECTIVE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID"; 
//	private static final String ECORE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID"; 
	private static final String ECORE_DIAGRAM_EDITOR_ID = "org.eclipse.emf.ecoretools.diagram.part.EcoreDiagramEditorID";
	
	private static PluginExtensionOperationsListener listener;
	private IEditorPart activeEditor = null;
	
	public static PluginExtensionOperationsListener getApplyStereotypeListener(){
		return EcoreDiagramEditorDecorator.listener;
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
	 * 
	 */
	public EcoreDiagramEditorDecorator() {
		// initilize active editor selection listener for notification of selection to plugin extension listener
		
		IWorkbenchPage activePage = getActivePage();
		IEditorPart editorPart = activePage.getActiveEditor();
		if(editorPart != null){
			if(editorPart.getSite().getId().equals(EcoreDiagramEditorDecorator.ECORE_DIAGRAM_EDITOR_ID)){
				activeEditor = editorPart;
				activeEditor.getSite().getPage().addSelectionListener(EcoreDiagramEditorDecorator.ECORE_DIAGRAM_EDITOR_ID, this);
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
					activeEditor.getSite().getPage().removeSelectionListener(EcoreDiagramEditorDecorator.this);
				}
				
			}
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				// ignore
			}
			
			@Override
			public void partActivated(IWorkbenchPart part) {
				if(part instanceof IEditorPart && part.getSite().getId().equals(EcoreDiagramEditorDecorator.ECORE_DIAGRAM_EDITOR_ID)){
					if(activeEditor != null){
						activeEditor.getSite().getPage().removeSelectionListener(EcoreDiagramEditorDecorator.this);
					}
					activeEditor = (IEditorPart) part;
					activeEditor.getSite().getPage().addSelectionListener(EcoreDiagramEditorDecorator.this);
				}
				
			}
		});
	}


	@Override
	public String[] canDecorateEditorParts() {
		return new String[]{ECORE_DIAGRAM_EDITOR_ID};
	}


	@Override
	public void setPluginExtensionOperationsListener(PluginExtensionOperationsListener listener) {
		EcoreDiagramEditorDecorator.listener = listener;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(part instanceof IEditorPart && part.equals(activeEditor)){
			if(selection != null && selection instanceof IStructuredSelection){
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				EditPart editPart = (EditPart) structuredSelection.getFirstElement();
				Object model = editPart.getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					EObject selectedEObject = node.getElement();
					EcoreDiagramEditorDecorator.getApplyStereotypeListener().eObjectSelected(selectedEObject);
				}else{
					EcoreDiagramEditorDecorator.getApplyStereotypeListener().eObjectSelected(null);
				}
			}
			
		}
	}


}
