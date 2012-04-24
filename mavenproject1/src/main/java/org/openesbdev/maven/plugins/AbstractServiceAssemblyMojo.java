package org.openesbdev.maven.plugins;

import java.io.File;

public abstract class AbstractServiceAssemblyMojo extends AbstractJbiMojo {

	/**
	 * The directory containing the build files.
	 * 
	 * @parameter expression="${project.build.directory}/service-assembly"
	 * @required
	 */
	protected File buildDirectory;
}
