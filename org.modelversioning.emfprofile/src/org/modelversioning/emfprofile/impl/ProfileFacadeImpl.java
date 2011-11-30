/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.emfprofile.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.util.ProfileImportResolver;
import org.modelversioning.emfprofileapplication.validation.ValidationDelegateClientSelector;

/**
 * Implements the {@link IProfileFacade}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ProfileFacadeImpl implements IProfileFacade {

	private static final String STEREOTYPE_APP_RESOURCE_ERROR = "Specified resource for the "
			+ "stereotype application is not set, null, or unloaded.";
	/**
	 * Currently loaded profiles.
	 */
	private EList<Profile> profiles = new BasicEList<Profile>();
	/**
	 * Currently loaded profile application resource.
	 */
	private Resource profileApplicationResource;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeApplicable(Profile profile) {
		for (Stereotype stereotype : profile.getStereotypes()) {
			if (!stereotype.getESuperTypes().contains(
					STEREOTYPE_APPLICATION_ECLASS)) {
				stereotype.getESuperTypes().add(STEREOTYPE_APPLICATION_ECLASS);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save() throws IOException {
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		if (profileApplicationResource != null) {
			profileApplicationResource.save(saveOptions);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadProfile(Profile profile) {
		this.profiles.add(profile);
		if (profileApplicationResource != null) {
			profileApplicationResource.getResourceSet().getPackageRegistry()
					.put(profile.getNsURI(), profile);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unloadProfile(Profile profile) {
		this.profiles.remove(profile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadProfiles(Resource resource) {
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof Profile) {
				this.loadProfile((Profile) eObject);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadProfiles(EList<Profile> profiles) {
		this.profiles.addAll(profiles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<Profile> getLoadedProfiles() {
		return ECollections.unmodifiableEList(profiles);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IOException
	 */
	@Override
	public void setProfileApplicationResource(Resource resource)
			throws IOException {
		profileApplicationResource = resource;
		if (!profileApplicationResource.isLoaded()) {
			profileApplicationResource.load(Collections.emptyMap());
		}

		EcoreUtil.resolveAll(profileApplicationResource);

		// resolve profile imports
		EList<ProfileApplication> profileApplications = getProfileApplications(profileApplicationResource);
		for (ProfileApplication application : profileApplications) {
			for (final ProfileImport profileImport : application
					.getImportedProfiles()) {
				if (requireTransaction()) {
					TransactionalEditingDomain domain = getTransactionalEditingDomain();
					domain.getCommandStack().execute(
							new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									ProfileImportResolver.resolve(
											profileImport,
											profileApplicationResource
													.getResourceSet());
								}
							});
				} else {
					ProfileImportResolver.resolve(profileImport,
							profileApplicationResource.getResourceSet());
				}
				profiles.add(profileImport.getProfile());
			}
		}

		for (Profile profile : this.profiles) {
			this.profileApplicationResource.getResourceSet()
					.getPackageRegistry().put(profile.getNsURI(), profile);
		}
	}

	/**
	 * Returns a list of {@link ProfileApplication ProfileApplications}
	 * contained by the specified <code>resource</code>.
	 * 
	 * @param resource
	 *            to get contained {@link ProfileApplication
	 *            ProfileApplications}.
	 * @return the list of {@link ProfileApplication ProfileApplications}.
	 */
	private EList<ProfileApplication> getProfileApplications(Resource resource) {
		EList<ProfileApplication> profileApplications = new BasicEList<ProfileApplication>();
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof ProfileApplication) {
				profileApplications.add((ProfileApplication) eObject);
			}
		}
		return profileApplications;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<Stereotype> getApplicableStereotypes(EClass eClass) {
		EList<Stereotype> applicableStereotypes = new BasicEList<Stereotype>();
		for (Profile profile : profiles) {
			applicableStereotypes.addAll(profile
					.getApplicableStereotypes(eClass));
		}
		return applicableStereotypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<Stereotype> getApplicableStereotypes(EObject eObject) {
		EList<Stereotype> applicableStereotypes = new BasicEList<Stereotype>();

		// check applicability concerning type
		for (Profile profile : profiles) {
			applicableStereotypes.addAll(profile
					.getApplicableStereotypes(eObject.eClass()));
		}

		// check applicability for each
		for (Stereotype stereotype : new BasicEList<Stereotype>(
				applicableStereotypes)) {
			if (!isApplicable(stereotype, eObject)) {
				applicableStereotypes.remove(stereotype);
			}
		}
		return applicableStereotypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isApplicable(Stereotype stereotype, EObject eObject) {
		return stereotype.isApplicable(eObject,
				cast(getAppliedStereotypes(eObject)));
	}

	/**
	 * Converts a list of {@link StereotypeApplication} to a list of
	 * {@link Stereotype}.
	 * 
	 * @param applications
	 *            to convert.
	 * @return the converted list.
	 */
	private EList<Stereotype> cast(EList<StereotypeApplication> applications) {
		EList<Stereotype> stereotypes = new BasicEList<Stereotype>();
		for (StereotypeApplication application : applications) {
			if (application.eClass() instanceof Stereotype) {
				stereotypes.add((Stereotype) application.eClass());
			}
		}
		return stereotypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StereotypeApplication apply(Stereotype stereotype, EObject eObject) {
		if (!isApplicable(stereotype, eObject)) {
			throw new IllegalArgumentException(
					"Stereotype is not applicable to the object.");
		}
		StereotypeApplication stereotypeApplication = createStereotypeApplication(stereotype);
		apply(stereotypeApplication, eObject);
		return stereotypeApplication;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StereotypeApplication apply(Stereotype stereotype,
			EList<EObject> eObjects) {
		for (EObject eObject : eObjects) {
			if (!isApplicable(stereotype, eObject)) {
				throw new IllegalArgumentException(
						"Stereotype is not applicable to all objects.");
			}
		}
		StereotypeApplication stereotypeApplication = createStereotypeApplication(stereotype);
		apply(stereotypeApplication, eObjects);
		return stereotypeApplication;
	}

	/**
	 * Creates a new instance of of the specified {@link Stereotype}.
	 * 
	 * <p>
	 * The created instance is {{@link #addToResource(StereotypeApplication)}
	 * added} to the currently set resource.
	 * 
	 * @param stereotype
	 *            to create instance for.
	 * @return created instance.
	 */
	protected StereotypeApplication createStereotypeApplication(
			Stereotype stereotype) {
		final StereotypeApplication stereotypeInstance = (StereotypeApplication) stereotype
				.getEPackage().getEFactoryInstance().create(stereotype);
		final ProfileApplication profileApplication = findOrCreateProfileApplication(stereotype
				.getProfile());
		if (requireTransaction()) {
			TransactionalEditingDomain domain = getTransactionalEditingDomain();
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					profileApplication.getStereotypeApplications().add(
							stereotypeInstance);
				}
			});
		} else {
			profileApplication.getStereotypeApplications().add(
					stereotypeInstance);
		}
		return stereotypeInstance;
	}

	/**
	 * Finds or creates a profile application for the specified
	 * <code>profile</code>.
	 * 
	 * @param profile
	 *            to find or create {@link ProfileApplication} for.
	 * @return found or created {@link ProfileApplication}.
	 */
	private ProfileApplication findOrCreateProfileApplication(
			final Profile profile) {
		boolean found = false;
		ProfileApplication profileApplication = null;
		for (EObject eObject : profileApplicationResource.getContents()) {
			if (eObject instanceof ProfileApplication) {
				found = true;
				profileApplication = (ProfileApplication) eObject;
				final ProfileApplication finalProfileApplication = profileApplication;
				if (!hasProfileImport(profileApplication, profile)) {
					if (requireTransaction()) {
						TransactionalEditingDomain domain = getTransactionalEditingDomain();
						domain.getCommandStack().execute(
								new RecordingCommand(domain) {
									@Override
									protected void doExecute() {
										finalProfileApplication
												.getImportedProfiles()
												.add(createProfileImport(profile));
									}
								});
					} else {
						finalProfileApplication.getImportedProfiles().add(
								createProfileImport(profile));
					}
				}
				profileApplication = finalProfileApplication;
			}
		}
		if (!found) {
			profileApplication = createProfileApplication();
			profileApplication.getImportedProfiles().add(
					createProfileImport(profile));
			addToResource(profileApplication);
		}
		return profileApplication;
	}

	/**
	 * Creates an empty {@link ProfileApplication}.
	 * 
	 * @return the created {@link ProfileApplication}.
	 */
	private ProfileApplication createProfileApplication() {
		return EMF_PROFILE_APPLICATION_FACTORY.createProfileApplication();
	}

	/**
	 * Checks if the specified <code>profile</code> is already imported by the
	 * supplied <code>profileApplication</code>.
	 * 
	 * @param profileApplication
	 *            to search in.
	 * @param profile
	 *            to search for.
	 * @return <code>true</code> if an import exists, otherwise
	 *         <code>false</code>.
	 */
	private boolean hasProfileImport(ProfileApplication profileApplication,
			Profile profile) {
		for (ProfileImport profileImport : profileApplication
				.getImportedProfiles()) {
			try {
				if (profileImport.getNsURI().equals(profile.getNsURI())) {
					return true;
				}
			} catch (NullPointerException npe) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Creates a new {@link ProfileImport} for the supplied <code>profile</code>
	 * .
	 * 
	 * @param profile
	 *            to create {@link ProfileImport} for.
	 * @return the created {@link ProfileImport}.
	 */
	private ProfileImport createProfileImport(Profile profile) {
		ProfileImport profileImport = EMF_PROFILE_APPLICATION_FACTORY
				.createProfileImport();
		profileImport.setProfile(profile);
		return profileImport;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(final StereotypeApplication stereotypeApplication,
			final EObject eObject) {
		if (requireTransaction()) {
			TransactionalEditingDomain domain = getTransactionalEditingDomain();
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					getAppliedToList(stereotypeApplication).add(eObject);
				}
			});
		} else {
			getAppliedToList(stereotypeApplication).add(eObject);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(final StereotypeApplication stereotypeApplication,
			final EList<EObject> eObjects) {
		if (requireTransaction()) {
			TransactionalEditingDomain domain = getTransactionalEditingDomain();
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					getAppliedToList(stereotypeApplication).addAll(eObjects);
				}
			});
		} else {
			getAppliedToList(stereotypeApplication).addAll(eObjects);
		}
	}

	/**
	 * Returns the list of {@link EObject EObjects} to which the specified
	 * <code>stereotypeApplication</code> is applied to.
	 * 
	 * @param stereotypeApplication
	 *            to get {@link EObject EObjects} to which it is applied to.
	 * @return the list of {@link EObject EObjects} to which the specified
	 *         <code>stereotypeApplication</code> is applied to
	 */
	protected EList<EObject> getAppliedToList(
			StereotypeApplication stereotypeApplication) {
		return stereotypeApplication.getAppliedTo();
	}

	/**
	 * Adds the specified <code>eObject</code> to the currently set
	 * {@link #profileApplicationResource}.
	 * 
	 * <p>
	 * If currently no {@link #profileApplicationResource} is set, this method
	 * throws an {@link IllegalArgumentException}.
	 * </p>
	 * 
	 * @param eObject
	 *            to be added.
	 * 
	 * @exception IllegalArgumentException
	 *                if {@link #profileApplicationResource} is not
	 *                {@link #setProfileApplicationResource(Resource) set}.
	 */
	protected void addToResource(final EObject eObject) {
		if (profileApplicationResource == null
				|| !profileApplicationResource.isLoaded()) {
			throw new IllegalArgumentException(STEREOTYPE_APP_RESOURCE_ERROR);
		} else {
			if (!profileApplicationResource.getContents().contains(eObject)) {
				if (requireTransaction()) {
					TransactionalEditingDomain domain = getTransactionalEditingDomain();
					domain.getCommandStack().execute(
							new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									profileApplicationResource.getContents()
											.add(eObject);
								}
							});
				} else {
					profileApplicationResource.getContents().add(eObject);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<StereotypeApplication> getStereotypeApplications() {
		for (EObject contentObject : this.profileApplicationResource
				.getContents()) {
			if (contentObject instanceof ProfileApplication) {
				return ((ProfileApplication) contentObject)
						.getStereotypeApplications();
			}
		}
		return ECollections.emptyEList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<StereotypeApplication> getAppliedStereotypes(EObject eObject) {
		EList<StereotypeApplication> appliedStereotypes = new BasicEList<StereotypeApplication>();
		for (StereotypeApplication stereotypeApplication : getStereotypeApplications()) {
			for (EObject appliedToObject : stereotypeApplication.getAppliedTo()) {
				if (resolvedEquals(appliedToObject, eObject)) {
					appliedStereotypes.add(stereotypeApplication);
				}
			}
		}
		return appliedStereotypes;
	}

	/**
	 * Returns all stereotypes currently applied to the specified
	 * <code>eObject</code> (list of {@link StereotypeApplication}s) that are of
	 * the type <code>stereotype</code>.
	 * 
	 * @param stereotype
	 *            the stereotype to filter stereotype applications.
	 * @param eObject
	 *            to get applied stereotypes for.
	 * @return the list of {@link StereotypeApplication}s.
	 */
	protected EList<StereotypeApplication> getAppliedStereotypes(
			Stereotype stereotype, EObject eObject) {
		EList<StereotypeApplication> appliedStereotypes = getAppliedStereotypes(eObject);
		for (StereotypeApplication application : new BasicEList<StereotypeApplication>(
				appliedStereotypes)) {
			if (application.eClass() != stereotype) {
				appliedStereotypes.remove(application);
			}
		}
		return appliedStereotypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeStereotypeApplication(
			final StereotypeApplication stereotypeApplication) {
		if (requireTransaction()) {
			TransactionalEditingDomain domain = getTransactionalEditingDomain();
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					getStereotypeApplications().remove(stereotypeApplication);
				}
			});
		} else {
			getStereotypeApplications().remove(stereotypeApplication);
		}
	}

	/**
	 * Specifies whether the two specified {@link EObject EObjects} are equal
	 * irrespectively of their resource.
	 * 
	 * @param eObject1
	 *            first {@link EObject}
	 * @param eObject2
	 *            second {@link EObject}
	 * @return <code>true</code> if equal, <code>false</code> otherwise.
	 */
	private boolean resolvedEquals(EObject eObject1, EObject eObject2) {
		if (eObject1.equals(eObject2)) {
			return true;
		}
		String eObject1URIFrag = eObject1.eResource().getURIFragment(eObject1);
		String eObject2URIFrag = eObject2.eResource().getURIFragment(eObject2);
		if (eObject1URIFrag.equals(eObject2URIFrag)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<EStructuralFeature> getStereotypeFeatures(Stereotype stereotype) {
		EList<EStructuralFeature> features = new BasicEList<EStructuralFeature>();
		for (EStructuralFeature feature : stereotype
				.getEAllStructuralFeatures()) {
			if (!STEREOTYPE_APPLICATION_APPLIED_TO_REFERENCE.equals(feature)) {
				features.add(feature);
			}
		}
		return features;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getStereotypeApplicationFeatureValue(
			EObject stereotypeApplication, EStructuralFeature feature) {
		return stereotypeApplication.eGet(feature);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setStereotypeApplicationFeatureValue(
			EObject stereotypeApplication, EStructuralFeature feature,
			Object newValue) {
		if (requireTransaction()) {
			TransactionalEditingDomain domain = getTransactionalEditingDomain();
			Command command = domain.createCommand(SetCommand.class,
					new CommandParameter(stereotypeApplication, feature,
							newValue));
			domain.getCommandStack().execute(command);
		} else {
			stereotypeApplication.eSet(feature, newValue);
		}
	}

	/**
	 * Specifies whether transaction aware modifications are required for
	 * {@link #profileApplicationResource}.
	 * 
	 * @return <code>true</code>, if {@link #profileApplicationResource} is
	 *         handled by a {@link TransactionalEditingDomain},
	 *         <code>false</code> otherwise.
	 */
	private boolean requireTransaction() {
		return getTransactionalEditingDomain() != null;
	}

	/**
	 * Returns the {@link TransactionalEditingDomain} of the
	 * {@link #profileApplicationResource}. If there is no
	 * {@link TransactionalEditingDomain}, this method returns
	 * <code>null.</code>
	 * 
	 * @return the {@link TransactionalEditingDomain} or <code>null</code>
	 */
	private TransactionalEditingDomain getTransactionalEditingDomain() {
		return TransactionUtil.getEditingDomain(profileApplicationResource);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unload() {
		// noop
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validateAll() {
		ValidationDelegateClientSelector.running = true;
		IBatchValidator validator = (IBatchValidator) ModelValidationService
				.getInstance().newValidator(EvaluationMode.BATCH);
		validator.setIncludeLiveConstraints(true);
		validator.setReportSuccesses(true);
		IStatus status = validator
				.validate(getProfileApplications(profileApplicationResource));
		ValidationDelegateClientSelector.running = false;
		return status;
	}

}
