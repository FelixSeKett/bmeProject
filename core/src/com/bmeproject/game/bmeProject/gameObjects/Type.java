package com.bmeproject.game.bmeProject.gameObjects;

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
		public Card createCard(){
			return new QuarterCard();
		}
	},
	FIGURE(408, 0) {
		@Override
		public Card createCard(){
			return new CreatureCard();
		}
	},
	MANIPULATION(612, 0) {
		@Override
		public Card createCard(){
			return new PhenomenonCard();
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

	abstract public Card createCard();
}