package org.modelversioning.emfprofile.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofile.ui.views.ProfileApplicationsView;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.osgi.framework.BundleContext;

/**
 * {@link Plugin} for EMF {@link Profile Profiles}.
 */
public class EMFProfileUIPlugin extends AbstractUIPlugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID = "org.modelversioning.emfprofiles.ui"; //$NON-NLS-1$

	/** Profile file extension. */
	public static final String EMF_PROFILE_EXTENSION = "emfprofile"; //$NON-NLS-1$

	/** Default profile application file extension. */
	public static final String EMF_PROFILE_APPLICATION_EXTENSION = "emfprofile.xmi"; //$NON-NLS-1$

	/** The view id of the Profile Application view. */
	private static final String PROFILE_APPLICATION_VIEW_ID = "org.modelversioning.emfprofile.ui.views.ProfileApplicationsView"; //$NON-NLS-1$

	/** The icons path. */
	private static final String iconPath = "icons/"; //$NON-NLS-1$

	/** The image for demonstrate action. */
	public static final String IMG_STEREOTYPE_DEFAULT = "stereotype"; //$NON-NLS-1$

	/** The image for demonstrate action. */
	public static final String IMG_VALIDATE = "validate"; //$NON-NLS-1$

	/** The shared instance. */
	private static EMFProfileUIPlugin plugin;

	/** Profile applications view instance. */
	private ProfileApplicationsView profileApplicationsView;

	/** {@link EObject} to {@link IDecorator} map. */
	private Map<EObject, IDecorator> eObjectToDecoratorMap = new HashMap<EObject, IDecorator>();

	/** Fallback resource set. */
	private ResourceSet resourceSet = new ResourceSetImpl();

	/**
	 * The empty default constructor.
	 */
	public EMFProfileUIPlugin() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * {@inheritDoc}
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
	public static EMFProfileUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Adds a new profile application for the {@link Profile} in the supplied
	 * <code>profileFile</code>.
	 * 
	 * @param workbenchPart
	 *            to use.
	 * @param profileApplicationFile
	 *            to save profile application in.
	 * @param profileFile
	 *            the profile.
	 */
	public void addNewProfileApplication(IWorkbenchPart workbenchPart,
			IFile profileApplicationFile, IFile profileFile) {
		try {
			ResourceSet resourceSet = getResourceSet(workbenchPart);

			Resource profileResource = resourceSet.getResource(
					URI.createFileURI(profileFile.getLocation().toString()),
					true);

			profileApplicationFile.refreshLocal(1, new NullProgressMonitor());

			if (profileResource != null
					&& profileResource.getContents().size() > 0) {
				IProfileFacade facade = createNewProfileFacade(workbenchPart,
						profileApplicationFile);
				loadProfilesFromResource(facade, profileResource);
				if (workbenchPart instanceof DiagramEditor) {
					DiagramEditor diagramEditor = (DiagramEditor) workbenchPart;
					DiagramEditPart diagramEditPart = diagramEditor
							.getDiagramEditPart();
					RootEditPart rootEditPart = diagramEditPart.getRoot();
					getProfileApplicationView().addToView(rootEditPart, facade,
							profileApplicationFile);
				} else {
					getProfileApplicationView().addToView(workbenchPart,
							facade, profileApplicationFile);
				}
			} else {
				ErrorDialog
						.openError(
								workbenchPart.getSite().getShell(),
								"No Profile File",
								"You did not select a valid profile file.",
								new Status(IStatus.ERROR,
										EMFProfileUIPlugin.PLUGIN_ID,
										"Resource was null or did not contain a profile!"));
			}
		} catch (Exception e) {
			IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
					e.getMessage(), e);
			ErrorDialog.openError(workbenchPart.getSite().getShell(),
					"Error While Applying Profile", e.getMessage(), status);
			getLog().log(status);
		}
	}

	private void loadProfilesFromResource(IProfileFacade facade,
			Resource profileResource) {
		for (EObject eObject : profileResource.getContents()) {
			if (eObject instanceof Profile) {
				facade.loadProfile((Profile) eObject);
			}
		}
	}

	/**
	 * Loads an existing profile application.
	 * 
	 * @param workbenchPart
	 *            to use.
	 * @param profileApplicationFile
	 *            to load.
	 */
	public void loadProfileApplication(IWorkbenchPart workbenchPart,
			IFile profileApplicationFile) {
		try {
			profileApplicationFile.refreshLocal(1, new NullProgressMonitor());
			IProfileFacade facade = createNewProfileFacade(workbenchPart,
					profileApplicationFile);
			if (workbenchPart instanceof DiagramEditor) {
				DiagramEditor diagramEditor = (DiagramEditor) workbenchPart;
				DiagramEditPart diagramEditPart = diagramEditor
						.getDiagramEditPart();
				RootEditPart rootEditPart = diagramEditPart.getRoot();
				getProfileApplicationView().addToView(rootEditPart, facade,
						profileApplicationFile);
				refreshAllDecorations();
				refresh(workbenchPart);
			} else {
				getProfileApplicationView().addToView(workbenchPart, facade,
						profileApplicationFile);
			}
		} catch (Exception e) {
			IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
					e.getMessage(), e);
			ErrorDialog
					.openError(workbenchPart.getSite().getShell(),
							"Error Loading Profile Application",
							e.getMessage(), status);
			getLog().log(status);
		}
	}

	private IProfileFacade createNewProfileFacade(IWorkbenchPart workbenchPart,
			IFile profileApplicationFile) throws IOException {
		ResourceSet resourceSet = getResourceSet(workbenchPart);
		Resource profileApplicationResource = createProfileApplicationResource(
				profileApplicationFile, resourceSet);
		IProfileFacade facade = new ProfileFacadeImpl();
		facade.setProfileApplicationResource(profileApplicationResource);
		return facade;
	}

	/**
	 * Creates the profile application resource.
	 * 
	 * @param profileApplicationFile
	 *            specifying the location.
	 * @param resourceSet
	 *            {@link ResourceSet} to use.
	 * @return the created resource.
	 * @throws IOException
	 *             if location not writable.
	 */
	private Resource createProfileApplicationResource(
			IFile profileApplicationFile, ResourceSet resourceSet)
			throws IOException {
		Resource profileApplicationResource = resourceSet
				.createResource(URI.createFileURI(profileApplicationFile
						.getLocation().toString()));
		if (!profileApplicationFile.exists()) {
			profileApplicationResource.save(Collections.emptyMap());
		}
		profileApplicationResource.load(Collections.emptyMap());
		return profileApplicationResource;
	}

	/**
	 * Adds a new {@link StereotypeApplication} to the specified
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            to apply stereotype to.
	 * @param workbenchPart
	 *            workbench part as key to identify facade.
	 */
	public void addNewStereotypeApplication(EObject eObject,
			IWorkbenchPart workbenchPart) {
		try {
			getProfileApplicationView().addNewStereotypeApplication(eObject,
					getPartKey(workbenchPart));
			refreshDecorations(eObject);
			refresh(workbenchPart);
		} catch (PartInitException e) {
			IStatus status = new Status(IStatus.ERROR, PLUGIN_ID,
					e.getMessage(), e);
			ErrorDialog.openError(workbenchPart.getSite().getShell(),
					"Profile Application View could not been resolved",
					e.getMessage(), status);
			getLog().log(status);
		}
	}

	private void refresh(IWorkbenchPart workbenchPart) {
		if (getPartKey(workbenchPart) instanceof RootEditPart) {
			RootEditPart rootEditPart = (RootEditPart) getPartKey(workbenchPart);
			rootEditPart.refresh();
		} else {
			// TODO refresh workbench part
		}
	}

	public void refreshDecorations(final EObject eObject) {
		if (eObjectToDecoratorMap.containsKey(eObject)) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					try {
						TransactionUtil.getEditingDomain(eObject.eResource())
								.runExclusive(new Runnable() {
									public void run() {
										eObjectToDecoratorMap.get(eObject)
												.refresh();
									}
								});
					} catch (Exception e) {
						EMFProfileUIPlugin.getDefault().logError(
								"Decorator refresh failure", e); //$NON-NLS-1$
					}
				}
			});
		}
	}

	public void logError(String message, Throwable ex) {
		EMFProfileUIPlugin
				.getDefault()
				.getLog()
				.log(new Status(IStatus.ERROR, EMFProfileUIPlugin.PLUGIN_ID,
						message, ex)); //$NON-NLS-1$
	}

	public void refreshDecorations(EList<EObject> eObjects) {
		for (EObject eObject : eObjects) {
			refreshDecorations(eObject);
		}
	}

	public void refreshAllDecorations() {
		for (IDecorator decorator : eObjectToDecoratorMap.values()) {
			decorator.refresh();
		}
	}

	/**
	 * Specifies whether any stereotype may currently be applied to the supplied
	 * <code>eObject</code> from the supplied <code>workbenchPart</code>.
	 * 
	 * @param eObject
	 *            to check for.
	 * @param workbenchPart
	 *            to check for.
	 * @return <code>true</code> if we may apply a stereotype, otherwise
	 *         <code>false</code>.
	 */
	public boolean mayApplyStereotype(EObject eObject,
			IWorkbenchPart workbenchPart) {
		try {
			return getProfileApplicationView().mayApplyStereotype(eObject,
					getPartKey(workbenchPart));
		} catch (PartInitException e) {
			return false;
		}
	}

	/**
	 * Retrieves the right part key for identifying the facade from the
	 * specified <code>workbenchPart</code>.
	 * 
	 * @param workbenchPart
	 *            to get part key for.
	 * @return part key.
	 */
	private IAdaptable getPartKey(IWorkbenchPart workbenchPart) {
		IAdaptable adaptable = null;
		if (workbenchPart instanceof DiagramEditor) {
			DiagramEditor diagramEditor = (DiagramEditor) workbenchPart;
			DiagramEditPart diagramEditPart = diagramEditor
					.getDiagramEditPart();
			adaptable = diagramEditPart.getRoot();
		} else {
			adaptable = workbenchPart;
		}
		return adaptable;
	}

	/**
	 * Specifies whether a profile application is currently loaded for the
	 * supplied <code>targetPart</code>.
	 * 
	 * @param targetPart
	 *            to check.
	 * @return <code>true</code> if an application is currently loaded,
	 *         otherwise <code>false</code>.
	 */
	public boolean isProfileApplicationLoaded(IWorkbenchPart targetPart) {
		try {
			return getProfileApplicationView().isProfileApplicationLoaded(
					targetPart);
		} catch (PartInitException e) {
			return false;
		}
	}

	/**
	 * Returns the resolved resource set from the editing domain of the supplied
	 * <code>workbenchPart</code>. If no {@link ResourceSet} can be resolved, a
	 * fall back resource set will be returned instead.
	 * 
	 * @param workbenchPart
	 *            to resolve resource set.
	 * @return the resource set.
	 */
	private ResourceSet getResourceSet(IWorkbenchPart workbenchPart) {
		Object adapter = workbenchPart.getAdapter(IEditingDomainProvider.class);
		if (adapter != null && adapter instanceof IEditingDomainProvider) {
			IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) adapter;
			return editingDomainProvider.getEditingDomain().getResourceSet();
		} else {
			return resourceSet;
		}
	}

	/**
	 * Returns the profile applications view.
	 * 
	 * @return the profile applications view.
	 * @throws PartInitException
	 *             if part cannot be initialized correctly.
	 */
	private ProfileApplicationsView getProfileApplicationView()
			throws PartInitException {
		if (profileApplicationsView == null
				&& PlatformUI.getWorkbench() != null
				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage() != null) {
			profileApplicationsView = (ProfileApplicationsView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView(EMFProfileUIPlugin.PROFILE_APPLICATION_VIEW_ID);
		}
		return profileApplicationsView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		registerImage(registry, IMG_STEREOTYPE_DEFAULT, "Stereotype.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_VALIDATE, "validate.png"); //$NON-NLS-1$
	}

	/**
	 * Registers the specified fileName under the specified key to the specified
	 * registry.
	 * 
	 * @param registry
	 *            registry to register image.
	 * @param key
	 *            key of image.
	 * @param fileName
	 *            file name.
	 */
	private void registerImage(ImageRegistry registry, String key,
			String fileName) {
		try {
			IPath path = new Path(iconPath + fileName);
			URL url = FileLocator.find(getBundle(), path, null);
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Registers the specified external fileName under the specified key to the
	 * specified registry.
	 * 
	 * <p>
	 * Currently only absolute file system paths are supported. With windows use
	 * double backslashes.
	 * </p>
	 * 
	 * TODO Also support http locations and platform:// locations
	 * 
	 * @param registry
	 *            registry to register image.
	 * @param key
	 *            key of image.
	 * @param fileLocation
	 *            file location.
	 */
	private void registerExternalImage(ImageRegistry registry, String key,
			String fileLocation) {
		ImageDescriptor desc = ImageDescriptor.createFromImage(new Image(
				getShell().getDisplay(), fileLocation));
		registry.put(key, desc);
	}

	/**
	 * Returns the image for the specified image name.
	 * 
	 * @param img
	 *            image name.
	 * @return the image.
	 */
	public static Image getImage(String key) {
		return getDefault().getImageRegistry().get(key);
	}

	/**
	 * Returns an image descriptor for the image file identified by the given
	 * key.
	 * 
	 * @param key
	 *            the key
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		return getDefault().getImageRegistry().getDescriptor(key);
	}

	/**
	 * Registers the specified <code>decorator</code> to be
	 * {@link IDecorator#refresh() refreshed} when the specified
	 * <code>eObject</code> is updated regarding stereotypes.
	 * 
	 * @param eObject
	 *            to register.
	 * @param decorator
	 *            to register.
	 */
	public void registerDecorator(EObject eObject, IDecorator decorator) {
		this.eObjectToDecoratorMap.put(eObject, decorator);
	}

	/**
	 * Unregisters the decorators to be for the specified <code>eObject</code>.
	 * 
	 * @param eObject
	 *            to unregister.
	 */
	public void unregisterDecorator(EObject eObject) {
		this.eObjectToDecoratorMap.remove(eObject);
	}

	/**
	 * Returns a set of images that should be used for decorating the specified
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            to get images for.
	 * @return the set of images.
	 */
	public List<Image> getImagesToDecorate(EObject eObject, RootEditPart partKey) {
		try {
			if (getProfileApplicationView() != null) {
				EList<StereotypeApplication> stereotypeApplications = getProfileApplicationView()
						.getAppliedStereotypes(eObject, partKey);
				List<Image> images = new ArrayList<Image>();
				for (StereotypeApplication application : stereotypeApplications) {
					images.add(getStereotypeImage(application));
				}
				return images;
			} else {
				return Collections.emptyList();
			}
		} catch (PartInitException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<String> getToolTipsToDecorate(EObject eObject,
			RootEditPart partKey) {
		try {
			if (getProfileApplicationView() != null) {
				EList<StereotypeApplication> stereotypeApplications = getProfileApplicationView()
						.getAppliedStereotypes(eObject, partKey);
				List<String> toolTips = new ArrayList<String>();
				for (StereotypeApplication application : stereotypeApplications) {
					toolTips.add(getStereotypeLabel(application));
				}
				return toolTips;
			} else {
				return Collections.emptyList();
			}
		} catch (PartInitException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/**
	 * Returns the tool tip text for the supplied
	 * <code>stereotypeApplication</code>.
	 * 
	 * @param stereotypeApplication
	 *            to get tool tip text for.
	 * @return the tool tip text.
	 */
	private String getStereotypeLabel(StereotypeApplication application) {
		if (application.eClass() instanceof Stereotype) {
			return "<<" + ((Stereotype) application.eClass()).getName() + ">>"; //$NON-NLS-1$ $NON-NLS-2$
		}
		return "Stereotype application"; //$NON-NLS-1$
	}

	/**
	 * Returns the image for the supplied <code>stereotypeApplication</code>.
	 * 
	 * @param stereotypeApplication
	 *            to get {@link Image} for.
	 * @return the {@link Image}.
	 */
	private Image getStereotypeImage(StereotypeApplication stereotypeApplication) {
		if (stereotypeApplication.getStereotype() != null) {
			Stereotype stereotype = stereotypeApplication.getStereotype();
			if (stereotype.hasIcon()) {
				if (getImage(getImageKey(stereotype)) == null) {
					registerExternalImage(getImageRegistry(),
							getImageKey(stereotype), stereotype.getIconPath());
				}
				return getImage(getImageKey(stereotype));
			}
		}
		return getImage(IMG_STEREOTYPE_DEFAULT);
	}

	/**
	 * Returns the image key for the supplied <code>stereotype</code>.
	 * 
	 * @param stereotype
	 *            to get image key for.
	 * @return the image key.
	 */
	private String getImageKey(Stereotype stereotype) {
		return stereotype.getEPackage().getNsURI() + "#" + stereotype.getName();
	}

	/**
	 * Returns the shell.
	 * 
	 * @return the shell.
	 */
	public static Shell getShell() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getShell();
	}

	/**
	 * Triggers unloading a profile application.
	 */
	public void unloadProfileApplication() {
		// TODO implement

	}

}
