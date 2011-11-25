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

package org.modelversioning.emfprofile.ui.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;

/**
 * Registers a profile.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class RegisterProfileAction implements IObjectActionDelegate {

	private IFile selectedFile;
	private Shell shell;

	@Override
	public void run(final IAction action) {
		try {
			EMFProfileUIPlugin.getDefault().getWorkbench().getProgressService()
					.run(false, false, new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							doRun(action, monitor);

						}
					});
		} catch (InvocationTargetException e) {
			showError(e);
		} catch (InterruptedException e) {
			showError(e);
		}
	}

	/**
	 * Registers the currently selected profile file.
	 * 
	 * @param action
	 *            to process.
	 * @param monitor
	 *            to report progress.
	 * @throws InvocationTargetException
	 *             if an error occurs.
	 */
	private void doRun(IAction action, IProgressMonitor monitor)
			throws InvocationTargetException {
		monitor.beginTask("Registering profile", 2);

		// reading input file
		monitor.subTask("Reading selected profile definition");
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource inputResource = resourceSet.getResource(URI
				.createPlatformResourceURI(selectedFile.getFullPath()
						.toString(), true), true);
		monitor.worked(1);

		boolean registeredProfile = false;
		for (EObject eObject : inputResource.getContents()) {
			if (eObject instanceof Profile) {
				monitor.subTask("Registering profile");
				Profile inputProfile = (Profile) eObject;
				EPackage.Registry.INSTANCE.remove(inputProfile.getNsURI());
				EPackage.Registry.INSTANCE.put(inputProfile.getNsURI(),
						inputProfile);
				registeredProfile = true;
			}
		}

		if (!registeredProfile) {
			showError("File does not contain a valid EMF Profile Definition");
			monitor.done();
			return;
		}

		monitor.worked(1);
		monitor.done();
	}

	/**
	 * Displays an error dialog for the specified message.
	 * 
	 * @param msg
	 *            message to display.
	 */
	private void showError(String msg) {
		IStatus status = new Status(IStatus.ERROR,
				EMFProfileUIPlugin.PLUGIN_ID, msg);
		// log error
		EMFProfileUIPlugin.getDefault().getLog().log(status);
		// show error
		ErrorDialog.openError(shell, "Error Occurred", msg, status);
	}

	/**
	 * Displays an error dialog for the specified exception.
	 * 
	 * @param e
	 *            excpetion to display.
	 */
	private void showError(Exception e) {
		IStatus status = new Status(IStatus.ERROR,
				EMFProfileUIPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
		// log error
		EMFProfileUIPlugin.getDefault().getLog().log(status);
		// show error
		ErrorDialog.openError(shell, "Unhandled Exception Occurred",
				e.getLocalizedMessage(), status);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			selectedFile = (IFile) structuredSelection.getFirstElement();
		}
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

}
