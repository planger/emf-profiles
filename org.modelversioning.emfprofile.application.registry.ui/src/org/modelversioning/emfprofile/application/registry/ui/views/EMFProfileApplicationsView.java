/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.views;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;
import org.modelversioning.emfprofile.application.registry.ui.observer.NestingCommonModelElementsInStereotypeApplications;
import org.modelversioning.emfprofile.application.registry.ui.providers.ProfileApplicationDecoratorReflectiveItemProviderAdapterFactory;
import org.modelversioning.emfprofile.application.registry.ui.providers.ProfileProviderContentAdapter;
import org.modelversioning.emfprofile.application.registry.ui.providers.ProfileProviderLabelAdapter;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.modelversioning.emfprofileapplication.provider.EMFProfileApplicationItemProviderAdapterFactory;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 * 
 */
public class EMFProfileApplicationsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.modelversioning.emfprofile.application.registry.ui.views.EMFProfileApplicationsView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private NestingCommonModelElementsInStereotypeApplications nestingCommonModelElements;

	private PropertySheetPage propertySheetPage;
	private static LocalResourceManager resourceManager;
	private static ComposedAdapterFactory adapterFactory;

	/**
	 * The constructor.
	 */
	public EMFProfileApplicationsView() {
		adapterFactory = EMFProfileApplicationsView.getAdapterFactory();
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setLabelProvider(new ProfileProviderLabelAdapter(
				getAdapterFactory()));
		viewer.setContentProvider(new ProfileProviderContentAdapter(
				getAdapterFactory()));

		viewer.setSorter(createGenericEObjectSorter());
		viewer.setAutoExpandLevel(2);
		getSite().setSelectionProvider(viewer);
		EMFProfileApplicationsView.resourceManager = new LocalResourceManager(
				JFaceResources.getResources());
		viewer.setInput(Collections.emptyList());
		ActiveEditorObserver.INSTANCE.setViewer(viewer);

		new AdapterFactoryTreeEditor(viewer.getTree(), adapterFactory);

		MenuManager menuManager = new MenuManager(
				"profileApplicationsPopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EMFProfileApplicationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		getSite().registerContextMenu(menuManager, viewer);
		// To this group come New Child/Sibling contributions
		menuManager.add(new Separator("edit"));
		// Other plug-ins can contribute there actions here
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		drillDownAdapter.addNavigationActions(menuManager);
		drillDownAdapter.addNavigationActions(getViewSite().getActionBars()
				.getToolBarManager());
		getViewSite().getActionBars().getToolBarManager().add(new Separator());
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeViewer viewer = (TreeViewer) event.getViewer();
				IStructuredSelection thisSelection = (IStructuredSelection) event
						.getSelection();
				Object selectedNode = thisSelection.getFirstElement();
				viewer.setExpandedState(selectedNode,
						!viewer.getExpandedState(selectedNode));
			}
		});

		nestingCommonModelElements = new NestingCommonModelElementsInStereotypeApplications();
		nestingCommonModelElements.contributeToMenu(menuManager);
		menuManager.addMenuListener(nestingCommonModelElements);
		viewer.addSelectionChangedListener(nestingCommonModelElements);

		// To enable the key binding we need to activate context
		// The reason why, is because this context overrides the
		// default key binding of workbench, e.g. key DEL
		IContextService contextService = (IContextService) getSite()
				.getService(IContextService.class);
		IContextActivation contextActivation = contextService
				.activateContext("org.modelversioning.emfprofile.application.registry.ui.keybindingcontext");

	}

	private ViewerSorter createGenericEObjectSorter() {
		return new ViewerSorter() {
			@Override
			public int category(Object element) {
				if (element instanceof EObject) {
					EObject eObject = (EObject) element;
					if (eObject.eContainmentFeature() != null) {
						eObject.eContainmentFeature().getFeatureID();
					}
				}
				return super.category(element);
			}

			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof EObject && e2 instanceof EObject) {
					EObject eObject1 = (EObject) e1;
					EObject eObject2 = (EObject) e2;
					if (haveSameContainerAndContainmentFeature(eObject1,
							eObject2)) {
						int indexEObject1 = getIndex(eObject1);
						int indexEObject2 = getIndex(eObject2);
						if (indexEObject1 < indexEObject2) {
							return -1;
						} else if (indexEObject1 > indexEObject2) {
							return 1;
						} else {
							return 0;
						}
					}
				}
				return super.compare(viewer, e1, e2);
			}

			private boolean haveSameContainerAndContainmentFeature(
					EObject eObject1, EObject eObject2) {
				return eObject1.eContainer() != null
						&& eObject2.eContainer() != null
						&& eObject1.eContainer().equals(eObject2.eContainer())
						&& eObject1.eContainmentFeature().equals(
								eObject2.eContainmentFeature());
			}

			private int getIndex(EObject eObject) {
				if (eObject.eContainmentFeature().isMany()) {
					Object containmentValue = eObject.eContainer().eGet(
							eObject.eContainmentFeature());
					if (containmentValue instanceof List<?>) {
						List<?> list = (List<?>) containmentValue;
						return list.indexOf(eObject);
					}
				}
				return 0;
			}
		};
	}

	/**
	 * Provides the adapters.
	 * 
	 * In particular this is the property sheet. The rest is handed over to
	 * super.
	 * 
	 * @param adapter
	 *            to get.
	 * @return the adapter.
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		} else {
			return super.getAdapter(adapter);
		}
	}

	public static ComposedAdapterFactory getAdapterFactory() {
		if (adapterFactory != null)
			return adapterFactory;

		// adapterFactory = new
		// ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		// this made some problems because dynamically created objects
		// (EObjectImpl) should have get reflective item provider, but they
		// didn't.
		// the provider that was supplied was EObjectItemProvider from
		// EcoreItemProviderAdapterFactory even if I didn't add it explicitly.
		// The cause was in providing the parameter for constructor -> new
		// ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)
		// This somehow added EcoreItemProviderAdapterFactory to the collection
		// of Composed Adapter Factory.

		adapterFactory = new ComposedAdapterFactory(); // this works as
														// expected.
		adapterFactory
				.addAdapterFactory(new EMFProfileItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		return adapterFactory;
	}

	/**
	 * This accesses a cached version of the property sheet.
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new PropertySheetPage();
			// only set the reflective adapter factory
			propertySheetPage
					.setPropertySourceProvider(new AdapterFactoryContentProvider(
							new ProfileApplicationDecoratorReflectiveItemProviderAdapterFactory()));
		}
		return propertySheetPage;
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		manager.add(new Separator());
		// To this group come New Child/Sibling contributions
		manager.add(new Separator("edit"));
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * After the view has been initialized, this method can be used to create an
	 * image for the descriptor. Images created in this way do not need to be
	 * extra disposed in the code. Images are created with usage of
	 * {@link LocalResourceManager} which takes care of disposal when the UI
	 * part is disposed.
	 * 
	 * @param imageDescriptor
	 * @return image or null if the image could not be located.
	 */
	public static Image createImage(ImageDescriptor imageDescriptor) {
		try {
			if (EMFProfileApplicationsView.resourceManager != null)
				return EMFProfileApplicationsView.resourceManager
						.createImage(imageDescriptor);
		} catch (DeviceResourceException e) {
			// e.printStackTrace();
		}
		return null;
	}

	@Override
	public void dispose() {
		ActiveEditorObserver.INSTANCE.cleanUp();

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		if (adapterFactory != null) {
			adapterFactory.dispose();
			adapterFactory = null;
		}
		// dispose our resource manager for images
		EMFProfileApplicationsView.resourceManager.dispose();
		super.dispose();
	}

}