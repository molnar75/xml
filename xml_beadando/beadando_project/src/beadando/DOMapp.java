package beadando;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

		try {
			File inputFile = new File(INPUT_FILE_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputFile);
			boolean end = false;
			
			
			while(!end) {
			    System.out.println("\n\n1 - Ettermek kilistázása");
			    System.out.println("2 - Etterem hozzáadása");
			    System.out.println("3 - Etterem módosítása id alapján");
			    System.out.println("4 - Vége");
			    System.out.print("\nAdja meg a számot:");
			    String action = reader.readLine(); 
			    
			    switch(action){
			        case "1":
			            listEttermek(document);			        
			            break;
			        case "2":
			            addEtterem(document, reader);
			            break;
			        case "3":
			            modifyEtterem(document, reader);
			            break;
			        case "4":
			            end = true;
			            System.out.println("A program leáll.");
			            break;
			    }
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void addEtterem(Document document, BufferedReader reader) throws IOException {
		Element ettermekElement = (Element) document.getElementsByTagName("ettermek").item(0);
		
	    System.out.print("\nAdja meg az etterem adatait!\n");
		System.out.print("\nAdja meg az id-t:");
		String id = reader.readLine();
		System.out.print("Adja meg a nevet:");
        String nev = reader.readLine();
        System.out.print("Adja meg a weblapot:");
        String weblap = reader.readLine();
        System.out.print("Adja meg a minositest:");
        String minosites = reader.readLine();
        System.out.print("Adja meg a telefonszamot:");
        String telefonszam = reader.readLine();
		
		ettermekElement.appendChild(document.createTextNode("  "));
		
		Element etteremElement = document.createElement("etterem");
		Attr attrType = document.createAttribute("eid");
		attrType.setValue(id);
		etteremElement.setAttributeNode(attrType);
		ettermekElement.appendChild(etteremElement);
		
		Element nevElement = document.createElement("nev");
		etteremElement.appendChild(nevElement);

		Text nevText = document.createTextNode(nev);
		nevElement.appendChild(nevText);
		
		Element weblapElement = document.createElement("weblap");
		etteremElement.appendChild(weblapElement);

		Text weblapText = document.createTextNode(weblap);
		weblapElement.appendChild(weblapText);
		
		Element minositesElement = document.createElement("minosites");
		etteremElement.appendChild(minositesElement);

		Text minositesText = document.createTextNode(minosites);
		minositesElement.appendChild(minositesText);
		
		Element telefonszamElement = document.createElement("telefonszam");
		etteremElement.appendChild(telefonszamElement);

		Text telefonszamText = document.createTextNode(telefonszam);
		telefonszamElement.appendChild(telefonszamText);
		
		System.out.println("\n-------------Documentum az új elementtel---------------\n");

		printDocument(document);
	}
	
	public static void listEttermek(Document document) {
        System.out.println("\n------------Ettermek listája----------------\n");
        NodeList nList = document.getElementsByTagName("ettermek");
        
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
    }
	
	private static void modifyEtterem(Document document, BufferedReader reader ) throws IOException {
        System.out.print("\nAdja meg a modosítandó etterem id-ját:");
        String id = reader.readLine();
        
        System.out.println("\nMit szeretne rajta modosítani?\n");
        System.out.println("1- név");
        System.out.println("2 - weblap");
        System.out.println("3 - minõsítés");
        System.out.println("4 - telefonszám");
        System.out.print("\nAdja meg a számot:");
        String action = reader.readLine();
        
        System.out.print("Adja meg az új adatot:");
        
        String newData = reader.readLine();
	    
		NodeList nList = document.getElementsByTagName("ettermek");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				NodeList etteremList = eElement.getElementsByTagName("etterem");

				for (int count = 0; count < etteremList.getLength(); count++) {
					Element etteremElement = (Element) etteremList.item(count);
					if(etteremElement.getAttribute("eid").equals( String.valueOf(id))) {
						
						NodeList list = etteremElement.getChildNodes();
						
						for(int i = 0; i < list.getLength(); i++) {
							if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
								Node child = list.item(i);
								Element childElement = (Element) child;
								switch(childElement.getNodeName()) {
									case "nev":
									    if(action.equals("1")) {
									      childElement.setTextContent(newData);
									    }
										break;
									case "weblap":
									    if(action.equals("2")) {
                                          childElement.setTextContent(newData);
                                        }
										break;
									case "minosites":
									    if(action.equals("3")) {
                                          childElement.setTextContent(newData);
                                        }
										break;
									case "telefonszam":
									    if(action.equals("4")) {
                                          childElement.setTextContent(newData);
                                        }										
									    break;
									}
								}
							}
						}
					}
				}
			}
		
		System.out.println("\n--------------Módosított documentum--------------\n");
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
