package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.math.Vector2;

public enum Party
{
	// ===================================
	// ENUMERATIONS
	// ===================================

	ALLY {
		double screenWidth=1920;
		double screenHeight=1080;

		@Override public float giveRotation()
		{
			return 0;
		}

		@Override public Vector2 giveSupplyVector()
		{
			return new Vector2(1300, 95);
		}

		@Override public Vector2 giveGraveyardVector()
		{
			return new Vector2(551, 95);
		}

		@Override public Vector2 giveHandVector()
		{
			return new Vector2(840, 20);
		}
	},
	ENEMY {
		@Override public float giveRotation()
		{
			return 180;
		}

		@Override public Vector2 giveSupplyVector()
		{
			return new Vector2(552, 876);
		}

		@Override public Vector2 giveGraveyardVector()
		{
			return new Vector2(1302, 876);
		}

		@Override public Vector2 giveHandVector()
		{
			return new Vector2(747, 948);
		}
	};

	// ===================================
	// METHODS
	// ===================================

	public abstract float giveRotation();

	public abstract Vector2 giveSupplyVector();

	public abstract Vector2 giveGraveyardVector();

	public abstract Vector2 giveHandVector();
}
