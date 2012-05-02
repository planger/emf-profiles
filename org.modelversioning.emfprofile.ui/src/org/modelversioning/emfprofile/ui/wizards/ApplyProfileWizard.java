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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;

/**
 * Wizard for collecting necessary information to apply a {@link Profile}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ApplyProfileWizard extends BasicNewFileResourceWizard {

	private static final String PROFILE_APPLICATION_PAGE_NAME = "newFilePage1"; //$NON-NLS-1$
	private static final String SELECT_PROFILE_PAGE_NAME = "selectProfilePage"; //$NON-NLS-1$
	private static final String WINDOW_TITLE = "Apply Proflie";

	private SelectProfileFilePage profileFilePage = null;
	private IWorkbenchPart targetPart = null;
	private WizardNewFileCreationPage profileAppFilePage = null;

	/**
	 * The default constructor
	 */
	public ApplyProfileWizard() {
		setHelpAvailable(false);
		setWindowTitle(WINDOW_TITLE);
	}

	/**
	 * Calls the {@link EMFProfileUIPlugin} to apply the profile.
	 */
	@Override
	public boolean performFinish() {
		IPath appContainerFullPath = profileAppFilePage.getContainerFullPath();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile profileApplicationFile = root.getFile(appContainerFullPath
				.append(profileAppFilePage.getFileName()));
		EMFProfileUIPlugin.getDefault().addNewProfileApplication(targetPart,
				profileApplicationFile, profileFilePage.getSelectedProfiles());
		return true;
	}

	/**
	 * Adds the {@link SelectProfileFilePage} and the
	 * {@link SelectProfileApplicationFilePage}.
	 */
	@Override
	public void addPages() {
		// super adds file selection page
		super.addPages();
		profileAppFilePage = (WizardNewFileCreationPage) super
				.getPage(PROFILE_APPLICATION_PAGE_NAME);
		profileAppFilePage.setDescription("Profile Application File");
		profileAppFilePage
				.setTitle("Select location and file name for the profile application file.");
		profileAppFilePage
				.setFileExtension(EMFProfileUIPlugin.EMF_PROFILE_APPLICATION_EXTENSION);
		profileAppFilePage.setFileName("application"); //$NON-NLS-1$
		// add selectProfileFilePage
		profileFilePage = new SelectProfileFilePage(SELECT_PROFILE_PAGE_NAME,
				"Select Profile File", null);
		super.addPage(profileFilePage);
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
