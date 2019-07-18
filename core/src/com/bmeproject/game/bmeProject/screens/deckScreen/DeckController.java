package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.Controller;

import java.util.ArrayList;

public class DeckController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Field deckField;
	private Field collectionField;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckController(SpriteBatch spriteBatch)
	{
		super(spriteBatch);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);
		createDeckArea();
		createCollectionArea();
	}

	@Override public void update(float delta)
	{
		super.update(delta);
		deckField.update();
		collectionField.update();
	}

	private void createDeckArea()
	{
		// Testzweck Anfang
		//		ArrayList<BattleCard> testList = new ArrayList<BattleCard>();
		//		for (int i = 0; i < 2; i++) {
		//			Card card = BMEProject.allCards.get(i + 1);
		//			stage.addActor(card);
		//			testList.add(card);
		//		}
		// Testzweck Ende

		//		deckField = new Field(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, 5f, testList, 10);
		//		System.out.println(testList);
		//		deckField.update();
	}

	private void createCollectionArea()
	{
		// Testzweck Anfang
		ArrayList<Card> testList = new ArrayList<Card>();
		for (int i = 0; i < 2; i++) {
			Card card = BMEProject.allCards.get(i + 3);
			//			stage.addActor(card);
			testList.add(card);
		}
		// Testzweck Ende

		//		collectionField = new Field(300f, 0f, 0f, 0f, 0f, 0f, 100f, 0f, 20f, 0f, testList, 2);
		collectionField.update();
	}
}
