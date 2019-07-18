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
		FIELDS.add(new Field(this, quarterFieldVector));
		FIELDS.add(new Field(this, field1Vector));
		FIELDS.add(new EntryField(this, field2Vector));
		FIELDS.add(new Field(this, field3Vector));
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

	@Override public Player giveCommander()
	{
		return giveQuarter().giveCommander();
	}

	public Zone giveCurrentZone()
	{
		return BATTLEFIELD.giveZoneOfSector(this);
	}

	public boolean hasBattleCard(BattleCard battleCardToHave)
	{
		for (Field field : FIELDS) {
			if (field.giveCards().contains(battleCardToHave)) {
				return true;
			}
		}
		return false;
	}

	// TODO: Liste ist noch nicht nach Strömungsregeln sortiert
	public ArrayList<BattleCard> giveSortedOuterBattleCards(Stream stream)
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();
		for (Field field : FIELDS) {
			// Implementierte Strömungsregel:

			if (stream == Stream.COUNTERCLOCKWISE) {
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
