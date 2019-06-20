package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.*;

/**
 * Gibt eine Auswahl aller drei möglichen Kartentypen, die jeweils Koordinaten für die entsprechende TextureRegion
 * der obersten visuellen Ebene einer dargestellten Karte mitliefern.
 */
public enum Type
{
	// ===================================
	// ENTRIES
	// ===================================

	QUARTER(204, 0) {
		@Override public BattleCard createCard(Player owner, Card card)
		{
			return new Quarter(owner, card);
		}
	},
	CREATURE(408, 0) {
		@Override public BattleCard createCard(Player owner, Card card)
		{
			return new Creature(owner, card);
		}
	},
	PHENOMENON(612, 0) {
		@Override public BattleCard createCard(Player owner, Card card)
		{
			return new Phenomenon(owner, card);
		}
	};
	
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final int X;
	public final int Y;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Type(int x, int y)
	{
		X = x;
		Y = y;
	}

	// ===================================
	// METHODS
	// ===================================

	abstract public BattleCard createCard(Player owner, Card card);
}