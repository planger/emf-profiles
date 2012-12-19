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
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class RemoveStereotypeApplicationOrNestedClassHandler extends
		AbstractHandler implements IHandler {


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if(selection != null && selection instanceof IStructuredSelection){
			Collection<Object> elementsToRefreshInView = new ArrayList<>();
			Set<EObject> eObjectsToRefreshTheirDecorations = new HashSet<>();
			IStructuredSelection sSelection = (IStructuredSelection)selection;
			Iterator<EObject> selectedObjectsIterator = sSelection.iterator();
			while (selectedObjectsIterator.hasNext()) {
					EObject eObject = selectedObjectsIterator.next();
					if( ! (eObject instanceof ProfileApplication)){
						ProfileApplicationDecorator profileApplicationDecorator = ActiveEditorObserver.INSTANCE.findProfileApplicationDecorator(eObject);
						if(profileApplicationDecorator == null){
							// if it couldn't be found, that most probably indicates
							// that any parent in chain to the root (which is profile application)
							// so, continue
							continue;
						}
							
						if(eObject instanceof StereotypeApplication){
							StereotypeApplication stereotypeApplication = (StereotypeApplication) eObject;
							profileApplicationDecorator.removeEObject(stereotypeApplication);
							eObjectsToRefreshTheirDecorations.add(stereotypeApplication.getAppliedTo());
							elementsToRefreshInView.add(profileApplicationDecorator);
						} else {
							elementsToRefreshInView.add(eObject.eContainer());
							// code for removing nested objects
							profileApplicationDecorator.removeEObject(eObject);
						}
						
					}
					// TODO Consider removing the resource of profile application with this handler
				
				
			}
			ActiveEditorObserver.INSTANCE.refreshViewer(elementsToRefreshInView);
			for (EObject eObject : eObjectsToRefreshTheirDecorations) {
				ActiveEditorObserver.INSTANCE.refreshDecoration(eObject);
			}
		}
		return null;
	}

}
