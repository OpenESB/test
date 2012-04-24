package org.openesbdev.maven.plugins.model;

import javax.xml.namespace.QName;

public class PartnerLink {

	private String name;
	private QName partnerLinkType;
	private String partnerRole;
	private String myRole;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public QName getPartnerLinkType() {
		return partnerLinkType;
	}
	
	public void setPartnerLinkType(QName partnerLinkType) {
		this.partnerLinkType = partnerLinkType;
	}
	
	public String getPartnerRole() {
		return partnerRole;
	}
	
	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}
	
	public String getMyRole() {
		return myRole;
	}
	
	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}
}
