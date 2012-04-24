package org.openesbdev.maven.plugins;

import java.io.File;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.plexus.util.xml.XMLWriter;
import org.openesbdev.maven.plugins.xml.JbiDescriptor;

abstract class AbstractJbiXmlWriter extends AbstractXmlWriter {

	public static final String NAMESPACE_PREFIX = "ns"; // NOI18N
	
	private Map<String, String> namespacePrefixMap = new HashMap<String, String>();
	
	AbstractJbiXmlWriter() {
		super();
	}
	
	public void write(File destinationFile) throws Exception {
		Writer w = initializeWriter(destinationFile);
		XMLWriter writer = initializeRootElement(w);
		
		writeContent(writer);
		
		// End JBI
		writer.endElement();

		close(w);
	}
	
	abstract void writeContent(XMLWriter writer);
		
	private XMLWriter initializeRootElement(Writer w) {
		XMLWriter writer = initializeXmlWriter(w, null);
		
		writer.startElement(JbiDescriptor.JBI_ELEM_NAME);
		writer.addAttribute(JbiDescriptor.NS_ATTR_NAME, JbiDescriptor.NS_ATTR_VALUE);
		writer.addAttribute(JbiDescriptor.NS_XSI_ATTR_NAME, JbiDescriptor.NS_XSI_ATTR_VALUE);
		writer.addAttribute(JbiDescriptor.XSI_ATTR_NAME, JbiDescriptor.XSI_ATTR_VALUE);
		writer.addAttribute(JbiDescriptor.VERSION_ATTR_NAME, JbiDescriptor.VERSION_ATTR_VALUE);

		for (Entry<String, String> namespaceEntry : namespacePrefixMap
				.entrySet()) {
			writer.addAttribute(JbiDescriptor.NS_ATTR_NAME + ':' + namespaceEntry.getValue(),
					namespaceEntry.getKey());
		}

		return writer;
	}
	
	protected String populateNamespace(String namespaceURI) {
		String namespacePrefix = namespacePrefixMap.get(namespaceURI);

		if (namespacePrefix == null) {
			namespacePrefix = NAMESPACE_PREFIX + namespacePrefixMap.size();
			namespacePrefixMap.put(namespaceURI, namespacePrefix);
		}

		return namespacePrefix;
	}
	
	protected Map<String, String> getNamespacePrefix() {
		return this.namespacePrefixMap;
	}

}
