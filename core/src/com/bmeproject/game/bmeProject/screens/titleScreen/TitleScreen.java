package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class TitleScreen extends AbstractScreen
{
	private static final String TAG = TitleScreen.class.getName();

	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
		Gdx.app.debug(TAG, "Screen initialized");
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();
		BME_PROJECT.activateTestScreen();
	}
}
