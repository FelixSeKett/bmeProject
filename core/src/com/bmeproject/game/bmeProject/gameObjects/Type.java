package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Creature;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Phenomenon;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Quarter;

import java.util.ArrayList;

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

		@Override public ArrayList<Zone> giveActivatingZones()
		{
			ArrayList<Zone> activatingZones = new ArrayList<Zone>();
			activatingZones.add(Zone.GREEN);
			return activatingZones;
		}
	},
	CREATURE {
		@Override public BattleCard createBattleCard(Player owner, Card card)
		{
			return new Creature(owner, card);
		}

		@Override public ArrayList<Zone> giveActivatingZones()
		{
			ArrayList<Zone> activatingZones = new ArrayList<Zone>();
			activatingZones.add(Zone.RED);
			activatingZones.add(Zone.BLUE);
			return activatingZones;
		}
	},
	PHENOMENON {
		@Override public BattleCard createBattleCard(Player owner, Card card)
		{
			return new Phenomenon(owner, card);
		}

		@Override public ArrayList<Zone> giveActivatingZones()
		{
			ArrayList<Zone> activatingZones = new ArrayList<Zone>();
			activatingZones.add(Zone.RED);
			activatingZones.add(Zone.GREEN);
			activatingZones.add(Zone.BLUE);
			return activatingZones;
		}
	};

	// ===================================
	// METHODS
	// ===================================

	public abstract BattleCard createBattleCard(Player owner, Card card);

	public abstract ArrayList<Zone> giveActivatingZones();
}