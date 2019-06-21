package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;

public class Battlefield
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;
	private final Sector           SECTOR_1;
	private final Sector           SECTOR_2;
	private final Sector           SECTOR_3;
	private final Sector           SECTOR_4;
	private final Sector           SECTOR_5;
	private final Sector           SECTOR_6;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Battlefield(BattleController battleController)
	{
		BATTLE_CONTROLLER = battleController;
		SECTOR_1 = new Sector(this, new Vector2(258f, 167f), new Vector2(294f, 167f), new Vector2(294f, 118f),
				new Vector2(336f, 175f));
		SECTOR_2 = new Sector(this, new Vector2(350f, 84f), new Vector2(385f, 90f), new Vector2(420f, 84f),
				new Vector2(385f, 147f));
		SECTOR_3 = new Sector(this, new Vector2(476f, 118), new Vector2(476f, 167f), new Vector2(512f, 167f),
				new Vector2(433f, 175f));
		SECTOR_4 = new Sector(this, new Vector2(512f, 241f), new Vector2(476f, 241f), new Vector2(476f, 290f),
				new Vector2(433f, 232f));
		SECTOR_5 = new Sector(this, new Vector2(420f, 324f), new Vector2(385f, 317f), new Vector2(350f, 324f),
				new Vector2(385f, 261f));
		SECTOR_6 = new Sector(this, new Vector2(294f, 290f), new Vector2(294f, 241f), new Vector2(258f, 241f),
				new Vector2(336f, 232f));
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
		return 0;
	}

	//methods for debug
	public Sector giveSectorOne(){
		return SECTOR_1;
	}


}
