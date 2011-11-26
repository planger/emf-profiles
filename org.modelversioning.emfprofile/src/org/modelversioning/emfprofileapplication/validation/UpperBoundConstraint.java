/**
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		if (eType == EMFEventType.NULL) {
			if (eObj instanceof ProfileApplication) {
				ProfileApplication profileApplication = (ProfileApplication) eObj;
				for (StereotypeApplication stereotypeApp : profileApplication
						.getStereotypeApplications()) {
					EClass eClass = stereotypeApp.eClass();
					System.out.println(eClass);

				}
			}

			if (eObj instanceof Stereotype
					&& eObj instanceof StereotypeApplication) {
				int upperBound = ((Stereotype) eObj).getUpperBound();
				String stereotypeName = ((Stereotype) eObj).getName();
				ProfileApplication profileApplication = ((StereotypeApplication) eObj)
						.getProfileApplication();
				int applications = 0;
				for (EObject extendedObject : ((StereotypeApplication) eObj)
						.getAppliedTo()) {
					applications += getNumberOfAppliedStereotypes(
							extendedObject, (Stereotype) eObj,
							profileApplication);
				}
				if (applications > upperBound) {
					return ctx
							.createFailureStatus(new Object[] { stereotypeName });
				}
			}
		}

		return ctx.createSuccessStatus();
	}

	private int getNumberOfAppliedStereotypes(EObject extendedObject,
			Stereotype eObj, ProfileApplication profileApplication) {
		// TODO Auto-generated method stub
		return 0;
	}
}
