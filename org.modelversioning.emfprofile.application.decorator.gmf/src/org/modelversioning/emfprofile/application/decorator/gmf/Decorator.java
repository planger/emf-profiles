/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.gmf;

import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.ApplyStereotypeListener;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class Decorator implements EMFProfileApplicationDecorator {

//	private static final String ECORE_REFLECTIVE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID"; 
//	private static final String ECORE_EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID"; 
	private static final String ECORE_DIAGRAM_EDITOR_ID = "org.eclipse.emf.ecoretools.diagram.part.EcoreDiagramEditorID";
	
	private static ApplyStereotypeListener listener;
	
	public static ApplyStereotypeListener getApplyStereotypeListener(){
		return Decorator.listener;
	}
	
	/**
	 * 
	 */
	public Decorator() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String[] canDecorateEditorParts() {
		return new String[]{ECORE_DIAGRAM_EDITOR_ID};
	}


	@Override
	public void setApplyStereotypeListener(ApplyStereotypeListener listener) {
		Decorator.listener = listener;
	}


}
