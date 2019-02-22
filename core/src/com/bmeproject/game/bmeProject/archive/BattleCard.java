package com.bmeproject.game.bmeProject.archive;

import com.bmeproject.game.bmeProject.dataAccess.CardContainer;
import com.bmeproject.game.bmeProject.gameObjects.Card;

public abstract class BattleCard extends Card
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final CardContainer CardContainer;
	private final int    STRENGTH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	BattleCard(CardContainer cardContainer)
	{
		CardContainer = cardContainer;
		STRENGTH = cardContainer.getStrength();
	}
}
