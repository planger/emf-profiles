/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.project;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class EMFProfileProjectNature implements IProjectNature {

	public static final String NATURE_ID = "org.modelversioning.emfprofile.project.nature"; //$NON-NLS-1$
	public static final String PLUGIN_XML_FILE_NAME = "plugin.xml";
	public static final String ICONS_FOLDER_NAME = "icons"; //$NON-NLS-1$
	public static final String DEFAULT_PROFILE_DIAGRAM_FILE_NAME = "profile.emfprofile_diagram"; //$NON-NLS-1$

	private IProject project;

	public void configure() throws CoreException {
		// noop
	}

	public void deconfigure() throws CoreException {
		// noop
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public IFolder getIconsFolder() {
		return project.getFolder(ICONS_FOLDER_NAME);
	}

	public Collection<IFile> getProfileDiagramFiles() {
		return EMFProfileProjectNatureUtil.getProfileDiagramFiles(project);
	}

}
