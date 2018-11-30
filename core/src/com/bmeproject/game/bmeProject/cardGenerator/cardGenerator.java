package com.bmeproject.game.bmeProject.cardGenerator;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.dataAccess.XMLReader;

import java.util.ArrayList;
import java.util.List;

public class cardGenerator {
    List<Entity> cardList;
    XMLReader reader;

    public cardGenerator(String xmlPath){
         cardList = new ArrayList<Entity>();
         reader =  new XMLReader(xmlPath);
    }

    public void getCardList(){
        cardList = reader.initCards();
        System.out.println("Entity: " +cardList.get(0).getCardName());
    }
}
