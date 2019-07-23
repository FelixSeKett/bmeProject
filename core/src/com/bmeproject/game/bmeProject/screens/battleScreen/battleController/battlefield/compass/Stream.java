package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass;

import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.RingField;

import java.util.ArrayList;
import java.util.Collections;

public enum Stream
{
	// ===================================
	// ENTRIES
	// ===================================

	CLOCKWISE {
		@Override public int giveDirection()
		{
			return -1;
		}

		@Override public Stream giveOppositeStream()
		{
			return Stream.COUNTERCLOCKWISE;
		}

		@Override public void orderSectors(ArrayList<Sector> sectorsToOrder)
		{
			Collections.reverse(sectorsToOrder);
		}

		@Override public void orderRingFields(ArrayList<RingField> ringFieldsToOrder)
		{
			Collections.reverse(ringFieldsToOrder);
		}
	},

	COUNTERCLOCKWISE {
		@Override public int giveDirection()
		{
			return 1;
		}

		@Override public Stream giveOppositeStream()
		{
			return Stream.CLOCKWISE;
		}
	};

	// ===================================
	// METHODS
	// ===================================

	public abstract int giveDirection();

	public abstract Stream giveOppositeStream();

	public void orderSectors(ArrayList<Sector> sectorsToOrder)
	{
	}

	public void orderRingFields(ArrayList<RingField> ringFieldsToOrder)
	{
	}
}
