package org.modelversioning.emfprofile.project;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.modelversioning.emfprofile.Profile;

public class EMFProfileProjectBuilder extends IncrementalProjectBuilder {

	class SampleDeltaVisitor implements IResourceDeltaVisitor, IResourceVisitor {

		private ResourceSet resourceSet = new ResourceSetImpl();

		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				checkAndUpdateProfileResource(resource);
				break;
			case IResourceDelta.REMOVED:
				checkAndUpdateProfileResource(resource);
				break;
			case IResourceDelta.CHANGED:
				checkAndUpdateProfileResource(resource);
				break;
			}
			return true;
		}
		
		@Override
		public boolean visit(IResource resource) throws CoreException {
			checkAndUpdateProfileResource(resource);
			return true;
		}

		private void checkAndUpdateProfileResource(IResource resource) {
			if (resource instanceof IFile && isProfileResource(resource)) {
				Profile profile = getProfile(resource);
				if (validate(profile, (IFile) resource)) {
					reregisterProfile(profile);
				}
			}
		}

		private boolean isProfileResource(IResource resource) {
			if (resource instanceof IFile) {
				IFile iFile = (IFile) resource;
				if ("emfprofile_diagram".equals(iFile.getFileExtension())
						|| "emfprofile".equals(iFile.getFileExtension())) {
					return true;
				}
			}
			return false;
		}

		private Profile getProfile(IResource resource) {
			if (resource instanceof IFile) {
				IFile profileFile = (IFile) resource;
				Resource profileResource = resourceSet.getResource(
						createProfileURI(profileFile), true);
				for (EObject eObject : profileResource.getContents()) {
					if (eObject instanceof Profile) {
						return (Profile) eObject;
					}
				}
			}
			return null;
		}

		private boolean validate(Profile profile, IFile profileFile) {
			Map<String, EObject> context = createValidationContextMap(profile);
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(profile,
					context);
			if (Diagnostic.ERROR == diagnostic.getSeverity()) {
				EMFProfileProjectBuilder.this.addMarker(profileFile,
						diagnostic.getMessage(), diagnostic.getSeverity());
				return false;
			} else {
				deleteMarkers(profileFile);
				return true;
			}
		}

		private void reregisterProfile(Profile profile) {
			// TODO register profile
			// not possible because of cyclic dependency
			// either resolve dependency problem or let the registry listen to the workspace
		}

		private Map<String, EObject> createValidationContextMap(
				EObject currentlySelectedEObject) {
			Map<String, EObject> context = new HashMap<String, EObject>();
			context.put("MODEL_OBJECT", currentlySelectedEObject);
			return context;
		}

		private URI createProfileURI(IFile profileDiagramFile) {
			return URI.createURI("platform:/resource/" //$NON-NLS-1$
					+ profileDiagramFile.getProject().getName()
					+ "/" //$NON-NLS-1$
					+ profileDiagramFile.getProjectRelativePath()
							.toPortableString());
		}
	}

	public static final String BUILDER_ID = "org.modelversioning.emfprofile.project.registeringbuilder"; //$NON-NLS-1$

	private static final String MARKER_TYPE = "org.modelversioning.emfprofile.project.invalid"; //$NON-NLS-1$

	private SAXParserFactory parserFactory;

	private void addMarker(IFile file, String message, int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
		} catch (CoreException e) {
		}
	}

	protected IProject[] build(int kind,
			@SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SampleDeltaVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		delta.accept(new SampleDeltaVisitor());
	}
}
