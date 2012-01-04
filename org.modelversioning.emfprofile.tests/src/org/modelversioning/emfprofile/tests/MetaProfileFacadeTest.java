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
package org.modelversioning.emfprofile.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;

/**
 * Tests for metaprofiles using the {@link ProfileFacadeImpl}.
 * 
 * @author <a href="langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class MetaProfileFacadeTest {

	private static final String METASTEREOTYPE_NAME = "MetaStereotype";
	private static final String SUB_METASTEREOTYPE_NAME = "SubMetaStereotype";
	private static final String SUB_STEREOTYPE_NAME = "SubStereotype";

	private static final String modelPath = "model/meta/sample_ecore_model.ecore";
	private static final String profilePath = "model/meta/metaprofile_for_ecore_models.emfprofile_diagram";
	private static final String profileApplicationPath = "model/meta/annotation.emfprofile.xmi";

	private final ResourceSet resourceSet = new ResourceSetImpl();

	private Profile profile;
	private Resource model;

	@Before
	public void loadProfile() {
		String absolutePath = getAbsolutePath(profilePath);
		Resource resource = loadResource(absolutePath);
		profile = extractProfile(resource);
	}

	@Before
	public void loadModel() {
		String absolutePath = getAbsolutePath(modelPath);
		model = loadResource(absolutePath);
	}

	private Profile extractProfile(Resource resource) {
		return (Profile) resource.getContents().get(0);
	}

	private String getAbsolutePath(String relativePath) {
		return new File(relativePath).getAbsolutePath();
	}

	private Resource loadResource(String path) {
		return resourceSet.getResource(URI.createFileURI(path), true);
	}

	private EClass getModelPersonEClass() {
		return (EClass) getModelEPackage().eContents().get(0);
	}

	private EAttribute getModelPersonFirstNameEAttribute() {
		return (EAttribute) getModelPersonEClass().getEStructuralFeature(
				"firstName");
	}

	private EObject getModelEPackage() {
		return model.getContents().get(0);
	}

	private Stereotype getStereotype(String stereotypeName) {
		return profile.getStereotype(stereotypeName);
	}

	private IProfileFacade createProfileFacade() throws IOException {
		IProfileFacade profileFacade = new ProfileFacadeImpl();
		profileFacade.loadProfile(profile);
		Resource profileApplicationRes = createProfileApplicationResource();
		profileFacade.setProfileApplicationResource(profileApplicationRes);
		return profileFacade;
	}

	private Resource createProfileApplicationResource() throws IOException {
		String absolutePath = getAbsolutePath(profileApplicationPath);
		return createResource(absolutePath);
	}

	private Resource createResource(String path) throws IOException {
		deleteIfFileExists(path);
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		resource.save(Collections.emptyMap());
		return resource;
	}

	private void deleteIfFileExists(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testApplicabilityOfMetaStereotypeToEClass() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(METASTEREOTYPE_NAME);
		EClass person = getModelPersonEClass();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, person));
	}

	@Test
	public void testApplicabilityOfMetaStereotypeToEAttribute()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(METASTEREOTYPE_NAME);
		EAttribute eAttribute = getModelPersonFirstNameEAttribute();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, eAttribute));
	}

	@Test
	public void testApplicabilityOfSubMetaStereotypeToEClass()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(SUB_METASTEREOTYPE_NAME);
		EClass person = getModelPersonEClass();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, person));
	}

	@Test
	public void testApplicabilityOfSubMetaStereotypeToEAttribute()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(SUB_METASTEREOTYPE_NAME);
		EAttribute eAttribute = getModelPersonFirstNameEAttribute();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, eAttribute));
	}

	@Test
	public void testApplicabilityOfSubStereotypeToEClass() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(SUB_STEREOTYPE_NAME);
		EClass person = getModelPersonEClass();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, person));
	}

	@After
	public void deleteProfileApplicationResource() {
		String absolutePath = getAbsolutePath(profileApplicationPath);
		deleteIfFileExists(absolutePath);
	}

}