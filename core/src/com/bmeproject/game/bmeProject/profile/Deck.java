package com.bmeproject.game.bmeProject.profile;

import com.bmeproject.game.bmeProject.Entity;

import java.util.ArrayList;

public class Deck
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private ArrayList<Entity> entities;

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

	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
}
