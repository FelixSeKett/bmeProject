package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.BMEProject;
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

        String name;
        int strengh;
        String illustrator;
        String path;
        String typeString;
        String backgroundString;


        System.out.println("----------------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                System.out.println("ID: " + eElement.getAttribute("id"));
                name = eElement.getElementsByTagName("cardName").item(0).getTextContent();
                strengh = Integer.parseInt(eElement.getElementsByTagName("cardStrengh").item(0).getTextContent());
                illustrator = eElement.getElementsByTagName("cardIllustrator").item(0).getTextContent();
                path = eElement.getElementsByTagName("cardIllustrationFilePath").item(0).getTextContent();
                typeString = eElement.getElementsByTagName("cardType").item(0).getTextContent();
                backgroundString = eElement.getElementsByTagName("cardBackground").item(0).getTextContent();
                //Type und Background Constructor müssen implementiert werden

                tempEntity = new Entity(name, strengh, illustrator, path, typeString,backgroundString);
                System.out.println(tempEntity.getCardName());
            }
        }
    }
}
