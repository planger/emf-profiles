/**
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.emf.validation.model.IClientSelector;

/**
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ValidationDelegateClientSelector implements IClientSelector {
	
	public static boolean running = false;

	public boolean selects(Object object) {
		return running;
	}

}
