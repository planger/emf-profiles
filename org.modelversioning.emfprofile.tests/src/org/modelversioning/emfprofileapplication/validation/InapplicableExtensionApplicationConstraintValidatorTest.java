package org.modelversioning.emfprofileapplication.validation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import junit.framework.Assert;

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
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * Tests the {@link InapplicableExtensionApplicationConstraintValidator}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class InapplicableExtensionApplicationConstraintValidatorTest {

	private static final String CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME = "ConcreteForEAttribute";
	private static final String CONCRETE_STEREOTYPE_FOR_ECLASS_NAME = "AbstractForEClass";
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
	public void testCorrectExtension() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// test with legal extension
		StereotypeApplication stereotypeApplication = profileFacade.apply(
				stereotype, getModelPersonFirstNameEAttribute());
		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertFalse(validator.isViolated());
	}

	@Test
	public void testAppliedUncontainedExtensionCorrectTarget()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		// apply stereotype using uncontained extension
		Extension extensionForEclasses = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_ECLASS_NAME).getExtensions().get(0);
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				stereotype, extensionForEclasses, getModelPersonEClass());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	@Test
	public void testAppliedUncontainedExtensionWrongTarget() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		// apply stereotype using uncontained extension
		Extension extensionForEclasses = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_ECLASS_NAME).getExtensions().get(0);
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				stereotype, extensionForEclasses,
				getModelPersonFirstNameEAttribute());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	@Test
	public void testAppliedExtensionWrongTarget() throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(profile,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonFirstNameEAttribute());

		// apply stereotype using its extension to wrong type
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				stereotype, stereotype.getExtensions().get(0),
				getModelPersonEClass());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	@Test
	public void testIllegalUseOfRedefinedExtensionWithTargetOfRedefiningExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacadeForProfileWithRedefining();
		Stereotype stereotype = getStereotype(profileWithRedefine,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());

		// apply stereotype using its extension to base type of redefining
		// extension
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				stereotype, stereotype.getExtensions().get(0),
				getModelPersonFirstNameEAttribute());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	@Test
	public void testIllegalUseOfRedefinedExtensionWithStereotypeHavingRedefiningExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Stereotype stereotype = getStereotype(profileWithRedefine,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype redefiningStereotype = getStereotype(profileWithRedefine,
				REDEFINING_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());

		// apply redefining stereotype using the redefined extension
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				redefiningStereotype, stereotype.getExtensions().get(0),
				getModelPersonFirstNameEAttribute());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	@Test
	public void testIllegalUseOfSubsettedExtensionWithStereotypeHavingSubsettingExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacadeForProfileWithSubset();
		Stereotype stereotype = getStereotype(profileWithSubset,
				CONCRETE_STEREOTYPE_FOR_EATTRIBUTE_NAME);
		Stereotype subsettingStereotype = getStereotype(profileWithSubset,
				SUBSETTING_STEREOTYPE_FOR_EATTRIBUTE_NAME);

		// apply legal extension
		profileFacade.apply(stereotype, getModelPersonOwnedCarsEReference());

		// apply subsetting stereotype using the subsetted extension
		StereotypeApplication stereotypeApplication = applyStereotypeBypassingFacade(
				subsettingStereotype, stereotype.getExtensions().get(0),
				getModelPersonFirstNameEAttribute());

		InapplicableExtensionApplicationConstraintValidator validator = new InapplicableExtensionApplicationConstraintValidator(
				stereotypeApplication);
		Assert.assertTrue(validator.isViolated());
	}

	protected StereotypeApplication applyStereotypeBypassingFacade(
			Stereotype stereotype, Extension extension, EObject eObject) {
		StereotypeApplication stereotypeApplication = (StereotypeApplication) stereotype
				.getEPackage().getEFactoryInstance().create(stereotype);
		stereotypeApplication.setExtension(extension);
		stereotypeApplication.setAppliedTo(eObject);
		getProfileApplication().getStereotypeApplications().add(
				stereotypeApplication);
		return stereotypeApplication;
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
