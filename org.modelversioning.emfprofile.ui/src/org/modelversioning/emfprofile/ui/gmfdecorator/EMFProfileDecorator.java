/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.emfprofile.ui.gmfdecorator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.Decoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.modelversioning.emfprofile.ui.EMFProfileUIPlugin;

/**
 * Decorator for EMF profiles.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class EMFProfileDecorator extends AbstractDecorator {

	/** The decorations being displayed */
	private Set<IDecoration> decorations = new HashSet<IDecoration>();

	public EMFProfileDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
	}

	/**
	 * @return Returns the decorations.
	 */
	public Set<IDecoration> getDecorations() {
		return decorations;
	}

	/**
	 * @param decoration
	 *            The decoration to add.
	 */
	public void addDecoration(IDecoration decoration) {
		this.decorations.add(decoration);
	}

	/**
	 * Removes all decorations.
	 */
	protected void removeDecorations() {
		for (IDecoration decoration : decorations) {
			getDecoratorTarget().removeDecoration(decoration);
		}
		decorations.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refresh() {
		removeDecorations();
		View view = (View) getDecoratorTarget().getAdapter(View.class);
		if (view == null || view.eResource() == null) {
			return;
		}
		EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(
				EditPart.class);
		if (editPart == null || editPart.getViewer() == null) {
			return;
		}

		List<Image> images = EMFProfileUIPlugin
				.getDefault()
				.getImagesToDecorate(
						GMFProfileDecoratorProvider
								.getDecoratorTargetElement(getDecoratorTarget()),
						GMFProfileDecoratorProvider
								.getRootEditPart(getDecoratorTarget()));
		List<String> toolTipTexts = EMFProfileUIPlugin
				.getDefault()
				.getToolTipsToDecorate(
						GMFProfileDecoratorProvider
								.getDecoratorTargetElement(getDecoratorTarget()),
						GMFProfileDecoratorProvider
								.getRootEditPart(getDecoratorTarget()));

		if (images.size() > 0) {
			Label toolTip = new Label();
			FlowLayout fl = new FlowLayout(false);
			fl.setMinorSpacing(0);
			toolTip.setLayoutManager(fl);
			for (int i = 0; i < images.size(); i++) {
				toolTip.add(new Label(toolTipTexts.get(i), images.get(i)));
			}

			if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
				Decoration decoration = null;
				if (view instanceof Edge) {
					decoration = (Decoration) getDecoratorTarget()
							.addConnectionDecoration(images.get(0), 50, true);
				} else {
					int margin = -1;
					decoration = (Decoration) getDecoratorTarget()
							.addShapeDecoration(images.get(0),
									IDecoratorTarget.Direction.NORTH_WEST,
									margin, true);
				}
				decoration.setEnabled(true);
				decoration.setVisible(true);
				decoration.setToolTip(toolTip);
				addDecoration(decoration);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activate() {
		EObject eObject = GMFProfileDecoratorProvider
				.getDecoratorTargetElement(getDecoratorTarget());
		if (eObject != null) {
			EMFProfileUIPlugin.getDefault().registerDecorator(eObject, this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivate() {
		EObject eObject = GMFProfileDecoratorProvider
				.getDecoratorTargetElement(getDecoratorTarget());
		if (eObject != null) {
			EMFProfileUIPlugin.getDefault().unregisterDecorator(eObject);
		}
		super.deactivate();
	}

}
