/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.project.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;

/**
 * Wizard for creating new EMF Profile projects.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ProfileProjectNewWizard extends Wizard implements INewWizard {

	private ProfileProjectData projectData;
	private IWorkbench workbench;

	protected ProfileProjectNewPage mainPage;

	public ProfileProjectNewWizard() {
		setWindowTitle("New EMF Profile Project");
		setNeedsProgressMonitor(true);
		projectData = new ProfileProjectData();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		mainPage = new ProfileProjectNewPage("main", projectData, selection); //$NON-NLS-1$
		mainPage.setTitle("EMF Profile Project");
		mainPage.setDescription("Create a new EMF Profile project.");
		this.addPage(mainPage);
	}

	@Override
	public boolean performFinish() {
		mainPage.updateData();

		try {
			createEMFProfileProject();
			addToWorkingSets();
			return true;
		} catch (InvocationTargetException e) {
			System.err.println(e.getMessage());
//			EMFProfileUIPlugin.logException(e);
		} catch (InterruptedException e) {
		}

		return false;
	}

	private void createEMFProfileProject() throws InvocationTargetException,
			InterruptedException {
		getContainer().run(false, true,
				new NewProfileProjectOperation(projectData));
	}

	private void addToWorkingSets() {
		IWorkingSet[] workingSets = mainPage.getSelectedWorkingSets();
		if (workingSets.length > 0)
			workbench.getWorkingSetManager().addToWorkingSets(
					mainPage.getProjectHandle(), workingSets);
	}

}
