package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Quarter;


import java.util.ArrayList;

/**
 * Achtung: von den insgesamt 4 Fields, die diese Klasse beinhaltet, ist das erste immer das QuarterField.
 */
public class Sector implements iFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield BATTLEFIELD;

	// Für Merge-Prozess: die Fields sollen in der ArrayList organisiert sein - nicht separat!
	private final ArrayList<Field> FIELDS;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Sector(Battlefield battlefield, Vector2 field1Vector, Vector2 field2Vector, Vector2 field3Vector,
			Vector2 quarterFieldVector)
	{
		BATTLEFIELD = battlefield;
		Stage stage = battlefield.giveStage();
		FIELDS = new ArrayList<Field>();
		FIELDS.add(new Field(this, stage, quarterFieldVector, false));
		FIELDS.add(new Field(this, stage, field1Vector, false));
		FIELDS.add(new Field(this, stage, field2Vector, true));
		FIELDS.add(new Field(this, stage, field3Vector, false));
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
	public Field giveQuarterField()
	{
		return FIELDS.get(0);
	}

	// TODO: Liste ist noch nicht nach Strömungsregeln
	public ArrayList<BattleCard> giveSortedOuterBattleCards()
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();
		for (Field field : FIELDS) {
			battleCards.addAll(field.giveCards());
		}
		return battleCards;
	}

	public BattleCard giveQuarter()
	{
		return FIELDS.get(0).giveCards().get(0);
	}
}
