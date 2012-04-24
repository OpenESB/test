package org.openesbdev.maven.plugins.model;

public class Endpoint {

	private String endpointName;
	private String interfaceName;
	private String name;
	private String serviceName;
	private String displayName;
	private String filePath;
	private String processName;
	
	public String getEndpointName() {
		return endpointName;
	}
	
	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}
	
	public String getInterfaceName() {
		return interfaceName;
	}
	
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getProcessName() {
		return processName;
	}
	
	public void setProcessName(String processName) {
		this.processName = processName;
	}
}
