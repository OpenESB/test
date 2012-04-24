package org.openesbdev.maven.plugins.model;

public abstract class ServiceUnit {

	private String artifactsZip;
	private String componentName;
	private String description;
	private String name;
	private String unitName;
	
	public String getArtifactsZip() {
		return artifactsZip;
	}
	
	public void setArtifactsZip(String artifactsZip) {
		this.artifactsZip = artifactsZip;
	}
	
	public String getComponentName() {
		return componentName;
	}
	
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
