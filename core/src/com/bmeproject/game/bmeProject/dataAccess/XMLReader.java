package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.archive.Background;
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
 * eine CardContainer zu bauen.
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
        NodeList nList = doc.getElementsByTagName("card");

        List<Card> cardList = new ArrayList<Card>();
        Card tempCard;

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("ID: " + eElement.getAttribute("id"));
                tempCard = new Card();
                tempCard.initialize(
                        Integer.parseInt(eElement.getAttribute("id")),
                        eElement.getElementsByTagName("cardName").item(0).getTextContent(),
                        Integer.parseInt(eElement.getElementsByTagName("cardStrengh").item(0).getTextContent()),
                        eElement.getElementsByTagName("cardIllustrationFilePath").item(0).getTextContent(),
                        getType(eElement.getElementsByTagName("cardType").item(0).getTextContent())
                        );

                cardList.add(tempCard);
            }
        }
        return cardList;
    }

    // returns Type of Card according to given String
    private Type getType(String type) {
        if (type.equals("BASE")) {
            return Type.BASE;
        } else if (type.equals("FIGURE")) {
            return Type.FIGURE;
        } else if (type.equals("MANIPULATION")) {
            return Type.MANIPULATION;
        } else return null;
    }

    // returns background of Card according to given String
    private Background getBackground(String background) {
        if (background.equals("OPEN_WATER")) {
            return Background.OPEN_WATER;
        } else if (background.equals("WRECK")) {
            return Background.WRECK;
        } else if (background.equals("GROUND")) {
            return Background.GROUND;
        } else return null;
    }

    // prints CardName and CardStrengh if read-process was successfull
    private void printEntity(CardContainer e) {
        System.out.println(e.getCardName());
        System.out.println(e.getStrength());
    }
}
