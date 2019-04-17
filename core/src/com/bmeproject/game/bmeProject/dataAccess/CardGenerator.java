package com.bmeproject.game.bmeProject.dataAccess;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardGenerator {
    List<Card> cardList;
    XMLReader reader;
    ArrayList<Card> list;
    private static final String TAG = CardGenerator.class.getName();

    public CardGenerator(String xmlPath){
        Gdx.app.debug(TAG, "CardGenerator constructed");
         cardList = new ArrayList<Card>();
         reader =  new XMLReader(xmlPath);
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
