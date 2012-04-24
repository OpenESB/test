package org.openesbdev.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractJbiMojo extends AbstractMojo {

	public static final String META_INF = "META-INF";
	public static final String JBI_DESCRIPTOR = "jbi.xml";
	public static final String CATALOG_DESCRIPTOR = "catalog.xml";
	
	/**
	 * The directory containing the source files.
	 * 
	 * @parameter default-value="${project.build.sourceDirectory}"
	 * @required
	 */
	protected File sourceDirectory;
}
