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
import org.eclipse.emf.ecore.EReference;
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
	private static final String SUBSETTING_STEREOTYPE_FOR_EATTRIBUTE_NAME = "SubsettingStereotype";
	private static final String REDEFINING_STEREOTYPE_FOR_EATTRIBUTE_NAME = "RedefiningStereotype";

	private static final String modelPath = "model/validation/sample_ecore_model.ecore";
	private static final String profilePath = "model/validation/profile_for_ecore_models.emfprofile_diagram";
	private static final String profileWithSubsetPath = "model/validation/profile_with_subset_for_ecore_models.emfprofile_diagram";
	private static final String profileWithRedefinePath = "model/validation/profile_with_redefine_for_ecore_models.emfprofile_diagram";
	private static final String profileApplicationPath = "model/validation/annotation.emfprofile.xmi";

	private final ResourceSet resourceSet = new ResourceSetImpl();

	private Profile profile;
	private Profile profileWithSubset;
	private Profile profileWithRedefine;
	private Resource model;
	private Resource profileApplicationResource;

	@Before
	public void loadProfile() {
		String absolutePath = getAbsolutePath(profilePath);
		Resource resource = loadResource(absolutePath);
		profile = extractProfile(resource);
	}

	@Before
	public void loadProfileWithSubset() {
		String absolutePath = getAbsolutePath(profileWithSubsetPath);
		Resource resource = loadResource(absolutePath);
		profileWithSubset = extractProfile(resource);
	}

	@Before
	public void loadProfileWithRedefining() {
		String absolutePath = getAbsolutePath(profileWithRedefinePath);
		Resource resource = loadResource(absolutePath);
		profileWithRedefine = extractProfile(resource);
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
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

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
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype stereotype2 = getStereotype(profile,
				SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME);

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

	@Test
	public void testLowerBoundViolationWithSubsetStereotype()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacadeForProfileWithSubset();
		Stereotype stereotype = getStereotype(profileWithSubset,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype subSettingStereotype = getStereotype(profileWithSubset,
				SUBSETTING_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		LowerBoundConstraintValidator validator = new LowerBoundConstraintValidator(
				profileApplication, getModelEPackage());
		EList<LowerBoundConstraintViolation> violations = validator
				.getViolations();

		// we expect three missing applications:
		// "subSettingStereotype" must be applied to both attributes
		// irrespectively of "stereotype" and "stereotype" must be applied to
		// the reference "ownedCars"
		Assert.assertEquals(3, violations.size());

		profileFacade.apply(subSettingStereotype,
				getModelPersonLastNameEAttribute());
		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());

		validator = new LowerBoundConstraintValidator(profileApplication,
				getModelEPackage());
		violations = validator.getViolations();

		// we still expect one application of "subSettingStereotype" to
		// attribute "firstName"
		Assert.assertEquals(1, violations.size());
		Assert.assertEquals(getModelPersonFirstNameEAttribute(), violations
				.get(0).getModelObject());
	}

	@Test
	public void testNoLowerBoundViolationWithRedefiningStereotype()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacadeForProfileWithRedefining();
		Stereotype stereotype = getStereotype(profileWithRedefine,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());

		ProfileApplication profileApplication = getProfileApplication();

		LowerBoundConstraintValidator validator = new LowerBoundConstraintValidator(
				profileApplication, getModelEPackage());
		EList<LowerBoundConstraintViolation> violations = validator
				.getViolations();

		// we expect no violations, because the two attributes in the model
		// must not and does not have to be annotated by "stereotype" anymore
		// and the redefining stereotype has a lower bound of 0.
		Assert.assertEquals(0, violations.size());
	}

	@Test
	public void testLowerBoundViolationWithRedefiningStereotype()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacadeForProfileWithRedefining();
		Stereotype stereotype = getStereotype(profileWithRedefine,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype redefiningStereotype = getStereotype(profileWithRedefine,
				REDEFINING_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		redefiningStereotype.getExtensions().get(0).setLowerBound(1);

		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());
		profileFacade.apply(redefiningStereotype,
				getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		LowerBoundConstraintValidator validator = new LowerBoundConstraintValidator(
				profileApplication, getModelEPackage());
		EList<LowerBoundConstraintViolation> violations = validator
				.getViolations();

		// we expect one violations:
		// lower bound of redefining stereotype has been set to 1 so it must be
		// applied to the remaining attribute "lastName"
		Assert.assertEquals(1, violations.size());
		LowerBoundConstraintViolation violation = violations.get(0);
		Assert.assertEquals(getModelPersonLastNameEAttribute(),
				violation.getModelObject());
		Assert.assertEquals(redefiningStereotype.getExtensions().get(0),
				violation.getExtension());

		profileFacade.apply(redefiningStereotype,
				getModelPersonLastNameEAttribute());

		validator = new LowerBoundConstraintValidator(profileApplication,
				getModelEPackage());
		violations = validator.getViolations();

		// we expect no violations, because also the redefining stereotype has
		// been applied to all attributes
		Assert.assertEquals(0, violations.size());
	}

	private ProfileApplication getProfileApplication() {
		return (ProfileApplication) profileApplicationResource.getContents()
				.get(0);
	}

	private IProfileFacade createProfileFacadeForProfileWithSubset()
			throws IOException {
		return createProfileFacade(profileWithSubset);
	}

	private IProfileFacade createProfileFacadeForProfileWithRedefining()
			throws IOException {
		return createProfileFacade(profileWithSubset);
	}

	private IProfileFacade createProfileFacade() throws IOException {
		return createProfileFacade(profile);
	}

	private IProfileFacade createProfileFacade(Profile profile)
			throws IOException {
		IProfileFacade profileFacade = new ProfileFacadeImpl();
		profileFacade.loadProfile(profile);
		profileApplicationResource = createProfileApplicationResource();
		profileFacade.setProfileApplicationResource(profileApplicationResource);
		return profileFacade;
	}

	private Stereotype getStereotype(Profile profile, String stereotypeName) {
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

	private EReference getModelPersonOwnedCarsEReference() {
		return (EReference) getModelPersonEClass().getEStructuralFeature(
				"ownedCars");
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
