package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;

public class TitleScreen extends TheatricalScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("TITLE SCREEN SHOWN");
		BME_PROJECT.activateBattleScreen();
	}
}
