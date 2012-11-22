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
public interface ApplyStereotypeListener {
	
	void applyStereotype(EObject eObject);
}
