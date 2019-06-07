package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;

public class Sector implements IFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield BATTLEFIELD;
	private final Field       SPOT_1;
	private final Field       SPOT_2;
	private final Field       SPOT_3;
	private final Field       QUARTER_SPOT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Sector(Battlefield battlefield, Vector2 spot1, Vector2 spot2, Vector2 spot3, Vector2 quarterSpot)
	{
		BATTLEFIELD = battlefield;
		SPOT_1 = new Field(this, spot1.x, spot1.y);
		SPOT_2 = new Field(this, spot2.x, spot2.y);
		SPOT_3 = new Field(this, spot3.x, spot3.y);
		QUARTER_SPOT = new Field(this, quarterSpot.x, quarterSpot.y);
	}

	// ===================================
	// ATTRIBUTES
	// ===================================

	@Override public Stage giveStage()
	{
		return BATTLEFIELD.giveStage();
	}

	@Override public float giveRotation()
	{
		return BATTLEFIELD.giveRotation();
	}
}
