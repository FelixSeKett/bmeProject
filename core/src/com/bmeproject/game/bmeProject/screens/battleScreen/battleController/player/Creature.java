package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;

public class Creature extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Creature(Player owner, Card card)
	{
		super(owner, card, 2);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void activate()
	{
		Gdx.app.log(toString(), "Ich bin eine Kreatur und greife an!");
	}
}
