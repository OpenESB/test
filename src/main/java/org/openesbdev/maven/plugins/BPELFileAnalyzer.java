package org.openesbdev.maven.plugins;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.maven.plugin.MojoExecutionException;
import org.openesbdev.maven.plugins.model.BpelModel;
import org.openesbdev.maven.plugins.model.Import;
import org.openesbdev.maven.plugins.model.PartnerLink;
import org.openesbdev.maven.plugins.utils.XmlDescriptorHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPELFileAnalyzer {

	private File bpelFile;
	private BpelModel bpelModel = new BpelModel();
	
	public BPELFileAnalyzer(File bpelFile) {
		this.bpelFile = bpelFile;
	}

	public void parseXML() throws MojoExecutionException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(bpelFile);

			// Stop at first process child node that is found
			NodeList childNodes = doc.getChildNodes();
			Node processNodes = null;
			for (int i = 0; i < childNodes.getLength(); i++) {
				if (XmlDescriptorHelper.isElement(
						childNodes.item(i),
						"http://docs.oasis-open.org/wsbpel/2.0/process/executable",
						"process")) {
					processNodes = childNodes.item(i);
					
					bpelModel.setProcessName(XmlDescriptorHelper.getAttribute((Element) processNodes, "name"));
					bpelModel.setProcessTargetNamespace(XmlDescriptorHelper.getAttribute((Element) processNodes, "targetNamespace"));

					break;
				}
			}
			
			if (processNodes != null) {
				NodeList importElementChildren = ((Element) processNodes).getElementsByTagName("import");
				for(int i = 0 ; i < importElementChildren.getLength() ; i++) {
					Element childElement = (Element) importElementChildren.item(i);
					Import importDecl = new Import();
					
					importDecl.setLocation(XmlDescriptorHelper.getAttribute(childElement, "location"));
					importDecl.setNamespaceURI(XmlDescriptorHelper.getAttribute(childElement, "namespace"));
					importDecl.setImportType(XmlDescriptorHelper.getAttribute(childElement, "importType"));
					
					bpelModel.getImports().add(importDecl);
				}
				
				Node partnerLinkNodes = null;
				
				// We will process the children
                Element processElement = (Element) processNodes;
                NodeList processChildren = processElement.getChildNodes();
                
				for (int i = 0; i < processChildren.getLength(); i++) {
					if (XmlDescriptorHelper.isElement(
							processChildren.item(i),
							"http://docs.oasis-open.org/wsbpel/2.0/process/executable",
							"partnerLinks")) {
						partnerLinkNodes = processChildren.item(i);
						break;
					}
				}
				
				if (partnerLinkNodes != null) {
	                // We will process the children
	                Element servicesElement = (Element) partnerLinkNodes;
	                NodeList children = servicesElement.getChildNodes();
	                for (int i = 0; i < children.getLength(); i++) {
	                    if (children.item(i) instanceof Element) {
	                        Element childElement = (Element) children.item(i);
	                        if (XmlDescriptorHelper.isElement(childElement,
	                        		"http://docs.oasis-open.org/wsbpel/2.0/process/executable", "partnerLink")) {
	                        	PartnerLink plnk = new PartnerLink();
	                        	
	                        	plnk.setPartnerLinkType(XmlDescriptorHelper.getQualifiedAttribute(childElement, "partnerLinkType"));
	                        	plnk.setMyRole(XmlDescriptorHelper.getAttribute(childElement, "myRole"));
	                        	plnk.setName(XmlDescriptorHelper.getAttribute(childElement, "name"));
	                        	plnk.setPartnerRole(XmlDescriptorHelper.getAttribute(childElement, "partnerRole"));
	                        	
	                        	bpelModel.getPartnerLinks().add(plnk);
	                        }
	                    }
	                }
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException("Unable to parse "
					+ bpelFile.getAbsolutePath());
		}
	}

	public BpelModel getBpelModel() {
		return bpelModel;
	}
}
