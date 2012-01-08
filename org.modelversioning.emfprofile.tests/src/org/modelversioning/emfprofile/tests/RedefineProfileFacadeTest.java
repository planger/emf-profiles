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
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.validation.InapplicableExtensionApplicationConstraintValidator;

/**
 * Tests the {@link InapplicableExtensionApplicationConstraintValidator}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class RedefineProfileFacadeTest {

	private static final String CONCRETE_STEREOTYPE_FOR_ESTRUCTURALFEATURE_NAME = "ConcreteForEAttribute";
	private static final String REDEFINING_STEREOTYPE_FOR_EATTRIBUTE_NAME = "RedefiningStereotype";

	private static final String modelPath = "model/redefine/sample_ecore_model.ecore";
	private static final String profileWithRedefinePath = "model/redefine/profile_with_redefine_for_ecore_models.emfprofile_diagram";
	private static final String profileApplicationPath = "model/redefine/annotation.emfprofile.xmi";

	private final ResourceSet resourceSet = new ResourceSetImpl();

	private Profile profileWithRedefine;
	private Resource model;
	private Resource profileApplicationResource;

	@Before
	public void loadProfileWithSubset() {
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
	public void testInapplicabilityOfRedefiningStereotypeUsingRedefinedExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();

		// check subsetting stereotype with subsetted extension
		Extension redefinedExtension = getRedefinedExtension();
		Stereotype redefiningStereotype = getRedefiningStereotype();
		Assert.assertFalse(profileFacade.isApplicable(redefiningStereotype,
				getModelPersonFirstNameEAttribute(), redefinedExtension));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailingApplicationOfRedefiningStereotypeUsingRedefinedExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();

		// check subsetting stereotype with subsetted extension
		Extension redefinedExtension = getRedefinedExtension();
		Stereotype redefiningStereotype = getRedefiningStereotype();

		// this should cause an exception
		profileFacade.apply(redefiningStereotype,
				getModelPersonFirstNameEAttribute(), redefinedExtension);
	}

	@Test
	public void testInapplicabilityOfRedefinedExtensionForTargetOfRedefiningExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Extension redefinedExtension = getRedefinedExtension();

		// it is disallowed to use the redefined extension for the target type
		// of the redefining extension
		Assert.assertFalse(profileFacade.isApplicable(getRedefinedStereotype(),
				getModelPersonFirstNameEAttribute(), redefinedExtension));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailingApplicationOfOfRedefinedExtensionForTargetOfRedefiningExtension()
			throws IOException {
		IProfileFacade profileFacade = createProfileFacade();
		Extension redefinedExtension = getRedefinedExtension();

		// it is disallowed to use the redefined extension for the target type
		// of the redefining extension. thus, this should cause an exception
		profileFacade.apply(getRedefinedStereotype(),
				getModelPersonFirstNameEAttribute(), redefinedExtension);
	}

	private Stereotype getRedefiningStereotype() {
		return getStereotype(profileWithRedefine,
				REDEFINING_STEREOTYPE_FOR_EATTRIBUTE_NAME);
	}

	private Stereotype getRedefinedStereotype() {
		return getStereotype(profileWithRedefine,
				CONCRETE_STEREOTYPE_FOR_ESTRUCTURALFEATURE_NAME);
	}

	private Extension getRedefinedExtension() {
		return getRedefinedStereotype().getExtensions().get(0);
	}

	private IProfileFacade createProfileFacade() throws IOException {
		return createProfileFacade(profileWithRedefine);
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
