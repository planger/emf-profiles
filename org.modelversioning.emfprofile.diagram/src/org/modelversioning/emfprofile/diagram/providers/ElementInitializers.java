package org.modelversioning.emfprofile.diagram.providers;

import org.modelversioning.emfprofile.diagram.part.EMFProfileDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = EMFProfileDiagramEditorPlugin
				.getInstance().getElementInitializers();
		if (cached == null) {
			EMFProfileDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
