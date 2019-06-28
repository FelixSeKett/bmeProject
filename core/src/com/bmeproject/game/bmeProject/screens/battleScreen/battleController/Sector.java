package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public class Sector implements iFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield BATTLEFIELD;
	private final Field       SPOT_1;
	private final Field       SPOT_2;
	private final Field       SPOT_3;
	private final Field       QUARTER_SPOT;
	private BattleController BATTLECONTROLLER;


	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Sector(Battlefield battlefield, Vector2 spot1, Vector2 spot2, Vector2 spot3, Vector2 quarterSpot)
	{
		BATTLEFIELD = battlefield;
		Stage stage = battlefield.giveStage();
		SPOT_1 = new Field(this, stage, spot1, false);
		SPOT_2 = new Field(this, stage, spot2, true);
		SPOT_3 = new Field(this, stage, spot3, false);
		QUARTER_SPOT = new Field(this, stage, quarterSpot, false);
		BATTLECONTROLLER = BATTLEFIELD.getBATTLE_CONTROLLER();
	}

	public void setLastClickedBattleCard(BattleCard battleCard){
		BATTLEFIELD.giveLastClickedBattleCard();
	}

	@Override
	public boolean hasSelectedACard(boolean selected) {
		return false;
	}

	@Override
	public BattleCard giveLastClickedBattleCard(){
		return BATTLEFIELD.giveLastClickedBattleCard();
	}

	@Override
	public void drawCardToField(){

	}

	//method for debug
	public Field giveQuarterField(){
		return QUARTER_SPOT;
	}
}
