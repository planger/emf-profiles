package org.modelversioning.emfprofile.registry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EMFProfileRegistryPlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		EMFProfileRegistryPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		EMFProfileRegistryPlugin.context = null;
	}

}
