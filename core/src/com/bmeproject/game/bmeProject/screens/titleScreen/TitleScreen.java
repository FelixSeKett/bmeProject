package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		BME_PROJECT.activateBattleScreen();
	}

	@Override protected Controller createController(SpriteBatch spriteBatch)
	{
		return new TitleController(spriteBatch);
	}
}
