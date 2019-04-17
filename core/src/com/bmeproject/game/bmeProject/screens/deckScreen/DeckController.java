package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.InputAdapter;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Area;

import java.util.LinkedList;

public class DeckController extends InputAdapter
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Area deckArea;
	private Area collectionArea;

	// ===================================
	// METHODS
	// ===================================

	private void init()
	{
		createDeckArea();
		createCollectionArea();
	}

	private void createDeckArea()
	{
		LinkedList<Card> testList = new LinkedList<Card>();
		for (int i = 0; i < 10; i++) {
			testList.add(new Card());
		}
		deckArea = new Area(100f, 100f, 0f, 0f, 20f, 200f, 0f, -100f, 50f, 0, testList, 6);
		deckArea.update();
	}

	private void createCollectionArea()
	{
		LinkedList<Card> testList = new LinkedList<Card>();
		for (int i = 0; i < 10; i++) {
			testList.add(new Card());
		}
		collectionArea = new Area(300f, 100f, 0f, 0f, 20f, 200f, 0f, -100f, 50f, 0, testList, 3);
		collectionArea.update();
	}

	public void update(float DeltaTime)
	{
		deckArea.update();
		collectionArea.update();
	}

	//initObjects + updateObjects + Controls
}
