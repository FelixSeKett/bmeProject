package com.bmeproject.game.bmeProject.userData;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Deck;

import java.util.HashMap;

public class Profile
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BMEProject BME_PROJECT;
	private       String     name;
	private HashMap<Integer, Deck> myDecks = new HashMap<Integer, Deck>();

	// ===================================
	// CONSTRUCTOR
	// ===================================

	public Profile(BMEProject bmeProject)
	{
		BME_PROJECT = bmeProject;
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	/*public Deck getDeck()
	{
		return deck;
	}*/
}
