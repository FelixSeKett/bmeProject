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

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckController() { }

	// ===================================
	// METHODS
	// ===================================

	private void init()
	{
		LinkedList<Card> testList = new LinkedList<>();
		for (int i = 0; i < 10; i++)
		{
			testList.add(new Card());
		}
		deckArea = new Area(100f, 100f, 0f, 0f, 20f, 200f, 0f, -100f, 50f, 0, testList, 6);
		deckArea.update();
	}

	public void update(float DeltaTime)
	{
		deckArea.update();
	}

	//initObjects + updateObjects + Controls
}
