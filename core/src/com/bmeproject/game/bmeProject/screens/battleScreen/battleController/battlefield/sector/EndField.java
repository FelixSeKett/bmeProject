package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;

public class EndField extends RingField
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EndField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public RingField givePreviousField()
	{
		return ((Sector)FIELD_USER).giveEntryField();
	}

	@Override public RingField giveNextField()
	{
		return ((Sector)FIELD_USER).giveNextSector().giveLeadField();
	}
}
