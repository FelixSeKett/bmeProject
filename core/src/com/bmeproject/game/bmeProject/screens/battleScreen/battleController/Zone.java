package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

public enum Zone
{
	// ===================================
	// ENTRIES
	// ===================================

	RED {
		@Override public int getColorIndex()
		{
			return 0;
		}
	},
	GREEN {
		@Override public int getColorIndex()
		{
			return 1;
		}
	},
	BLUE {
		@Override public int getColorIndex()
		{
			return 2;
		}
	};

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

	public abstract int getColorIndex();

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
