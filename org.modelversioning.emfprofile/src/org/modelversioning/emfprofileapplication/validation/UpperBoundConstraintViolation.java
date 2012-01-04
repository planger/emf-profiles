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
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Represents a violation of a stereotype's upper bound in the context of a profile application.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 *
 */
public class UpperBoundConstraintViolation {
	
	private final Stereotype stereotype;
	private final Extension extension;
	private final EObject modelObject;
	
	public UpperBoundConstraintViolation(Stereotype stereotype, Extension extension,
			EObject modelObject) {
		super();
		this.stereotype = stereotype;
		this.extension = extension;
		this.modelObject = modelObject;
	}

	public UpperBoundConstraintViolation(
			StereotypeApplication stereotypeApplication) {
		this.stereotype = (Stereotype) stereotypeApplication.eClass();
		this.extension = stereotypeApplication.getExtension();
		this.modelObject = stereotypeApplication.getAppliedTo();
	}

	public Stereotype getStereotype() {
		return stereotype;
	}
	
	public Extension getExtension() {
		return extension;
	}

	public EObject getModelObject() {
		return modelObject;
	}

}
