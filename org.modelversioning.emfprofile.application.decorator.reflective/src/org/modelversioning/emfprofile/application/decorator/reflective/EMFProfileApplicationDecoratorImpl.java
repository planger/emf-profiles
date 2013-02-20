/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.reflective;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class EMFProfileApplicationDecoratorImpl implements
		EMFProfileApplicationDecorator, ISelectionListener {
	private static final String ECORE_REFLECTIVE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID";
	private static final String ECORE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID";

	private static final String[] CAN_DECORATE_EDITORS = new String[] {
			ECORE_REFLECTIVE_EDITOR_ID, ECORE_EDITOR_ID };

	private static PluginExtensionOperationsListener pluginExtensionOperationsListener;

	private IEditorPart activeEditor = null;

	public EMFProfileApplicationDecoratorImpl() {
		getActiveEditorAndCheckWhetherToDecorate();
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

	public IWorkbenchPage getActivePage() {
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
		for (String id : CAN_DECORATE_EDITORS) {
			if (id.equals(editorId))
				return true;
		}
		return false;
	}

	@Override
	public String[] canDecorateEditorParts() {
		return CAN_DECORATE_EDITORS;
	}

	@Override
	public void setPluginExtensionOperationsListener(
			PluginExtensionOperationsListener listener) {
		pluginExtensionOperationsListener = listener;
	}

	@Override
	public void decorate(EObject eObject, List<Image> images,
			List<String> toolTipTexts) {
		// TODO Auto-generated method stub

	}

	public static PluginExtensionOperationsListener getPluginExtensionOperationsListener() {
		return pluginExtensionOperationsListener;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (pluginExtensionOperationsListener != null
				&& part instanceof IEditorPart && part.equals(activeEditor)) {
			if (selection != null && selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object element = structuredSelection.getFirstElement();
				if (element instanceof EObject) {
					EObject selectedEObject = (EObject) element;
					EMFProfileApplicationDecoratorImpl
							.getPluginExtensionOperationsListener()
							.eObjectSelected(selectedEObject);
				} else {
					EMFProfileApplicationDecoratorImpl
							.getPluginExtensionOperationsListener()
							.eObjectSelected(null);
				}
			}
		}
	}

}
