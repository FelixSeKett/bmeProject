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
public class XMLReader
{

	String path;

	public XMLReader(String filepath)
	{
		path = filepath;
	}

	//Initialises process
	public List<Card> initCards()
	{
		Document document = readXML();
		return readCardsFromXML(document);
	}

	//Builds document from XML and returns it
	private Document readXML()
	{
		try {
			File xmlFile = new File(path);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder        dBuilder  = dbFactory.newDocumentBuilder();
			Document               doc       = dBuilder.parse(xmlFile);

			return doc;
		}
		catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	// reads given Document with specific card-tags
	private List<Card> readCardsFromXML(Document doc)
	{
		NodeList   nList    = doc.getElementsByTagName("card");
		List<Card> cardList = new ArrayList<Card>();
		Card       tempCard;
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element)nNode;
				int     id       = Integer.parseInt(eElement.getAttribute("id"));
				String  name     = eElement.getElementsByTagName("cardName").item(0).getTextContent();
				String illustrationFilePath = eElement.getElementsByTagName("cardIllustrationFilePath").item(0).getTextContent();
				Type type = getType(eElement.getElementsByTagName("cardType").item(0).getTextContent());
				tempCard = new Card(id, name, illustrationFilePath, type);
				cardList.add(tempCard);
			}
		}
		return cardList;
	}

	// returns Type of Card according to given String
	private Type getType(String type)
	{
		if (type.equals("QUARTER")) {
			return Type.QUARTER;
		} else if (type.equals("CREATURE")) {
			return Type.CREATURE;
		} else if (type.equals("PHENOMENON")) {
			return Type.PHENOMENON;
		} else {
			return null;
		}
	}


}
