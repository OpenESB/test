package org.openesbdev.maven.plugins.xml;

public interface CasaDescriptor {

	public static final String JBI_ELEM_NAME = "casa"; // NOI18N
	public static final String NS_ATTR_NAME = "xmlns"; // NOI18N
	public static final String NS_ATTR_VALUE = "http://java.sun.com/xml/ns/casa"; // NOI18N
	
	public static final String ENDPOINTS_ELEM_NAME = "endpoints"; // NOI18N
	public static final String ENDPOINT_ELEM_NAME = "endpoint"; // NOI18N
	
	public static final String ENDPOINT_INTERFACE_ATTR_NAME = "interface-name"; // NOI18N
	public static final String ENDPOINT_ENDPOINT_ATTR_NAME = "endpoint-name"; // NOI18N
	public static final String ENDPOINT_SERVICE_ATTR_NAME = "service-name"; // NOI18N
	public static final String ENDPOINT_PROCESS_ATTR_NAME = "process-name"; // NOI18N
	public static final String ENDPOINT_DISPLAY_ATTR_NAME = "display-name"; // NOI18N
	public static final String ENDPOINT_FILE_PATH_ATTR_NAME = "file-path"; // NOI18N
	public static final String ENDPOINT_NAME_ATTR_NAME = "name"; // NOI18N
	
	public static final String SERVICE_UNITS_ELEM_NAME = "service-units"; // NOI18N
	public static final String SE_SERVICE_UNITS_ELEM_NAME = "service-engine-service-unit"; // NOI18N
	public static final String BC_SERVICE_UNITS_ELEM_NAME = "binding-component-service-unit"; // NOI18N
	
	public static final String SERVICE_UNITS_ARTIFACTS_ZIP_ATTR_NAME = "artifacts-zip"; // NOI18N
	public static final String SERVICE_UNITS_COMPONENT_NAME_ATTR_NAME = "component-name"; // NOI18N
	public static final String SERVICE_UNITS_DESCRIPTION_ATTR_NAME = "description"; // NOI18N
	public static final String SERVICE_UNITS_NAME_ATTR_NAME = "name"; // NOI18N
	public static final String SERVICE_UNITS_UNIT_NAME_ATTR_NAME = "unit-name"; // NOI18N
	
	// XLink Domain
    public static final String XLINK_NAMESPACE_URI = "http://www.w3.org/2000/xlink"; // NOI18N
    public static final String XLINK_NAMESPACE_PREFIX = "xlink"; // NOI18N
    public static final String XLINK_HREF_ATTR_NAME = "href"; // NOI18N
    public static final String XLINK_TYPE_ATTR_NAME = "type"; // NOI18N
}
