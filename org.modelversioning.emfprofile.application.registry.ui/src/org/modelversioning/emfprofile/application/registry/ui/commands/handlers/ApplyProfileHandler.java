/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler.EMFProfileApplicationDecoratorHandler;
import org.modelversioning.emfprofile.application.registry.ui.wizards.ApplyProfileWizard;


/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ApplyProfileHandler extends AbstractHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.commands.IHandler#execute(java.util.Map)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IEditorPart activeEditor = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor();
		if(activeEditor == null){
			MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "No model resource is opened!", "Before you can apply profiles you have to open a model resource in editor.");
		}else{
			if(EMFProfileApplicationDecoratorHandler.getInstance().hasDecoratorForEditorPart(activeEditor)){
				ApplyProfileWizard wizard = new ApplyProfileWizard();
				wizard.setWorkbenchPart(activeEditor);
				wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection)HandlerUtil.getActivePart(event).getSite().getSelectionProvider().getSelection());
				WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				wizardDialog.open();
			}else{
				MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Can not applay a profile to this resource!", 
						"You can not apply a profile to the resource opened in current active editor:\n" + activeEditor.getTitle());
			}
		}

		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
//	private IWorkbenchPart targetPart;
//
//	/**
//	 * Constructor for Action1.
//	 */
//	public ApplyProfileAction() {
//		super();
//	}
//
//	/**
//	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
//	 */
//	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
//		this.targetPart = targetPart;
//	}
//
//	/**
//	 * @see IActionDelegate#run(IAction)
//	 */
//	public void run(IAction action) {
//		ApplyProfileWizard wizard = new ApplyProfileWizard();
//		wizard.init(PlatformUI.getWorkbench(),
//				(IStructuredSelection) targetPart.getSite()
//						.getSelectionProvider().getSelection());
//		wizard.setWorkbenchPart(targetPart);
//		WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench()
//				.getActiveWorkbenchWindow().getShell(), wizard);
//		wizardDialog.open();
//	}
//
//	/**
//	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
//	 */
//	public void selectionChanged(IAction action, ISelection selection) {
//		if (targetPart == null) {
//			action.setEnabled(false);
//			return;
//		}
//		ISelection realSelection = targetPart.getSite().getSelectionProvider()
//				.getSelection();
//		if (realSelection instanceof StructuredSelection) {
//			StructuredSelection structuredSelection = (StructuredSelection) realSelection;
//			Object selectedObject = structuredSelection.getFirstElement();
//			if (selectedObject instanceof EObject) {
//				action.setEnabled(true);
//				return;
//			} else if (selectedObject instanceof EditPart) {
//				EditPart editPart = (EditPart) selectedObject;
//				Object model = editPart.getModel();
//				if (model instanceof Node) {
//					action.setEnabled(true);
//					return;
//				}
//			}
//
//		}
//		action.setEnabled(false);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * Adds a new profile application for the {@link Profile} in the supplied
//	 * <code>profileFile</code>.
//	 * 
//	 * @param workbenchPart
//	 *            to use.
//	 * @param profileApplicationFile
//	 *            to save profile application in.
//	 * @param profiles
//	 *            the profiles to load.
//	 */
//	public void addNewProfileApplication(IWorkbenchPart workbenchPart,
//			IFile profileApplicationFile, Collection<Profile> profiles) {
//		try {
//			profileApplicationFile.refreshLocal(1, new NullProgressMonitor());
//
//			IProfileFacade facade = createNewProfileFacade(workbenchPart,
//					profileApplicationFile);
//			for (Profile profile : profiles) {
//				facade.loadProfile(profile);
//			}
//			if (workbenchPart instanceof DiagramEditor) {
//				DiagramEditor diagramEditor = (DiagramEditor) workbenchPart;
//				DiagramEditPart diagramEditPart = diagramEditor
//						.getDiagramEditPart();
//				RootEditPart rootEditPart = diagramEditPart.getRoot();
//				getProfileApplicationView().addToView(rootEditPart, facade,
//						profileApplicationFile);
//			} else {
//				getProfileApplicationView().addToView(workbenchPart, facade,
//						profileApplicationFile);
//			}
//		} catch (Exception e) {
//			IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
//					e.getMessage(), e);
//			ErrorDialog.openError(workbenchPart.getSite().getShell(),
//					"Error While Applying Profile", e.getMessage(), status);
//			getLog().log(status);
//		}
//	}

}
