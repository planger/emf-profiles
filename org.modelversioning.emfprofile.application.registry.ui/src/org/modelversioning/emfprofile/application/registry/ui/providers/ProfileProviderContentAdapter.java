package org.modelversioning.emfprofile.application.registry.ui.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.provider.EMFProfileApplicationItemProviderAdapterFactory;

public class ProfileProviderContentAdapter implements ITreeContentProvider {

	private AdapterFactoryContentProvider provider;

	public ProfileProviderContentAdapter() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory
				.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		provider = new AdapterFactoryContentProvider(adapterFactory);
	}

	private ITreeContentProvider getTreeContentProvider(Object element) {
		return (ITreeContentProvider) provider;
	}

	@Override
	public void dispose() {
		provider.dispose();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		provider.inputChanged(viewer, oldInput, newInput);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return getTreeContentProvider(inputElement).getElements(inputElement);
//		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Collection<?>) {
			return ((Collection<?>) parentElement).toArray();
		} 
//		else if (parentElement instanceof IProfileProvider) {
//			return ((IProfileProvider) parentElement).getProfile()
//					.getStereotypes().toArray();
//		}
//		
//		ArrayList<Object> result = new ArrayList<>();
//		result.add(parentElement);
//		
//		return result.toArray(); 
		if (parentElement instanceof ProfileApplication){
//			return ((ProfileApplication)parentElement).getStereotypeApplications().toArray();
//			return ((ProfileApplicationDecorator)parentElement).getStereotypeApplications().toArray();
//			return getTreeContentProvider( ((ProfileApplicationDecorator)parentElement).getProfileApplication()).getChildren( ((ProfileApplicationDecorator)parentElement).getProfileApplication());
		}
		return getTreeContentProvider(parentElement).getChildren(parentElement);
		
//		return filter(getTreeContentProvider(parentElement).getChildren(
//				parentElement));
	}

	private Object[] filter(Object[] contents) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Object object : contents) {
			if (!isStereotypeAppGenType(object)
					&& !EMFProfilePackage.eINSTANCE.getExtension().equals(
							object)) {
				list.add(object);
			}
		}
		return list.toArray();
	}

	private boolean isStereotypeAppGenType(Object object) {
		if (object instanceof EGenericType) {
			EGenericType eGenericType = (EGenericType) object;
			if (EMFProfileApplicationPackage.eINSTANCE
					.getStereotypeApplication().equals(
							eGenericType.getEClassifier())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getParent(Object element) {		
		return getTreeContentProvider(element).getParent(element);
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element) != null && getChildren(element).length > 0;
	}

}
