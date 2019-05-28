package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;

public class Sector implements iFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Area  AREA;
	private final Field SPOT_1;
	private final Field SPOT_2;
	private final Field SPOT_3;
	private final Field QUARTER_SPOT;

	// ===================================
	// ATTRIBUTES
	// ===================================

	public Sector(Area area, Vector2 spot1, Vector2 spot2, Vector2 spot3, Vector2 quarterSpot)
	{
		AREA = area;
		SPOT_1 = new Field(this, spot1.x, spot1.y);
		SPOT_2 = new Field(this, spot2.x, spot2.y);
		SPOT_3 = new Field(this, spot3.x, spot3.y);
		QUARTER_SPOT = new Field(this, quarterSpot.x, quarterSpot.y);
	}

	// ===================================
	// ATTRIBUTES
	// ===================================

	public Stage giveStage()
	{
		return AREA.giveStage();
	}

	public float giveRotation()
	{
		return AREA.giveRotation();
	}
}
