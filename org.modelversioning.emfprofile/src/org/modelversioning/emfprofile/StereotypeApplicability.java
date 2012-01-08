/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile;


/**
 * Represents an applicability of a {@link Stereotype}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public interface StereotypeApplicability {

	/**
	 * Specifies the applicable stereotype.
	 * 
	 * @return the applicable stereotype.
	 */
	public Stereotype getStereotype();

	/**
	 * Specifies the applicable extension to use for applying the
	 * {@link #getStereotype() stereotype}.
	 * 
	 * @return the applicable extension.
	 */
	public Extension getExtension();
}
