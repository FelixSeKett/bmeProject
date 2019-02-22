package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.dataAccess.CardContainer;

import java.util.ArrayList;

public class Deck
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private ArrayList<CardContainer> entities;

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

	public ArrayList<CardContainer> getEntities()
	{
		return entities;
	}
}
