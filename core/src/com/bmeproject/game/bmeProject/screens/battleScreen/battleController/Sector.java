package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;

public class Sector
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
		Stage stage = battlefield.giveStage();
		SPOT_1 = new Field(stage, spot1);
		SPOT_2 = new Field(stage, spot2);
		SPOT_3 = new Field(stage, spot3);
		QUARTER_SPOT = new Field(stage, quarterSpot);
	}

	//method for debug
	public Field giveQuarterField(){
		return QUARTER_SPOT;
	}
}
