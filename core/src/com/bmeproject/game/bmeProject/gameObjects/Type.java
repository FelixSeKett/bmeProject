package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Creature;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Phenomenon;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Quarter;

/**
 * Gibt eine Auswahl aller drei möglichen Kartentypen.
 */
public enum Type
{
	// ===================================
	// ENTRIES
	// ===================================

	QUARTER {
		@Override public BattleCard createBattleCard(Player owner, Card card)
		{
			return new Quarter(owner, card);
		}
	},
	CREATURE {
		@Override public BattleCard createBattleCard(Player owner, Card card)
		{
			return new Creature(owner, card);
		}
	},
	PHENOMENON {
		@Override public BattleCard createBattleCard(Player owner, Card card)
		{
			return new Phenomenon(owner, card);
		}
	};

	// ===================================
	// METHODS
	// ===================================

	abstract public BattleCard createBattleCard(Player owner, Card card);
}