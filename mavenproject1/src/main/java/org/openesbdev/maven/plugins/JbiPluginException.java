package org.openesbdev.maven.plugins;

/**
 * The base exception of the EAR plugin.
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 */
public class JbiPluginException extends Exception {

	public JbiPluginException() {
	}

	public JbiPluginException(String message) {
		super(message);
	}

	public JbiPluginException(Throwable cause) {
		super(cause);
	}

	public JbiPluginException(String message, Throwable cause) {
		super(message, cause);
	}
}
