/**
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofileapplication.validation;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Checks for a violation of the lower bound constraint of a {@link Stereotype}.
 * 
 * TODO respect redefines (no lower bound violation for redefined extensions)
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class LowerBoundConstraintValidator {

	private final ProfileApplication profileApplication;
	private final EObject rootModelObject;
	private EList<LowerBoundConstraintViolation> violations = new BasicEList<LowerBoundConstraintViolation>();

	public LowerBoundConstraintValidator(ProfileApplication profileApplication,
			EObject rootModelObject) {
		this.profileApplication = profileApplication;
		this.rootModelObject = rootModelObject;
	}

	public EList<LowerBoundConstraintViolation> getViolations() {
		EList<Extension> extensions = collectRequiredExtensionsFromProfiles();
		checkExtensions(extensions);
		cleanViolationsConcerningSubsettedExtensions();
		return ECollections.unmodifiableEList(violations);
	}

	private EList<Extension> collectRequiredExtensionsFromProfiles() {
		EList<Profile> profiles = getUsedProfiles();
		return collectRequiredExtensions(profiles);
	}

	private EList<Profile> getUsedProfiles() {
		EList<Profile> profiles = new BasicEList<Profile>();
		for (ProfileImport profileImport : profileApplication
				.getImportedProfiles()) {
			if (profileImport.getProfile() != null
					&& !profiles.contains(profileImport.getProfile())) {
				profiles.add(profileImport.getProfile());
			}
		}
		return profiles;
	}

	private EList<Extension> collectRequiredExtensions(EList<Profile> profiles) {
		EList<Extension> extensions = new BasicEList<Extension>();
		for (Profile profile : profiles) {
			for (Stereotype stereotype : profile.getStereotypes()) {
				for (Extension extension : stereotype.getExtensions()) {
					if (extension.isRequired()) {
						extensions.add(extension);
					}
				}
			}
		}
		return extensions;
	}

	private void checkExtensions(EList<Extension> extensions) {
		for (Extension extension : extensions) {
			validateExtension(extension);
		}
	}

	private void validateExtension(Extension extension) {
		// validate root model object
		validateModelObjectForExtension(extension, rootModelObject);
		// validate children of root model object
		for (TreeIterator<EObject> contents = rootModelObject.eAllContents(); contents
				.hasNext();) {
			EObject modelObject = (EObject) contents.next();
			validateModelObjectForExtension(extension, modelObject);
		}
	}

	private void validateModelObjectForExtension(Extension extension,
			EObject modelObject) {
		if (isExtensionApplicable(extension, modelObject)) {
			EList<Extension> appliedExtensions = getAppliedExtensions(
					extension, modelObject);
			if (extension.getLowerBound() > appliedExtensions.size()) {
				violations.add(new LowerBoundConstraintViolation(extension,
						modelObject));
			}
		}
	}

	private EList<Extension> getAppliedExtensions(Extension extension,
			EObject modelObject) {
		EList<StereotypeApplication> stereotypeApplications = profileApplication
				.getStereotypeApplications(modelObject);
		EList<Extension> appliedExtension = new BasicEList<Extension>();
		for (StereotypeApplication stereotypeApplication : stereotypeApplications) {
			if (EcoreUtil.equals(extension,
					stereotypeApplication.getExtension())
					|| isSubsetted(extension,
							stereotypeApplication.getExtension())) {
				appliedExtension.add(stereotypeApplication.getExtension());
			}
		}
		return appliedExtension;
	}

	private void cleanViolationsConcerningSubsettedExtensions() {
		for (LowerBoundConstraintViolation violation : new BasicEList<LowerBoundConstraintViolation>(
				violations)) {
			if (foundViolationForSubsettingForSameModelObject(violation)) {
				violations.remove(violation);
			}
		}
	}

	private boolean foundViolationForSubsettingForSameModelObject(
			LowerBoundConstraintViolation violation) {
		for (LowerBoundConstraintViolation currentViolation : violations) {
			if (isSubsetted(violation.getExtension(),
					currentViolation.getExtension())
					&& EcoreUtil.equals(currentViolation.getModelObject(),
							violation.getModelObject())) {
				return true;
			}
		}
		return false;
	}

	private boolean isSubsetted(Extension extension, Extension extension2) {
		for (Extension subsettedExtension : extension2.getSubsetted()) {
			if (EcoreUtil.equals(subsettedExtension, extension)) {
				return true;
			}
		}
		return false;
	}

	private boolean isExtensionApplicable(Extension extension, EObject eObject) {
		Stereotype stereotype = extension.getSource();
		EList<Extension> applicableExtensions = stereotype
				.getApplicableExtensions(eObject, getEmptyExtensionList());
		return applicableExtensions.size() > 0
				&& applicableExtensions.contains(extension);
	}

	private EList<Extension> getEmptyExtensionList() {
		return ECollections.emptyEList();
	}

}
