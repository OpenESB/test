package org.openesbdev.maven.plugins.catalog;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import org.apache.xml.resolver.CatalogManager;
import org.apache.xml.resolver.tools.ResolvingXMLReader;
import org.openesbdev.maven.plugins.utils.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CatalogReader {

	private MyContentHandler mContentHandler = new MyContentHandler();
	private Stack<File> nextCatalogs = new Stack<File>();
	
	private File rootCatalog;
	private File currentCatalog;

	private CatalogItemListener [] catalogListeners;
	
//	private Map<String, String> catalogEntries = new HashMap<String, String>();
	
//	private List<String> systemIds = new ArrayList<String>();
//	private List<String> locations = new ArrayList<String>();

	public CatalogReader(String catalogXML, CatalogItemListener [] listeners) throws IOException {
		this.catalogListeners = listeners;
		
		CatalogManager manager = new CatalogManager("#171444/");
		manager.setUseStaticCatalog(false);
		manager.setPreferPublic(false);
		manager.setIgnoreMissingProperties(true);

		ResolvingXMLReader saxParser = new ResolvingXMLReader(manager);
		saxParser.setContentHandler(mContentHandler);

		rootCatalog = new File(catalogXML);
		nextCatalogs.push(rootCatalog);

		try {
			do {
				currentCatalog = nextCatalogs.pop();
	
				if (currentCatalog.exists() && (currentCatalog.length() > 0)) {
					saxParser.parse(new InputSource(new FileReader(currentCatalog)));
				}
			} while (nextCatalogs.size() > 0);
		} catch (SAXException saxe) {
			throw new IOException("An error occured during SAX parsing : " + saxe.getMessage());
		}
	}
/*
	public Map<String, String> getCatalogEntries() {
		return catalogEntries;
	}
*/
	private class MyContentHandler extends DefaultHandler {
		private static final String SYSTEM_CONST = "system";
		private static final String SYSTEM_ID_CONST = "systemId";
		private static final String URI_CONST = "uri";
		private static final String NEXT_CATALOG_CONST = "nextCatalog";
		private static final String CATALOG_CONST = "catalog";

		@Override
		public void startElement(final String uri, final String localName,
				final String qName, final Attributes atts) {

			if (qName.equals(SYSTEM_CONST)) {
				final String systemId = atts.getValue(SYSTEM_ID_CONST);
				String location = atts.getValue(URI_CONST);

				if (systemId != null) {
					if (currentCatalog != rootCatalog) {
						location = FileUtils.getRelativePath(
								rootCatalog.getParentFile(),
								currentCatalog.getParentFile())
								+ '/' + location;
					}

					for(CatalogItemListener listener  : catalogListeners) {
						listener.onItem(systemId, location.replace("\\", "/"));
					//	catalogEntries.put(systemId, location.replace("\\", "/"));
					}
				}
			}

			if (qName.equals(NEXT_CATALOG_CONST)) {
				final String catalog = atts.getValue(CATALOG_CONST);

				nextCatalogs.push(new File(currentCatalog.getParentFile(),
						catalog));
			}
		}
	}
}
