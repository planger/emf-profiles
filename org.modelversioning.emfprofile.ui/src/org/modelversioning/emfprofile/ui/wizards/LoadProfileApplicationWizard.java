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

package org.modelversioning.emfprofile.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;
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
	private static final String WINDOW_TITLE = "Select Proflie Application File";

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
	 * Calls the {@link EMFProfileUIPlugin} to apply the profile.
	 */
	@Override
	public boolean performFinish() {
		IFile profileApplicationFile = profileAppFilePage.getSelectedFile();
		EMFProfileUIPlugin.getDefault().loadProfileApplication(targetPart,
				profileApplicationFile);
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
		profileAppFilePage.setSelection(getNewSelection());
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
