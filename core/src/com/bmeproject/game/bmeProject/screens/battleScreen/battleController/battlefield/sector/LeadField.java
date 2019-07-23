package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;

public class LeadField extends RingField
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public LeadField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public RingField givePreviousField()
	{
		return ((Sector)FIELD_USER).givePreviousSector().giveEndField();
	}

	@Override public RingField giveNextField()
	{
		return ((Sector)FIELD_USER).giveEntryField();
	}
}
