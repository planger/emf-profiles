package org.modelversioning.emfprofileapplication.validation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
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
import org.modelversioning.emfprofileapplication.ProfileApplication;

/**
 * Tests the LowerBoundConstraintValidator for lower bound violations.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class LowerBoundConstraintValidatorTest {

	private static final String CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME = "ConcreteForEAttribute";
	private static final String SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME = "SubForEAttribute";

	private static final String modelPath = "model/validation/sample_ecore_model.ecore";
	private static final String profilePath = "model/validation/profile_for_ecore_models.emfprofile_diagram";
	private static final String profileApplicationPath = "model/validation/annotation.emfprofile.xmi";

	private final ResourceSet resourceSet = new ResourceSetImpl();

	private Profile profile;
	private Resource model;
	private Resource profileApplicationResource;

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
	public void testLowerBoundViolationWithSameStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		LowerBoundConstraintValidator validator = new LowerBoundConstraintValidator(
				profileApplication, getModelEPackage());
		EList<LowerBoundConstraintViolation> violations = validator
				.getViolations();
		Assert.assertEquals(1, violations.size());
		Assert.assertEquals(getModelPersonLastNameEAttribute(),
				violations.get(0).getModelObject());

		profileFacade.apply(stereotype, getModelPersonLastNameEAttribute());

		validator = new LowerBoundConstraintValidator(profileApplication,
				getModelEPackage());
		violations = validator.getViolations();
		Assert.assertEquals(0, violations.size());
	}

	@Test
	public void testLowerBoundViolationWithMixedStereotypes()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype stereotype2 = getStereotype(SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		LowerBoundConstraintValidator validator = new LowerBoundConstraintValidator(
				profileApplication, getModelEPackage());
		EList<LowerBoundConstraintViolation> violations = validator
				.getViolations();
		Assert.assertEquals(1, violations.size());
		Assert.assertEquals(getModelPersonLastNameEAttribute(),
				violations.get(0).getModelObject());

		profileFacade.apply(stereotype2, getModelPersonLastNameEAttribute());

		validator = new LowerBoundConstraintValidator(profileApplication,
				getModelEPackage());
		violations = validator.getViolations();
		Assert.assertEquals(0, violations.size());
	}

	private ProfileApplication getProfileApplication() {
		return (ProfileApplication) profileApplicationResource.getContents()
				.get(0);
	}

	private IProfileFacade createProfileFacade() throws IOException {
		IProfileFacade profileFacade = new ProfileFacadeImpl();
		profileFacade.loadProfile(profile);
		profileApplicationResource = createProfileApplicationResource();
		profileFacade.setProfileApplicationResource(profileApplicationResource);
		return profileFacade;
	}

	private Stereotype getStereotype(String stereotypeName) {
		return profile.getStereotype(stereotypeName);
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

	private EObject getModelEPackage() {
		return model.getContents().get(0);
	}

	private EAttribute getModelPersonFirstNameEAttribute() {
		return (EAttribute) getModelPersonEClass().getEStructuralFeature(
				"firstName");
	}

	private EAttribute getModelPersonLastNameEAttribute() {
		return (EAttribute) getModelPersonEClass().getEStructuralFeature(
				"lastName");
	}

	private EClass getModelPersonEClass() {
		return (EClass) getModelEPackage().eContents().get(0);
	}

	@After
	public void deleteProfileApplicationResource() {
		String absolutePath = getAbsolutePath(profileApplicationPath);
		deleteIfFileExists(absolutePath);
	}

	private void deleteIfFileExists(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

}
