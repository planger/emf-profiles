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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
 * Tests for basic functions with basic profiles using the
 * {@link ProfileFacadeImpl}.
 * 
 * @author <a href="langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class BasicProfileFacadeTest {

	private static final String ABSTRACT_STEREOTYPE_FOR_ECLASS_NAME = "AbstractForEClass";
	private static final String CONCRETE_STEREOTYPE_FOR_ECLASS_NAME = "ConcreteForEClassInherited";
	private static final String CONCRETE_STEREOTYPE_FOR_ECLASSIFIER_NAME = "ConcreteForEClassifiers";
	private static final String CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME = "ConcreteForEAttribute";
	private static final String SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME = "SubForEAttribute";

	private static final String modelPath = "model/basic/sample_ecore_model.ecore";
	private static final String profilePath = "model/basic/profile_for_ecore_models.emfprofile_diagram";
	private static final String profileApplicationPath = "model/basic/annotation.emfprofile.xmi";

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
	public void testAppliedToValueAfterApplyingStereotype() throws IOException {
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

		Stereotype superStereotype = getStereotype(ABSTRACT_STEREOTYPE_FOR_ECLASS_NAME);
		Extension expectedExtension = superStereotype.getExtensions().get(0);

		Assert.assertEquals(expectedExtension,
				stereotypeApplication.getExtension());
	}

	@Test
	public void testSavingAndReloadingExistingProfileApplication()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		StereotypeApplication application = profileFacade.apply(stereotype,
				person);
		EStructuralFeature taggedValue = stereotype
				.getTaggedValue("testTaggedValue");
		profileFacade.setTaggedValue(application, taggedValue, "test");

		profileFacade.save();

		profileFacade = new ProfileFacadeImpl();
		profileFacade.loadProfile(profile);
		String absolutePath = getAbsolutePath(profileApplicationPath);
		Resource profileApplicationRes = loadResource(absolutePath);
		profileFacade.setProfileApplicationResource(profileApplicationRes);

		EList<StereotypeApplication> appliedStereotypes = profileFacade
				.getAppliedStereotypes(person);
		Assert.assertEquals(1, appliedStereotypes.size());

		StereotypeApplication stereotypeApplication = appliedStereotypes.get(0);
		Assert.assertTrue(stereotypeApplication.eClass().equals(stereotype));
		Assert.assertEquals(person, stereotypeApplication.getAppliedTo());

		Object value = profileFacade.getTaggedValue(stereotypeApplication,
				taggedValue);
		Assert.assertEquals("test", value);
	}

	@Test
	public void testRemovingExistingProfileApplication() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();
		StereotypeApplication application = profileFacade.apply(stereotype,
				person);
		EStructuralFeature taggedValue = stereotype
				.getTaggedValue("testTaggedValue");
		profileFacade.setTaggedValue(application, taggedValue, "test");

		profileFacade.save();

		profileFacade = new ProfileFacadeImpl();
		profileFacade.loadProfile(profile);
		String absolutePath = getAbsolutePath(profileApplicationPath);
		Resource profileApplicationRes = loadResource(absolutePath);
		profileFacade.setProfileApplicationResource(profileApplicationRes);

		Assert.assertEquals(1, profileFacade.getAppliedStereotypes(person)
				.size());

		StereotypeApplication stereotypeApplication = profileFacade
				.getAppliedStereotypes(person).get(0);
		profileFacade.removeStereotypeApplication(stereotypeApplication);

		Assert.assertEquals(0, profileFacade.getAppliedStereotypes(person)
				.size());

		profileFacade.save();

		Assert.assertEquals(0, profileFacade.getAppliedStereotypes(person)
				.size());
	}

	@Test
	public void testApplicabilityOfSubmetaclass() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASSIFIER_NAME);
		EClass person = getModelPersonEClass();

		Assert.assertTrue(profileFacade.isApplicable(stereotype, person));
	}

	@Test
	public void testInapplicabilityCausedByAbstract() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(ABSTRACT_STEREOTYPE_FOR_ECLASS_NAME);
		EClass person = getModelPersonEClass();

		Assert.assertFalse(profileFacade.isApplicable(stereotype, person));
	}

	@Test
	public void testInapplicabilityCausedByWrongBaseType() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_ECLASSIFIER_NAME);
		EAttribute firstNameAttribute = getModelPersonFirstNameEAttribute();

		Assert.assertFalse(profileFacade.isApplicable(stereotype,
				firstNameAttribute));
	}

	@Test
	public void testInapplicabilityCausedByUpperBound() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		EAttribute firstNameAttribute = getModelPersonFirstNameEAttribute();

		Assert.assertTrue(profileFacade.isApplicable(stereotype,
				firstNameAttribute));

		profileFacade.apply(stereotype, firstNameAttribute);

		Assert.assertTrue(profileFacade.isApplicable(stereotype,
				firstNameAttribute));

		profileFacade.apply(stereotype, firstNameAttribute);

		Assert.assertFalse(profileFacade.isApplicable(stereotype,
				firstNameAttribute));
	}
	
	@Test
	public void testInapplicabilityCausedByUpperBoundWithMixedStereotypes() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype1 = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype stereotype2 = getStereotype(SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		EAttribute firstNameAttribute = getModelPersonFirstNameEAttribute();

		Assert.assertTrue(profileFacade.isApplicable(stereotype1,
				firstNameAttribute));

		profileFacade.apply(stereotype1, firstNameAttribute);

		Assert.assertTrue(profileFacade.isApplicable(stereotype2,
				firstNameAttribute));

		profileFacade.apply(stereotype1, firstNameAttribute);

		Assert.assertFalse(profileFacade.isApplicable(stereotype1,
				firstNameAttribute));
	}

	@After
	public void deleteProfileApplicationResource() {
		String absolutePath = getAbsolutePath(profileApplicationPath);
		deleteIfFileExists(absolutePath);
	}

}