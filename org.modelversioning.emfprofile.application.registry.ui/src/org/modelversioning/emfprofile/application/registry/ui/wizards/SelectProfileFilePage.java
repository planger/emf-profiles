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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
//import org.modelversioning.emfprofile.registry.IProfileRegistry;

/**
 * {@link WizardPage} for selecting the profile file.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class SelectProfileFilePage extends WizardPage {

	private Collection<Profile> selectedProfiles = new ArrayList<Profile>();

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
	protected SelectProfileFilePage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setMessage("Select the EMF Profile to apply.");
		updatePageCompleteState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		// create overall container
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());
		container
				.setLayoutData(new GridData(GridData.FILL_BOTH
						| GridData.VERTICAL_ALIGN_FILL
						| GridData.HORIZONTAL_ALIGN_FILL));

		final ListViewer profileList = new ListViewer(container, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.SINGLE | SWT.BORDER);
		profileList.getList().setLayoutData(new GridData(GridData.FILL_BOTH));
		profileList.setContentProvider(new ITreeContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				if (newInput instanceof Collection<?>) {
					for (Object element : ((Collection<?>) newInput)) {
						profileList.add(element);
					}
				}
			}

			@Override
			public void dispose() {
			}

			@Override
			public boolean hasChildren(Object element) {
				if (element instanceof Collection<?>) {
					return true;
				} else {
					return false;
				}
			}

			@Override
			public Object getParent(Object element) {
				return null;
			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Collection<?>) {
					return ((Collection<?>) inputElement).toArray();
				}
				return null;
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof Collection<?>) {
					return ((Collection<?>) parentElement).toArray();
				}
				return null;
			}
		});
		profileList.getList().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) profileList
						.getSelection();
				selectedProfiles.clear();
				for (Iterator<Object> iterator = selection.iterator(); iterator
						.hasNext();) {
					Object next = iterator.next();
					if (next instanceof Profile) {
						selectedProfiles.add((Profile) next);
					}
				}
				updatePageCompleteState();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		profileList.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Profile) {
					Profile profile = (Profile) element;
					return profile.getName() + " (" + profile.getNsURI() + ")";
				}
				return super.getText(element);
			}
		});
		profileList
				.setInput(IProfileRegistry.eINSTANCE.getRegisteredProfiles());

		setControl(container);
	}

	private void updatePageCompleteState() {
		if (getSelectedProfiles().size() < 1) {
			this.setErrorMessage("Select a profile to be applied.");
			setPageComplete(false);
		} else {
			this.setErrorMessage(null);
			setPageComplete(true);
		}
	}

	public Collection<Profile> getSelectedProfiles() {
		return selectedProfiles;
	}

}
