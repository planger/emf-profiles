package org.modelversioning.emfprofile.project;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;

public class EMFProfileProjectNatureUtil {

	public static void addNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 1, prevNatures.length);
		newNatures[0] = EMFProfileProjectNature.NATURE_ID;
		description.setNatureIds(newNatures);
		project.setDescription(description, new NullProgressMonitor());
	}

	public static IFile getProfileDiagramFile(IProject project) {
		return project
				.getFile(EMFProfileProjectNature.PROFILE_DIAGRAM_FILE_NAME);
	}

	public static URI getProfileDiagramURI(IProject project) {
		IFile profileDiagramFile = project
				.getFile(EMFProfileProjectNature.PROFILE_DIAGRAM_FILE_NAME);
		URI uri = URI.createURI("platform:/resource" //$NON-NLS-1$
				+ profileDiagramFile.getFullPath().toString());
		return uri;
	}

}
