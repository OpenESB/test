package org.openesbdev.maven.plugins.model;

public class Provider {

	//Member variable representing partner link name
    private String partnerLinkName = null;
    
    //Member variable representing port name
    private String portName = null;
    
    //Member variable representing partnerlink Namespace
    private String partnerLinkNS = null;
    
    //Member variable representing portname namespace
    private String portNameNS = null;
    
    //Member variable representing  role name
    private String roleName = null;
    
    //Member variable representing Partnerlink Namespace Prefix
    private String partnerLinkNSPrefix = null;
    
    //Member variable representing Portname Namespace Prefix
    private String portNameNSPrefix = null;    
    
    private String processName = null;
    private String filePath = null;
    
    public Provider(
            String partnerLinkName, 
            String portName, 
            String partnerLinkNS, 
            String portNameNS, 
            String roleName, 
            String partnerLinkNSPrefix, 
            String portNameNSPrefix,
            String processName,
            String filePath) {
        this.partnerLinkName = partnerLinkName;
        this.portName = portName;
        this.partnerLinkNS = partnerLinkNS;
        this.portNameNS = portNameNS;
        this.roleName = roleName;
        this.partnerLinkNSPrefix = partnerLinkNSPrefix;
        this.portNameNSPrefix = portNameNSPrefix;
        this.processName = processName;
        this.filePath = filePath;
    }

	public String getPartnerLinkName() {
		return partnerLinkName;
	}
	
	public void setPartnerLinkName(String partnerLinkName) {
		this.partnerLinkName = partnerLinkName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPartnerLinkNS() {
		return partnerLinkNS;
	}

	public void setPartnerLinkNS(String partnerLinkNS) {
		this.partnerLinkNS = partnerLinkNS;
	}

	public String getPortNameNS() {
		return portNameNS;
	}

	public void setPortNameNS(String portNameNS) {
		this.portNameNS = portNameNS;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPartnerLinkNSPrefix() {
		return partnerLinkNSPrefix;
	}

	public void setPartnerLinkNSPrefix(String partnerLinkNSPrefix) {
		this.partnerLinkNSPrefix = partnerLinkNSPrefix;
	}

	public String getPortNameNSPrefix() {
		return portNameNSPrefix;
	}

	public void setPortNameNSPrefix(String portNameNSPrefix) {
		this.portNameNSPrefix = portNameNSPrefix;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
