package com.bmeproject.game.bmeProject.screens.battleScreen;

public enum Party
{
	// ===================================
	// ENUMERATIONS
	// ===================================

	ALLY {
		@Override public float giveRotation()
		{
			return 0;
		}
	},
	ENEMY {
		@Override public float giveRotation()
		{
			return 180;
		}
	};

	// ===================================
	// METHODS
	// ===================================

	public abstract float giveRotation();
}
