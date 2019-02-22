package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardGenerator {
    List<Card> cardList;
    XMLReader reader;
    ArrayList<Card> list;

    public CardGenerator(String xmlPath){
         cardList = new ArrayList<Card>();
         reader =  new XMLReader(xmlPath);
    }

    public ArrayList<Card> getCardList(){
        cardList = reader.initCards();
       // System.out.println("CardContainer: " + cardList.get(2).getIllustrationFilePath());
        list = (ArrayList<Card>) cardList;
        return list;
    }

    public HashMap createAllCards()
    {
        ArrayList<Card> cardList = getCardList();
        HashMap<Integer, Card> allCards = new HashMap<>();

        for(Card card : cardList)
        {
            allCards.put(card.getCardId(), card);
        }

        return allCards;
    }

}
