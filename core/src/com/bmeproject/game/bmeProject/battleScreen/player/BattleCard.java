package com.bmeproject.game.bmeProject.battleScreen.player;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

public abstract class BattleCard extends Card
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Entity ENTITY;
	private final int    STRENGTH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	BattleCard(Entity entity)
	{
		ENTITY = entity;
		STRENGTH = entity.getStrength();
	}
}
