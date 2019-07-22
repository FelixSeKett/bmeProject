package com.bmeproject.game.bmeProject.gameObjects;

/**
 * Basis aller auf einem Screen darstellbaren Karten. Stellt alle Attribute und Funktionen bereit, die zur formellen
 * Darstellung (Grafik, Akustik) nötig sind. Werden durch
 * <p>
 * {@link DeckCard} für die jeweiligen screens erweitert.
 */

public class Card
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final int    ID;
	public final String NAME;
	public final String ILLUSTRATION_FILE_PATH;
	public final String ILLUSTRATION_FILE_PATH_SMALL;
	public final Type   TYPE;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Card( int id, String name, String illustrationFilePath, Type type )
	{
		ID = id;
		NAME = name;
		ILLUSTRATION_FILE_PATH = "core/assets/visuals/cards/large/" + illustrationFilePath;
		ILLUSTRATION_FILE_PATH_SMALL = "core/assets/visuals/cards/small/" + illustrationFilePath;
		TYPE = type;
	}


}
