package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.Entity;
import org.w3c.dom.*;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class XMLReader {

    String path;

    public XMLReader(String filepath) {
        path = filepath;
    }

    public void initCard() {
        Document document = readXML();
        readCardsFromXML(document);
    }

    private Document readXML() {
        try {
            System.out.println("INIT Filereader´- Path: " + this.path);

            File xmlFile = new File("/" + path);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            return doc;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    private void readCardsFromXML(Document doc) {
        System.out.println("Root element:" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("card");

        List<Entity> entityList;
        Entity tempEntity;

        System.out.println("----------------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                System.out.println("ID: " + eElement.getAttribute("id"));
                System.out.println("Card Name: " + eElement.getElementsByTagName("cardName").item(0).getTextContent());
                System.out.println("cardStrength: " + eElement.getElementsByTagName("cardStrength").item(0).getTextContent());
                System.out.println("cardIllustrator: " + eElement.getElementsByTagName("cardIllustrator").item(0).getTextContent());
                System.out.println("cardIllustrationFilePath: " + eElement.getElementsByTagName("cardIllustrationFilePath").item(0).getTextContent());
                System.out.println("cardType: " + eElement.getElementsByTagName("cardType").item(0).getTextContent());
                System.out.println("cardBackground: " + eElement.getElementsByTagName("cardBackground").item(0).getTextContent());
            }
        }
    }
}
