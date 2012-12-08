/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.observer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public final class ViewerState {
	
	private final Object input;
	public final Object getInput() {
		return input;
	}

	private final Object[] viewerExpandedElements;
	private final ISelection selection;

	public ViewerState(TreeViewer viewer) {
		super();
		this.input = viewer.getInput();
		this.viewerExpandedElements = viewer.getExpandedElements();
		this.selection = viewer.getSelection();
	}
	
	public void restoreTreeViewerState(TreeViewer viewer){
		viewer.setInput(input);
		viewer.setExpandedElements(viewerExpandedElements);
		viewer.setSelection(selection, true);
	}
}