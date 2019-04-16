package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Area;
import com.bmeproject.game.bmeProject.screens.Controller;

import java.util.ArrayList;

public class DeckController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Area deckArea;
	private Area collectionArea;

	// ===================================
	// METHODS
	// ===================================

	@Override public void init()
	{
		createDeckArea();
		createCollectionArea();
	}

	@Override public void update(float delta)
	{
		deckArea.update();
		collectionArea.update();
	}

	private void createDeckArea()
	{
		ArrayList<Card> testList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			testList.add(new Card());
		}
		deckArea = new Area(100f, 100f, 0f, 0f, 20f, 200f, 0f, -100f, 50f, 0, testList, 6);
		deckArea.update();
	}

	private void createCollectionArea()
	{
		ArrayList<Card> testList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			testList.add(new Card());
		}
		collectionArea = new Area(300f, 100f, 0f, 0f, 20f, 200f, 0f, -100f, 50f, 0, testList, 3);
		collectionArea.update();
	}

	//initObjects + updateObjects + Controls

}
