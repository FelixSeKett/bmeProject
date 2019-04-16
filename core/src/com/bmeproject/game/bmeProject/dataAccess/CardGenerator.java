package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardGenerator {
    List<Card> cardList;
    XMLReader reader;
    ArrayList<Card> list;
    BMEProject bmeProject;

    public CardGenerator(String xmlPath, BMEProject bmeProject){
        this.bmeProject = bmeProject;
         cardList = new ArrayList<Card>();
         reader =  new XMLReader(xmlPath, bmeProject);
    }

    public ArrayList<Card> getCardList(){
        cardList = reader.initCards();
        list = (ArrayList<Card>) cardList;
        return list;
    }

    public HashMap createAllCards()
    {
        ArrayList<Card> cardList = getCardList();
        HashMap<Integer, Card> allCards = new HashMap<Integer, Card>();

        for(Card card : cardList)
        {
            allCards.put(card.getCardId(), card);
        }

        return allCards;
    }

}
