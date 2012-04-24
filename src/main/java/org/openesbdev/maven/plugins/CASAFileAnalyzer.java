package org.openesbdev.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.openesbdev.maven.plugins.casa.CompositeApplicationContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class CASAFileAnalyzer {

	private File casaFile;
	
	public CASAFileAnalyzer(File casaFile) {
		this.casaFile = casaFile;
	}
	
	public void parseXML() throws MojoExecutionException {
		try {
			CompositeApplicationContentHandler casaHandler = new CompositeApplicationContentHandler();
			XMLReader saxReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            saxReader.setContentHandler(casaHandler);
            saxReader.parse(casaFile.toURI().getPath());
            
            casaHandler.getCompositeApplication();

		} catch (Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException("Unable to parse "
					+ casaFile.getAbsolutePath());
		}
	}
}
