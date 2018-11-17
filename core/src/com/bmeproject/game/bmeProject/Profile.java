package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.profile.Deck;

public class Profile
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BMEProject BME_PROJECT;
	private       String     name;
	private       Deck       deck;

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

	public Deck getDeck()
	{
		return deck;
	}
}
