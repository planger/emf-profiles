package org.modelversioning.emfprofile.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EMFProfilePaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createEMFProfiles1Group());
		paletteRoot.add(createEcore2Group());
	}

	/**
	 * Creates "EMF Profiles" palette tool group
	 * @generated
	 */
	private PaletteContainer createEMFProfiles1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.EMFProfiles1Group_title);
		paletteContainer.setId("createEMFProfiles1Group"); //$NON-NLS-1$
		paletteContainer.add(createStereotype1CreationTool());
		paletteContainer.add(createTaggedValue2CreationTool());
		paletteContainer.add(createExtension3CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Ecore" palette tool group
	 * @generated
	 */
	private PaletteContainer createEcore2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Ecore2Group_title);
		paletteContainer.setId("createEcore2Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Ecore2Group_desc);
		paletteContainer.add(createEClass1CreationTool());
		paletteContainer.add(createGeneralization2CreationTool());
		paletteContainer.add(createReference3CreationTool());
		paletteContainer.add(createPackage4CreationTool());
		paletteContainer.add(createEnumeration5CreationTool());
		paletteContainer.add(createDataType6CreationTool());
		paletteContainer.add(createLiteral7CreationTool());
		paletteContainer.add(createAttribute8CreationTool());
		paletteContainer.add(createOperation9CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createStereotype1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Stereotype1CreationTool_title,
				Messages.Stereotype1CreationTool_desc,
				Collections
						.singletonList(EMFProfileElementTypes.Stereotype_2006));
		entry.setId("createStereotype1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.Stereotype_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTaggedValue2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.TaggedValue2CreationTool_title,
				Messages.TaggedValue2CreationTool_desc,
				Collections
						.singletonList(EMFProfileElementTypes.EAttribute_3001));
		entry.setId("createTaggedValue2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EAttribute_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExtension3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Extension3CreationTool_title,
				Messages.Extension3CreationTool_desc,
				Collections
						.singletonList(EMFProfileElementTypes.Extension_4005));
		entry.setId("createExtension3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.Extension_4005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEClass1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(EMFProfileElementTypes.EClass_2002);
		types.add(EMFProfileElementTypes.EClass_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.EClass1CreationTool_title, null, types);
		entry.setId("createEClass1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EClass_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGeneralization2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Generalization2CreationTool_title,
				null,
				Collections
						.singletonList(EMFProfileElementTypes.EClassESuperTypes_4002));
		entry.setId("createGeneralization2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EClassESuperTypes_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReference3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Reference3CreationTool_title, null,
				Collections
						.singletonList(EMFProfileElementTypes.EReference_4003));
		entry.setId("createReference3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EReference_4003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPackage4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Package4CreationTool_title, null,
				Collections.singletonList(EMFProfileElementTypes.EPackage_2003));
		entry.setId("createPackage4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EPackage_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEnumeration5CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Enumeration5CreationTool_title, null,
				Collections.singletonList(EMFProfileElementTypes.EEnum_2004));
		entry.setId("createEnumeration5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EEnum_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDataType6CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.DataType6CreationTool_title, null,
				Collections
						.singletonList(EMFProfileElementTypes.EDataType_2005));
		entry.setId("createDataType6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EDataType_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLiteral7CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Literal7CreationTool_title,
				null,
				Collections
						.singletonList(EMFProfileElementTypes.EEnumLiteral_3003));
		entry.setId("createLiteral7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EEnumLiteral_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttribute8CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Attribute8CreationTool_title, null,
				Collections
						.singletonList(EMFProfileElementTypes.EAttribute_3004));
		entry.setId("createAttribute8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EAttribute_3004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOperation9CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Operation9CreationTool_title, null,
				Collections
						.singletonList(EMFProfileElementTypes.EOperation_3005));
		entry.setId("createOperation9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(EMFProfileElementTypes
				.getImageDescriptor(EMFProfileElementTypes.EOperation_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
