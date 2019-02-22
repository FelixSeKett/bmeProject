package com.bmeproject.game.bmeProject.cardGenerator;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.dataAccess.XMLReader;

import java.util.ArrayList;
import java.util.List;

public class CardGenerator {
    List<Entity> cardList;
    XMLReader reader;
    ArrayList<Entity> list;

    public CardGenerator(String xmlPath){
         cardList = new ArrayList<Entity>();
         reader =  new XMLReader(xmlPath);
    }

    public ArrayList<Entity> getCardList(){
        cardList = reader.initCards();
        System.out.println("Entity: " + cardList.get(2).getIllustrationFilePath());
        list = (ArrayList<Entity>) cardList;
        return list;
    }
}
// Warum kann ich nicht sofort "cardList" als return benutzen? warum denkt er dass es einfach list ist, obwohl in Zeile 15 haben wir es als ArrayList definiert.