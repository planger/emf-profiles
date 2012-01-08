/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelversioning.emfprofile.Extension;

/**
 * Computes the applicability of a extensions.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ExtensionApplicabilityAdvisor {

	Map<Extension, Integer> remainingApplications = new HashMap<Extension, Integer>();
	final EList<Extension> allExtensions;
	final EList<Extension> usedExtensions;

	public ExtensionApplicabilityAdvisor(EList<Extension> allExtensions,
			EList<Extension> usedExtensions) {
		this.allExtensions = allExtensions;
		this.usedExtensions = usedExtensions;
		initializeRemainingApplications();
		computeRemainingApplications();
	}

	private void initializeRemainingApplications() {
		for (Extension extension : allExtensions) {
			int upperBound = extension.getUpperBound();
			remainingApplications.put(extension, upperBound);
		}
	}

	private void computeRemainingApplications() {
		for (Extension extension : allExtensions) {
			int usedExtensionCount = countOccurrencesInUsedExtensions(extension);
			int remainingApplicationCount = remainingApplications
					.get(extension) - usedExtensionCount;
			remainingApplications.put(extension, remainingApplicationCount);
		}
	}

	private int countOccurrencesInUsedExtensions(Extension extension) {
		int count = 0;
		for (Extension usedExtension : usedExtensions) {
			if (equals(extension, usedExtension)
					|| isSubsettingExtensionUsed(extension, usedExtension)) {
				count++;
			}
		}
		return count;
	}

	private boolean isSubsettingExtensionUsed(Extension extension,
			Extension usedExtension) {
		for (Extension subsettingExtension : extension.getSubsetting()) {
			if (equals(usedExtension, subsettingExtension)) {
				return true;
			}
		}
		return false;
	}

	private boolean equals(Extension extension1, Extension extension2) {
		return EcoreUtil.equals(extension1, extension2);
	}

	/**
	 * Returns the extensions that may still be used to apply a stereotype.
	 * 
	 * @return the applicable extensions.
	 */
	public EList<Extension> getApplicableExtensions() {
		EList<Extension> reminingExtensions = new BasicEList<Extension>();
		for (Extension extension : allExtensions) {
			if (getRemainingApplicationCount(extension) != 0) {
				reminingExtensions.add(extension);
			}
		}
		return ECollections.unmodifiableEList(reminingExtensions);
	}

	private int getRemainingApplicationCount(Extension extension) {
		return remainingApplications.get(extension);
	}

}
