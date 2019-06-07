package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.gameObjects.Card;

public class BattleCard extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Player OWNER;
	private final Card   CARD;
	private       Player commander;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleCard(Player owner, Card card)
	{
		OWNER = owner;
		CARD = card;
		commander = owner;
	}

	// ===================================
	// METHODS
	// ===================================

	public Player giveOwner()
	{
		return OWNER;
	}

	public Player giveCommander()
	{
		return commander;
	}
}
