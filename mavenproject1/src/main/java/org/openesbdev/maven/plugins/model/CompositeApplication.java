package org.openesbdev.maven.plugins.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeApplication {

	private List<Endpoint> endpoints = new ArrayList<Endpoint>();
	private List<ServiceUnit> serviceUnits = new ArrayList<ServiceUnit>();
	
	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	public void addEnpoint(Endpoint endpoint) {
		getEndpoints().add(endpoint);
	}

	public List<ServiceUnit> getServiceUnits() {
		return serviceUnits;
	}
	
	public void addServiceUnit(ServiceUnit serviceUnit) {
		getServiceUnits().add(serviceUnit);
	}
}
