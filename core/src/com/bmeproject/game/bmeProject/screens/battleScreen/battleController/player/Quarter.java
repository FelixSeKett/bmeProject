package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;

public class Quarter extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Quarter(Player owner, Card card)
	{
		super(owner, card);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void activate()
	{
		Gdx.app.log(toString(), "Ich bin ein Quatier und dürfte garnicht reden können!");
	}
}
