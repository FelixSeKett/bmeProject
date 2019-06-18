package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.Player;

/**
 * Gibt eine Auswahl aller drei möglichen Kartentypen, die jeweils Koordinaten für die entsprechende TextureRegion
 * der obersten visuellen Ebene einer dargestellten Karte mitliefern.
 */
public enum Type
{
	// ===================================
	// ENTRIES
	// ===================================

	BASE(204, 0) {

		@Override
		public BattleCard createCard(Player owner, Card card){
			return new QuarterCard(owner, card);
		}
	},
	FIGURE(408, 0) {
		@Override
		public BattleCard createCard(Player owner, Card card){
			return new CreatureCard(owner, card);
		}
	},
	MANIPULATION(612, 0) {
		@Override
		public BattleCard createCard(Player owner, Card card){
			return new PhenomenonCard(owner, card);
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