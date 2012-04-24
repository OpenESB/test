package org.openesbdev.maven.plugins.model;

import java.util.ArrayList;
import java.util.List;

public class BpelModel {

	private String processName;
	private String processTargetNamespace;
	private List<Import> imports;
	private List<PartnerLink> partnerLinks;
	
	public String getProcessName() {
		return processName;
	}
	
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public String getProcessTargetNamespace() {
		return processTargetNamespace;
	}
	
	public void setProcessTargetNamespace(String processTargetNamespace) {
		this.processTargetNamespace = processTargetNamespace;
	}
	
	public List<PartnerLink> getPartnerLinks() {
		if (partnerLinks == null)
			partnerLinks = new ArrayList<PartnerLink>();
		return partnerLinks;
	}
	
	public List<Import> getImports() {
		if (imports == null)
			imports = new ArrayList<Import>();
		return imports;
	}
	
	public String getLocationFromNamespace(String namespace) {
		for(Import imp : getImports()) {
			if (imp.getNamespaceURI().equals(namespace)) {
				return imp.getLocation();
			}
		}
		
		return null;
	}
}
