/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator;



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
}
