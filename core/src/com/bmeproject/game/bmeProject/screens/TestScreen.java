package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
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
	private static final String TAG = TestScreen.class.getName();

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TestScreen(BMEProject bmeProject)
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


		card = BMEProject.allCards.get(1);
		stage.addActor(card);

		deck = new Deck();
		deck.addCardToDeck(5);


		for (int i = 0; i < deck.getSize(); i++)
		{
			int id = deck.getCardIdFromDeck(i);
			//System.out.println("ID = " + deck.getCardIdFromDeck(i));

			String type = deck.getCardFromDeck(id).getCardType().toString();
			//System.out.println("CardType = " + type);
		}

		deck.removeCardFromDeck(1);

		for (int i = 0; i < deck.getSize(); i++  )
		{
			int id = deck.getCardIdFromDeck(i);
			//System.out.println("ID = " + deck.getCardIdFromDeck(i));

		}
	}
}
