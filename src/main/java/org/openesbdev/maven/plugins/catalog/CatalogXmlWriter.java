package org.openesbdev.maven.plugins.catalog;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.codehaus.plexus.util.xml.XMLWriter;
import org.openesbdev.maven.plugins.AbstractXmlWriter;
import org.openesbdev.maven.plugins.xml.CatalogDescriptor;

public class CatalogXmlWriter extends AbstractXmlWriter {

	private XMLWriter writer;
	
	public CatalogXmlWriter() {
		super();
	}
	
	public void write(File destinationFile) throws IOException {
		Writer w = initializeWriter(destinationFile);
		initializeRootElement(w);
		
	//	writeContent(writer);
		
		// End Catalog
		
		// Close ! 
	}
	
	public void addSystemElement(String systemId, String uri) {
		writer.startElement(CatalogDescriptor.SYSTEM_ELEM_NAME);
		writer.addAttribute(CatalogDescriptor.SYSTEM_ID_ATTR_NAME, systemId);
		writer.addAttribute(CatalogDescriptor.URI_ATTR_NAME, uri);
	}
	
	private void initializeRootElement(Writer w) {
		writer = initializeXmlWriter(w, null);
		
		writer.startElement(CatalogDescriptor.CATALOG_ELEM_NAME);
		writer.addAttribute(CatalogDescriptor.NS_ATTR_NAME, CatalogDescriptor.NS_ATTR_VALUE);
		writer.addAttribute(CatalogDescriptor.PREFER_ATTR_NAME, CatalogDescriptor.PREFER_ATTR_VALUE);
	}

}
