package org.modelversioning.emfprofile.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.modelversioning.emfprofile.diagram.edit.policies.EDataTypeItemSemanticEditPolicy;
import org.modelversioning.emfprofile.diagram.edit.policies.EMFProfileTextSelectionEditPolicy;
import org.modelversioning.emfprofile.diagram.layout.GradientRectangleFigure;
import org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry;
import org.modelversioning.emfprofile.diagram.providers.EMFProfileElementTypes;

/**
 * @generated
 */
public class EDataTypeEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2005;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public EDataTypeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new EDataTypeItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		ConstrainedToolbarLayoutEditPolicy lep = new ConstrainedToolbarLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null) {
					if (child instanceof ITextAwareEditPart) {
						return new EMFProfileTextSelectionEditPolicy();
					}
				}
				return super.createChildEditPolicy(child);
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new DataTypeFigure();
	}

	/**
	 * @generated
	 */
	public DataTypeFigure getPrimaryShape() {
		return (DataTypeFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof EDataTypeNameEditPart) {
			((EDataTypeNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureDataTypeNameLabel());
			return true;
		}
		if (childEditPart instanceof EDataTypeInstanceClassNameEditPart) {
			((EDataTypeInstanceClassNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureDataTypeJavaLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof EDataTypeNameEditPart) {
			return true;
		}
		if (childEditPart instanceof EDataTypeInstanceClassNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(EMFProfileVisualIDRegistry
				.getType(EDataTypeNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(EMFProfileElementTypes.EReference_4003);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == EMFProfileElementTypes.EReference_4003) {
			types.add(EMFProfileElementTypes.Stereotype_2006);
			types.add(EMFProfileElementTypes.EClass_2002);
			types.add(EMFProfileElementTypes.EClass_3002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification event) {
		if (event.getNotifier() == getModel()
				&& EcorePackage.eINSTANCE.getEModelElement_EAnnotations()
						.equals(event.getFeature())) {
			handleMajorSemanticChange();
		} else {
			super.handleNotificationEvent(event);
		}
	}

	/**
	 * @generated NOT
	 */
	public class DataTypeFigure extends GradientRectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureDataTypeNameLabel;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureDataTypeJavaLabel;

		/**
		 * @generated NOT
		 */
		public DataTypeFigure() {

			ToolbarLayout layoutThis = new ToolbarLayout();
			layoutThis.setStretchMinorAxis(true);
			layoutThis.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);

			layoutThis.setSpacing(1);
			layoutThis.setVertical(true);

			this.setLayoutManager(layoutThis);
			this.setBackgroundColor(THIS_BACK);
			this.setForegroundColor(ColorConstants.black);

			this.setShouldUseGradient(true);

			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(30)));

			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			WrappingLabel dataTypeFixedNameLabel0 = new WrappingLabel();
			dataTypeFixedNameLabel0.setText("<<datatype>>");
			dataTypeFixedNameLabel0.setAlignment(PositionConstants.CENTER);

			this.add(dataTypeFixedNameLabel0);

			fFigureDataTypeNameLabel = new WrappingLabel();
			fFigureDataTypeNameLabel.setText("<..>");
			fFigureDataTypeNameLabel.setAlignment(PositionConstants.MIDDLE);

			this.add(fFigureDataTypeNameLabel);

			fFigureDataTypeJavaLabel = new WrappingLabel();
			fFigureDataTypeJavaLabel.setText("");
			fFigureDataTypeJavaLabel.setAlignment(PositionConstants.MIDDLE);

			this.add(fFigureDataTypeJavaLabel);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDataTypeNameLabel() {
			return fFigureDataTypeNameLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDataTypeJavaLabel() {
			return fFigureDataTypeJavaLabel;
		}

	}

	/**
	 * @generated NOT
	 */
	static final Color THIS_BACK = new Color(null, 248, 249, 212);

}
