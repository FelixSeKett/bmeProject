package com.bmeproject.game.bmeProject.entity;

/**
 * Gibt eine Auswahl aller möglichen Hintergrundtypen, die jeweils ein fertiges Design für die unterste visuelle Ebene
 * einer dargestellten Karte mitliefern.
 */
public enum Background
{
	// ===================================
	// ENTRIES
	// ===================================

	OPEN_WATER("filepath"),
	WRECK("filepath"),
	GROUND("filepath");

	// ===================================
	// ATTRIBUTES
	// ===================================

	public final String TEXTURE_FILE_PATH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Background(String textureFilePath)
	{
		TEXTURE_FILE_PATH = textureFilePath;
	}
}
