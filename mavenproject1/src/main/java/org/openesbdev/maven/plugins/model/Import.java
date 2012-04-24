package org.openesbdev.maven.plugins.model;

public class Import {

	private String namespaceURI;
	private String location;
	private String importType;
	
	public String getNamespaceURI() {
		return namespaceURI;
	}
	
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getImportType() {
		return importType;
	}
	
	public void setImportType(String importType) {
		this.importType = importType;
	}
}
