package org.openesbdev.maven.plugins.catalog;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openesbdev.maven.plugins.AbstractJbiMojo;
import org.openesbdev.maven.plugins.utils.FileUtils;

public class ServiceUnitCatalogResolver {

	final private String retrieverPathPrefix = "nbproject/private/cache/retriever/"; // NOI18N
    final private String retrieverPathPrefix2 = "retrieved/"; // NOI18N
    
	private File catalogFile;
	private File sourceDir;
	private File buildDirectory;
	
	public ServiceUnitCatalogResolver(File catalogFile, File sourceDir, File buildDirectory) {
		this.catalogFile = catalogFile;
		this.sourceDir = sourceDir;
		this.buildDirectory = buildDirectory;
	}
	
	public void process() throws IOException {
		File metaInfDirectory = new File(buildDirectory, AbstractJbiMojo.META_INF);
		metaInfDirectory.mkdirs();
		
		CatalogXmlWriter cWriter = new CatalogXmlWriter();
		cWriter.write(new File(metaInfDirectory, AbstractJbiMojo.CATALOG_DESCRIPTOR));
		
		CatalogReader cReader = new CatalogReader(catalogFile.getCanonicalPath(), new CatalogItemListener [] 
				{ new HandleItemCatalogListener(cWriter)});
	}
	
	class HandleItemCatalogListener implements CatalogItemListener {

		private CatalogXmlWriter catalogWriter;
		
		public HandleItemCatalogListener(CatalogXmlWriter catalogWriter) {
			this.catalogWriter = catalogWriter;
		}
			
		public void onItem(String systemId, String location) {
			try {
				File projectDirectory = sourceDir.getParentFile();
				
				final URI uri = new URI(location);
				
				// The location is a relative path, i.e. the URI scheme is null
	            if (uri.getScheme() == null) {
	            	// The URI leads us to the sources directory -- just correct it and proceed
	                if (location.startsWith("src/")) { 
	                    String localUri = ".." + location.substring(3);
	                    printToCatalog(catalogWriter, systemId, localUri);
	                }
	                else 
	                // The URI leads to the nbproject directory -- it is a resource fetched by the retriever -- copy it
	                if (location.startsWith(retrieverPathPrefix)) {
	                    String localUri = "src/_references/_cache/" + location.substring(retrieverPathPrefix.length());
	                    localUri = localUri.replace("../", "__/").replace("//", "/");
	                    FileUtils.copyFile(new File(projectDirectory, location), new File(buildDirectory, AbstractJbiMojo.META_INF + localUri));
	                    printToCatalog(catalogWriter, systemId, localUri);
	                } else 
	                // The URI leads to the retrieved directory -- it is a resource fetched by the retriever -- copy it
	                if (location.startsWith(retrieverPathPrefix2)) {
	                    String localUri = "src/_references/_retrieved/" + location.substring(retrieverPathPrefix2.length());
	                    localUri = localUri.replace("../", "__/").replace("//", "/");
	                    FileUtils.copyFile(new File(projectDirectory, location), new File(buildDirectory, AbstractJbiMojo.META_INF + localUri));
	                    printToCatalog(catalogWriter, systemId, localUri);
	                } else {
	                    String localUri = "src/_references/_relative/" + location;
	                    localUri = localUri.replace("../", "__/").replace("//", "/");
	                    FileUtils.copyFile(new File(projectDirectory, location), new File(buildDirectory, AbstractJbiMojo.META_INF + localUri));
	                    printToCatalog(catalogWriter, systemId, localUri);
	                }
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void printToCatalog(CatalogXmlWriter catalogWriter, String systemId, String uri) {
			catalogWriter.addSystemElement(systemId, uri.replace("\\", "/"));
		}
	}
}
