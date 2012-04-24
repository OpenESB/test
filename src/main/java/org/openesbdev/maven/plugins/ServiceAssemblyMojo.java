package org.openesbdev.maven.plugins;

import java.io.File;
import java.io.IOException;

import org.apache.maven.archiver.MavenArchiveConfiguration;
import org.apache.maven.archiver.MavenArchiver;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.ArchiverException;
import org.codehaus.plexus.archiver.jar.JarArchiver;
import org.codehaus.plexus.archiver.jar.ManifestException;

/**
 * This plugin is used to generate the Service Assembly archive
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 * 
 * @goal service-assembly
 * @phase package
 */
public class ServiceAssemblyMojo extends AbstractServiceAssemblyMojo {

	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The Jar archiver needed for archiving.
	 * 
	 * @parameter expression="${component.org.codehaus.plexus.archiver.Archiver#jar}"
	 * @required
	 */
	private JarArchiver jarArchiver;
	
	/**
	 * The maven archive configuration to use
	 * @parameter
	 */
	protected MavenArchiveConfiguration archive = new MavenArchiveConfiguration();
	
	/**
     * The name of the generated war.
     *
     * @parameter expression="${project.build.finalName}.zip"
     * @required
     */
    private String finalName;

	public void execute() throws MojoExecutionException, MojoFailureException {
		File custFile = new File(buildDirectory, finalName);
		
	    // Configure archiver
	    MavenArchiver archiver = new MavenArchiver();
	    archiver.setArchiver(jarArchiver);
	    archiver.setOutputFile(custFile);
	    
	    archive.setAddMavenDescriptor(false);
	    
	    try {
	        archiver.getArchiver().addDirectory(buildDirectory);
	        // create archive
	        archiver.createArchive(project, archive);
	        // set archive as artifact
	        project.getArtifact().setFile(custFile);
	    } catch (ArchiverException e) {
	        throw new MojoExecutionException("Exception while packaging", e);
	    } catch (ManifestException e) {
	        throw new MojoExecutionException("Exception while packaging", e);
	    } catch (IOException e) {
	        throw new MojoExecutionException("Exception while packaging", e);
	    } catch (DependencyResolutionRequiredException e) {
	        throw new MojoExecutionException("Exception while packaging", e);
	    }
	}
}
