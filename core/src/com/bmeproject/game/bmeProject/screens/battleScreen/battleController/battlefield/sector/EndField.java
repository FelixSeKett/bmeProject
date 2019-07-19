package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.iFieldable;

public class EndField extends RingField
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EndField(iFieldable fieldable, Vector2 position)
	{
		super(fieldable, position);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public Field givePreviousField()
	{
		return ((Sector)FIELDABLE).giveEntryField();
	}

	@Override public Field giveNextField()
	{
		return ((Sector)FIELDABLE).giveNextSector().giveLeadField();
	}
}
