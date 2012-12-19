package org.modelversioning.emfprofile.application.registry.ui.providers;

import java.net.URL;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.views.EMFProfileApplicationsView;
import org.modelversioning.emfprofileapplication.StereotypeApplicability;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

public class ProfileProviderLabelAdapter implements ILabelProvider {

	private AdapterFactoryLabelProvider provider;

	public ProfileProviderLabelAdapter(ComposedAdapterFactory adapterFactory) {
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
		Stereotype stereotype = null;
		if(element instanceof StereotypeApplication){
			stereotype = ((StereotypeApplication)element).getStereotype();
		}else if(element instanceof StereotypeApplicability){
			stereotype = ((StereotypeApplicability)element).getStereotype();	
		}
		
		if(stereotype != null && stereotype.hasIcon()){
			URL url = getPlatformURLToImageOfStereotype(stereotype);
			if(url != null){
				try {
					Image image = EMFProfileApplicationsView.createImage(ImageDescriptor.createFromURL(url));
					return image;
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
			}
		}
		return getLabelProvider(element).getImage(element);
	}
	
	private URL getPlatformURLToImageOfStereotype(Stereotype stereotype){
		URL url = null;
		try{
			String uriToProfileResource = stereotype.getProfile().eResource().getURI().toString();
			String result = uriToProfileResource; //.replaceFirst("/resource/", "/plugin/");
			String strResource = "resource/";
			result = result.substring(0, result.indexOf("/", result.indexOf(strResource)+strResource.length() + 1)+1);
			result += stereotype.getIconPath();
			url = new URL(result);
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
		return url;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof ProfileApplicationDecorator){
			ProfileApplicationDecorator profileApplicationDecorator = (ProfileApplicationDecorator) element;
			return profileApplicationDecorator.isDirty() ? "*"+profileApplicationDecorator.getName() : profileApplicationDecorator.getName();
		}
		return getLabelProvider(element).getText(element);
	}

}
