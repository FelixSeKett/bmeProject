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

		//		System.out.println("TEST SCREEN SHOWN");
		//
		//		card = BMEProject.allCards.get(1);
		//		getStage().addActor(card);
		//		System.out.println("CardType = " + card.getCardType().toString());
		//
		//
		//		deck = new Deck();
		//		System.out.println("Deck besteht aus " + deck.getSize() + " Karten");
		//		deck.addCardToDeck(5);
		//
		//
		//		for (int i = 0; i < deck.getSize(); i++) {
		//			int id = deck.getCardIdFromDeck(i);
		//			System.out.println("ID = " + deck.getCardIdFromDeck(i));
		//
		//			String type = deck.getCardFromDeck(id).getCardType().toString();
		//			System.out.println("CardType = " + type);
		//		}
		//
		//		deck.removeCardFromDeck(1);
		//		System.out.println("Deck besteht aus " + deck.getSize() + " Karten");
		//
		//		for (int i = 0; i < deck.getSize(); i++) {
		//			int id = deck.getCardIdFromDeck(i);
		//			System.out.println("ID = " + deck.getCardIdFromDeck(i));
		//
		//		}
	}

	@Override protected Controller createController()
	{
		return new Controller();
	}
}
