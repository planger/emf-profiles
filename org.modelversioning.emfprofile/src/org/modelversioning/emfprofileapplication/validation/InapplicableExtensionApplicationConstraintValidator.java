/**
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.emf.ecore.EClass;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Checks for application of an inapplicable extension or extensions that are
 * not usable by the respective stereotype.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class InapplicableExtensionApplicationConstraintValidator {

	private StereotypeApplication stereotypeApplication;

	public InapplicableExtensionApplicationConstraintValidator(
			StereotypeApplication stereotypeApplication) {
		this.stereotypeApplication = stereotypeApplication;
	}

	public boolean isViolated() {
		return !isUsedExtensionApplicable()
				|| !isUsedExtensionUsableByStereotype();
	}

	private boolean isUsedExtensionApplicable() {
		return getExtension().isApplicable(getBaseObjectClass());
	}

	private boolean isUsedExtensionUsableByStereotype() {
		return getStereotype().getApplicableExtensions(getBaseObjectClass())
				.contains(getExtension());
	}

	private Extension getExtension() {
		return stereotypeApplication.getExtension();
	}

	private EClass getBaseObjectClass() {
		return stereotypeApplication.getAppliedTo().eClass();
	}

	private Stereotype getStereotype() {
		return (Stereotype) stereotypeApplication.eClass();
	}

	public InapplicableExtensionApplicationViolation createViolation() {
		return new InapplicableExtensionApplicationViolation(
				(Stereotype) stereotypeApplication.eClass(),
				stereotypeApplication.getExtension(),
				stereotypeApplication.getAppliedTo());
	}
}
