package org.openesbdev.maven.plugins.utils;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class XmlDescriptorHelper {

	private XmlDescriptorHelper() {
	}
	
	public static QName getQualifiedAttribute(Element childElement, String attributeName) {
        if (childElement.hasAttribute(attributeName)) {
            String prefixAndLocalPart = childElement
                    .getAttribute(attributeName);
            String prefix = prefixAndLocalPart.substring(0, prefixAndLocalPart
                    .indexOf(':'));
            String localPart = prefixAndLocalPart.substring(prefixAndLocalPart
                    .indexOf(':') + 1);
            return new QName(childElement.lookupNamespaceURI(prefix), localPart);
        }
        return null;
    }
	
	public static String getAttribute(Element childElement, String attributeName) {
        if (childElement.hasAttribute(attributeName)) {
            return childElement.getAttribute(attributeName);
        }
        return null;
    }
	
	public static boolean isElement(Node node, String namespaceURI,
			String localPart) {
		if (node instanceof Element) {
			Element element = (Element) node;
			if (localPart.equals(element.getNodeName())) {
				return true;
			} else {
				// Compare the namespace URI and localname
				if ((namespaceURI.equals(element.getNamespaceURI()))
						&& (localPart.equals(element.getLocalName()))) {
					return true;
				}
			}
		}
		return false;
	}
}
