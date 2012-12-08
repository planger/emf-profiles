/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.EMFProfileApplicationDecorator;
import org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator.PluginExtensionOperationsListener;

/**
 * This handler class looks in platforms extension registry for 
 * {@link EMFProfileApplicationDecorator} extension points and
 * manages a map of editor part id to decorators that can decorate
 * the elements of that editor.
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 * 
 */
public class EMFProfileApplicationDecoratorHandler {
	
	private static EMFProfileApplicationDecoratorHandler INSTANCE;
	
	public static EMFProfileApplicationDecoratorHandler getInstance() {
		if(EMFProfileApplicationDecoratorHandler.INSTANCE == null)
			EMFProfileApplicationDecoratorHandler.INSTANCE = new EMFProfileApplicationDecoratorHandler();
		return EMFProfileApplicationDecoratorHandler.INSTANCE;
	}

	public static final String DECORATOR_ID = "org.modelversioning.emfprofile.application.registry.ui.extensionpoint.decorator";

	/** editor part id, decorators that support it */
	private Map<String, Collection<EMFProfileApplicationDecorator>> decorators = new HashMap<>();

	// hidden default constructor
	private EMFProfileApplicationDecoratorHandler() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(DECORATOR_ID);
		
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				Object o = null;
				try {
					o = e.createExecutableExtension("class");
				} catch (CoreException e2) {
					e2.printStackTrace();
				}
				if(o != null){
					EMFProfileApplicationDecorator decorator = (EMFProfileApplicationDecorator) o;
					String[] supportedEditorIDs = null;
					try {
						supportedEditorIDs = decorator.canDecorateEditorParts();
//						decorator.setPluginExtensionOperationsListener(ActiveEditorObserver.INSTANCE);
						for (String id : supportedEditorIDs) {
							if( ! decorators.containsKey(id)){
								decorators.put(id, new ArrayList<EMFProfileApplicationDecorator>());
							}
							decorators.get(id).add(decorator);
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			
			}
			
			System.out.println("DECORATOR HANDLER CONTAINS FOLLOWING DECORATORS:");
			for (String id : decorators.keySet()) {
				System.out.println("editor id: " +id + ", has decorators amount: " + decorators.get(id).size());
			}
		
	}

	public boolean hasDecoratorForEditorPart(IWorkbenchPart part){
		if(part instanceof IEditorPart){
			return decorators.containsKey(part.getSite().getId());
		}
		return false;
	}
	
	public EMFProfileApplicationDecorator getDecoratorForEditorPart(IWorkbenchPart part){
		return decorators.get(part.getSite().getId()).iterator().next();
	}
	
	public void setPluginExtensionOperationsListener(PluginExtensionOperationsListener listener){
		for (Collection<EMFProfileApplicationDecorator> _decorators : decorators.values()) {
			for (EMFProfileApplicationDecorator emfProfileApplicationDecorator : _decorators) {
				emfProfileApplicationDecorator.setPluginExtensionOperationsListener(listener);
			}
		}
	}
	
	public void unsetPluginExtensionOperationsListener(){
		for (Collection<EMFProfileApplicationDecorator> _decorators : decorators.values()) {
			for (EMFProfileApplicationDecorator emfProfileApplicationDecorator : _decorators) {
				emfProfileApplicationDecorator.setPluginExtensionOperationsListener(null);
			}
		}
	}

//	public void execute(IExtensionRegistry registry) {
//		evaluate(registry);
//	}
//
//	private void evaluate(IExtensionRegistry registry) {
//		IConfigurationElement[] config = registry
//				.getConfigurationElementsFor(DECORATOR_ID);
//		try {
//			for (IConfigurationElement e : config) {
//				System.out.println("Evaluating extension");
//				final Object o = e.createExecutableExtension("class");
//				if (o instanceof EMFProfileApplicationDecorator) {
//					executeExtension(o);
//				}
//			}
//		} catch (CoreException ex) {
//			System.out.println(ex.getMessage());
//		}
//	}
//
//	private void executeExtension(final Object o) {
//		ISafeRunnable runnable = new ISafeRunnable() {
//			@Override
//			public void handleException(Throwable e) {
//				System.out.println("Exception in client");
//			}
//
//			@Override
//			public void run() throws Exception {
//				String greet = ((EMFProfileApplicationDecorator) o).greet();
//
//				System.out.println("MELDUNG VOM DECORATOR: " + greet);
//			}
//		};
//		SafeRunner.run(runnable);
//	}
}
