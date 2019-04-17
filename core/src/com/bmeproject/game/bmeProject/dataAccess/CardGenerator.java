package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Effect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardGenerator {
    List<Card> cardList;
    HashMap<Integer, Effect> effectList;
    XMLReader reader;
    ArrayList<Card> list;
    BMEProject bmeProject;

    public CardGenerator(String xmlPath, BMEProject bmeProject){
        this.bmeProject = bmeProject;
        cardList = new ArrayList<Card>();
        reader =  new XMLReader(xmlPath);
    }

    private ArrayList<Card> buildAllCards(){
        cardList = reader.initCards();
        list = (ArrayList<Card>) cardList;

        effectList = getAllEffects();
        for(Card card : cardList){
            card.setAllCardEffects(buildEffectsForAllCards(card));
            card.initialize();
        }
        return list;
    }

    private ArrayList buildEffectsForAllCards(Card card){
        String[] effectID = card.getEffectIdsFromCard();
        ArrayList<Effect> cardEffects = new ArrayList();

        for (String id: effectID) {
            cardEffects.add(effectList.get(Integer.parseInt(id)));
        }
        return cardEffects;
    }

    private HashMap getAllEffects(){
        reader.path = "core/src/com/bmeproject/game/bmeProject/dataAccess/EffectsXML.xml";
        return reader.initEffects();
    }

    public HashMap createAllCards()
    {
        ArrayList<Card> cardList = buildAllCards();
        HashMap<Integer, Card> allCards = new HashMap<Integer, Card>();

        for(Card card : cardList)
        {
            allCards.put(card.getCardId(), card);
        }

        return allCards;
    }

}
