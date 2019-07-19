package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

public enum Zone
{
	// ===================================
	// ENTRIES
	// ===================================

	RED,
	GREEN,
	BLUE;

	// ===================================
	// ATTRIBUTES
	// ===================================

	private boolean activated;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Zone()
	{
		activated = false;
	}

	// ===================================
	// ATTRIBUTES
	// ===================================

	public static Zone giveZoneByColorIndex(int index)
	{
		for (Zone zone : values()) {
			int ordinal = zone.ordinal() * 2;
			if (ordinal == index || ordinal == (index + 1)) {
				return zone;
			}
		}
		return null;
	}

	public boolean isActivated()
	{
		return activated;
	}

	public void activate()
	{
		activated = true;
	}

	public void deactivate()
	{
		activated = false;
	}
}
