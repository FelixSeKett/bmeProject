package com.bmeproject.game.bmeProject.gameObjects;

import java.util.ArrayList;

public class Deck
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private ArrayList<Card> entities;

	private int[] deck;

	public Deck()
	{
		deck = new int[]{1, 4, 5};
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	public int[] getDeck()
	{
		return deck;
	}

	public ArrayList<Card> getEntities()
	{
		return entities;
	}
}
