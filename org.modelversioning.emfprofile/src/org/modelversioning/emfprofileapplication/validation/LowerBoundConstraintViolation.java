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

/**
 * Represents a violation of a stereotype's lower bound in the context of a profile application.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 *
 */
public class LowerBoundConstraintViolation {
	
	private final Extension extension;
	private final EObject modelObject;
	
	public LowerBoundConstraintViolation(Extension extension,
			EObject modelObject) {
		super();
		this.extension = extension;
		this.modelObject = modelObject;
	}

	public Extension getExtension() {
		return extension;
	}

	public EObject getModelObject() {
		return modelObject;
	}

	@Override
	public String toString() {
		return extension.toString() + " at " + modelObject.toString();
	}
	
	

}
