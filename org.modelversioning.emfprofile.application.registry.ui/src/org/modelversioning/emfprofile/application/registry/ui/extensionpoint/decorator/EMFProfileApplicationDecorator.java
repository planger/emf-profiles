/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator;

import org.eclipse.emf.ecore.EObject;


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
	 * Sets the listener that can be notified if a stereotype should be
	 * applied on {@link EObject}
	 * @param listener or <code>null</code> to remove a listener.
	 */
	void setApplyStereotypeListener(ApplyStereotypeListener listener);
}
