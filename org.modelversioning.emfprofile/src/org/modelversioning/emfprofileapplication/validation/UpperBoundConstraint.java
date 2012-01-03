/**
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Checks for a violation of the upper bound constraint of a {@link Stereotype}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class UpperBoundConstraint extends AbstractModelConstraint {

	private ProfileApplication profileApplication = null;
	private IValidationContext context = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		this.context = ctx;
		EObject eObj = context.getTarget();
		EMFEventType eType = ctx.getEventType();

		if (eType == EMFEventType.NULL) {
			obtainProfileApplication(eObj);
			if (profileApplication != null) {
				return validateProfileApplication();
			}
		}
		return ctx.createSuccessStatus();
	}

	private void obtainProfileApplication(EObject eObj) {
		if (eObj instanceof ProfileApplication) {
			profileApplication = (ProfileApplication) eObj;
		}
	}

	private IStatus validateProfileApplication() {
		EList<EObject> annotatedObjects = profileApplication
				.getAnnotatedObjects();
		for (EObject annotatedObject : annotatedObjects) {
			IStatus status = checkObject(annotatedObject, profileApplication);
			if (!status.isOK()) {
				return status;
			}
		}
		return context.createSuccessStatus();
	}

	private IStatus checkObject(EObject annotatedObject,
			ProfileApplication profileApplication) {
		EList<StereotypeApplication> stereotypeApplications = profileApplication
				.getStereotypeApplications(annotatedObject);
		for (StereotypeApplication stereotypeApplication : stereotypeApplications) {
			Extension extension = stereotypeApplication.getExtension();
			if (extension.getUpperBound() != -1
					&& !isUpperBoundOk(extension, stereotypeApplications)) {
				return context.createFailureStatus(new Object[] {
						((Stereotype) stereotypeApplication.eClass()), extension });
			}
		}
		return context.createSuccessStatus();
	}

	private boolean isUpperBoundOk(Extension extension,
			EList<StereotypeApplication> stereotypeApplications) {
		EList<Extension> usedExtensions = extractUsedExtensions(
				stereotypeApplications, extension);
		if (usedExtensions.size() > extension.getUpperBound()) {
			return false;
		} else {
			return true;
		}
	}

	private EList<Extension> extractUsedExtensions(
			EList<StereotypeApplication> stereotypeApplications,
			Extension extension) {
		EList<Extension> extensions = new BasicEList<Extension>();
		for (StereotypeApplication stereotypeApplication : stereotypeApplications) {
			if (extension.equals(stereotypeApplication.getExtension())) {
				extensions.add(stereotypeApplication.getExtension());
			}
		}
		return extensions;
	}

}
