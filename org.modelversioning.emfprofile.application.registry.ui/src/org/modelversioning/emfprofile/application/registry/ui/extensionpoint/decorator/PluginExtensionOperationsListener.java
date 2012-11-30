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
public interface PluginExtensionOperationsListener {
	
	/**
	 * Executes the operation of applying stereotypes on 
	 * given {@link EObject}
	 * @param eObject
	 */
	void applyStereotype(EObject eObject);
	
	/**
	 * Notifies the extended plug-in that the 
	 * new selection on an instance of {@link EObject} 
	 * was registered. 
	 * @param eObject
	 */
	void eObjectSelected(EObject eObject);
}
