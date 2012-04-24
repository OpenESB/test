package org.openesbdev.maven.plugins;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;

/**
 * A Mojo used to copy resources used to generate the Service Unit package.
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 * 
 * @goal service-unit-process-resources
 * @phase process-resources
 */
public class ServiceUnitResourcesProcessorMojo extends AbstractServiceUnitMojo {

	private static final List<String> EXTENSIONS = 
		Arrays.asList("bpel", "wsdl", "xsd", "xsl", "xslt", "jar");
	
	private final FileFilter frr = new FileResourceFilter();
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			copyResources(sourceDirectory);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void copyResources(File directory) throws IOException {
		File [] lstFiles = directory.listFiles(frr);
		for(File file : lstFiles) {
			if (file.isDirectory()) {
				copyResources(file);
			} else {
				FileUtils.copyFile(file, new File(buildDirectory, file.getName()));
			}
		}
	}
	
	static class FileResourceFilter implements FileFilter {

		public boolean accept(File pathname) {
			if (pathname.isDirectory())
				return true;
			
			String fileName = pathname.getName();
			int dotIndex = fileName.lastIndexOf(".");
			if (dotIndex != -1) {
				String ext = fileName.substring(dotIndex + 1, fileName.length());
				return EXTENSIONS.contains(ext);
			}
			
			return false;
		}
		
	}
}
