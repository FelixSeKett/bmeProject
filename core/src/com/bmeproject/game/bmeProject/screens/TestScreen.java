package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;

import java.util.ArrayList;

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
		System.out.println("CardType = " + card.getCardType().toString());


		deck = new Deck();
		System.out.println("Deck besteht aus " + deck.getSize() + " Karten");
		deck.addCardToDeck(5);


		for (int i = 0; i < deck.getSize(); i++)
		{
			int id = deck.getCardIdFromDeck(i);
			System.out.println("ID = " + deck.getCardIdFromDeck(i));

			String type = deck.getCardFromDeck(id).getCardType().toString();
			System.out.println("CardType = " + type);
		}

		deck.removeCardFromDeck(1);
		System.out.println("Deck besteht aus " + deck.getSize() + " Karten");

		for (int i = 0; i < deck.getSize(); i++  )
		{
			int id = deck.getCardIdFromDeck(i);
			System.out.println("ID = " + deck.getCardIdFromDeck(i));

		}
	}
}
