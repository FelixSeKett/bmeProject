package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass;

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

}
