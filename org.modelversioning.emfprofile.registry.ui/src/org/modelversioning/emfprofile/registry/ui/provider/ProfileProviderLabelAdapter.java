package org.modelversioning.emfprofile.registry.ui.provider;

import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.modelversioning.emfprofile.registry.IProfileProvider;
import org.modelversioning.emfprofile.registry.ui.EMFProfileRegistryImages;

public class ProfileProviderLabelAdapter implements ILabelProvider {

	private AdapterFactoryLabelProvider provider;

	public ProfileProviderLabelAdapter() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory
				.addAdapterFactory(new EMFProfileItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		provider = new AdapterFactoryLabelProvider(adapterFactory);
	}

	private ILabelProvider getLabelProvider(Object element) {
		return (ILabelProvider) provider;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		provider.addListener(listener);
	}

	@Override
	public void dispose() {
		provider.dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		provider.removeListener(listener);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IProfileProvider) {
			IProfileProvider profileProvider = (IProfileProvider) element;
			if (isPluginProvided(profileProvider)) {
				return EMFProfileRegistryImages
						.get(EMFProfileRegistryImages.IMG_PLUGIN_PROFILE);
			} else {
				return EMFProfileRegistryImages
						.get(EMFProfileRegistryImages.IMG_PROJECT_PROFILE);
			}
		}
		return getLabelProvider(element).getImage(element);
	}

	private boolean isPluginProvided(IProfileProvider profileProvider) {
		return profileProvider.getProfileLocationType().equals(
				IProfileProvider.ProfileLocationType.BUNDLE);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IProfileProvider) {
			return ((IProfileProvider) element).getProfileName();
		}
		return getLabelProvider(element).getText(element);
	}

}
