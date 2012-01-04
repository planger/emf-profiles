package org.modelversioning.emfprofileapplication.validation;

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
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Tests the {@link UpperBoundConstraintValidator} for upper bound violations.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class UpperBoundConstraintValidatorTest {

	private static final String CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME = "ConcreteForEAttribute";
	private static final String SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME = "SubForEAttribute";
	private static final String SUBSETTING_STEREOTYPE_FOR_EATTRIBUTE_NAME = "SubsettingStereotype";

	private static final String modelPath = "model/validation/sample_ecore_model.ecore";
	private static final String profilePath = "model/validation/profile_with_subset_for_ecore_models.emfprofile_diagram";
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
	public void testUpperBoundViolationWithSameStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());
		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		UpperBoundConstraintValidator validator = new UpperBoundConstraintValidator(
				profileApplication);
		Assert.assertEquals(0, validator.getViolations().size());

		applyStereotypeBypassingFacade(stereotype, getModelPersonFirstNameEAttribute());

		validator = new UpperBoundConstraintValidator(profileApplication);
		Assert.assertEquals(1, validator.getViolations().size());
	}
	
	@Test
	public void testUpperBoundViolationWithMixedStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype stereotype2 = getStereotype(SUB_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());
		profileFacade.apply(stereotype2, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		UpperBoundConstraintValidator validator = new UpperBoundConstraintValidator(
				profileApplication);
		Assert.assertEquals(0, validator.getViolations().size());

		applyStereotypeBypassingFacade(stereotype, getModelPersonFirstNameEAttribute());

		validator = new UpperBoundConstraintValidator(profileApplication);
		Assert.assertEquals(1, validator.getViolations().size());
	}
	
	@Test
	public void testUpperBoundViolationWithSubsettingStereotype() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype subsettingStereotype = getStereotype(SUBSETTING_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());
		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		ProfileApplication profileApplication = getProfileApplication();

		UpperBoundConstraintValidator validator = new UpperBoundConstraintValidator(
				profileApplication);
		Assert.assertEquals(0, validator.getViolations().size());

		applyStereotypeBypassingFacade(subsettingStereotype, getModelPersonFirstNameEAttribute());

		validator = new UpperBoundConstraintValidator(profileApplication);
		Assert.assertEquals(1, validator.getViolations().size());
	}

	protected void applyStereotypeBypassingFacade(
			Stereotype stereotype, EObject eObject) {
		StereotypeApplication stereotypeApplication = (StereotypeApplication) stereotype
				.getEPackage().getEFactoryInstance().create(stereotype);
		stereotypeApplication.setExtension(stereotype.getAllExtensions().get(0));
		stereotypeApplication.setAppliedTo(eObject);
		getProfileApplication().getStereotypeApplications().add(
				stereotypeApplication);
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
