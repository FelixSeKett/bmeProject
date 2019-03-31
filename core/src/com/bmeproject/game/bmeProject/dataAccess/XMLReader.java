package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse hat den Zweck, aus einem XML-File, dessen Pfad sie übergeben bekommt anhand zu lesen und daraus
 * eine Card zu bauen.
 */
public class XMLReader {

    String path;

    public XMLReader(String filepath) {
        path = filepath;
    }

    //Initialises process
    public List<Card> initCards() {
        Document document = readXML();
        return readCardsFromXML(document);
    }

    private Document readEffectsXML() {
        try {
            File xmlFile = new File("core/src/com/bmeproject/game/bmeProject/dataAccess/EffectsXML.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            return doc;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    //Builds document from XML and returns it
    private Document readXML() {
        try {
            File xmlFile = new File(path);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            return doc;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    // reads given Document with specific card-tags
    private List<Card> readCardsFromXML(Document doc) {
        System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
        NodeList nodeListCards = doc.getElementsByTagName("card");
        NodeList nodeListEffect = doc.getElementsByTagName("effect");
        List<Card> cardList = new ArrayList<Card>();
        Card tempCard;


        System.out.println(doc.getElementById("asdf"));
        for (int temp = 0; temp < nodeListCards.getLength(); temp++) {
            Node nNode = nodeListCards.item(temp);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("ID: " + eElement.getAttribute("id"));
                tempCard = new Card(
                        Integer.parseInt(eElement.getAttribute("id")),
                        eElement.getElementsByTagName("cardName").item(0).getTextContent(),
                        getType(eElement.getElementsByTagName("cardType").item(0).getTextContent()),
                        Integer.parseInt(eElement.getElementsByTagName("cardEffect1").item(0).getTextContent()),
                        Integer.parseInt(eElement.getElementsByTagName("cardEffect2").item(0).getTextContent()),
                        Integer.parseInt(eElement.getElementsByTagName("cardEffect3").item(0).getTextContent()),
                        eElement.getElementsByTagName("cardDescription").item(0).getTextContent()
                        );
                tempCard.initialize();

                cardList.add(tempCard);
                //Der Effekt wird ausgegeben, den die Karte benutzt jedoch nicht nach ID sondern nach Reihenfolge
                System.out.println(nodeListEffect.item(tempCard.getEffect1() - 1).getTextContent());
            }
        }
        return cardList;
    }

    // returns Type of Card according to given String
    private Type getType(String type) {
        if (type.equals("Quartier")) {
            return Type.QUARTIER;
        } else if (type.equals("FIGURE")) {
            return Type.KREATUR;
        } else if (type.equals("MANIPULATION")) {
            return Type.PHAENOMEN;
        } else return null;
    }


}
