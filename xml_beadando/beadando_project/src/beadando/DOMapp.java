package beadando;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DOMapp {

	private static final String INPUT_FILE_PATH = "data/megrendeles.xml";

	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);

		try {
			File inputFile = new File(INPUT_FILE_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputFile);
			
			System.out.print("Root element: ");
			System.out.println(document.getDocumentElement().getNodeName());
			
			NodeList nList = document.getElementsByTagName("ettermek");
			
			System.out.println("----------------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				System.out.println("Current Element:");
				System.out.print(nNode.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					NodeList etteremList = eElement.getElementsByTagName("etterem");

					for (int count = 0; count < etteremList.getLength(); count++) {
						Element etteremElement = (Element) etteremList.item(count);
						
						NodeList list = etteremElement.getChildNodes();
						
						System.out.print("\netterem id: ");
						System.out.print(etteremElement.getAttribute("eid"));
						
						for(int i = 0; i < list.getLength(); i++) {
							if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
								Node child = list.item(i);
								System.out.print("\n " + child.getNodeName() + ":");
								System.out.print(" " + child.getTextContent());	
							}
						}
					}
				}
			}

			addEtterem(document, new Etterem(1,"masodik", "wwww.valami.com", "***", "06473643786"));
			modifyEtterem(document, new Etterem(1,"MasodikEtterem", "wwww.valamiketto.com", "****", "06473643786"));

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void addEtterem(Document document, Etterem etterem) {
		Element ettermekElement = (Element) document.getElementsByTagName("ettermek").item(0);
		
		ettermekElement.appendChild(document.createTextNode("  "));
		
		Element etteremElement = document.createElement("etterem");
		Attr attrType = document.createAttribute("eid");
		attrType.setValue("1");
		etteremElement.setAttributeNode(attrType);
		ettermekElement.appendChild(etteremElement);
		
		Element nevElement = document.createElement("nev");
		etteremElement.appendChild(nevElement);

		Text nevText = document.createTextNode(etterem.getNev());
		nevElement.appendChild(nevText);
		
		Element weblapElement = document.createElement("weblap");
		etteremElement.appendChild(weblapElement);

		Text weblapText = document.createTextNode(etterem.getWeblap());
		weblapElement.appendChild(weblapText);
		
		Element minositesElement = document.createElement("minosites");
		etteremElement.appendChild(minositesElement);

		Text minositesText = document.createTextNode(etterem.getMinosites());
		minositesElement.appendChild(minositesText);
		
		Element telefonszamElement = document.createElement("telefonszam");
		etteremElement.appendChild(telefonszamElement);

		Text telefonszamText = document.createTextNode(etterem.getTelefonszam());
		telefonszamElement.appendChild(telefonszamText);
		
		System.out.println("\n\n-------------Document with new element---------------\n");

		printDocument(document);
	}
	
	private static void modifyEtterem(Document document, Etterem etterem) {
		NodeList nList = document.getElementsByTagName("ettermek");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				NodeList etteremList = eElement.getElementsByTagName("etterem");

				for (int count = 0; count < etteremList.getLength(); count++) {
					Element etteremElement = (Element) etteremList.item(count);
					if(etteremElement.getAttribute("eid").equals( String.valueOf(etterem.getId()))) {
						
						NodeList list = etteremElement.getChildNodes();
						
						for(int i = 0; i < list.getLength(); i++) {
							if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
								Node child = list.item(i);
								Element childElement = (Element) child;
								System.out.println(childElement.getNodeName());
								switch(childElement.getNodeName()) {
									case "nev":
										childElement.setTextContent(etterem.getNev());
										break;
									case "weblap":
										childElement.setTextContent(etterem.getWeblap());
										break;
									case "minosites":
										childElement.setTextContent(etterem.getMinosites());
										break;
									case "telefonszam":
										childElement.setTextContent(etterem.getTelefonszam());
										break;
									}
								}
							}
						}
					}
				}
			}
		
		System.out.println("\n\n--------------Modified document--------------\n");
		printDocument(document);
	}

	private static void printDocument(Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
