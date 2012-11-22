package org.modelversioning.emfprofile.application.registry.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class EMFProfileApplicationRegistryUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.modelversioning.emfprofile.application.registry.ui"; //$NON-NLS-1$

	// The shared instance
	private static EMFProfileApplicationRegistryUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public EMFProfileApplicationRegistryUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// load the emf profile registry plugin
		IProfileRegistry.eINSTANCE.getClass();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static EMFProfileApplicationRegistryUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
//	static class EMFProfileUIPlugin extends AbstractUIPlugin {
//
//		/** The plug-in ID. */
//		public static final String PLUGIN_ID = "org.modelversioning.emfprofiles.ui"; //$NON-NLS-1$
//
//		/** Profile file extension. */
//		public static final String EMF_PROFILE_EXTENSION = "emfprofile"; //$NON-NLS-1$
//
//		/** Default profile application file extension. */
//		public static final String EMF_PROFILE_APPLICATION_EXTENSION = "emfprofile.xmi"; //$NON-NLS-1$
//
//		/** The view id of the Profile Application view. */
//		private static final String PROFILE_APPLICATION_VIEW_ID = "org.modelversioning.emfprofile.ui.views.ProfileApplicationsView"; //$NON-NLS-1$
//
//		/** The icons path. */
//		private static final String iconPath = "icons/"; //$NON-NLS-1$
//
//		/** The image for demonstrate action. */
//		public static final String IMG_STEREOTYPE_DEFAULT = "stereotype"; //$NON-NLS-1$
//
//		/** The image for demonstrate action. */
//		public static final String IMG_VALIDATE = "validate"; //$NON-NLS-1$
//
//		/** The shared instance. */
//		private static EMFProfileUIPlugin plugin;
//
//		/** Profile applications view instance. */
//		private ProfileApplicationsView profileApplicationsView;
//
//		/** {@link EObject} to {@link IDecorator} map. */
//		private Map<EObject, IDecorator> eObjectToDecoratorMap = new HashMap<EObject, IDecorator>();
//
//		/** Fallback resource set. */
//		private ResourceSet resourceSet = new ResourceSetImpl();
//
//		/**
//		 * The empty default constructor.
//		 */
//		public EMFProfileUIPlugin() {
//		}
//
//		/**
//		 * {@inheritDoc}
//		 */
//		public void start(BundleContext context) throws Exception {
//			super.start(context);
//			plugin = this;
//		}
//
//		/**
//		 * {@inheritDoc}
//		 */
//		public void stop(BundleContext context) throws Exception {
//			plugin = null;
//			super.stop(context);
//		}
//
//		/**
//		 * Returns the shared instance
//		 * 
//		 * @return the shared instance
//		 */
//		public static EMFProfileUIPlugin getDefault() {
//			return plugin;
//		}
//
//		/**
//		 * Adds a new {@link StereotypeApplication} to the specified
//		 * <code>eObject</code>.
//		 * 
//		 * @param eObject
//		 *            to apply stereotype to.
//		 * @param workbenchPart
//		 *            workbench part as key to identify facade.
//		 */
//		public void addNewStereotypeApplication(EObject eObject,
//				IWorkbenchPart workbenchPart) {
//			try {
//				getProfileApplicationView().addNewStereotypeApplication(eObject,
//						getPartKey(workbenchPart));
//				refreshDecorations(eObject);
//				refresh(workbenchPart);
//			} catch (PartInitException e) {
//				IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
//						e.getMessage(), e);
//				ErrorDialog.openError(workbenchPart.getSite().getShell(),
//						"Profile Application View could not been resolved",
//						e.getMessage(), status);
//				getLog().log(status);
//			}
//		}
//
//		private void refresh(IWorkbenchPart workbenchPart) {
//			if (getPartKey(workbenchPart) instanceof RootEditPart) {
//				RootEditPart rootEditPart = (RootEditPart) getPartKey(workbenchPart);
//				rootEditPart.refresh();
//			} else {
//				// TODO refresh workbench part
//			}
//		}
//
//		public void refreshDecorations(final EObject eObject) {
//			if (eObjectToDecoratorMap.containsKey(eObject)) {
//				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
//					public void run() {
//						try {
//							TransactionUtil.getEditingDomain(eObject.eResource())
//									.runExclusive(new Runnable() {
//										public void run() {
//											eObjectToDecoratorMap.get(eObject)
//													.refresh();
//										}
//									});
//						} catch (Exception e) {
//							logException(e);
//						}
//					}
//				});
//			}
//		}
//
//		public static void log(IStatus status) {
//			ResourcesPlugin.getPlugin().getLog().log(status);
//		}
//
//		public static void logErrorMessage(String message) {
//			log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, null));
//		}
//
//		public static void logException(Throwable e, final String title,
//				String message) {
//			if (e instanceof InvocationTargetException) {
//				e = ((InvocationTargetException) e).getTargetException();
//			}
//			IStatus status = null;
//			if (e instanceof CoreException)
//				status = ((CoreException) e).getStatus();
//			else {
//				if (message == null)
//					message = e.getMessage();
//				if (message == null)
//					message = e.toString();
//				status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message,
//						e);
//			}
//			ResourcesPlugin.getPlugin().getLog().log(status);
//			Display display = getStandardDisplay();
//			final IStatus fstatus = status;
//			display.asyncExec(new Runnable() {
//				public void run() {
//					ErrorDialog.openError(null, title, null, fstatus);
//				}
//			});
//		}
//
//		public static void logException(Throwable e) {
//			logException(e, null, null);
//		}
//
//		public static void log(Throwable e) {
//			if (e instanceof InvocationTargetException)
//				e = ((InvocationTargetException) e).getTargetException();
//			IStatus status = null;
//			if (e instanceof CoreException)
//				status = ((CoreException) e).getStatus();
//			else
//				status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK,
//						e.getMessage(), e);
//			log(status);
//		}
//
//		public static Display getStandardDisplay() {
//			Display display;
//			display = Display.getCurrent();
//			if (display == null)
//				display = Display.getDefault();
//			return display;
//		}
//
//		public void refreshDecorations(EList<EObject> eObjects) {
//			for (EObject eObject : eObjects) {
//				refreshDecorations(eObject);
//			}
//		}
//
//		public void refreshAllDecorations() {
//			for (IDecorator decorator : eObjectToDecoratorMap.values()) {
//				decorator.refresh();
//			}
//		}
//
//		/**
//		 * Specifies whether any stereotype may currently be applied to the supplied
//		 * <code>eObject</code> from the supplied <code>workbenchPart</code>.
//		 * 
//		 * @param eObject
//		 *            to check for.
//		 * @param workbenchPart
//		 *            to check for.
//		 * @return <code>true</code> if we may apply a stereotype, otherwise
//		 *         <code>false</code>.
//		 */
//		public boolean mayApplyStereotype(EObject eObject,
//				IWorkbenchPart workbenchPart) {
//			try {
//				return getProfileApplicationView().mayApplyStereotype(eObject,
//						getPartKey(workbenchPart));
//			} catch (PartInitException e) {
//				return false;
//			}
//		}
//
//		/**
//		 * Retrieves the right part key for identifying the facade from the
//		 * specified <code>workbenchPart</code>.
//		 * 
//		 * @param workbenchPart
//		 *            to get part key for.
//		 * @return part key.
//		 */
//		private IAdaptable getPartKey(IWorkbenchPart workbenchPart) {
//			IAdaptable adaptable = null;
//			if (workbenchPart instanceof DiagramEditor) {
//				DiagramEditor diagramEditor = (DiagramEditor) workbenchPart;
//				DiagramEditPart diagramEditPart = diagramEditor
//						.getDiagramEditPart();
//				adaptable = diagramEditPart.getRoot();
//			} else {
//				adaptable = workbenchPart;
//			}
//			return adaptable;
//		}
//
//		/**
//		 * Specifies whether a profile application is currently loaded for the
//		 * supplied <code>targetPart</code>.
//		 * 
//		 * @param targetPart
//		 *            to check.
//		 * @return <code>true</code> if an application is currently loaded,
//		 *         otherwise <code>false</code>.
//		 */
//		public boolean isProfileApplicationLoaded(IWorkbenchPart targetPart) {
//			try {
//				return getProfileApplicationView().isProfileApplicationLoaded(
//						targetPart);
//			} catch (PartInitException e) {
//				return false;
//			}
//		}
//
//		/**
//		 * Returns the resolved resource set from the editing domain of the supplied
//		 * <code>workbenchPart</code>. If no {@link ResourceSet} can be resolved, a
//		 * fall back resource set will be returned instead.
//		 * 
//		 * @param workbenchPart
//		 *            to resolve resource set.
//		 * @return the resource set.
//		 */
//		private ResourceSet getResourceSet(IWorkbenchPart workbenchPart) {
//			Object adapter = workbenchPart.getAdapter(IEditingDomainProvider.class);
//			if (adapter != null && adapter instanceof IEditingDomainProvider) {
//				IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) adapter;
//				return editingDomainProvider.getEditingDomain().getResourceSet();
//			} else {
//				return resourceSet;
//			}
//		}
//
//		/**
//		 * Returns the profile applications view.
//		 * 
//		 * @return the profile applications view.
//		 * @throws PartInitException
//		 *             if part cannot be initialized correctly.
//		 */
//		private ProfileApplicationsView getProfileApplicationView()
//				throws PartInitException {
//			if (profileApplicationsView == null
//					&& PlatformUI.getWorkbench() != null
//					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
//					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//							.getActivePage() != null) {
//				profileApplicationsView = (ProfileApplicationsView) PlatformUI
//						.getWorkbench().getActiveWorkbenchWindow().getActivePage()
//						.showView(EMFProfileUIPlugin.PROFILE_APPLICATION_VIEW_ID);
//			}
//			return profileApplicationsView;
//		}
//
//		/**
//		 * {@inheritDoc}
//		 */
//		@Override
//		protected void initializeImageRegistry(ImageRegistry registry) {
//			registerImage(registry, IMG_STEREOTYPE_DEFAULT, "Stereotype.gif"); //$NON-NLS-1$
//			registerImage(registry, IMG_VALIDATE, "validate.png"); //$NON-NLS-1$
//		}
//
//		/**
//		 * Registers the specified fileName under the specified key to the specified
//		 * registry.
//		 * 
//		 * @param registry
//		 *            registry to register image.
//		 * @param key
//		 *            key of image.
//		 * @param fileName
//		 *            file name.
//		 */
//		private void registerImage(ImageRegistry registry, String key,
//				String fileName) {
//			try {
//				IPath path = new Path(iconPath + fileName);
//				URL url = FileLocator.find(getBundle(), path, null);
//				if (url != null) {
//					ImageDescriptor desc = ImageDescriptor.createFromURL(url);
//					registry.put(key, desc);
//				}
//			} catch (Exception e) {
//			}
//		}
//
//		/**
//		 * Registers the specified external fileName under the specified key to the
//		 * specified registry.
//		 * 
//		 * <p>
//		 * Currently only absolute file system paths are supported. With windows use
//		 * double backslashes.
//		 * </p>
//		 * 
//		 * TODO Also support http locations and platform:// locations
//		 * 
//		 * @param registry
//		 *            registry to register image.
//		 * @param key
//		 *            key of image.
//		 * @param fileLocation
//		 *            file location.
//		 */
//		private void registerExternalImage(ImageRegistry registry, String key,
//				String fileLocation) {
//			ImageDescriptor desc = ImageDescriptor.createFromImage(new Image(
//					getShell().getDisplay(), fileLocation));
//			registry.put(key, desc);
//		}
//
//		/**
//		 * Returns the image for the specified image name.
//		 * 
//		 * @param img
//		 *            image name.
//		 * @return the image.
//		 */
//		public static Image getImage(String key) {
//			return getDefault().getImageRegistry().get(key);
//		}
//
//		/**
//		 * Returns an image descriptor for the image file identified by the given
//		 * key.
//		 * 
//		 * @param key
//		 *            the key
//		 * @return the image descriptor
//		 */
//		public static ImageDescriptor getImageDescriptor(String key) {
//			return getDefault().getImageRegistry().getDescriptor(key);
//		}
//
//		/**
//		 * Registers the specified <code>decorator</code> to be
//		 * {@link IDecorator#refresh() refreshed} when the specified
//		 * <code>eObject</code> is updated regarding stereotypes.
//		 * 
//		 * @param eObject
//		 *            to register.
//		 * @param decorator
//		 *            to register.
//		 */
//		public void registerDecorator(EObject eObject, IDecorator decorator) {
//			this.eObjectToDecoratorMap.put(eObject, decorator);
//		}
//
//		/**
//		 * Unregisters the decorators to be for the specified <code>eObject</code>.
//		 * 
//		 * @param eObject
//		 *            to unregister.
//		 */
//		public void unregisterDecorator(EObject eObject) {
//			this.eObjectToDecoratorMap.remove(eObject);
//		}
//
//		/**
//		 * Returns a set of images that should be used for decorating the specified
//		 * <code>eObject</code>.
//		 * 
//		 * @param eObject
//		 *            to get images for.
//		 * @return the set of images.
//		 */
//		public List<Image> getImagesToDecorate(EObject eObject, RootEditPart partKey) {
//			try {
//				if (getProfileApplicationView() != null) {
//					EList<StereotypeApplication> stereotypeApplications = getProfileApplicationView()
//							.getAppliedStereotypes(eObject, partKey);
//					List<Image> images = new ArrayList<Image>();
//					for (StereotypeApplication application : stereotypeApplications) {
//						images.add(getStereotypeImage(application));
//					}
//					return images;
//				} else {
//					return Collections.emptyList();
//				}
//			} catch (PartInitException e) {
//				e.printStackTrace();
//				return Collections.emptyList();
//			}
//		}
//
//		public List<String> getToolTipsToDecorate(EObject eObject,
//				RootEditPart partKey) {
//			try {
//				if (getProfileApplicationView() != null) {
//					EList<StereotypeApplication> stereotypeApplications = getProfileApplicationView()
//							.getAppliedStereotypes(eObject, partKey);
//					List<String> toolTips = new ArrayList<String>();
//					for (StereotypeApplication application : stereotypeApplications) {
//						toolTips.add(getStereotypeLabel(application));
//					}
//					return toolTips;
//				} else {
//					return Collections.emptyList();
//				}
//			} catch (PartInitException e) {
//				e.printStackTrace();
//				return Collections.emptyList();
//			}
//		}
//
//		/**
//		 * Returns the tool tip text for the supplied
//		 * <code>stereotypeApplication</code>.
//		 * 
//		 * @param stereotypeApplication
//		 *            to get tool tip text for.
//		 * @return the tool tip text.
//		 */
//		private String getStereotypeLabel(StereotypeApplication application) {
//			if (application.eClass() instanceof Stereotype) {
//				return "<<" + ((Stereotype) application.eClass()).getName() + ">>"; //$NON-NLS-1$ $NON-NLS-2$
//			}
//			return "Stereotype application"; //$NON-NLS-1$
//		}
//
//		/**
//		 * Returns the image for the supplied <code>stereotypeApplication</code>.
//		 * 
//		 * @param stereotypeApplication
//		 *            to get {@link Image} for.
//		 * @return the {@link Image}.
//		 */
//		private Image getStereotypeImage(StereotypeApplication stereotypeApplication) {
//			if (stereotypeApplication.getStereotype() != null) {
//				Stereotype stereotype = stereotypeApplication.getStereotype();
//				if (stereotype.hasIcon()) {
//					if (getImage(getImageKey(stereotype)) == null) {
//						registerExternalImage(getImageRegistry(),
//								getImageKey(stereotype), stereotype.getIconPath());
//					}
//					return getImage(getImageKey(stereotype));
//				}
//			}
//			return getImage(IMG_STEREOTYPE_DEFAULT);
//		}
//
//		/**
//		 * Returns the image key for the supplied <code>stereotype</code>.
//		 * 
//		 * @param stereotype
//		 *            to get image key for.
//		 * @return the image key.
//		 */
//		private String getImageKey(Stereotype stereotype) {
//			return stereotype.getEPackage().getNsURI() + "#" + stereotype.getName();
//		}
//
//		/**
//		 * Returns the shell.
//		 * 
//		 * @return the shell.
//		 */
//		public static Shell getShell() {
//			return getDefault().getWorkbench().getActiveWorkbenchWindow()
//					.getShell();
//		}
//
//		/**
//		 * Triggers unloading a profile application.
//		 */
//		public void unloadProfileApplication() {
//			// TODO implement
//
//		}
//
//	}
//	
//	static class ProfileApplicationsView extends ViewPart implements IPartListener,
//		ISelectionChangedListener, ITreeContentProvider, ISelectionProvider {
//
//		/** ID of the view as specified by the extension. */
//		public static final String ID = "org.modelversioning.emfprofile.ui.views.ProfileApplicationsView";
//		
//		/** Content tree viewer. */
//		private TreeViewer viewer;
//		
//		/** Mapping of registered (root edit or workbench) parts to profile facades. */
//		private Map<Object, IProfileFacade> partProfileMapping = new HashMap<Object, IProfileFacade>();
//		
//		/** Content facade. */
//		private IProfileFacade currentProfileFacade;
//		
//		/** Currently selected {@link EObject}. */
//		private EObject currentEObject = null;
//		
//		/** The property sheet page. */
//		protected PropertySheetPage propertySheetPage;
//		
//		/**
//		 * Specifies whether this view is sticky, i.e., won't refresh on selection
//		 * change.
//		 */
//		private boolean sticky = false;
//		
//		/** This is the one adapter factory used for providing views of the model. */
//		protected ComposedAdapterFactory adapterFactory;
//		
//		/** Validate stereotype application action */
//		private Action validateStereotypeApplicationAction;
//		
//		/** Save stereotype application action */
//		private Action saveStereotypeApplicationAction;
//		
//		/** Save all stereotype application action */
//		private Action saveAllStereotypeApplicationsAction;
//		
//		/** Remove selected stereotype application action */
//		private Action removeStereotypeApplication;
//		
//		/**
//		 * A map maintaining the facade and a handle to the profile application
//		 * file.
//		 */
//		private Map<IProfileFacade, IFile> facadeToFileMap = new HashMap<IProfileFacade, IFile>();
//		
//		/**
//		 * The constructor.
//		 */
//		public ProfileApplicationsView() {
//			adapterFactory = new ComposedAdapterFactory(
//					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
//			adapterFactory
//					.addAdapterFactory(new EMFProfileItemProviderAdapterFactory());
//			adapterFactory
//					.addAdapterFactory(new EMFProfileApplicationItemProviderAdapterFactory());
//			adapterFactory
//					.addAdapterFactory(new ResourceItemProviderAdapterFactory());
//			adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
//			adapterFactory
//					.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
//		}
//		
//		/**
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void dispose() {
//			if (propertySheetPage != null) {
//				propertySheetPage.dispose();
//			}
//			if (adapterFactory != null) {
//				adapterFactory.dispose();
//			}
//			if (currentProfileFacade != null) {
//				currentProfileFacade.unload();
//			}
//			super.dispose();
//		}
//		
//		/**
//		 * Returns the property sheet page. If necessary, this initializes the sheet
//		 * page.
//		 * 
//		 * @return the property sheet page.
//		 */
//		public IPropertySheetPage getPropertySheetPage() {
//			if (propertySheetPage == null) {
//				propertySheetPage = new PropertySheetPage();
//				// only set the reflective adapter factory
//				propertySheetPage
//						.setPropertySourceProvider(new AdapterFactoryContentProvider(
//								new ReflectiveItemProviderAdapterFactory()));
//			}
//		
//			return propertySheetPage;
//		}
//		
//		/**
//		 * Adds the supplied <code>workbenchPart</code> and
//		 * <code>profileFacade</code> to this view.
//		 * 
//		 * @param workbenchPart
//		 *            to add.
//		 * @param profileFacade
//		 *            to add.
//		 * @param profileApplicationFile
//		 *            handle to the file containing the profile application.
//		 */
//		public void addToView(IWorkbenchPart workbenchPart,
//				IProfileFacade profileFacade, IFile profileApplicationFile) {
//			maintainProfileApplicationFile(profileFacade, profileApplicationFile);
//			partProfileMapping.put(workbenchPart, profileFacade);
//			registerWorkbenchPart(workbenchPart);
//			updateView();
//		}
//		
//		/**
//		 * Removes the supplied <code>workbenchPart</code> from this view.
//		 * 
//		 * @param workbenchPart
//		 *            to remove.
//		 */
//		public void removeFromView(IWorkbenchPart workbenchPart) {
//			IProfileFacade iProfileFacade = partProfileMapping.get(workbenchPart);
//			facadeToFileMap.remove(iProfileFacade);
//			partProfileMapping.remove(workbenchPart);
//			unregisterWorkbenchPart(workbenchPart);
//			updateView();
//		}
//		
//		/**
//		 * Registers the supplied <code>workbenchPart</code>.
//		 * 
//		 * @param workbenchPart
//		 *            to register.
//		 */
//		private void registerWorkbenchPart(IWorkbenchPart workbenchPart) {
//			workbenchPart.getSite().getPage().addPartListener(this);
//			ISelectionProvider selectionProvider = workbenchPart.getSite()
//					.getSelectionProvider();
//			if (selectionProvider != null) {
//				selectionProvider.addSelectionChangedListener(this);
//			}
//		}
//		
//		/**
//		 * Unregisters the supplied <code>workbenchPart</code>.
//		 * 
//		 * @param workbenchPart
//		 *            to unregister.
//		 */
//		private void unregisterWorkbenchPart(IWorkbenchPart workbenchPart) {
//			workbenchPart.getSite().getPage().removePartListener(this);
//			ISelectionProvider selectionProvider = workbenchPart.getSite()
//					.getSelectionProvider();
//			if (selectionProvider != null) {
//				selectionProvider.removeSelectionChangedListener(this);
//			}
//		}
//		
//		/**
//		 * Adds the supplied <code>editPart</code> and <code>profileFacade</code> to
//		 * this view.
//		 * 
//		 * @param editPart
//		 *            to add.
//		 * @param profileFacade
//		 *            to add.
//		 * @param profileApplicationFile
//		 *            handle to the file containing the profile application.
//		 */
//		public void addToView(RootEditPart editPart, IProfileFacade profileFacade,
//				IFile profileApplicationFile) {
//			partProfileMapping.put(editPart, profileFacade);
//			maintainProfileApplicationFile(profileFacade, profileApplicationFile);
//			registerEditPart(editPart);
//			updateView();
//		}
//		
//		private void maintainProfileApplicationFile(IProfileFacade profileFacade,
//				IFile profileApplicationFile) {
//			this.facadeToFileMap.put(profileFacade, profileApplicationFile);
//		}
//		
//		/**
//		 * Removes the supplied <code>editPart</code> from this view.
//		 * 
//		 * @param editPart
//		 *            to remove.
//		 */
//		public void removeFromView(RootEditPart editPart) {
//			partProfileMapping.remove(editPart);
//			unregisterEditPart(editPart);
//			updateView();
//		}
//		
//		/**
//		 * Registers the supplied <code>editPart</code>.
//		 * 
//		 * @param editPart
//		 *            to register.
//		 */
//		private void registerEditPart(RootEditPart editPart) {
//			// TODO add listener to clear up if disposed
//			editPart.getViewer().addSelectionChangedListener(this);
//		}
//		
//		/**
//		 * Unregisters the supplied <code>editPart</code>.
//		 * 
//		 * @param editPart
//		 *            to unregister.
//		 */
//		private void unregisterEditPart(RootEditPart editPart) {
//			editPart.getViewer().removeSelectionChangedListener(this);
//		}
//		
//		/**
//		 * Saves the current profile application.
//		 */
//		public void save() {
//			if (currentProfileFacade != null) {
//				saveFacade(currentProfileFacade);
//			}
//		}
//		
//		private void saveFacade(final IProfileFacade facade) {
//			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
//				@Override
//				public void execute(IProgressMonitor monitor) {
//					try {
//						facade.save();
//						refreshProfileApplicationFile(facade);
//					} catch (IOException e) {
//						showError(
//								"Error while saving profile application resource",
//								e);
//					}
//				}
//			};
//			try {
//				new ProgressMonitorDialog(getSite().getShell()).run(true, false,
//						operation);
//			} catch (Exception e) {
//				showError("Error while saving profile application resource", e);
//			}
//		}
//		
//		/**
//		 * Saves all currently loaded profile applications.
//		 */
//		public void saveAll() {
//			for (IProfileFacade facade : getProfileFacades()) {
//				saveFacade(facade);
//			}
//		}
//		
//		private void refreshProfileApplicationFile(IProfileFacade facade) {
//			IFile profileApplicationFile = facadeToFileMap.get(facade);
//			if (profileApplicationFile != null) {
//				try {
//					profileApplicationFile.refreshLocal(1,
//							new NullProgressMonitor());
//				} catch (CoreException e) {
//				}
//			}
//		}
//		
//		/**
//		 * Validates all currently loaded profile applications.
//		 */
//		public void validateAll() {
//			// TODO provide nicer way to show diagnostics (even with error markers?)
//			if (currentProfileFacade != null) {
//				Diagnostic diagnostic = currentProfileFacade
//						.validateAll(currentEObject);
//				DiagnosticDialog dialog = new DiagnosticDialog(
//						getSite().getShell(), "Profile Application",
//						diagnostic.getMessage(), diagnostic,
//						diagnostic.getSeverity());
//				dialog.setBlockOnOpen(true);
//				dialog.open();
//			}
//		}
//		
//		/**
//		 * Returns all currently registered profile facades.
//		 * 
//		 * @return all currently registered profile facades.
//		 */
//		private Collection<IProfileFacade> getProfileFacades() {
//			return partProfileMapping.values();
//		}
//		
//		/**
//		 * Sets the current {@link EObject}.
//		 * 
//		 * @param eObject
//		 *            to set.
//		 */
//		private void setCurrentEObject(EObject eObject) {
//			this.currentEObject = eObject;
//		}
//		
//		/**
//		 * Returns the currently selected {@link EObject}.
//		 * 
//		 * @return the currently selected {@link EObject}.
//		 */
//		public EObject getCurrentEObject() {
//			return currentEObject;
//		}
//		
//		/**
//		 * Sets the current profile facade.
//		 * 
//		 * @param profileFacade
//		 *            to set.
//		 */
//		private void setProfileFacade(IProfileFacade profileFacade) {
//			this.currentProfileFacade = profileFacade;
//		}
//		
//		/**
//		 * Returns the current profile facade.
//		 * 
//		 * @return the current profile facade.
//		 */
//		public IProfileFacade getProfileFacade() {
//			return currentProfileFacade;
//		}
//		
//		/**
//		 * Specified whether a {@link IProfileFacade} is currently loaded.
//		 * 
//		 * @return <code>true</code> if loaded, otherwise <code>false</code>.
//		 */
//		public boolean isProfileFacadeLoaded() {
//			return currentProfileFacade != null;
//		}
//		
//		/**
//		 * Updates the view, if not {@link #sticky}.
//		 */
//		@Override
//		public void selectionChanged(SelectionChangedEvent event) {
//			if (!sticky) {
//				ISelection selection = event.getSelection();
//		
//				if (selection instanceof IStructuredSelection) {
//					IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;
//		
//					if (iStructuredSelection.getFirstElement() instanceof EObject) {
//						IWorkbenchPart workbenchPart = null;
//						if (event.getSource() instanceof IWorkbenchPart) {
//							workbenchPart = (IWorkbenchPart) event.getSource();
//						} else if (event.getSelectionProvider() instanceof IWorkbenchPart) {
//							workbenchPart = (IWorkbenchPart) event
//									.getSelectionProvider();
//						}
//						if (workbenchPart != null) {
//							setCurrentEObject((EObject) iStructuredSelection
//									.getFirstElement());
//							setProfileFacade(partProfileMapping.get(workbenchPart));
//							updateView();
//						}
//		
//					} else if (iStructuredSelection.getFirstElement() instanceof EditPart) {
//		
//						EditPart editPart = (EditPart) iStructuredSelection
//								.getFirstElement();
//						if (editPart.getModel() instanceof Node) {
//							Node node = (Node) editPart.getModel();
//							EObject element = node.getElement();
//							RootEditPart partKey = editPart.getRoot();
//							if (element != null) {
//								setCurrentEObject(element);
//								setProfileFacade(partProfileMapping.get(partKey));
//								updateView();
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		/**
//		 * No operation.
//		 * 
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void partActivated(IWorkbenchPart part) {
//			// noop
//		}
//		
//		/**
//		 * No operation.
//		 * 
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void partBroughtToTop(IWorkbenchPart part) {
//			// noop
//		}
//		
//		/**
//		 * Detaches this viewer from the supplied <code>part</code>.
//		 * 
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void partClosed(IWorkbenchPart part) {
//			removeFromView(part);
//		}
//		
//		/**
//		 * No operation.
//		 * 
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void partDeactivated(IWorkbenchPart part) {
//			// noop
//		}
//		
//		/**
//		 * No operation.
//		 * 
//		 * {@inheritDoc}
//		 */
//		@Override
//		public void partOpened(IWorkbenchPart part) {
//			// noop
//		}
//		
//		/**
//		 * Updates this view.
//		 */
//		public void updateView() {
//			viewer.refresh(true);
//			refreshProfileApplicationFile(currentProfileFacade);
//		}
//		
//		/**
//		 * Specifies whether we may apply a stereotype to the supplied
//		 * <code>eObject</code>.
//		 * 
//		 * @param eObject
//		 *            in question.
//		 * @param keyPart
//		 *            to identify the profile facade ({@link RootEditPart} or
//		 *            {@link IWorkbenchPart}).
//		 * @return the resource set.
//		 */
//		public boolean mayApplyStereotype(EObject eObject, IAdaptable keyPart) {
//			if (partProfileMapping.containsKey(keyPart)) {
//				IProfileFacade iProfileFacade = partProfileMapping.get(keyPart);
//				return iProfileFacade.getApplicableStereotypes(eObject).size() > 0;
//			}
//			return false;
//		}
//		
//		/**
//		 * Returns the applied stereotypes for the <code>eObject</code>.
//		 * 
//		 * @param eObject
//		 *            to get {@link StereotypeApplication StereotypeApplications}
//		 *            for.
//		 * @param keyPart
//		 *            to identify facade.
//		 * @return the list.
//		 */
//		public EList<StereotypeApplication> getAppliedStereotypes(EObject eObject,
//				IAdaptable keyPart) {
//			if (partProfileMapping.containsKey(keyPart)) {
//				IProfileFacade iProfileFacade = partProfileMapping.get(keyPart);
//				return iProfileFacade.getAppliedStereotypes(eObject);
//			}
//			return ECollections.emptyEList();
//		}
//		
//		/**
//		 * Adds a new {@link StereotypeApplication}.
//		 * 
//		 * @param eObject
//		 *            to add {@link StereotypeApplication} for.
//		 * @param keyPart
//		 *            to resolve {@link IProfileFacade}.
//		 */
//		public void addNewStereotypeApplication(EObject eObject, IAdaptable keyPart) {
//			if (mayApplyStereotype(eObject, keyPart)) {
//				ElementListSelectionDialog dialog = new ElementListSelectionDialog(
//						EMFProfileUIPlugin.getShell(),
//						new AdapterFactoryLabelProvider(adapterFactory));
//				dialog.setTitle("Stereotype Selection");
//				dialog.setMessage("Select a Stereotype (* = any string, ? = any char):");
//				dialog.setElements(currentProfileFacade.getApplicableStereotypes(
//						eObject).toArray());
//				int result = dialog.open();
//				if (Dialog.OK == result) {
//					Object firstResult = dialog.getFirstResult();
//					if (firstResult instanceof StereotypeApplicability) {
//						currentProfileFacade.apply(
//								(StereotypeApplicability) firstResult, eObject);
//						updateView();
//					}
//				}
//			} else {
//				showError("Cannot apply any stereotype to " + eObject);
//			}
//		}
//		
//		/**
//		 * Specifies whether a profile application is currently loaded for the
//		 * supplied <code>keyPart</code>.
//		 * 
//		 * @param keyPart
//		 *            to check ({@link RootEditPart} or {@link IWorkbenchPart}).
//		 * @return <code>true</code> if an application is currently loaded,
//		 *         otherwise <code>false</code>.
//		 */
//		public boolean isProfileApplicationLoaded(IAdaptable keyPart) {
//			if (partProfileMapping.containsKey(keyPart)) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//		
//		/**
//		 * {@inheritDoc}
//		 */
//		public void createPartControl(Composite parent) {
//			Tree tree = new Tree(parent, SWT.MULTI | SWT.BORDER);
//			viewer = new TreeViewer(tree);
//			viewer.setContentProvider(this);
//			viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
//			viewer.setInput(getViewSite());
//			viewer.getTree().addKeyListener(new KeyListener() {
//				@Override
//				public void keyReleased(KeyEvent e) {
//					if (SWT.DEL == e.character) {
//						for (StereotypeApplication app : getSelectedStereotypes()) {
//							removeStereotypeApplication(app);
//						}
//					}
//				}
//		
//				@Override
//				public void keyPressed(KeyEvent e) {
//				}
//			});
//			getSite().setSelectionProvider(this);
//			new AdapterFactoryTreeEditor(viewer.getTree(), adapterFactory);
//		
//			makeActions();
//			hookContextMenu();
//			contributeToActionBars();
//		}
//		
//		/**
//		 * Provides the adapters.
//		 * 
//		 * In particular this is the property sheet. The rest is handed over to
//		 * super.
//		 * 
//		 * @param adapter
//		 *            to get.
//		 * @return the adapter.
//		 */
//		@Override
//		public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
//			if (adapter.equals(IPropertySheetPage.class)) {
//				return getPropertySheetPage();
//			} else {
//				return super.getAdapter(adapter);
//			}
//		}
//		
//		private void hookContextMenu() {
//			MenuManager menuMgr = new MenuManager("#PopupMenu");
//			menuMgr.setRemoveAllWhenShown(true);
//			menuMgr.addMenuListener(new IMenuListener() {
//				public void menuAboutToShow(IMenuManager manager) {
//					ProfileApplicationsView.this.fillContextMenu(manager);
//				}
//			});
//			Menu menu = menuMgr.createContextMenu(viewer.getControl());
//			viewer.getControl().setMenu(menu);
//			getSite().registerContextMenu(menuMgr, viewer);
//		}
//		
//		private void contributeToActionBars() {
//			IActionBars bars = getViewSite().getActionBars();
//			fillLocalPullDown(bars.getMenuManager());
//			fillLocalToolBar(bars.getToolBarManager());
//		}
//		
//		private void fillLocalPullDown(IMenuManager manager) {
//			manager.add(saveStereotypeApplicationAction);
//			manager.add(new Separator());
//			manager.add(saveAllStereotypeApplicationsAction);
//		}
//		
//		private void fillContextMenu(IMenuManager manager) {
//			manager.add(removeStereotypeApplication);
//			manager.add(new Separator());
//			// drillDownAdapter.addNavigationActions(manager);
//			// Other plug-ins can contribute there actions here
//			// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//		}
//		
//		private void fillLocalToolBar(IToolBarManager manager) {
//			manager.add(validateStereotypeApplicationAction);
//			manager.add(new Separator());
//			manager.add(saveStereotypeApplicationAction);
//			manager.add(saveAllStereotypeApplicationsAction);
//			manager.add(new Separator());
//		}
//		
//		private void makeActions() {
//			validateStereotypeApplicationAction = new Action() {
//				public void run() {
//					validateAll();
//				}
//			};
//			validateStereotypeApplicationAction.setText("Validate");
//			validateStereotypeApplicationAction
//					.setToolTipText("Validate profile application");
//			validateStereotypeApplicationAction
//					.setImageDescriptor(EMFProfileUIPlugin
//							.getImageDescriptor(EMFProfileUIPlugin.IMG_VALIDATE));
//		
//			saveStereotypeApplicationAction = new Action() {
//				public void run() {
//					save();
//				}
//			};
//			saveStereotypeApplicationAction.setText("Save");
//			saveStereotypeApplicationAction
//					.setToolTipText("Save profile application");
//			saveStereotypeApplicationAction.setImageDescriptor(PlatformUI
//					.getWorkbench().getSharedImages()
//					.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
//		
//			saveAllStereotypeApplicationsAction = new Action() {
//				public void run() {
//					saveAll();
//				}
//			};
//			saveAllStereotypeApplicationsAction.setText("Save All");
//			saveAllStereotypeApplicationsAction
//					.setToolTipText("Save all profile applications");
//			saveAllStereotypeApplicationsAction.setImageDescriptor(PlatformUI
//					.getWorkbench().getSharedImages()
//					.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVEALL_EDIT));
//		
//			removeStereotypeApplication = new Action() {
//				public void run() {
//					if (showQuestion("Confirm",
//							"Are you sure to remove the selected elements?")) {
//						EList<StereotypeApplication> stereotypes = getSelectedStereotypes();
//						for (StereotypeApplication app : stereotypes) {
//							removeStereotypeApplication(app);
//						}
//					}
//				}
//			};
//			removeStereotypeApplication.setText("Remove");
//			removeStereotypeApplication
//					.setToolTipText("Removes the stereotype application");
//			removeStereotypeApplication.setImageDescriptor(PlatformUI
//					.getWorkbench().getSharedImages()
//					.getImageDescriptor(ISharedImages.IMG_ELCL_REMOVE));
//		
//		}
//		
//		private EList<StereotypeApplication> getSelectedStereotypes() {
//			EList<StereotypeApplication> apps = new BasicEList<StereotypeApplication>();
//			if (viewer.getSelection() instanceof IStructuredSelection) {
//				IStructuredSelection selection = (IStructuredSelection) viewer
//						.getSelection();
//				List<?> list = selection.toList();
//				for (Object element : list) {
//					if (element instanceof StereotypeApplication) {
//						apps.add((StereotypeApplication) element);
//					}
//				}
//			}
//			return apps;
//		}
//		
//		private void removeStereotypeApplication(StereotypeApplication element) {
//			currentProfileFacade.removeStereotypeApplication(element);
//			EMFProfileUIPlugin.getDefault().refreshDecorations(
//					element.getAppliedTo());
//			updateView();
//		}
//		
//		private boolean showQuestion(String title, String message) {
//			return MessageDialog.openQuestion(viewer.getControl().getShell(),
//					title, message);
//		}
//		
//		private void showError(String message) {
//			ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
//					message, new Status(IStatus.ERROR,
//							EMFProfileUIPlugin.PLUGIN_ID, message));
//		}
//		
//		private void showError(String message, Throwable throwable) {
//			ErrorDialog.openError(viewer.getControl().getShell(), "Error Occured",
//					message, new Status(IStatus.ERROR,
//							EMFProfileUIPlugin.PLUGIN_ID, throwable.getMessage(),
//							throwable));
//		}
//		
//		/**
//		 * Passing the focus request to the viewer's control.
//		 */
//		public void setFocus() {
//			viewer.getControl().setFocus();
//		}
//		
//		/**
//		 * No operation.
//		 */
//		@Override
//		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//			// returns the same irrespectively of the viewer and input
//		}
//		
//		/**
//		 * Returns the applied {@link StereotypeApplication stereotype applications}
//		 * for the {{@link #getCurrentEObject() currently set} {@link EObject}.
//		 */
//		@Override
//		public Object[] getElements(Object inputElement) {
//			if (getProfileFacade() != null) {
//				return getProfileFacade()
//						.getAppliedStereotypes(getCurrentEObject()).toArray();
//			} else {
//				return new Object[] {};
//			}
//		}
//		
//		/**
//		 * Provider for current stereotype applications provided by the current
//		 * profile facade.
//		 */
//		@Override
//		public Object[] getChildren(Object parentElement) {
//			if (parentElement instanceof EObject) {
//				EObject eObject = (EObject) parentElement;
//				return eObject.eContents().toArray();
//			}
//			return new Object[] {};
//		}
//		
//		/**
//		 * Provider for current stereotype applications provided by the current
//		 * profile facade.
//		 */
//		@Override
//		public Object getParent(Object element) {
//			if (element instanceof EObject) {
//				return ((EObject) element).eContainer();
//			}
//			return null;
//		}
//		
//		/**
//		 * Provider for current stereotype applications provided by the current
//		 * profile facade.
//		 */
//		@Override
//		public boolean hasChildren(Object element) {
//			return getChildren(element) != null && getChildren(element).length > 0;
//		}
//		
//		/**
//		 * Propagates this call to the viewer.
//		 * 
//		 * @param listener
//		 *            to propagate.
//		 */
//		@Override
//		public void addSelectionChangedListener(ISelectionChangedListener listener) {
//			viewer.addSelectionChangedListener(listener);
//		}
//		
//		/**
//		 * Propagates this call to the viewer.
//		 * 
//		 * @return the selection.
//		 */
//		@Override
//		public ISelection getSelection() {
//			return viewer.getSelection();
//		}
//		
//		/**
//		 * Propagates this call to the viewer.
//		 * 
//		 * @param listener
//		 *            to propagate.
//		 */
//		@Override
//		public void removeSelectionChangedListener(
//				ISelectionChangedListener listener) {
//			viewer.removeSelectionChangedListener(listener);
//		}
//		
//		/**
//		 * Propagates this call to the viewer.
//		 * 
//		 * @param selection
//		 *            to propagate.
//		 */
//		@Override
//		public void setSelection(ISelection selection) {
//			viewer.setSelection(selection);
//		}
//	}
}
