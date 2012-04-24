package org.openesbdev.maven.plugins;

import java.io.File;

public abstract class AbstractServiceUnitMojo extends AbstractJbiMojo {

	/**
	 * The directory containing the build files.
	 * 
	 * @parameter expression="${project.build.directory}/service-unit"
	 * @required
	 */
	protected File buildDirectory;
}
