package org.openesbdev.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * A Mojo that generate the Service Unit descriptor as the jbi.xml file.
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 * 
 * @goal service-unit-generate-resources
 * @phase generate-resources
 */
public class ServiceUnitDescriptorMojo extends AbstractServiceUnitMojo {

	/**
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			// Generate jbi.xml
			getLog().info("Generating JBI descriptor file...");
			generateJbiDescriptor();
		} catch (Exception e) {
			throw new MojoExecutionException(
					"Exception while generating resources", e);
		}
	}

	/**
	 * Generates the JBI descriptor.
	 */
	protected void generateJbiDescriptor() throws Exception {
		File outputDir = new File(buildDirectory, META_INF);
		
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}

		File descriptor = new File(outputDir, JBI_DESCRIPTOR);

		ServiceUnitDescriptorXmlWriter writer = new ServiceUnitDescriptorXmlWriter(sourceDirectory);
		writer.write(descriptor);
	}
}
