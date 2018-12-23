package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.profile.Deck;
import com.bmeproject.game.bmeProject.testScreen.TestCard;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends TheatricalScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;
	private Deck deck;

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

		card = new Card();
		card.initialize(2);
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
