package org.modelversioning.emfprofile.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EMFProfileProjectNatureUtil {

	private static final String PLATFORM_RESOURCE_PREFIX = "platform:/resource"; //$NON-NLS-1$
	private static final String EXTENSION_POINT_PROFILE_RESOURCE_ATT_NAME = "profile_resource"; //$NON-NLS-1$
	private static final String EXTENSION_POINT_PROFILE_ELEM_NAME = "profile"; //$NON-NLS-1$

	public static void addNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 1, prevNatures.length);
		newNatures[0] = EMFProfileProjectNature.NATURE_ID;
		description.setNatureIds(newNatures);
		project.setDescription(description, new NullProgressMonitor());
	}

	public static Collection<String> getProfileDiagramFileNames(IProject project) {
		IFile pluginXml = project
				.getFile(EMFProfileProjectNature.PLUGIN_XML_FILE_NAME);
		if (pluginXml.exists()) {
			return extractProfileFileNames(pluginXml);
		} else {
			return Collections.emptyList();
		}
	}

	private static Collection<String> extractProfileFileNames(IFile pluginXml) {
		try {
			ExtensionPointReader extensionPointReader = new ExtensionPointReader();
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(pluginXml.getContents(), extensionPointReader);
			return extensionPointReader.profileDiagramFileNames;
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (CoreException e) {
		}
		return Collections.emptyList();
	}

	public static Collection<IFile> getProfileDiagramFiles(IProject project) {
		Collection<String> profileDiagramFileNames = getProfileDiagramFileNames(project);
		Collection<IFile> profileDiagramFiles = new ArrayList<IFile>();
		for (String profileDiagramFileName : profileDiagramFileNames) {
			IFile profileDiagramFile = project.getFile(profileDiagramFileName);
			profileDiagramFiles.add(profileDiagramFile);
		}
		return profileDiagramFiles;
	}

	public static Collection<URI> getProfileDiagramURIs(IProject project) {
		Collection<String> profileDiagramFileNames = getProfileDiagramFileNames(project);
		Collection<URI> profileDiagramURIs = new ArrayList<URI>();
		for (String profileDiagramFileName : profileDiagramFileNames) {
			URI uri = URI.createURI(PLATFORM_RESOURCE_PREFIX
					+ project.getName() + "/" + profileDiagramFileName); //$NON-NLS-1$
			profileDiagramURIs.add(uri);
		}
		return profileDiagramURIs;
	}

	public static URI getDefaultProfileDiagramURI(IProject project) {
		IFile profileDiagramFile = project
				.getFile(EMFProfileProjectNature.DEFAULT_PROFILE_DIAGRAM_FILE_NAME);
		URI uri = URI.createURI(PLATFORM_RESOURCE_PREFIX
				+ profileDiagramFile.getFullPath().toString());
		return uri;
	}

	static class ExtensionPointReader extends DefaultHandler {

		private Collection<String> profileDiagramFileNames;

		public ExtensionPointReader() {
			profileDiagramFileNames = new ArrayList<String>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (EXTENSION_POINT_PROFILE_ELEM_NAME.equals(qName)) {
				String attributeValue = attributes
						.getValue(EXTENSION_POINT_PROFILE_RESOURCE_ATT_NAME);
				if (attributeValue != null) {
					profileDiagramFileNames.add(attributeValue);
				}
			}
		}

		public Collection<String> getProfileDiagramFileNames() {
			return profileDiagramFileNames;
		}
	}

}
