package com.bmeproject.game.bmeProject.screens.testScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card       card;
	private Deck       deck;
	private BMEProject bmeProject;
	private Batch      batch;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TestScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();

				System.out.println("TEST SCREEN SHOWN");


	}

	@Override protected Controller createController()
	{
		return new Controller();
	}
}
