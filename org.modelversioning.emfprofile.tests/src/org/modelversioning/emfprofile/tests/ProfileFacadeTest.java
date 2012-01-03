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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Tests for creating profile applications using the {@link ProfileFacadeImpl}.
 * 
 * @author <a href="mailto:wimmer@big.tuwien.ac.at">Manuel Wimmer</a>
 * 
 */
public class ProfileFacadeTest {

	private static final String ABSTRACT_STEREOTYPE_FOR_E_CLASS_NAME = "AbstractForEClass";
	private static final String CONCRETE_STEREOTYPE_FOR_ECLASS_NAME = "ConcreteForEClassInherited";
	private final String modelPath = "model/basic/sample_ecore_model.ecore";
	private final String profilePath = "model/basic/profile_for_ecore_models.emfprofile_diagram";
	private final String profileApplicationPath = "model/basic/annotation.emfprofile.xmi";

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

	@Test
	public void testAppliedToAfterApplyingStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		
		StereotypeApplication stereotypeApplication = profileFacade.apply(
				stereotype, person);
		
		Assert.assertEquals(person, stereotypeApplication.getAppliedTo());
	}

	private EClass getModelPersonEClass() {
		return (EClass) getModelEPackage().eContents().get(0);
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
	public void testTypeOfAppliedStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		
		StereotypeApplication stereotypeApplication = profileFacade.apply(
				stereotype, person);
		
		Assert.assertTrue(stereotypeApplication.eClass().equals(stereotype));
	}

	@Test
	public void testExtensionOfAppliedStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		
		StereotypeApplication stereotypeApplication = profileFacade.apply(
				stereotype, person);
		
		Stereotype superStereotype = getStereotype(ABSTRACT_STEREOTYPE_FOR_E_CLASS_NAME);
		Extension expectedExtension = superStereotype.getExtensions().get(0);;
		
		Assert.assertEquals(expectedExtension, stereotypeApplication.getExtension());
	}
	
	@Test
	public void testApplicabilityOfSubmetaclass() throws IOException {
		Assert.fail();
	}
	
	@Test
	public void testInapplicabilityCausedByAbstract() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		
		Assert.fail();
		
		StereotypeApplication stereotypeApplication = profileFacade.apply(
				stereotype, person);
		
		Stereotype superStereotype = getStereotype(ABSTRACT_STEREOTYPE_FOR_E_CLASS_NAME);
		Extension expectedExtension = superStereotype.getExtensions().get(0);;
		
		Assert.assertEquals(expectedExtension, stereotypeApplication.getExtension());
	}
	
	@Test
	public void testInapplicabilityCausedByWrongBaseType() throws IOException {
		Assert.fail();
	}
	
	@Test
	public void testInapplicabilityCausedByUpperBound() throws IOException {
		Assert.fail();
	}
	
	
	
	
	@After
	public void deleteProfileApplicationResource() {
		String absolutePath = getAbsolutePath(profileApplicationPath);
		deleteIfFileExists(absolutePath);
	}

}