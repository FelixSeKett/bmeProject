package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Achtung: von den insgesamt 4 Fields, die diese Klasse beinhaltet, ist das erste immer das QuarterField.
 */
public class Sector implements iFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield      BATTLEFIELD;
	private final ArrayList<Field> FIELDS; // Muss aus Kapselungsgründen private bleiben!

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Sector(Battlefield battlefield, Vector2 field1Vector, Vector2 field2Vector, Vector2 field3Vector,
			Vector2 quarterFieldVector)
	{
		BATTLEFIELD = battlefield;
		FIELDS = new ArrayList<Field>();
		FIELDS.add(new Field(this, quarterFieldVector, false));
		FIELDS.add(new Field(this, field1Vector, false));
		FIELDS.add(new Field(this, field2Vector, true));
		FIELDS.add(new Field(this, field3Vector, false));
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public BattleController giveBattleController()
	{
		return BATTLEFIELD.BATTLE_CONTROLLER;
	}

	@Override public Field giveCurrentFieldOfBattleCard(BattleCard battleCard)
	{
		for (Field field : FIELDS) {
			if (field.giveCards().contains(battleCard)) {
				return field;
			}
		}
		return null;
	}

	// TODO: Liste ist noch nicht nach Strömungsregeln
	public ArrayList<BattleCard> giveSortedOuterBattleCards(Compass compass)
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();
		for (Field field : FIELDS) {
			// Implementierte Strömungsregel:

			if (compass.giveCurrentStream() == Stream.COUNTERCLOCKWISE) {
				battleCards.addAll(field.giveCards());
			} else {
				battleCards.addAll(reverseCardOrder(field.giveCards()));
			}
		}
		return battleCards;
	}

	// Methode, um die Karten in einer Liste basierend auf der Strömungsrichtung vorwärts oder rückwärts anzuordnen.
	private ArrayList<BattleCard> reverseCardOrder(ArrayList<BattleCard> list)
	{
		Collections.reverse(list);
		return list;
	}

	public BattleCard giveQuarter()
	{
		return FIELDS.get(0).giveCards().get(0);
	}
}
