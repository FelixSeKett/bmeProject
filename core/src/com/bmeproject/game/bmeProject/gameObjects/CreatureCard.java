package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.Player;

public class CreatureCard extends BattleCard
{

	// default constructor
	public CreatureCard(Player owner, Card card)
	{
		super(owner, card);
	}

	@Override public void activate()
	{
		System.out.println("Ich bin eine Kreatur und greife an!");
	}
}
