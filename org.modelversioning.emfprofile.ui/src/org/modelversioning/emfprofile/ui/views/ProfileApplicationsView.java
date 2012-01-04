/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package org.modelversioning.emfprofile.ui.views;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.modelversioning.emfprofileapplication.provider.EMFProfileApplicationItemProviderAdapterFactory;

/**
 * {@link ViewPart} for profile applications.
 * 
 * FIXME check if profile is applicable based on base-relationship
 * 
 * TODO remove appliedTo, ... from property sheet
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class ProfileApplicationsView extends ViewPart implements IPartListener,
		ISelectionChangedListener, ITreeContentProvider, ISelectionProvider {

	/** ID of the view as specified by the extension. */
	public static final String ID = "org.modelversioning.emfprofile.ui.views.ProfileApplicationsView";

	/** Content tree viewer. */
	private TreeViewer viewer;

	/** Mapping of registered (root edit or workbench) parts to profile facades. */
	private Map<Object, IProfileFacade> partProfileMapping = new HashMap<Object, IProfileFacade>();

	/** Content facade. */
	private IProfileFacade currentProfileFacade;

	/** Currently selected {@link EObject}. */
	private EObject currentEObject = null;

	/** The property sheet page. */
	protected PropertySheetPage propertySheetPage;

	/**
	 * Specifies whether this view is sticky, i.e., won't refresh on selection
	 * change.
	 */
	private boolean sticky = false;

	/** This is the one adapter factory used for providing views of the model. */
	protected ComposedAdapterFactory adapterFactory;

	/** Validate stereotype application action */
	private Action validateStereotypeApplicationAction;

	/** Save stereotype application action */
	private Action saveStereotypeApplicationAction;

	/** Save all stereotype application action */
	private Action saveAllStereotypeApplicationsAction;

	/** Remove selected stereotype application action */
	private Action removeStereotypeApplication;

	/**
	 * The constructor.
	 */
	public ProfileApplicationsView() {
		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new EMFProfileItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}
		if (adapterFactory != null) {
			adapterFactory.dispose();
		}
		if (currentProfileFacade != null) {
			currentProfileFacade.unload();
		}
		super.dispose();
	}

	/**
	 * Returns the property sheet page. If necessary, this initializes the sheet
	 * page.
	 * 
	 * @return the property sheet page.
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new PropertySheetPage();
			// only set the reflective adapter factory
			propertySheetPage
					.setPropertySourceProvider(new AdapterFactoryContentProvider(
							new ReflectiveItemProviderAdapterFactory()));
		}

		return propertySheetPage;
	}

	/**
	 * Adds the supplied <code>workbenchPart</code> and
	 * <code>profileFacade</code> to this view.
	 * 
	 * @param workbenchPart
	 *            to add.
	 * @param profileFacade
	 *            to add.
	 */
	public void addToView(IWorkbenchPart workbenchPart,
			IProfileFacade profileFacade) {
		partProfileMapping.put(workbenchPart, profileFacade);
		registerWorkbenchPart(workbenchPart);
		updateView();
	}

	/**
	 * Removes the supplied <code>workbenchPart</code> from this view.
	 * 
	 * @param workbenchPart
	 *            to remove.
	 */
	public void removeFromView(IWorkbenchPart workbenchPart) {
		partProfileMapping.remove(workbenchPart);
		unregisterWorkbenchPart(workbenchPart);
		updateView();
	}

	/**
	 * Registers the supplied <code>workbenchPart</code>.
	 * 
	 * @param workbenchPart
	 *            to register.
	 */
	private void registerWorkbenchPart(IWorkbenchPart workbenchPart) {
		workbenchPart.getSite().getPage().addPartListener(this);
		ISelectionProvider selectionProvider = workbenchPart.getSite()
				.getSelectionProvider();
		if (selectionProvider != null) {
			selectionProvider.addSelectionChangedListener(this);
		}
	}

	/**
	 * Unregisters the supplied <code>workbenchPart</code>.
	 * 
	 * @param workbenchPart
	 *            to unregister.
	 */
	private void unregisterWorkbenchPart(IWorkbenchPart workbenchPart) {
		workbenchPart.getSite().getPage().removePartListener(this);
		ISelectionProvider selectionProvider = workbenchPart.getSite()
				.getSelectionProvider();
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
	}

	/**
	 * Adds the supplied <code>editPart</code> and <code>profileFacade</code> to
	 * this view.
	 * 
	 * @param editPart
	 *            to add.
	 * @param profileFacade
	 *            to add.
	 */
	public void addToView(RootEditPart editPart, IProfileFacade profileFacade) {
		partProfileMapping.put(editPart, profileFacade);
		registerEditPart(editPart);
		updateView();
	}

	/**
	 * Removes the supplied <code>editPart</code> from this view.
	 * 
	 * @param editPart
	 *            to remove.
	 */
	public void removeFromView(RootEditPart editPart) {
		partProfileMapping.remove(editPart);
		unregisterEditPart(editPart);
		updateView();
	}

	/**
	 * Registers the supplied <code>editPart</code>.
	 * 
	 * @param editPart
	 *            to register.
	 */
	private void registerEditPart(RootEditPart editPart) {
		// TODO add listener to clear up if disposed
		editPart.getViewer().addSelectionChangedListener(this);
	}

	/**
	 * Unregisters the supplied <code>editPart</code>.
	 * 
	 * @param editPart
	 *            to unregister.
	 */
	private void unregisterEditPart(RootEditPart editPart) {
		editPart.getViewer().removeSelectionChangedListener(this);
	}

	/**
	 * Saves the current profile application.
	 */
	public void save() {
		if (currentProfileFacade != null) {
			try {
				currentProfileFacade.save();
			} catch (IOException e) {
				showError("Error while saving profile application resource", e);
			}
		}
	}

	/**
	 * Saves all currently loaded profile applications.
	 */
	public void saveAll() {
		for (IProfileFacade facade : getProfileFacades()) {
			try {
				facade.save();
			} catch (IOException e) {
				showError("Error while saving profile application resource", e);
			}
		}
	}

	/**
	 * Validates all currently loaded profile applications.
	 */
	public void validateAll() {
		// TODO provide nicer way to show diagnostics (even with error markers?)
		Diagnostic diagnostic = currentProfileFacade.validateAll(currentEObject);
		DiagnosticDialog dialog = new DiagnosticDialog(getSite().getShell(),
				"Profile Application", diagnostic.getMessage(), diagnostic,
				diagnostic.getSeverity());
		dialog.setBlockOnOpen(true);
		dialog.open();
	}

	/**
	 * Returns all currently registered profile facades.
	 * 
	 * @return all currently registered profile facades.
	 */
	private Collection<IProfileFacade> getProfileFacades() {
		return partProfileMapping.values();
	}

	/**
	 * Sets the current {@link EObject}.
	 * 
	 * @param eObject
	 *            to set.
	 */
	private void setCurrentEObject(EObject eObject) {
		this.currentEObject = eObject;
	}

	/**
	 * Returns the currently selected {@link EObject}.
	 * 
	 * @return the currently selected {@link EObject}.
	 */
	public EObject getCurrentEObject() {
		return currentEObject;
	}

	/**
	 * Sets the current profile facade.
	 * 
	 * @param profileFacade
	 *            to set.
	 */
	private void setProfileFacade(IProfileFacade profileFacade) {
		this.currentProfileFacade = profileFacade;
	}

	/**
	 * Returns the current profile facade.
	 * 
	 * @return the current profile facade.
	 */
	public IProfileFacade getProfileFacade() {
		return currentProfileFacade;
	}

	/**
	 * Specified whether a {@link IProfileFacade} is currently loaded.
	 * 
	 * @return <code>true</code> if loaded, otherwise <code>false</code>.
	 */
	public boolean isProfileFacadeLoaded() {
		return currentProfileFacade != null;
	}

	/**
	 * Updates the view, if not {@link #sticky}.
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (!sticky) {
			ISelection selection = event.getSelection();

			if (selection instanceof IStructuredSelection) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;

				if (iStructuredSelection.getFirstElement() instanceof EObject) {
					IWorkbenchPart workbenchPart = null;
					if (event.getSource() instanceof IWorkbenchPart) {
						workbenchPart = (IWorkbenchPart) event.getSource();
					} else if (event.getSelectionProvider() instanceof IWorkbenchPart) {
						workbenchPart = (IWorkbenchPart) event
								.getSelectionProvider();
					}
					if (workbenchPart != null) {
						setCurrentEObject((EObject) iStructuredSelection
								.getFirstElement());
						setProfileFacade(partProfileMapping.get(workbenchPart));
						updateView();
					}

				} else if (iStructuredSelection.getFirstElement() instanceof EditPart) {

					EditPart editPart = (EditPart) iStructuredSelection
							.getFirstElement();
					if (editPart.getModel() instanceof Node) {
						Node node = (Node) editPart.getModel();
						EObject element = node.getElement();
						RootEditPart partKey = editPart.getRoot();
						if (element != null) {
							setCurrentEObject(element);
							setProfileFacade(partProfileMapping.get(partKey));
							updateView();
						}
					}
				}
			}
		}
	}

	/**
	 * No operation.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void partActivated(IWorkbenchPart part) {
		// noop
	}

	/**
	 * No operation.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// noop
	}

	/**
	 * Detaches this viewer from the supplied <code>part</code>.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void partClosed(IWorkbenchPart part) {
		removeFromView(part);
	}

	/**
	 * No operation.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// noop
	}

	/**
	 * No operation.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void partOpened(IWorkbenchPart part) {
		// noop
	}

	/**
	 * Updates this view.
	 */
	public void updateView() {
		viewer.refresh(true);
	}

	/**
	 * Specifies whether we may apply a stereotype to the supplied
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            in question.
	 * @param keyPart
	 *            to identify the profile facade ({@link RootEditPart} or
	 *            {@link IWorkbenchPart}).
	 * @return the resource set.
	 */
	public boolean mayApplyStereotype(EObject eObject, IAdaptable keyPart) {
		if (partProfileMapping.containsKey(keyPart)) {
			IProfileFacade iProfileFacade = partProfileMapping.get(keyPart);
			return iProfileFacade.getApplicableStereotypes(eObject).size() > 0;
		}
		return false;
	}

	/**
	 * Returns the applied stereotypes for the <code>eObject</code>.
	 * 
	 * @param eObject
	 *            to get {@link StereotypeApplication StereotypeApplications}
	 *            for.
	 * @param keyPart
	 *            to identify facade.
	 * @return the list.
	 */
	public EList<StereotypeApplication> getAppliedStereotypes(EObject eObject,
			IAdaptable keyPart) {
		if (partProfileMapping.containsKey(keyPart)) {
			IProfileFacade iProfileFacade = partProfileMapping.get(keyPart);
			return iProfileFacade.getAppliedStereotypes(eObject);
		}
		return ECollections.emptyEList();
	}

	/**
	 * Adds a new {@link StereotypeApplication}.
	 * 
	 * @param eObject
	 *            to add {@link StereotypeApplication} for.
	 * @param keyPart
	 *            to resolve {@link IProfileFacade}.
	 */
	public void addNewStereotypeApplication(EObject eObject, IAdaptable keyPart) {
		if (mayApplyStereotype(eObject, keyPart)) {
			ElementListSelectionDialog dialog = new ElementListSelectionDialog(
					EMFProfileUIPlugin.getShell(),
					new AdapterFactoryLabelProvider(adapterFactory));
			dialog.setTitle("Stereotype Selection");
			dialog.setMessage("Select a Stereotype (* = any string, ? = any char):");
			dialog.setElements(currentProfileFacade.getApplicableStereotypes(
					eObject).toArray());
			int result = dialog.open();
			if (Dialog.OK == result) {
				Object firstResult = dialog.getFirstResult();
				if (firstResult instanceof Stereotype) {
					currentProfileFacade.apply((Stereotype) firstResult,
							eObject);
					updateView();
				}
			}
		} else {
			showError("Cannot apply any stereotype to " + eObject);
		}
	}

	/**
	 * Specifies whether a profile application is currently loaded for the
	 * supplied <code>keyPart</code>.
	 * 
	 * @param keyPart
	 *            to check ({@link RootEditPart} or {@link IWorkbenchPart}).
	 * @return <code>true</code> if an application is currently loaded,
	 *         otherwise <code>false</code>.
	 */
	public boolean isProfileApplicationLoaded(IAdaptable keyPart) {
		if (partProfileMapping.containsKey(keyPart)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void createPartControl(Composite parent) {
		Tree tree = new Tree(parent, SWT.MULTI | SWT.BORDER);
		viewer = new TreeViewer(tree);
		viewer.setContentProvider(this);
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		viewer.setInput(getViewSite());
		viewer.getTree().addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (SWT.DEL == e.character) {
					for (StereotypeApplication app : getSelectedStereotypes()) {
						removeStereotypeApplication(app);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		getSite().setSelectionProvider(this);
		new AdapterFactoryTreeEditor(viewer.getTree(), adapterFactory);

		makeActions();
		hookContextMenu();
		contributeToActionBars();
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

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ProfileApplicationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(saveStereotypeApplicationAction);
		manager.add(new Separator());
		manager.add(saveAllStereotypeApplicationsAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(removeStereotypeApplication);
		manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(validateStereotypeApplicationAction);
		manager.add(new Separator());
		manager.add(saveStereotypeApplicationAction);
		manager.add(saveAllStereotypeApplicationsAction);
		manager.add(new Separator());
	}

	private void makeActions() {
		validateStereotypeApplicationAction = new Action() {
			public void run() {
				validateAll();
			}
		};
		validateStereotypeApplicationAction.setText("Validate");
		validateStereotypeApplicationAction
				.setToolTipText("Validate profile application");
		validateStereotypeApplicationAction
				.setImageDescriptor(EMFProfileUIPlugin
						.getImageDescriptor(EMFProfileUIPlugin.IMG_VALIDATE));

		saveStereotypeApplicationAction = new Action() {
			public void run() {
				save();
			}
		};
		saveStereotypeApplicationAction.setText("Save");
		saveStereotypeApplicationAction
				.setToolTipText("Save profile application");
		saveStereotypeApplicationAction.setImageDescriptor(PlatformUI
				.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));

		saveAllStereotypeApplicationsAction = new Action() {
			public void run() {
				saveAll();
			}
		};
		saveAllStereotypeApplicationsAction.setText("Save All");
		saveAllStereotypeApplicationsAction
				.setToolTipText("Save all profile applications");
		saveAllStereotypeApplicationsAction.setImageDescriptor(PlatformUI
				.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVEALL_EDIT));

		removeStereotypeApplication = new Action() {
			public void run() {
				if (showQuestion("Confirm",
						"Are you sure to remove the selected elements?")) {
					EList<StereotypeApplication> stereotypes = getSelectedStereotypes();
					for (StereotypeApplication app : stereotypes) {
						removeStereotypeApplication(app);
					}
				}
			}
		};
		removeStereotypeApplication.setText("Remove");
		removeStereotypeApplication
				.setToolTipText("Removes the stereotype application");
		removeStereotypeApplication.setImageDescriptor(PlatformUI
				.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ELCL_REMOVE));

	}

	private EList<StereotypeApplication> getSelectedStereotypes() {
		EList<StereotypeApplication> apps = new BasicEList<StereotypeApplication>();
		if (viewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			List<?> list = selection.toList();
			for (Object element : list) {
				if (element instanceof StereotypeApplication) {
					apps.add((StereotypeApplication) element);
				}
			}
		}
		return apps;
	}

	private void removeStereotypeApplication(StereotypeApplication element) {
		currentProfileFacade.removeStereotypeApplication(element);
		EMFProfileUIPlugin.getDefault().refreshDecorations(
				element.getAppliedTo());
		updateView();
	}

	private boolean showQuestion(String title, String message) {
		return MessageDialog.openQuestion(viewer.getControl().getShell(),
				title, message);
	}

	private void showError(String message) {
		ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileUIPlugin.PLUGIN_ID, message));
	}

	private void showError(String message, Throwable throwable) {
		ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
				message, new Status(IStatus.ERROR,
						EMFProfileUIPlugin.PLUGIN_ID, throwable.getMessage(),
						throwable));
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * No operation.
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// returns the same irrespectively of the viewer and input
	}

	/**
	 * Returns the applied {@link StereotypeApplication stereotype applications}
	 * for the {{@link #getCurrentEObject() currently set} {@link EObject}.
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if (getProfileFacade() != null) {
			return getProfileFacade()
					.getAppliedStereotypes(getCurrentEObject()).toArray();
		} else {
			return new Object[] {};
		}
	}

	/**
	 * Provider for current stereotype applications provided by the current
	 * profile facade.
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof EObject) {
			EObject eObject = (EObject) parentElement;
			return eObject.eContents().toArray();
		}
		return new Object[] {};
	}

	/**
	 * Provider for current stereotype applications provided by the current
	 * profile facade.
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof EObject) {
			return ((EObject) element).eContainer();
		}
		return null;
	}

	/**
	 * Provider for current stereotype applications provided by the current
	 * profile facade.
	 */
	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element) != null && getChildren(element).length > 0;
	}

	/**
	 * Propagates this call to the viewer.
	 * 
	 * @param listener
	 *            to propagate.
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		viewer.addSelectionChangedListener(listener);
	}

	/**
	 * Propagates this call to the viewer.
	 * 
	 * @return the selection.
	 */
	@Override
	public ISelection getSelection() {
		return viewer.getSelection();
	}

	/**
	 * Propagates this call to the viewer.
	 * 
	 * @param listener
	 *            to propagate.
	 */
	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		viewer.removeSelectionChangedListener(listener);
	}

	/**
	 * Propagates this call to the viewer.
	 * 
	 * @param selection
	 *            to propagate.
	 */
	@Override
	public void setSelection(ISelection selection) {
		viewer.setSelection(selection);
	}
}