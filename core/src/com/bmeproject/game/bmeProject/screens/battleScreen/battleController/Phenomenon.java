package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;

public class Phenomenon extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Phenomenon(Player owner, Card card)
	{
		super(owner, card, 1);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void getActivated()
	{
	}

	@Override public void getDestroyed()
	{
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addCard(this);
	}
}
