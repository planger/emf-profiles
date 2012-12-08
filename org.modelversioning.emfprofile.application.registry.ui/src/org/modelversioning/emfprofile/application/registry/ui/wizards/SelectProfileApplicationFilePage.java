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

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.modelversioning.emfprofile.application.registry.ui.ProfileApplicationConstantsAndUtil;

/**
 * {@link WizardPage} for selecting the profile application file.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class SelectProfileApplicationFilePage extends WizardPage {

	private static final String PROFILE_FILE = "PROFILE_FILE";
	/**
	 * The file selection field.
	 */
	private FileFieldEditor fileFieldEditor;
	/**
	 * The selection.
	 */
	private ISelection selection = null;

	/**
	 * Constructs a new page.
	 * 
	 * @param pageName
	 *            page name.
	 * @param title
	 *            title.
	 * @param titleImage
	 *            the title image.
	 */
	protected SelectProfileApplicationFilePage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setMessage("Select the EMF Profile Application file.");
		updatePageCompleteState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		// create overall container
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		fileFieldEditor = new FileFieldEditor(PROFILE_FILE, "EMF Profile Application File",
				container);
		if (selection != null
				&& !selection.isEmpty()
				&& selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).getFirstElement() instanceof IFile) {
			Object object = ((IStructuredSelection) selection)
					.getFirstElement();
			File file = new File(((IFile) object).getParent().getLocationURI());
			fileFieldEditor.setFilterPath(file);
		} else {
			fileFieldEditor.setFilterPath(new File(ResourcesPlugin
					.getWorkspace().getRoot().getLocationURI()));
		}
		((FileFieldEditor) fileFieldEditor)
				.setFileExtensions(new String[] { "*." + ProfileApplicationConstantsAndUtil.EMF_PROFILE_APPLICATION_FILE_EXTENSION }); //$NON-NLS-1$
		fileFieldEditor.getTextControl(container).addModifyListener(
				new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						updatePageCompleteState();
					}
				});
		fileFieldEditor.setEmptyStringAllowed(false);
	}

	/**
	 * Returns the selected profile file.
	 * 
	 * @return the selected profile file.
	 */
	public IFile getSelectedFile() {
		IFile file = null;
		if (fileFieldEditor != null) {
			file = ResourcesPlugin
					.getWorkspace()
					.getRoot()
					.getFileForLocation(
							new Path(fileFieldEditor.getStringValue()));
		}
		return file;
	}

	/**
	 * Sets the {@link #setPageComplete(boolean)} value.
	 */
	private void updatePageCompleteState() {
		IFile file = getSelectedFile();
		if (file == null) {
			this.setErrorMessage("Select profile application file.");
			setPageComplete(false);
		} else if (!file.exists()) {
			this.setErrorMessage("Selected file does not exist");
			setPageComplete(false);
		} else {
			this.setErrorMessage(null);
			setPageComplete(true);
		}
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection
	 *            to set.
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

}
