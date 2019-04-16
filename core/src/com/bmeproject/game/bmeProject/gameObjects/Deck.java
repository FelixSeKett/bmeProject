package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.BMEProject;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	private ArrayList<Integer> deck = new ArrayList<Integer>();

	public Deck()
	{
		//Hard gecodet
		deck.add(1);
		deck.add(3);
		deck.add(4);
		deck.add(1);

		Collections.sort(deck);
	}

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

	public int getCardIdFromDeck(int index)
	{
		return deck.get(index);
	}

	public Card getCardFromDeck(int id)
	{
		//hier muss noch eine Prüfung hin, ob deck überhaupt id contained
		Card card = BMEProject.allCards.get(id);
		return card;
	}
	// Note for ArrayLists: contains(), set(), indexOf(), clear()
}
