package org.modelversioning.emfprofile.diagram.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @generated
 */
public class DiagramEditorPerspective implements IPerspectiveFactory {
	/**
	 * @generated
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addPerspectiveShortcut(DiagramEditorWorkbenchAdvisor.PERSPECTIVE_ID);
		IFolderLayout left = layout.createFolder(
				"left", IPageLayout.LEFT, 0.15f, layout.getEditorArea());//$NON-NLS-1$
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);
//		IFolderLayout right = layout.createFolder(
//				"right", IPageLayout.RIGHT, 0.6f, layout.getEditorArea()); //$NON-NLS-1$
		IFolderLayout bottomLeft = layout.createFolder(
				"bottomLeft", IPageLayout.BOTTOM, 0.5f, "left");//$NON-NLS-1$
		bottomLeft.addView(IPageLayout.ID_OUTLINE);
		IFolderLayout bottomRight = layout.createFolder(
				"bottomRight", IPageLayout.BOTTOM, 0.7f, layout.getEditorArea()); //$NON-NLS-1$	 //$NON-NLS-2$
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
	}
}
