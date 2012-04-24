package org.openesbdev.maven.plugins.catalog;

public interface CatalogItemListener {

	void onItem(String systemId, String location);
}
