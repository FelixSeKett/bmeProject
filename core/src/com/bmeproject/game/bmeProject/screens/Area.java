package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.LinkedList;

public class Area extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final float            PILE_X;
	private final float            PILE_Y;
	private final float            PILE_OFFSET_X;
	private final float            PILE_OFFSET_Y;
	private final float            CARD_OFFSET_X;
	private final float            CARD_OFFSET_Y;
	private final LinkedList<Card> CARDS;
	private final int              CARDS_PER_PILE;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Area(float x, float y, float width, float height, float pileX, float pileY, float pileOffsetX,
			float pileOffsetY, float cardOffsetX, float cardOffsetY, LinkedList<Card> cards, int cardsPerPile)
	{
		setBounds(x, y, width, height);
		PILE_X = pileX;
		PILE_Y = pileY;
		PILE_OFFSET_X = pileOffsetX;
		PILE_OFFSET_Y = pileOffsetY;
		CARD_OFFSET_X = cardOffsetX;
		CARD_OFFSET_Y = cardOffsetY;
		CARDS = cards;
		CARDS_PER_PILE = cardsPerPile;
	}

	// ===================================
	// METHODS
	// ===================================

	public void update()
	{
		for (int i = 0, j = 0; i < CARDS.size(); i++)
		{
			if (i % CARDS_PER_PILE == 0)
			{
				j++;
			}
			float x = PILE_X + i * CARD_OFFSET_X + (j - 1) * PILE_OFFSET_X;
			float y = PILE_Y + i * CARD_OFFSET_Y + (j - 1) * PILE_OFFSET_Y;
			CARDS.get(i).setPosition(x, y);
		}
	}

	public void addCard(Card cardToAdd)
	{
		CARDS.add(cardToAdd);
		update();
	}

	public void removeCard(Card cardToRemove)
	{
		CARDS.remove(cardToRemove);
		update();
	}
}
