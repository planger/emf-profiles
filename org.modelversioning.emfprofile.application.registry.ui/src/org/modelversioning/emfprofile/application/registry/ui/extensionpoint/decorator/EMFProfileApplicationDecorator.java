/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.Stereotype;



/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public interface EMFProfileApplicationDecorator {
	
	/**
	 * returns editor part extension ids which this decorator supports. 
	 * @return
	 */
	String[] canDecorateEditorParts();
	
	/**
	 * Sets the listener for extension plug-in which can execute operations on
	 * extended plug-in.
	 * @param listener or <code>null</code> to remove a listener.
	 */
	void setPluginExtensionOperationsListener(PluginExtensionOperationsListener listener);
	
	/**
	 * Decorating graphical representation of element holding {@link EObject}. 
	 * Graphical element will be decorated with images and tool tip texts
	 * that represent applied {@link Stereotype}s on this {@link EObject}. 
	 * @param eObject
	 * 		{@link EObject} in question.
	 * @param images
	 * 		Image representation of applied {@link Stereotype}s on {@link EOjbect}.
	 * @param toolTipTexts
	 * 		Names of {@link Stereotype}s applied on {@link EObject}.
	 */
	void decorate(EObject eObject, List<Image> images, List<String> toolTipTexts);
}
