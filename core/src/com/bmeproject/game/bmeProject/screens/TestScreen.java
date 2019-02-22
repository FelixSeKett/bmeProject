package com.bmeproject.game.bmeProject.screens;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.gameObjects.Card;

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
