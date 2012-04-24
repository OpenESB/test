package org.openesbdev.maven.plugins;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.wsdl.Definition;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.codehaus.plexus.util.xml.XMLWriter;
import org.openesbdev.maven.plugins.model.BpelModel;
import org.openesbdev.maven.plugins.model.Consumer;
import org.openesbdev.maven.plugins.model.PartnerLink;
import org.openesbdev.maven.plugins.model.Provider;
import org.openesbdev.maven.plugins.utils.FileUtils;
import org.openesbdev.maven.plugins.utils.XmlDescriptorHelper;
import org.openesbdev.maven.plugins.xml.JbiDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

final class ServiceUnitDescriptorXmlWriter extends AbstractJbiXmlWriter {

	private final Logger logger = Logger.getLogger(ServiceUnitDescriptorXmlWriter.class.getName());

	private List<Provider> providersList = new ArrayList<Provider>();
	private List<Consumer> consumersList = new ArrayList<Consumer>();

	private static final String PARTNER_ROLE = "partnerRole";
	private static final String MY_ROLE = "myRole";

	private final FileFilter BPEL_FILE_FILTER = new BPELFileFilter();

	ServiceUnitDescriptorXmlWriter(File sourceDirectory) {
		super();
		process(sourceDirectory);
	}

	private void process(File sourceDirectory) {
		// Initializing namespace for JBI Service Unit
		populateNamespace(JbiDescriptor.JBI_EXT_NS);

		processFolder(sourceDirectory, sourceDirectory);
	}

	private void processFileObject(File file, File sourceDir) {
		if (file.isDirectory()) {
			processFolder(file, sourceDir);
		} else {
			processFile(file, sourceDir);
		}
	}

	private void processFolder(File fileDir, File sourceDir) {
		File[] children = fileDir.listFiles(BPEL_FILE_FILTER);

		for (int i = 0; i < children.length; i++) {
			processFileObject(children[i], sourceDir);
		}
	}

	static class BPELFileFilter implements FileFilter {
		public boolean accept(File pathname) {
			return pathname.isDirectory()
					|| pathname.getName().endsWith("bpel");
		}
	}

