package org.modelversioning.emfprofile.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttributeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClass2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassESuperTypesEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EOperationEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ProfileEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeEditPart;
import org.modelversioning.emfprofile.diagram.part.EMFProfileDiagramEditorPlugin;

/**
 * @generated
 */
public class EMFProfileElementTypes {

	/**
	 * @generated
	 */
	private EMFProfileElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Profile_1000 = getElementType("org.modelversioning.emfprofile.diagram.Profile_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Stereotype_2006 = getElementType("org.modelversioning.emfprofile.diagram.Stereotype_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType EClass_2002 = getElementType("org.modelversioning.emfprofile.diagram.EClass_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EPackage_2003 = getElementType("org.modelversioning.emfprofile.diagram.EPackage_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EEnum_2004 = getElementType("org.modelversioning.emfprofile.diagram.EEnum_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EDataType_2005 = getElementType("org.modelversioning.emfprofile.diagram.EDataType_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EAttribute_3001 = getElementType("org.modelversioning.emfprofile.diagram.EAttribute_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EAttribute_3004 = getElementType("org.modelversioning.emfprofile.diagram.EAttribute_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EOperation_3005 = getElementType("org.modelversioning.emfprofile.diagram.EOperation_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EClass_3002 = getElementType("org.modelversioning.emfprofile.diagram.EClass_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EEnumLiteral_3003 = getElementType("org.modelversioning.emfprofile.diagram.EEnumLiteral_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Extension_4005 = getElementType("org.modelversioning.emfprofile.diagram.Extension_4005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType EClassESuperTypes_4002 = getElementType("org.modelversioning.emfprofile.diagram.EClassESuperTypes_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EReference_4003 = getElementType("org.modelversioning.emfprofile.diagram.EReference_4003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return EMFProfileDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Profile_1000, EMFProfilePackage.eINSTANCE.getProfile());

			elements.put(Stereotype_2006,
					EMFProfilePackage.eINSTANCE.getStereotype());

			elements.put(EClass_2002, EcorePackage.eINSTANCE.getEClass());

			elements.put(EPackage_2003, EcorePackage.eINSTANCE.getEPackage());

			elements.put(EEnum_2004, EcorePackage.eINSTANCE.getEEnum());

			elements.put(EDataType_2005, EcorePackage.eINSTANCE.getEDataType());

			elements.put(EAttribute_3001,
					EcorePackage.eINSTANCE.getEAttribute());

			elements.put(EAttribute_3004,
					EcorePackage.eINSTANCE.getEAttribute());

			elements.put(EOperation_3005,
					EcorePackage.eINSTANCE.getEOperation());

			elements.put(EClass_3002, EcorePackage.eINSTANCE.getEClass());

			elements.put(EEnumLiteral_3003,
					EcorePackage.eINSTANCE.getEEnumLiteral());

			elements.put(Extension_4005,
					EMFProfilePackage.eINSTANCE.getExtension());

			elements.put(EClassESuperTypes_4002,
					EcorePackage.eINSTANCE.getEClass_ESuperTypes());

			elements.put(EReference_4003,
					EcorePackage.eINSTANCE.getEReference());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Profile_1000);
			KNOWN_ELEMENT_TYPES.add(Stereotype_2006);
			KNOWN_ELEMENT_TYPES.add(EClass_2002);
			KNOWN_ELEMENT_TYPES.add(EPackage_2003);
			KNOWN_ELEMENT_TYPES.add(EEnum_2004);
			KNOWN_ELEMENT_TYPES.add(EDataType_2005);
			KNOWN_ELEMENT_TYPES.add(EAttribute_3001);
			KNOWN_ELEMENT_TYPES.add(EAttribute_3004);
			KNOWN_ELEMENT_TYPES.add(EOperation_3005);
			KNOWN_ELEMENT_TYPES.add(EClass_3002);
			KNOWN_ELEMENT_TYPES.add(EEnumLiteral_3003);
			KNOWN_ELEMENT_TYPES.add(Extension_4005);
			KNOWN_ELEMENT_TYPES.add(EClassESuperTypes_4002);
			KNOWN_ELEMENT_TYPES.add(EReference_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case ProfileEditPart.VISUAL_ID:
			return Profile_1000;
		case StereotypeEditPart.VISUAL_ID:
			return Stereotype_2006;
		case EClassEditPart.VISUAL_ID:
			return EClass_2002;
		case EPackageEditPart.VISUAL_ID:
			return EPackage_2003;
		case EEnumEditPart.VISUAL_ID:
			return EEnum_2004;
		case EDataTypeEditPart.VISUAL_ID:
			return EDataType_2005;
		case EAttributeEditPart.VISUAL_ID:
			return EAttribute_3001;
		case EAttribute2EditPart.VISUAL_ID:
			return EAttribute_3004;
		case EOperationEditPart.VISUAL_ID:
			return EOperation_3005;
		case EClass2EditPart.VISUAL_ID:
			return EClass_3002;
		case EEnumLiteralEditPart.VISUAL_ID:
			return EEnumLiteral_3003;
		case ExtensionEditPart.VISUAL_ID:
			return Extension_4005;
		case EClassESuperTypesEditPart.VISUAL_ID:
			return EClassESuperTypes_4002;
		case EReferenceEditPart.VISUAL_ID:
			return EReference_4003;
		}
		return null;
	}

}
