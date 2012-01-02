package org.modelversioning.emfprofile.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttribute2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EAttributeEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassName2EditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EClassNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeInstanceClassNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EDataTypeNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumLiteralEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EEnumNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EOperationEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EPackageNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceLowerBoundUpperBoundEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.EReferenceNameEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.ExtensionLowerBoundUpperBoundEditPart;
import org.modelversioning.emfprofile.diagram.edit.parts.StereotypeNameEditPart;
import org.modelversioning.emfprofile.diagram.parsers.MessageFormatParser;
import org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry;

/**
 * @generated
 */
public class EMFProfileParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser stereotypeName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getStereotypeName_5008Parser() {
		if (stereotypeName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			stereotypeName_5008Parser = parser;
		}
		return stereotypeName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser eClassName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getEClassName_5002Parser() {
		if (eClassName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eClassName_5002Parser = parser;
		}
		return eClassName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser ePackageName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getEPackageName_5004Parser() {
		if (ePackageName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			ePackageName_5004Parser = parser;
		}
		return ePackageName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser eEnumName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getEEnumName_5005Parser() {
		if (eEnumName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eEnumName_5005Parser = parser;
		}
		return eEnumName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser eDataTypeName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getEDataTypeName_5006Parser() {
		if (eDataTypeName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eDataTypeName_5006Parser = parser;
		}
		return eDataTypeName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser eDataTypeInstanceClassName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getEDataTypeInstanceClassName_5007Parser() {
		if (eDataTypeInstanceClassName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getEClassifier_InstanceClassName() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eDataTypeInstanceClassName_5007Parser = parser;
		}
		return eDataTypeInstanceClassName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser eAttribute_3001Parser;

	/**
	 * @generated
	 */
	private IParser getEAttribute_3001Parser() {
		if (eAttribute_3001Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			eAttribute_3001Parser = parser;
		}
		return eAttribute_3001Parser;
	}

	/**
	 * @generated
	 */
	private IParser eAttribute_3004Parser;

	/**
	 * @generated
	 */
	private IParser getEAttribute_3004Parser() {
		if (eAttribute_3004Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eAttribute_3004Parser = parser;
		}
		return eAttribute_3004Parser;
	}

	/**
	 * @generated
	 */
	private IParser eOperation_3005Parser;

	/**
	 * @generated
	 */
	private IParser getEOperation_3005Parser() {
		if (eOperation_3005Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eOperation_3005Parser = parser;
		}
		return eOperation_3005Parser;
	}

	/**
	 * @generated
	 */
	private IParser eClassName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getEClassName_5003Parser() {
		if (eClassName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eClassName_5003Parser = parser;
		}
		return eClassName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser eEnumLiteral_3003Parser;

	/**
	 * @generated
	 */
	private IParser getEEnumLiteral_3003Parser() {
		if (eEnumLiteral_3003Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eEnumLiteral_3003Parser = parser;
		}
		return eEnumLiteral_3003Parser;
	}

	/**
	 * @generated
	 */
	private IParser extensionLowerBoundUpperBound_6004Parser;

	/**
	 * @generated
	 */
	private IParser getExtensionLowerBoundUpperBound_6004Parser() {
		if (extensionLowerBoundUpperBound_6004Parser == null) {
			EAttribute[] features = new EAttribute[] {
					EMFProfilePackage.eINSTANCE.getExtension_LowerBound(),
					EMFProfilePackage.eINSTANCE.getExtension_UpperBound() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}..{1,choice,-2#?|-1#*|-1<{1}}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}..{1,choice,-2#?|-1#*|-1<{1}}"); //$NON-NLS-1$
			parser.setEditPattern("{0}..{1}"); //$NON-NLS-1$
			extensionLowerBoundUpperBound_6004Parser = parser;
		}
		return extensionLowerBoundUpperBound_6004Parser;
	}

	/**
	 * @generated
	 */
	private IParser eReferenceName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getEReferenceName_6001Parser() {
		if (eReferenceName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { EcorePackage.eINSTANCE
					.getENamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eReferenceName_6001Parser = parser;
		}
		return eReferenceName_6001Parser;
	}

	/**
	 * @generated
	 */
	private IParser eReferenceLowerBoundUpperBound_6002Parser;

	/**
	 * @generated
	 */
	private IParser getEReferenceLowerBoundUpperBound_6002Parser() {
		if (eReferenceLowerBoundUpperBound_6002Parser == null) {
			EAttribute[] features = new EAttribute[] {
					EcorePackage.eINSTANCE.getETypedElement_LowerBound(),
					EcorePackage.eINSTANCE.getETypedElement_UpperBound() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}..{1,choice,-2#?|-1#*|-1<{1}}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}..{1,choice,-2#?|-1#*|-1<{1}}"); //$NON-NLS-1$
			parser.setEditPattern("{0}..{1}"); //$NON-NLS-1$
			eReferenceLowerBoundUpperBound_6002Parser = parser;
		}
		return eReferenceLowerBoundUpperBound_6002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case StereotypeNameEditPart.VISUAL_ID:
			return getStereotypeName_5008Parser();
		case EClassNameEditPart.VISUAL_ID:
			return getEClassName_5002Parser();
		case EPackageNameEditPart.VISUAL_ID:
			return getEPackageName_5004Parser();
		case EEnumNameEditPart.VISUAL_ID:
			return getEEnumName_5005Parser();
		case EDataTypeNameEditPart.VISUAL_ID:
			return getEDataTypeName_5006Parser();
		case EDataTypeInstanceClassNameEditPart.VISUAL_ID:
			return getEDataTypeInstanceClassName_5007Parser();
		case EAttributeEditPart.VISUAL_ID:
			return getEAttribute_3001Parser();
		case EAttribute2EditPart.VISUAL_ID:
			return getEAttribute_3004Parser();
		case EOperationEditPart.VISUAL_ID:
			return getEOperation_3005Parser();
		case EClassName2EditPart.VISUAL_ID:
			return getEClassName_5003Parser();
		case EEnumLiteralEditPart.VISUAL_ID:
			return getEEnumLiteral_3003Parser();
		case ExtensionLowerBoundUpperBoundEditPart.VISUAL_ID:
			return getExtensionLowerBoundUpperBound_6004Parser();
		case EReferenceNameEditPart.VISUAL_ID:
			return getEReferenceName_6001Parser();
		case EReferenceLowerBoundUpperBoundEditPart.VISUAL_ID:
			return getEReferenceLowerBoundUpperBound_6002Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(EMFProfileVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(EMFProfileVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (EMFProfileElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
