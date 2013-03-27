package org.modelversioning.emfprofile.registry;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.internal.ProfileRegistry;

/**
 * Registry for all available {@link Profile profiles}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public interface IProfileRegistry {

	public static final String PROFILE_EXTENSION_POINT_ID = "org.modelversioning.emfprofile.profile"; //$NON-NLS-1$
	public static final String PROFILE_EXTENSION_POINT_RESOURCE_NAME = "profile_resource"; //$NON-NLS-1$

	/**
	 * The singleton instance.
	 */
	public static final IProfileRegistry INSTANCE = new ProfileRegistry();

	/**
	 * Returns an unmodifiable collection of all registered profiles.
	 * 
	 * @return all registered profiles.
	 */
	Collection<Profile> getRegisteredProfiles();

	/**
	 * Returns an unmodifiable collection of all registered profile providers.
	 * 
	 * @return all registered profile providers.
	 */
	Collection<IProfileProvider> getRegisteredProfileProviders();

	/**
	 * Returns the profile provider that provides the specified {@code profile}.
	 * 
	 * @param profile
	 *            the {@link Profile}
	 * @return the {@link IProfileProvider provider} that provides the
	 *         {@code profile} or <code>null</code>.
	 */
	IProfileProvider getProfileProvider(Profile profile);

	/**
	 * Registers the specified profile.
	 * 
	 * @param profileProvider
	 *            provider for the profile to register.
	 */
	void registerProfile(IProfileProvider profileProvider);

	/**
	 * Removes the specified profile from the registry.
	 * 
	 * @param profileProvider
	 *            provider for the profile to register.
	 */
	void unregisterProfile(IProfileProvider profileProvider);

	/**
	 * @see Observable#addObserver(Observer)
	 * @param observer
	 */
	void addObserver(Observer observer);

}
