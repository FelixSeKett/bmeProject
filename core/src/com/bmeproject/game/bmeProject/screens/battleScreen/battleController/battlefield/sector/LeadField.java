package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.iFieldable;

public class LeadField extends RingField
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public LeadField(iFieldable fieldable, Vector2 position)
	{
		super(fieldable, position);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public Field givePreviousField()
	{
		return ((Sector)FIELDABLE).givePreviousSector().giveEndField();
	}

	@Override public Field giveNextField()
	{
		return ((Sector)FIELDABLE).giveEntryField();
	}
}
