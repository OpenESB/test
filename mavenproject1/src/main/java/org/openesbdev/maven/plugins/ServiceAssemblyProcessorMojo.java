package org.openesbdev.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public class ServiceAssemblyProcessorMojo extends AbstractServiceAssemblyMojo {

	/**
	 * The file which describe the composite application
	 * 
	 * @parameter expression="${project.build.sourceDirectory}/${project.artifactId}.casa"
	 * @required
	 */
	protected File casaFile;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		
	}
}
