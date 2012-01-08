/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.StereotypeApplicability;

/**
 * Implements the interface {@link StereotypeApplicability}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class StereotypeApplicabilityImpl implements StereotypeApplicability {

	private Stereotype stereotype;
	private Extension extension;

	protected StereotypeApplicabilityImpl(Stereotype stereotype,
			Extension extension) {
		super();
		this.stereotype = stereotype;
		this.extension = extension;
	}

	@Override
	public Stereotype getStereotype() {
		return stereotype;
	}

	@Override
	public Extension getExtension() {
		return extension;
	}
}
