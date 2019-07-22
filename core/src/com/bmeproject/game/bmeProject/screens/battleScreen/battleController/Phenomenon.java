package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;

public class Phenomenon extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Phenomenon(Player owner, Card card)
	{
		super(owner, card);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public int giveDefaultHitpoints()
	{
		return 1;
	}

	@Override public void getActivated()
	{
	}

	@Override public void getDestroyed()
	{
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addBattleCard(this);
	}
}
