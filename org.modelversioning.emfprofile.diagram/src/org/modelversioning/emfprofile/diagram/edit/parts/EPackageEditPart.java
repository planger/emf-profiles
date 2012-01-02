package org.modelversioning.emfprofile.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.modelversioning.emfprofile.diagram.edit.policies.EPackageItemSemanticEditPolicy;
import org.modelversioning.emfprofile.diagram.layout.AlphaDropShadowBorder;
import org.modelversioning.emfprofile.diagram.layout.GradientRectangleFigure;
import org.modelversioning.emfprofile.diagram.layout.PackageLabelRectangle;
import org.modelversioning.emfprofile.diagram.part.EMFProfileVisualIDRegistry;

/**
 * @generated
 */
public class EPackageEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

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
	public EPackageEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new EPackageItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new PackageFigure();
	}

	/**
	 * @generated
	 */
	public PackageFigure getPrimaryShape() {
		return (PackageFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof EPackageNameEditPart) {
			((EPackageNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigurePackageNameLabel());
			return true;
		}
		if (childEditPart instanceof EPackageContentsEditPart) {
			IFigure pane = getPrimaryShape().getFigurePackageBodyRectangle();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((EPackageContentsEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof EPackageNameEditPart) {
			return true;
		}
		if (childEditPart instanceof EPackageContentsEditPart) {
			IFigure pane = getPrimaryShape().getFigurePackageBodyRectangle();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((EPackageContentsEditPart) childEditPart).getFigure());
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
		if (editPart instanceof EPackageContentsEditPart) {
			return getPrimaryShape().getFigurePackageBodyRectangle();
		}
		return getContentPane();
	}

	/**
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(40), getMapMode().DPtoLP(40)) {

			@Override
			public PointList getPolygonPoints() {
				PointList points = new PointList(7);
				Rectangle nameRectangle = getPrimaryShape()
						.getFigurePackageLabelRectangle().getBounds();
				Rectangle bodyRectangle = getPrimaryShape()
						.getFigurePackageBodyRectangle().getBounds();
				points.addPoint(nameRectangle.x, nameRectangle.y);
				points.addPoint(nameRectangle.x + nameRectangle.width,
						nameRectangle.y);
				points.addPoint(nameRectangle.x + nameRectangle.width,
						nameRectangle.y + nameRectangle.height);
				points.addPoint(bodyRectangle.x + bodyRectangle.width,
						bodyRectangle.y);
				points.addPoint(bodyRectangle.x + bodyRectangle.width,
						bodyRectangle.y + bodyRectangle.height);
				points.addPoint(bodyRectangle.x, bodyRectangle.y
						+ bodyRectangle.height);
				points.addPoint(nameRectangle.x, nameRectangle.y);
				return points;
			}
		};
		AlphaDropShadowBorder shadowBorder = new AlphaDropShadowBorder();
		shadowBorder.setShouldDrawDropShadow(true);
		result.setBorder(shadowBorder);
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
				.getType(EPackageNameEditPart.VISUAL_ID));
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
	 * @generated
	 */
	public class PackageFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private RectangleFigure fFigurePackageBodyRectangle;
		/**
		 * @generated
		 */
		private WrappingLabel fFigurePackageNameLabel;
		/**
		 * @generated
		 */
		private RectangleFigure fFigurePackageLabelRectangle;

		/**
		 * @generated
		 */
		public PackageFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 2;
			layoutThis.makeColumnsEqualWidth = false;
			layoutThis.horizontalSpacing = 0;
			layoutThis.verticalSpacing = 0;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setFill(false);
			this.setOutline(false);
			this.setLineWidth(2);
			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(110),
					getMapMode().DPtoLP(100)));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			fFigurePackageLabelRectangle = new PackageLabelRectangle();
			fFigurePackageLabelRectangle.setLineWidth(1);

			fFigurePackageLabelRectangle.setBorder(new MarginBorder(
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5)));

			GridData constraintFFigurePackageLabelRectangle = new GridData();
			constraintFFigurePackageLabelRectangle.verticalAlignment = GridData.BEGINNING;
			constraintFFigurePackageLabelRectangle.horizontalAlignment = GridData.BEGINNING;
			constraintFFigurePackageLabelRectangle.horizontalIndent = 0;
			constraintFFigurePackageLabelRectangle.horizontalSpan = 1;
			constraintFFigurePackageLabelRectangle.verticalSpan = 1;
			constraintFFigurePackageLabelRectangle.grabExcessHorizontalSpace = false;
			constraintFFigurePackageLabelRectangle.grabExcessVerticalSpace = false;
			this.add(fFigurePackageLabelRectangle,
					constraintFFigurePackageLabelRectangle);

			ToolbarLayout layoutFFigurePackageLabelRectangle = new ToolbarLayout();
			layoutFFigurePackageLabelRectangle.setStretchMinorAxis(false);
			layoutFFigurePackageLabelRectangle
					.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);

			layoutFFigurePackageLabelRectangle.setSpacing(5);
			layoutFFigurePackageLabelRectangle.setVertical(true);

			fFigurePackageLabelRectangle
					.setLayoutManager(layoutFFigurePackageLabelRectangle);

			fFigurePackageNameLabel = new WrappingLabel();
			fFigurePackageNameLabel.setText("<..>");

			fFigurePackageLabelRectangle.add(fFigurePackageNameLabel);
			fFigurePackageLabelRectangle
					.setBackgroundColor(FFIGUREPACKAGELABELRECTANGLE_BACK);
			fFigurePackageLabelRectangle
					.setForegroundColor(ColorConstants.darkGray);

			RectangleFigure fillerFigure0 = new RectangleFigure();

			GridData constraintFillerFigure0 = new GridData();
			constraintFillerFigure0.verticalAlignment = GridData.CENTER;
			constraintFillerFigure0.horizontalAlignment = GridData.CENTER;
			constraintFillerFigure0.horizontalIndent = 0;
			constraintFillerFigure0.horizontalSpan = 1;
			constraintFillerFigure0.verticalSpan = 1;
			constraintFillerFigure0.grabExcessHorizontalSpace = false;
			constraintFillerFigure0.grabExcessVerticalSpace = false;
			this.add(fillerFigure0, constraintFillerFigure0);

			fFigurePackageBodyRectangle = new GradientRectangleFigure();
			fFigurePackageBodyRectangle.setLineWidth(1);
			fFigurePackageBodyRectangle
					.setBackgroundColor(FFIGUREPACKAGEBODYRECTANGLE_BACK);
			fFigurePackageBodyRectangle
					.setForegroundColor(ColorConstants.darkGray);

			GridData constraintFFigurePackageBodyRectangle = new GridData();
			constraintFFigurePackageBodyRectangle.verticalAlignment = GridData.FILL;
			constraintFFigurePackageBodyRectangle.horizontalAlignment = GridData.FILL;
			constraintFFigurePackageBodyRectangle.horizontalIndent = 0;
			constraintFFigurePackageBodyRectangle.horizontalSpan = 1;
			constraintFFigurePackageBodyRectangle.verticalSpan = 1;
			constraintFFigurePackageBodyRectangle.grabExcessHorizontalSpace = true;
			constraintFFigurePackageBodyRectangle.grabExcessVerticalSpace = true;
			this.add(fFigurePackageBodyRectangle,
					constraintFFigurePackageBodyRectangle);

		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigurePackageBodyRectangle() {
			return fFigurePackageBodyRectangle;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigurePackageNameLabel() {
			return fFigurePackageNameLabel;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigurePackageLabelRectangle() {
			return fFigurePackageLabelRectangle;
		}

	}

	/**
	 * @generated
	 */
	static final Color FFIGUREPACKAGELABELRECTANGLE_BACK = new Color(null, 211,
			211, 211);

	/**
	 * @generated
	 */
	static final Color FFIGUREPACKAGEBODYRECTANGLE_BACK = new Color(null, 211,
			211, 211);

}
