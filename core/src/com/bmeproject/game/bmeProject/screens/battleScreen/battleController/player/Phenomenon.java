package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;

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

	@Override public void activate()
	{
		Gdx.app.log(toString(), "Ich bin ein Phänomen und gehöre zu den coolen Kids!");
	}
}
