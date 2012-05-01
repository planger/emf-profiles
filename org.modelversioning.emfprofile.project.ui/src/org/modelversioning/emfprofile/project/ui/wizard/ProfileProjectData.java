package org.modelversioning.emfprofile.project.ui.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

public class ProfileProjectData {
	
	private String projectName;
	private String profileNamespace;
	private String profileName;
	private IProject projectHandle;
	private IPath locationPath;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProfileNamespace() {
		return profileNamespace;
	}
	public void setProfileNamespace(String profileNamespace) {
		this.profileNamespace = profileNamespace;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public IProject getProjectHandle() {
		return projectHandle;
	}
	public void setProjectHandle(IProject projectHandle) {
		this.projectHandle = projectHandle;
	}
	public IPath getLocationPath() {
		return locationPath;
	}
	public void setLocationPath(IPath locationPath) {
		this.locationPath = locationPath;
	}

}
