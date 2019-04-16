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

	BASE(204, 0),
	FIGURE(408, 0),
	MANIPULATION(612, 0);

	// ===================================
	// ATTRIBUTES
	// ===================================

	public int X ;
	public int Y;

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


}