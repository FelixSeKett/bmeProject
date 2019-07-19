package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.BMEProject;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Integer> deck = new ArrayList<Integer>();

    public Deck() {
        addStarterCardsToDeck();
        Collections.sort(deck);
    }

    public ArrayList<Integer> getDeck() {
        return deck;
    }

    public void addCardToDeck(int id) {
        deck.add(id);
    }


    private void addStarterCardsToDeck() {
        // added 37  Karten von der Id 6-44 in das Deck (alle Karten die kein Quarier sind)
        for (int i = 0; i < 37; i++) {
            int random = (int) (Math.random() * 44 + 6);
            deck.add(random);

        }
        //added 3 Random Karten mit der Id 1-5 in das Deck (Karten die Quartiere sind)
        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * 5 + 1);
            deck.add(random);

        }
    }

    public void removeCardFromDeck(int id) {
        deck.remove(id);
    }

    public int getSize() {
        return deck.size();
    }

    public int getCardIdFromDeck(int index) {
        return deck.get(index);
    }

    public Card getCardFromDeck(int id) {
        //hier muss noch eine Prüfung hin, ob deck überhaupt id contained
        Card card = BMEProject.allCards.get(id);
        return card;
    }

    public ArrayList getAllCardsFromDeck() {
        return deck;
    }
    // Note for ArrayLists: contains(), set(), indexOf(), clear()
}
