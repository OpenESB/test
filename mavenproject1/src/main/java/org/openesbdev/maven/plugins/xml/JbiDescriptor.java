package org.openesbdev.maven.plugins.xml;

public interface JbiDescriptor {

	public static final String JBI_ELEM_NAME = "jbi"; // NOI18N
	public static final String VERSION_ATTR_NAME = "version"; // NOI18N
	public static final String VERSION_ATTR_VALUE = "1.0"; // NOI18N
	public static final String NS_ATTR_NAME = "xmlns"; // NOI18N
	public static final String NS_ATTR_VALUE = "http://java.sun.com/xml/ns/jbi"; // NOI18N
	public static final String NS_XSI_ATTR_NAME = "xmlns:xsi"; // NOI18N
	public static final String NS_XSI_ATTR_VALUE = "http://www.w3.org/2001/XMLSchema-instance"; // NOI18N
	public static final String XSI_ATTR_NAME = "xsi:schemaLocation"; // NOI18N
	public static final String XSI_ATTR_VALUE = "http://java.sun.com/xml/ns/jbi jbi.xsd"; // NOI18N
	
	public static final String SERVICES_ELEM_NAME = "services"; // NOI18N
	public static final String PROVIDES_ELEM_NAME = "provides"; // NOI18N
	public static final String CONSUMES_ELEM_NAME = "consumes"; // NOI18N
	public static final String BINDING_ATTR_NAME = "binding-component"; // NOI18N
	public static final String INTERFACE_ATTR_NAME = "interface-name"; // NOI18N
	public static final String ENDPOINT_ATTR_NAME = "endpoint-name"; // NOI18N
	public static final String SERVICE_ATTR_NAME = "service-name"; // NOI18N

	public static final String JBI_EXT_NS = "http://www.sun.com/jbi/descriptor/service-unit"; // NOI18N

	public static final String JBI_EXT_DISPLAY_NAME = "display-name";
	public static final String JBI_EXT_PROC_NAME_ATTR = "process-name";
	public static final String JBI_EXT_FILE_PATH_ATTR = "file-path";
}
