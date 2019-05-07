package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

public class DeckScreen extends AbstractScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected Controller createController()
	{
		return new DeckController();
	}
}
