package com.bmeproject.game.bmeProject.battleScreen.player;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.battleScreen.Player;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

public abstract class BattleCard extends Card
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Player PLAYER;
	private final int    STRENGTH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	BattleCard(Entity entity, Player player)
	{
		super(entity);
		PLAYER = player;
		STRENGTH = entity.getStrength();
	}
}
