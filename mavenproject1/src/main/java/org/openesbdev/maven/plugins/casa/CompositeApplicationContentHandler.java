package org.openesbdev.maven.plugins.casa;

import org.openesbdev.maven.plugins.model.BCServiceUnit;
import org.openesbdev.maven.plugins.model.CompositeApplication;
import org.openesbdev.maven.plugins.model.Endpoint;
import org.openesbdev.maven.plugins.model.SEServiceUnit;
import org.openesbdev.maven.plugins.xml.CasaDescriptor;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CompositeApplicationContentHandler extends DefaultHandler {

	private CompositeApplication casa = new CompositeApplication();
	
	@Override
	public void startElement(final String uri, final String localName,
			final String qName, final Attributes atts) {
		if (CasaDescriptor.ENDPOINT_ELEM_NAME.equals(localName)) {
			Endpoint endpoint = new Endpoint();
			
			endpoint.setName(atts.getValue(CasaDescriptor.ENDPOINT_NAME_ATTR_NAME));
			endpoint.setDisplayName(atts.getValue(CasaDescriptor.ENDPOINT_DISPLAY_ATTR_NAME));
			endpoint.setEndpointName(atts.getValue(CasaDescriptor.ENDPOINT_ENDPOINT_ATTR_NAME));
			endpoint.setFilePath(atts.getValue(CasaDescriptor.ENDPOINT_FILE_PATH_ATTR_NAME));
			endpoint.setInterfaceName(atts.getValue(CasaDescriptor.ENDPOINT_INTERFACE_ATTR_NAME));
			endpoint.setProcessName(atts.getValue(CasaDescriptor.ENDPOINT_PROCESS_ATTR_NAME));
			endpoint.setServiceName(atts.getValue(CasaDescriptor.ENDPOINT_SERVICE_ATTR_NAME));
			
			casa.addEnpoint(endpoint);
			
		} else if (CasaDescriptor.SE_SERVICE_UNITS_ELEM_NAME.equals(localName)) {
			SEServiceUnit seServiceUnit = new SEServiceUnit();
			
			seServiceUnit.setArtifactsZip(atts.getValue(CasaDescriptor.SERVICE_UNITS_ARTIFACTS_ZIP_ATTR_NAME));
			seServiceUnit.setComponentName(atts.getValue(CasaDescriptor.SERVICE_UNITS_COMPONENT_NAME_ATTR_NAME));
			seServiceUnit.setDescription(atts.getValue(CasaDescriptor.SERVICE_UNITS_DESCRIPTION_ATTR_NAME));
			seServiceUnit.setName(atts.getValue(CasaDescriptor.SERVICE_UNITS_NAME_ATTR_NAME));
			seServiceUnit.setUnitName(atts.getValue(CasaDescriptor.SERVICE_UNITS_UNIT_NAME_ATTR_NAME));
			
			casa.addServiceUnit(seServiceUnit);
			
		} else if (CasaDescriptor.BC_SERVICE_UNITS_ELEM_NAME.equals(localName)) {
			BCServiceUnit bcServiceUnit = new BCServiceUnit();
			
			bcServiceUnit.setArtifactsZip(atts.getValue(CasaDescriptor.SERVICE_UNITS_ARTIFACTS_ZIP_ATTR_NAME));
			bcServiceUnit.setComponentName(atts.getValue(CasaDescriptor.SERVICE_UNITS_COMPONENT_NAME_ATTR_NAME));
			bcServiceUnit.setDescription(atts.getValue(CasaDescriptor.SERVICE_UNITS_DESCRIPTION_ATTR_NAME));
			bcServiceUnit.setName(atts.getValue(CasaDescriptor.SERVICE_UNITS_NAME_ATTR_NAME));
			bcServiceUnit.setUnitName(atts.getValue(CasaDescriptor.SERVICE_UNITS_UNIT_NAME_ATTR_NAME));
			
			casa.addServiceUnit(bcServiceUnit);
		}
	}
	
	public CompositeApplication getCompositeApplication() {
		return this.casa;
	}
}
