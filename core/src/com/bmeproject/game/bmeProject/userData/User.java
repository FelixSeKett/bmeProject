package com.bmeproject.game.bmeProject.userData;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Deck;

import java.util.HashMap;

public class User
{
	private final BMEProject BME_PROJECT;
	private String name;
	private HashMap<Integer, Deck> deckList;

	public User(BMEProject bmeProject, String userName)
	{
		name = userName;
		deckList = new HashMap<Integer, Deck>();

		BME_PROJECT = bmeProject;

        addTestObjects();
	}

	private void addTestObjects(){
	    Deck deck = new Deck();
	    Deck deck2 = new Deck();

	    addDeck(deck);
	    addDeck(deck2);
    }

	// ===================================
	// FUNCTIONS
	// ===================================

	public Deck getDeck(int deckId)	{
	    return deckList.get(deckId);
}

	public String getName(){
		return name;
	}

	public void addDeck(Deck userDeck){
	    deckList.put(deckList.size()+1, userDeck);
	}

	public HashMap getDecks(){
	    return deckList;
    }
}
