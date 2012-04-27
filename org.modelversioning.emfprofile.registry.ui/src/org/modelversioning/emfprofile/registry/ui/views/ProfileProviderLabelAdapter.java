package org.modelversioning.emfprofile.registry.ui.views;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.registry.IProfileProvider;

public class ProfileProviderLabelAdapter implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IProfileProvider) {
			// TODO return icon according to profile location
			return null;
		} else if (element instanceof Stereotype) {
			// TODO return stereotype icon
			return null;
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IProfileProvider) {
			return ((IProfileProvider) element).getProfileName();
		} else if (element instanceof Stereotype) {
			return ((Stereotype) element).getName();
		}
		return null;
	}

}
