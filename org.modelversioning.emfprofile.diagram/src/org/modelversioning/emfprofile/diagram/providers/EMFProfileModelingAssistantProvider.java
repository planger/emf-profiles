package org.modelversioning.emfprofile.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.modelversioning.emfprofile.diagram.edit.parts.EClass2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageContentsEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ProfileEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeEditPart;
import org.modelversioning.emfprofile.diagram.part.EMFProfileDiagramEditorPlugin;
import org.modelversioning.emfprofile.diagram.part.Messages;

/**
 * @generated
 */
public class EMFProfileModelingAssistantProvider extends
		ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof ProfileEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(5);
			types.add(EMFProfileElementTypes.Stereotype_2006);
			types.add(EMFProfileElementTypes.EClass_2002);
			types.add(EMFProfileElementTypes.EPackage_2003);
			types.add(EMFProfileElementTypes.EEnum_2004);
			types.add(EMFProfileElementTypes.EDataType_2005);
			return types;
		}
		if (editPart instanceof StereotypeEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(EMFProfileElementTypes.EAttribute_3001);
			return types;
		}
		if (editPart instanceof EClassEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(EMFProfileElementTypes.EAttribute_3004);
			types.add(EMFProfileElementTypes.EOperation_3005);
			return types;
		}
		if (editPart instanceof EEnumEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(EMFProfileElementTypes.EEnumLiteral_3003);
			return types;
		}
		if (editPart instanceof EClass2EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(2);
			types.add(EMFProfileElementTypes.EAttribute_3004);
			types.add(EMFProfileElementTypes.EOperation_3005);
			return types;
		}
		if (editPart instanceof EPackageContentsEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(EMFProfileElementTypes.EClass_3002);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof StereotypeEditPart) {
			return ((StereotypeEditPart) sourceEditPart)
					.getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof EClassEditPart) {
			return ((EClassEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof EClass2EditPart) {
			return ((EClass2EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof StereotypeEditPart) {
			return ((StereotypeEditPart) targetEditPart)
					.getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof EClassEditPart) {
			return ((EClassEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof EEnumEditPart) {
			return ((EEnumEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof EDataTypeEditPart) {
			return ((EDataTypeEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof EClass2EditPart) {
			return ((EClass2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof StereotypeEditPart) {
			return ((StereotypeEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof EClassEditPart) {
			return ((EClassEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof EClass2EditPart) {
			return ((EClass2EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof StereotypeEditPart) {
			return ((StereotypeEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof EClassEditPart) {
			return ((EClassEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof EEnumEditPart) {
			return ((EEnumEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof EDataTypeEditPart) {
			return ((EDataTypeEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof EClass2EditPart) {
			return ((EClass2EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof StereotypeEditPart) {
			return ((StereotypeEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof EClassEditPart) {
			return ((EClassEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof EClass2EditPart) {
			return ((EClass2EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target,
				getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source,
				getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for (Iterator<EObject> it = diagram.getElement().eAllContents(); it
				.hasNext();) {
			EObject element = it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				EMFProfileDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.EMFProfileModelingAssistantProviderMessage);
		dialog.setTitle(Messages.EMFProfileModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
