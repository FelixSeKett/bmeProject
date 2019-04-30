package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class TitleScreen extends AbstractScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();
		//System.out.println("TITLE SCREEN SHOWN");
		BME_PROJECT.activateTestScreen();
	}
}
