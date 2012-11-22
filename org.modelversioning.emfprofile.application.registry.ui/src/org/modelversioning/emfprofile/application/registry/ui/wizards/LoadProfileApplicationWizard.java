/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.emfprofile.application.registry.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;
import org.modelversioning.emfprofile.application.registry.ui.EMFProfileApplicationRegistryUIPlugin;
import org.modelversioning.emfprofile.application.registry.ui.ProfileApplicationConstsAndUtil;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofileapplication.ProfileApplication;

/**
 * Wizard for collecting necessary information to import a
 * {@link ProfileApplication}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class LoadProfileApplicationWizard extends Wizard {

	private static final String PROFILE_APPLICATION_PAGE_NAME = "newFilePage1"; //$NON-NLS-1$
	private static final String WINDOW_TITLE = "Select Profile Application File";

	private IWorkbenchPart targetPart = null;
	private SelectProfileApplicationFilePage profileAppFilePage = null;
	private ISelection selection;

	/**
	 * The default constructor
	 */
	public LoadProfileApplicationWizard() {
		setHelpAvailable(false);
		setWindowTitle(WINDOW_TITLE);
	}

	/**
	 * Calls the {@link ProfileApplicationRegistry} to load the profile application.
	 */
	@Override
	public boolean performFinish() {
		IFile profileApplicationFile = profileAppFilePage.getSelectedFile();
		try {
			String modelId = ActiveEditorObserver.INSTANCE.getModelIdForWorkbenchPart(targetPart);
			if(modelId == null)
				throw new RuntimeException("Could not find modelId for a part: " + targetPart);
			boolean success = ProfileApplicationRegistry.INSTANCE.loadProfileApplicationForModel(modelId, profileApplicationFile, ProfileApplicationConstsAndUtil.getResourceSet(targetPart));
			if(! success){
				org.eclipse.jface.dialogs.MessageDialog.openInformation(targetPart.getSite().getShell(), "Profile Application already loaded", "Profile Application already loaded from file: " 
						+ profileApplicationFile.getLocation().toString());
				return false;
			}
			ActiveEditorObserver.INSTANCE.refreshViewer();
		} catch (Exception e) {
			IStatus status = new Status(IStatus.ERROR, EMFProfileApplicationRegistryUIPlugin.PLUGIN_ID,
					e.getMessage(), e);
			ErrorDialog.openError(targetPart.getSite().getShell(),
					"Error Loading Profile Application", e.getMessage(), status);
			EMFProfileApplicationRegistryUIPlugin.getDefault().getLog().log(status);
		}		
		return true;
	}

	/**
	 * Adds the {@link SelectProfileFilePage} and the
	 * {@link SelectProfileApplicationFilePage}.
	 */
	@Override
	public void addPages() {
		super.addPages();
		profileAppFilePage = new SelectProfileApplicationFilePage(
				PROFILE_APPLICATION_PAGE_NAME,
				"Select Profile Application File", null);
//		profileAppFilePage.setSelection(getNewSelection());
		super.addPage(profileAppFilePage);
	}

	/**
	 * Sets the initial selection.
	 * 
	 * @param selection
	 *            to set.
	 */
	private IStructuredSelection getNewSelection() {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;
			if (iStructuredSelection.getFirstElement() != null
					&& iStructuredSelection.getFirstElement() instanceof EObject) {
				EObject eObject = (EObject) iStructuredSelection
						.getFirstElement();
				if (eObject.eResource() != null) {
					IFile file = ResourcesPlugin
							.getWorkspace()
							.getRoot()
							.getFile(
									new Path(eObject.eResource().getURI()
											.toPlatformString(true)));
					return new StructuredSelection(file);
				}
			}
			return (IStructuredSelection) selection;
		}
		return new StructuredSelection();
	}

	/**
	 * Sets the initial selection.
	 * 
	 * @param selection
	 *            to set.
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	/**
	 * Sets the workbench part to use for profile application creation.
	 * 
	 * @param targetPart
	 *            to set.
	 */
	public void setWorkbenchPart(IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}
}
