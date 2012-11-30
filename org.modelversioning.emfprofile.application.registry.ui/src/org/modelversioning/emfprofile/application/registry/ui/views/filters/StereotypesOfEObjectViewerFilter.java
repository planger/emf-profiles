/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.views.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class StereotypesOfEObjectViewerFilter extends ViewerFilter {
	
	private EObject selectedEObject;

	public StereotypesOfEObjectViewerFilter(EObject eObject) {
		this.selectedEObject = eObject;
	}

	public void setSelectedEObject(EObject eObject){
		this.selectedEObject = eObject;
	}
	
	public EObject getSelectedEObject(){
		return selectedEObject;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
//		ProfileApplicationDecorator pa = (ProfileApplicationDecorator) parentElement;
		if(element instanceof StereotypeApplication){
			StereotypeApplication sa = (StereotypeApplication) element;
			if(selectedEObject != null && ! sa.getAppliedTo().equals(selectedEObject))
				return false;			
		}
		return true;
	}

}
