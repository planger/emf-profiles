/**
 * 
 */
package org.modelversioning.emfprofile.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Konrad Wieland
 * 
 */
public class ExtendedLoadResourceAction extends LoadResourceAction {

	private Shell shell;

	// EditingDomain domain
	// =((IEditingDomainProvider)activeEditorPart).getEditingDomain();

	@Override
	public EditingDomain getEditingDomain() {
		return super.getEditingDomain();
	}

	public ExtendedLoadResourceAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtendedLoadResourceAction(EditingDomain domain) {
		super(domain);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setEditingDomain(EditingDomain domain) {
		super.setEditingDomain(domain);
	}

	@Override
	public void run() {
		// super.run();

		// ExtendedLoadResourceDialog loadResourceDialog =
		// new ExtendedLoadResourceDialog
		// (shell, domain);
		//
		// if (loadResourceDialog.open() == Window.OK &&
		// !loadResourceDialog.getRegisteredPackages().isEmpty())
		// {
		// String source = EcoreEditorPlugin.INSTANCE.getSymbolicName();
		// BasicDiagnostic diagnosic =
		// new BasicDiagnostic(Diagnostic.INFO, source, 0,
		// EcoreEditorPlugin.INSTANCE.getString("_UI_RuntimePackageDetail_message"),
		// null);
		// for (EPackage ePackage : loadResourceDialog.getRegisteredPackages())
		// {
		// diagnosic.add(new BasicDiagnostic(Diagnostic.INFO, source, 0,
		// ePackage.getNsURI(), null));
		// }
		// new DiagnosticDialog
		// (shell,
		// EcoreEditorPlugin.INSTANCE.getString("_UI_Information_title"),
		// EcoreEditorPlugin.INSTANCE.getString("_UI_RuntimePackageHeader_message"),
		// diagnosic,
		// Diagnostic.INFO).open();
		// }

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, new LabelProvider() {
					public Image getImage(Object element) {
						return ExtendedImageRegistry.getInstance().getImage(
								EcoreEditPlugin.INSTANCE
										.getImage("full/obj16/EPackage"));
					}
				});

		dialog.setTitle("Select Base Metamodel:");
		dialog.setMessage("Select a String (* = any string, ? = any char):");
		dialog.setElements(getRegistryEntries());

		// dialog.setMultipleSelection(true);

		if (dialog.open() == Dialog.OK) {

			Object firstResult = dialog.getFirstResult();
			if (firstResult != null) {
				if (firstResult instanceof String) {

					String testURI = firstResult.toString();
					System.out.println("TestURI: "+testURI);
					
					URI packageURI = getRegistryEntryURI(firstResult);
					
					
					
					System.out.println("PackageURI "+packageURI);
					

					// String testURI = packageURI.toString();

					if (getEditingDomain() != null) {
						ResourceSet resourceSet = getEditingDomain()
								.getResourceSet();
						Resource resource = resourceSet.getResource(packageURI,
								true);
						try {
							resource.load(Collections.emptyMap());
							// TODO check which URI we actually need

							resource.setURI(URI.createURI((String) firstResult,
									true));
							
							
							EObject eObject = resource.getContents().get(0);
							if (eObject instanceof EPackage) {
								//resourceSet.getPackageRegistry().put(
									//	((EPackage) eObject).getNsURI(),
										//eObject);
								//TEST
								resourceSet.getPackageRegistry().put(testURI, eObject);
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}
	}

	private URI getRegistryEntryURI(Object firstResult) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getURIConverter().getURIMap()
				.putAll(EcorePlugin.computePlatformURIMap());
		StringBuffer uris = new StringBuffer();
		Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin
				.getEPackageNsURIToGenModelLocationMap();
		URI location = ePackageNsURItoGenModelLocationMap
				.get((String) firstResult);
		Resource resource = resourceSet.getResource(location, true);
		EcoreUtil.resolveAll(resource);

		URI realURI = null;
		for (Resource resourceIt : resourceSet.getResources()) {
			for (EPackage ePackage : getAllPackages(resourceIt)) {
				if (firstResult.equals(ePackage.getNsURI())) {
					realURI = resourceIt.getURI();
					
					break;
				}
			}
		}

		System.out.println("RealURI: " + realURI);
		return realURI;

	}

	protected Collection<EPackage> getAllPackages(Resource resource) {
		List<EPackage> result = new ArrayList<EPackage>();
		for (TreeIterator<?> j = new EcoreUtil.ContentTreeIterator<Object>(
				resource.getContents()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<? extends EObject> getEObjectChildren(
					EObject eObject) {
				return eObject instanceof EPackage ? ((EPackage) eObject)
						.getESubpackages().iterator() : Collections
						.<EObject> emptyList().iterator();
			}
		}; j.hasNext();) {
			Object content = j.next();
			if (content instanceof EPackage) {
				result.add((EPackage) content);
			}
		}
		return result;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public void setText(String text) {
		super.setText("Select Base Metamodel...");
	}

	@Override
	public void setActiveEditor(IEditorPart editorPart) {
		super.setActiveEditor(editorPart);
	}

	@Override
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		super.setActiveWorkbenchPart(workbenchPart);
		System.out.println("WorkbenchPart: " + workbenchPart);
	}

	public Object[] getRegistryEntries() {

		Object[] result = EPackage.Registry.INSTANCE.keySet().toArray(
				new Object[EPackage.Registry.INSTANCE.size()]);
		Arrays.sort(result);

		return result;
	}
}
