package org.modelversioning.emfprofile.application.registry.ui.providers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.views.EMFProfileApplicationsView;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.provider.EMFProfileApplicationItemProviderAdapterFactory;

public class ProfileProviderLabelAdapter implements ILabelProvider {

	private AdapterFactoryLabelProvider provider;

	public ProfileProviderLabelAdapter() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory
				.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
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
//		if (element instanceof IProfileProvider) {
//			IProfileProvider profileProvider = (IProfileProvider) element;
//			if (isPluginProvided(profileProvider)) {
//				return EMFProfileRegistryImages
//						.get(EMFProfileRegistryImages.IMG_PLUGIN_PROFILE);
//			} else {
//				return EMFProfileRegistryImages
//						.get(EMFProfileRegistryImages.IMG_PROJECT_PROFILE);
//			}
//		}
		if(element instanceof StereotypeApplication){
			Extension extension = ((StereotypeApplication) element).getExtension();
			EObject object = ((StereotypeApplication) element).getAppliedTo();
			Stereotype stereotype = ((StereotypeApplication)element).getStereotype();
			if(stereotype.hasIcon()){
				URL url = null;
				try {
					url = new URL("file:" + ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile() + ((StereotypeApplication)element).getStereotype().getIconPath());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(url != null){
					Image image = EMFProfileApplicationsView.createImage(ImageDescriptor.createFromURL(url));
//					new Image(Display.getDefault() ,
//							ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + ((StereotypeApplication)element).getStereotype().getIconPath())));
			return image;
				}
			}
		}
		return getLabelProvider(element).getImage(element);
	}

	@Override
	public String getText(Object element) {
		if(element instanceof ProfileApplicationDecorator){
			return ((ProfileApplicationDecorator) element).getName();
		}
		if(element instanceof StereotypeApplication){
			StereotypeApplication stereotypeApplication = (StereotypeApplication) element;
			Stereotype stereotype = (stereotypeApplication).getStereotype();
			Extension extension = stereotypeApplication.getExtension();
			EObject eObject = stereotypeApplication.getAppliedTo();
			if(eObject instanceof ENamedElement){
				ENamedElement namedElement = (ENamedElement) eObject;
				return stereotype.getName() + " -> " + namedElement.getName();
			}
		}
		return getLabelProvider(element).getText(element);
	}

}
