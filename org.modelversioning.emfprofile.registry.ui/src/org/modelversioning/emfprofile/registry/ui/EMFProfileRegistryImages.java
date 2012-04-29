package org.modelversioning.emfprofile.registry.ui;

import java.net.URL;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class EMFProfileRegistryImages {

	private static final String NAME_PREFIX = EMFProfileRegistryUIPlugin.PLUGIN_ID
			+ "."; //$NON-NLS-1$

	private static ImageRegistry PLUGIN_REGISTRY;

	public final static String ICONS_PATH = "icons/"; //$NON-NLS-1$

	public static final String IMG_PROJECT_PROFILE = NAME_PREFIX
			+ "IMG_PROJECT_PROFILE"; //$NON-NLS-1$
	public static final String IMG_PLUGIN_PROFILE = NAME_PREFIX
			+ "IMG_PLUGIN_PROFILE"; //$NON-NLS-1$

	public static final ImageDescriptor DESC_PLUGIN_PROFILE = create(
			ICONS_PATH, "plugin.png"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PROJECT_PROFILE = create(
			ICONS_PATH, "project.png"); //$NON-NLS-1$

	private static ImageDescriptor create(String prefix, String name) {
		return ImageDescriptor.createFromURL(makeImageURL(prefix, name));
	}

	public static Image get(String key) {
		if (PLUGIN_REGISTRY == null)
			initialize();
		return PLUGIN_REGISTRY.get(key);
	}

	private static final void initialize() {
		PLUGIN_REGISTRY = new ImageRegistry();
		manage(IMG_PLUGIN_PROFILE, DESC_PLUGIN_PROFILE);
		manage(IMG_PROJECT_PROFILE, DESC_PROJECT_PROFILE);
	}

	private static URL makeImageURL(String prefix, String name) {
		String path = "$nl$/" + prefix + name; //$NON-NLS-1$
		return FileLocator.find(EMFProfileRegistryUIPlugin.getDefault()
				.getBundle(), new Path(path), null);
	}

	public static Image manage(String key, ImageDescriptor desc) {
		Image image = desc.createImage();
		PLUGIN_REGISTRY.put(key, image);
		return image;
	}
}
