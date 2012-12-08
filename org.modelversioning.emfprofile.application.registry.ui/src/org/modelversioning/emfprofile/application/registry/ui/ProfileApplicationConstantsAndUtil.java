/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public final class ProfileApplicationConstantsAndUtil {
	// hides default constructor
	private ProfileApplicationConstantsAndUtil() {	}

	public static final String EMF_PROFILE_APPLICATION_FILE_EXTENSION = "pa.xmi";
	
	/**
	 * Returns the resolved resource set from the editing domain of the supplied
	 * <code>workbenchPart</code>. If no {@link ResourceSet} can be resolved, a
	 * <code>null</code> will be returned, so the registry will use fall back resource set instead.
	 * 
	 * @param workbenchPart
	 *            to resolve resource set.
	 * @return the resource set.
	 */
	public static final ResourceSet getResourceSet(IWorkbenchPart workbenchPart) {
		Object adapter = workbenchPart.getAdapter(IEditingDomainProvider.class);
		if (adapter != null && adapter instanceof IEditingDomainProvider) {
			IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) adapter;
			return editingDomainProvider.getEditingDomain().getResourceSet();
		} else {
			return null;
		}
	}

}
