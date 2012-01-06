/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;

/**
 * Represents an illegal application of an extension according to the
 * applicability of an extension to a metamodel type.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class InapplicableExtensionApplicationViolation extends IllegalStereotypeApplication {

	public InapplicableExtensionApplicationViolation(Stereotype stereotype,
			Extension extension, EObject modelObject) {
		super(stereotype, extension, modelObject);
	}

}
