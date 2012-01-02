/**
 * <copyright>
 *
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package org.modelversioning.emfprofile.impl;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.junit.Test;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;

/**
 * Test for annotating models with EMF Profiles.
 * 
 * @author <a href="mailto:wimmer@big.tuwien.ac.at">Manuel Wimmer</a>
 * 
 */
public class ProfileFacadeTest {

	/* MODEL PATHS */
	final String modelPath = "model/model.ecore";
	final String annotationModelPath = "model/annotation.emfprofile.xmi";
	final String profilePath = "model/conflict.emfprofile_diagram";

	/** The common resource set */
	ResourceSet resourceSet = new ResourceSetImpl();

	@Test
	public void testMerge() throws IOException {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new EcoreResourceFactoryImpl());

		// load model to annotate
		Resource modelResource = (XMLResource) resourceSet.getResource(
				URI.createFileURI(new File(modelPath).getAbsolutePath()), true);

		assert (modelResource.getContents().size() > 0);

		// load profile application model
		Resource profileApplicationResource = resourceSet
				.createResource(URI.createFileURI(new File(annotationModelPath)
						.getAbsolutePath()));
		profileApplicationResource.save(null);

		// load profile model
		Resource profileResource = resourceSet.getResource(
				URI.createURI(profilePath, true), true);

		assert (profileResource.getContents().size() > 0);

		Profile profile = (Profile) profileResource.getContents().get(0);

		// Initialize the profile facade
		IProfileFacade profileFacade;
		profileFacade = new ProfileFacadeImpl();
		profileFacade.makeApplicable(profile);
		profileFacade.setProfileApplicationResource(profileApplicationResource);
		profileFacade.loadProfile(profile);

		// retrieve the Update/Update Stereotype from the profile
		Stereotype upUpStereo = profile.getStereotype("UpdateUpdate");

		// get a model element to be annotated with the Update/Update Stereotype
		EObject rootObject = modelResource.getContents().get(0);

		// check applicability
		assert (upUpStereo.isApplicable(rootObject));
		assert (profileFacade.getApplicableStereotypes(rootObject)
				.contains(upUpStereo));

		// apply stereotype
		profileFacade.apply(upUpStereo, rootObject);

		// check if it is applied now
		assert (profileFacade.getAppliedStereotypes(rootObject).size() == 1);

		// save the profile application
		profileApplicationResource.save(null);

		// unload all resources
		modelResource.unload();
		profileApplicationResource.unload();
		profileResource.unload();
	}

}
