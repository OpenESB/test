package org.openesbdev.maven.plugins.xml;

public interface CatalogDescriptor {

	public static final String CATALOG_ELEM_NAME = "catalog"; // NOI18N
	public static final String NS_ATTR_NAME = "xmlns"; // NOI18N
	public static final String NS_ATTR_VALUE = "urn:oasis:names:tc:entity:xmlns:xml:catalog"; // NOI18N
	public static final String PREFER_ATTR_NAME = "prefer"; // NOI18N
	public static final String PREFER_ATTR_VALUE = "system"; // NOI18N
	
	public static final String SYSTEM_ELEM_NAME = "system"; // NOI18N
	public static final String SYSTEM_ID_ATTR_NAME = "systemId"; // NOI18N
	public static final String URI_ATTR_NAME = "uri"; // NOI18N
}
