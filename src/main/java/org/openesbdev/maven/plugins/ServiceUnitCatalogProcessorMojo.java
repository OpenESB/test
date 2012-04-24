package org.openesbdev.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.openesbdev.maven.plugins.catalog.ServiceUnitCatalogResolver;

/**
 * A Mojo used to copy all resources linked by the XML Catalog.
 * The XML Catalog is a document describing a mapping between external entity references
 * and locally-cached equivalents.
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 * 
 * @goal service-unit-process-catalog
 * @phase process-resources
 */
public class ServiceUnitCatalogProcessorMojo extends AbstractServiceUnitMojo {
	
	/**
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Search Catalog XML in " + sourceDirectory.getPath());
		File catalogFile = new File(sourceDirectory, CATALOG_DESCRIPTOR);
		
		if (!catalogFile.exists() || catalogFile.length() == 0L)
			return ;
	
		getLog().info("Process Catalog XML from " + catalogFile.getPath());
		
		try {
			doCopy(catalogFile);
		} catch (Exception ioe) {
			throw new MojoFailureException("Unable to process Catalog XML : " + ioe.getMessage());
		}
	}
	
	private void doCopy(File catalogFile) throws Exception {
		ServiceUnitCatalogResolver catalogResolver = new ServiceUnitCatalogResolver(
				catalogFile, sourceDirectory, buildDirectory);
		catalogResolver.process();
	}
}
