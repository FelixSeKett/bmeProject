package com.bmeproject.game.bmeProject;

import com.bmeproject.game.bmeProject.dataAccess.Entity;
import com.bmeproject.game.bmeProject.gameObjects.Card;

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
