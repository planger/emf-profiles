package org.modelversioning.emfprofile.application.decorator.gmf;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EMFProfileApplicationGMFDecoratorPlugin implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		EMFProfileApplicationGMFDecoratorPlugin.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		EMFProfileApplicationGMFDecoratorPlugin.context = null;
	}

}