	protected void processFile(File file, File sourceDir) {
		BpelModel bpelModel = null;
		try {
			BPELFileAnalyzer bpelAnalyzer = new BPELFileAnalyzer(file);
			bpelAnalyzer.parseXML();
			bpelModel = bpelAnalyzer.getBpelModel();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error while creating BPEL Model ", ex);
			throw new RuntimeException("Error while creating BPEL Model ", ex);
		}

		try {
			populateProviderConsumer(bpelModel, file, sourceDir);
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error encountered while processing BPEL file - "
							+ file.getAbsolutePath());
			throw new RuntimeException(ex);
		}
	}

	protected void populateProviderConsumer(BpelModel bpelModel, File file,
			File sourceDir) throws Exception {
		Provider provider = null;
		Consumer consumer = null;

		String processName = bpelModel.getProcessName();
		String filePath = FileUtils.getRelativePath(sourceDir, file);

		for(PartnerLink pLink : bpelModel.getPartnerLinks()) {
			String partnerLinkName = pLink.getName();

			String partnerLinkNameSpaceURI = bpelModel.getProcessTargetNamespace();
			String partnerLinkNSPrefix = populateNamespace(partnerLinkNameSpaceURI);

			String wsdlLocation = bpelModel.getLocationFromNamespace(pLink.getPartnerLinkType().getNamespaceURI());
        	File wsdlFile = new File(file.getParentFile(), wsdlLocation);
        	WSDLReader wsdlReader = WSDLFactory.newInstance().newWSDLReader();
            Definition wsdlDefinition = wsdlReader.readWSDL(null, wsdlFile.getPath());

            List<ExtensibilityElement> extensibilityElements = wsdlDefinition.getExtensibilityElements();
            for(ExtensibilityElement extensibilityElt : extensibilityElements) {
            	if (extensibilityElt instanceof UnknownExtensibilityElement
            			&& extensibilityElt.getElementType().getNamespaceURI().equals("http://docs.oasis-open.org/wsbpel/2.0/plnktype")) {
            		UnknownExtensibilityElement uee = (UnknownExtensibilityElement) extensibilityElt;
            		String plnkName = XmlDescriptorHelper.getAttribute(uee.getElement(), "name");
            		
            		if (plnkName.equals(pLink.getPartnerLinkType().getLocalPart())) {
            			NodeList roles = uee.getElement().getChildNodes();
            			
            			for (int j = 0; j < roles.getLength(); j++) {
    	                    if (roles.item(j) instanceof Element) {
    	                        Element roleElement = (Element) roles.item(j);
    	                        if (XmlDescriptorHelper.isElement( roleElement,
    	    							"http://docs.oasis-open.org/wsbpel/2.0/plnktype", "role")) {
    	                        	
    	                        	if (pLink.getPartnerRole() != null && 
    	                        			roleElement.getAttribute("name").equals(pLink.getPartnerRole())) {
    	                        		QName portType = XmlDescriptorHelper.getQualifiedAttribute(roleElement, "portType");
    	                        		String portNameNSPrefix = populateNamespace(portType.getNamespaceURI());
    	                        		consumer = new Consumer(partnerLinkName, portType.getLocalPart(),
    	                						partnerLinkNameSpaceURI, portType.getNamespaceURI(), roleElement.getAttribute("name"),
    	                						partnerLinkNSPrefix, portNameNSPrefix, processName,
    	                						filePath);
    	                        		
    	                				if (!consumersList.contains(consumer)) {
    	                					this.consumersList.add(consumer);
    	                				}
    	                				
    	                        		// Partner port type;
        	                        	System.out.println("Port type PartnerRole : " + portType.getLocalPart());
    	                        	}
    	                        	
    	                        	if (pLink.getMyRole() != null &&
    	                        			roleElement.getAttribute("name").equals(pLink.getMyRole())) {
    	                        		QName portType = XmlDescriptorHelper.getQualifiedAttribute(roleElement, "portType");
    	                        		String portNameNSPrefix = populateNamespace(portType.getNamespaceURI());
    	                        		provider = new Provider(
    	                						partnerLinkName, portType.getLocalPart(),
    	                						partnerLinkNameSpaceURI, portType.getNamespaceURI(), pLink.getMyRole(),
    	                						partnerLinkNSPrefix, portNameNSPrefix, processName,
    	                						filePath);
    	                				
    	                				if (! providersList.contains(provider)) {
    	                					providersList.add(provider);
    	                				}
    	                				
    	                        		// Partner port type;
        	                        	System.out.println("Port type MyRole : " + portType.getLocalPart());
    	                        	}
    	                        	
    	    					}
    	                    }
            			}
            			break;
            		}
            	}
            }
		}
	}

	public void writeContent(XMLWriter writer) {
		// Add services
		writer.startElement(JbiDescriptor.SERVICES_ELEM_NAME);
		writer.addAttribute(JbiDescriptor.BINDING_ATTR_NAME, Boolean.FALSE.toString());

		// Add providers
		for (Provider provider : this.providersList) {
			String partnerLink = provider.getPartnerLinkName();

			if (partnerLink != null) {
				// Start provider
				writer.startElement(JbiDescriptor.PROVIDES_ELEM_NAME);
				writer.addAttribute(JbiDescriptor.INTERFACE_ATTR_NAME, provider
						.getPortNameNSPrefix()
						+ ':' + provider.getPortName());
				writer.addAttribute(JbiDescriptor.SERVICE_ATTR_NAME, provider
						.getPartnerLinkNSPrefix()
						+ ':' + provider.getPartnerLinkName());
				writer.addAttribute(JbiDescriptor.ENDPOINT_ATTR_NAME, provider.getRoleName()
						+ '_' + MY_ROLE);

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_DISPLAY_NAME);
				writer.writeText(provider.getPartnerLinkName());
				writer.endElement();

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_PROC_NAME_ATTR);
				writer.writeText(provider.getProcessName());
				writer.endElement();

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_FILE_PATH_ATTR);
				writer.writeText(provider.getFilePath());
				writer.endElement();

				// End provider
				writer.endElement();
			}
		}

		// Add consumers
		for (Consumer consumer : this.consumersList) {
			String partnerLink = consumer.getPartnerLinkName();
			if (partnerLink != null) {
				// Start consumer
				writer.startElement(JbiDescriptor.CONSUMES_ELEM_NAME);
				writer.addAttribute(JbiDescriptor.INTERFACE_ATTR_NAME, consumer
						.getPortNameNSPrefix()
						+ ':' + consumer.getPortName());
				writer.addAttribute(JbiDescriptor.SERVICE_ATTR_NAME, consumer
						.getPartnerLinkNSPrefix()
						+ ':' + consumer.getPartnerLinkName());
				writer.addAttribute(JbiDescriptor.ENDPOINT_ATTR_NAME, consumer
						.getPartnerRoleName()
						+ '_' + PARTNER_ROLE);

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_DISPLAY_NAME);
				writer.writeText(consumer.getPartnerLinkName());
				writer.endElement();

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_PROC_NAME_ATTR);
				writer.writeText(consumer.getProcessName());
				writer.endElement();

				writer.startElement(getNamespacePrefix().get(JbiDescriptor.JBI_EXT_NS)
						+ ':' + JbiDescriptor.JBI_EXT_FILE_PATH_ATTR);
				writer.writeText(consumer.getFilePath());
				writer.endElement();

				// End consumer
				writer.endElement();
			}
		}

		// End services
		writer.endElement();
	}
}
