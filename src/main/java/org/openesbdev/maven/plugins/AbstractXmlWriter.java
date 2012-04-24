package org.openesbdev.maven.plugins;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.codehaus.plexus.util.xml.PrettyPrintXMLWriter;
import org.codehaus.plexus.util.xml.XMLWriter;

/**
 * A base class for descriptor file generators.
 * 
 * @author <a href="brasseld@gmail.com">David BRASSELY</a>
 */
public class AbstractXmlWriter {

	/**
	 * Character encoding for the auto-generated deployment file(s).
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	private final String encoding;

	protected AbstractXmlWriter(String encoding) {
		this.encoding = encoding;
	}
	
	protected AbstractXmlWriter() {
		this.encoding = DEFAULT_ENCODING;
	}

	protected Writer initializeWriter(final File destinationFile)
			throws IOException {
		try {
			return new FileWriter(destinationFile);
		} catch (IOException ex) {
			throw new IOException("Exception while opening file ["
					+ destinationFile.getAbsolutePath() + "]", ex);
		}
	}

	protected XMLWriter initializeXmlWriter(final Writer writer,
			final String docType) {
		return new PrettyPrintXMLWriter(writer, encoding, docType);
	}

	protected void close(Writer closeable) {
		if (closeable == null) {
			return;
		}

		try {
			closeable.close();
		} catch (Exception e) {
			// TODO: warn
		}
	}

	public String getEncoding() {
		return encoding;
	}
}
