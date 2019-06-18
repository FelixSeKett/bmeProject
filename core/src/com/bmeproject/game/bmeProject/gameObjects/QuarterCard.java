package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.Player;

public class QuarterCard extends BattleCard
{
	// default constructor
	public QuarterCard(Player owner, Card card)
	{
		super(owner, card);
	}

	@Override public void activate()
	{
		System.out.println("Ich bin ein Quatier und dürfte garnicht reden können!");
	}
}
