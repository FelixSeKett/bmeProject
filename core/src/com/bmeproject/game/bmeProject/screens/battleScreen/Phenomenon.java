package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;

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

	@Override public void activate()
	{
		Gdx.app.log(toString(), "Ich bin ein Phänomen und gehöre zu den coolen Kids!");
	}
}
