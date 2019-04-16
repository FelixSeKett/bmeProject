package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;
	private Deck deck;
	private BMEProject bmeProject;
	private Batch batch;

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

		card = BMEProject.allCards.get(1);
		stage.addActor(card);

		deck = new Deck();
		int[] tempDeck =  deck.getDeck();
		System.out.println("Deck besteht aus " + tempDeck.length + " Karten");
		for (int i = 0; i < tempDeck.length; i++  )
		{
			System.out.println("ID = " + tempDeck[i]);
		}
	}
}
