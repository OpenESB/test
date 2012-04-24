package org.openesbdev.maven.plugins.model;

import java.util.ArrayList;
import java.util.List;

public class SEServiceUnit extends ServiceUnit {

	private List<String> providerEnpoint = new ArrayList<String>();
	private List<String> consumerEnpoint = new ArrayList<String>();
	
	public List<String> getProviderEnpoint() {
		return providerEnpoint;
	}
	
	public void setProviderEnpoint(List<String> providerEnpoint) {
		this.providerEnpoint = providerEnpoint;
	}
	
	public List<String> getConsumerEnpoint() {
		return consumerEnpoint;
	}
	
	public void setConsumerEnpoint(List<String> consumerEnpoint) {
		this.consumerEnpoint = consumerEnpoint;
	}
}
