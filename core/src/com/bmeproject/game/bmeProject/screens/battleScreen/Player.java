package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;
	private final float            ROTATION;
	private final Area             AREA;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Player(BattleController battleController, float rotation)
	{
		BATTLE_CONTROLLER = battleController;
		ROTATION = rotation;
		AREA = new Area(this);
	}

	// ===================================
	// METHODS
	// ===================================

	public Stage giveStage()
	{
		return BATTLE_CONTROLLER.giveStage();
	}

	public float giveRotation()
	{
		return ROTATION;
	}
}
