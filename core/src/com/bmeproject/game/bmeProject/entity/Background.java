package com.bmeproject.game.bmeProject.entity;

/**
 * Gibt eine Auswahl aller möglichen Hintergrundtypen, die jeweils Koordinaten für die entsprechende TextureRegion
 * der untersten visuellen Ebene einer dargestellten Karte mitliefern.
 */
public enum Background
{
	// ===================================
	// ENTRIES
	// ===================================

	OPEN_WATER(408, 300),
	WRECK(612, 300),
	GROUND(816, 300);

	// ===================================
	// ATTRIBUTES
	// ===================================

	public final int X;
	public final int Y;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Background(int x, int y)
	{
		X = x;
		Y = y;
	}
}