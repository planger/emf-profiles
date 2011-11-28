package org.modelversioning.emfprofile.diagram.part;

import java.util.Arrays;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateOrSelectElementCommand.LabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @generated NOT
 */
public class CreateShortcutAction extends AbstractHandler {
	/**
	 * @generated NOT
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart diagramEditor = HandlerUtil.getActiveEditorChecked(event);
		Shell shell = diagramEditor.getEditorSite().getShell();
		assert diagramEditor instanceof DiagramEditor;
		TransactionalEditingDomain editingDomain = ((DiagramEditor) diagramEditor)
				.getEditingDomain();
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		assert selection instanceof IStructuredSelection;
		assert ((IStructuredSelection) selection).size() == 1;
		assert ((IStructuredSelection) selection).getFirstElement() instanceof EditPart;
		EditPart selectedDiagramPart = (EditPart) ((IStructuredSelection) selection)
				.getFirstElement();
		final View view = (View) selectedDiagramPart.getModel();
		//Resource resource = EMFProfileDiagramEditorUtil.openModel(shell,
		//	Messages.CreateShortcutAction_OpenModelTitle, editingDomain);
		//if (resource == null || resource.getContents().isEmpty()) {
		//	return null;
		//	}

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, new LabelProvider() {
					public Image getImage(Object element) {
						return ExtendedImageRegistry.getInstance().getImage(
								EcoreEditPlugin.INSTANCE
										.getImage("full/obj16/EPackage"));
					}
				});

		dialog.setTitle("Import Metaclass:");
		dialog.setMessage("Select a String (* = any string, ? = any char):");
		dialog.setElements(getRegistryEntries());

		if (dialog.open() == Dialog.OK) {

			Object firstResult = dialog.getFirstResult();

			String stringURI = firstResult.toString();

			EPackage ePackage = EPackage.Registry.INSTANCE
					.getEPackage(stringURI);

			ShortcutCreationWizard wizard = new ShortcutCreationWizard(
					ePackage, view, editingDomain);
			wizard.setWindowTitle(Messages.CreateShortcutAction_WizardTitle);
			EMFProfileDiagramEditorUtil.runWizard(shell, wizard,
					"CreateShortcut"); //$NON-NLS-1$
		}
		// ShortcutCreationWizard wizard = new ShortcutCreationWizard((EObject)
		// resource.getContents().get(0), view, editingDomain);
		// wizard.setWindowTitle(Messages.CreateShortcutAction_WizardTitle);
		//EMFProfileDiagramEditorUtil.runWizard(shell, wizard, "CreateShortcut"); //$NON-NLS-1$
		return null;
	}

	public Object[] getRegistryEntries() {

		Object[] result = EPackage.Registry.INSTANCE.keySet().toArray(
				new Object[EPackage.Registry.INSTANCE.size()]);
		Arrays.sort(result);

		return result;
	}

}
