/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class RemoveStereotypeApplicationOrNestedClassHandler extends
		AbstractHandler implements IHandler {

	/**
	 * 
	 */
	public RemoveStereotypeApplicationOrNestedClassHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if(selection != null && selection instanceof ITreeSelection){
			ITreeSelection treeSelection = (ITreeSelection) selection;
			Collection<ProfileApplicationDecorator> profileApplicationsToRefreshInView = new ArrayList<>();
			Set<EObject> eObjectsToRefreshTheirDecorations = new HashSet<>();
			for (TreePath path : treeSelection.getPaths()) {
					EObject eObject = (EObject)path.getLastSegment();
					if( ! (eObject instanceof ProfileApplicationDecorator)){
						ProfileApplicationDecorator profileApplicationDecorator = findProfileApplicationDecorator(path);
						if(profileApplicationDecorator == null)
							throw new RuntimeException("Could not find a profile application element as parent of selected element: " + eObject.toString());
						if(eObject instanceof StereotypeApplication){
							StereotypeApplication stereotypeApplication = (StereotypeApplication) eObject;
							profileApplicationDecorator.removeStereotypeApplication(stereotypeApplication);
							eObjectsToRefreshTheirDecorations.add(stereotypeApplication.getAppliedTo());
							profileApplicationsToRefreshInView.add(profileApplicationDecorator);
						}
						// TODO code for removing nested objects, not only Stereotypes
						
					}
					// TODO Consider removing the resource of profile application with this handler
				
				
			}
			ActiveEditorObserver.INSTANCE.refreshViewer(profileApplicationsToRefreshInView);
			for (EObject eObject : eObjectsToRefreshTheirDecorations) {
				ActiveEditorObserver.INSTANCE.refreshDecoration(eObject);
			}
		}
		return null;
	}

	private ProfileApplicationDecorator findProfileApplicationDecorator(TreePath treePath) {
		TreePath parentPath = treePath.getParentPath();
		if(parentPath == null)
			return null;
		EObject element = (EObject)parentPath.getLastSegment();
		if(element instanceof ProfileApplicationDecorator)
			return (ProfileApplicationDecorator) element;
		else
			return findProfileApplicationDecorator(parentPath);
	}
}
