package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.BMEProject;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	// ===================================
	// ATTRIBUTES
	// ===================================


	private ArrayList<Integer> deck = new ArrayList<Integer>();



	public Deck()
	{
		deck.add(1);
		deck.add(3);
		deck.add(4);
		deck.add(1);

		Collections.sort(deck);
	}

	// ===================================
	// FUNCTIONS
	// ===================================


	public ArrayList<Integer> getDeck()
	{
		return deck;
	}

	public void addCardToDeck(int id)
	{
		deck.add(id);
	}

	public void removeCardFromDeck(int id)
	{
		deck.remove(id);

	}

	public int getSize()
	{
		return deck.size();
	}

	public int getCardIdFromDeck(int id)
	{
		return deck.get(id);
	}

	public Card getCardFromDeck(int id)
	{
		Card card = BMEProject.allCards.get(id);
		return card;
	}


	// Note for ArrayLists: contains(), set(), indexOf(), clear()

}
