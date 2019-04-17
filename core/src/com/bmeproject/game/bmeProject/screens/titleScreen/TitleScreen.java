package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

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
		System.out.println("TITLE SCREEN SHOWN");
		BME_PROJECT.activateTestScreen();
	}

	@Override protected Controller createController()
	{
		return new TitleController();
	}
}
