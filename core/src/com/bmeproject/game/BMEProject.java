package com.bmeproject.game;

import com.badlogic.gdx.Game;

public class BMEProject extends Game
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	static final boolean    DEBUG = true;
	private      GameScreen gameScreen; // aktuell auch lokal ausreichend - später in dieser Form sinvoll

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void create()
	{
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
}
