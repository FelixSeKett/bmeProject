package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.EndField;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.EntryField;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.LeadField;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Achtung: von den insgesamt 4 Fields, die diese Klasse beinhaltet, ist das erste immer das QuarterField.
 */
public class Sector extends FieldUser
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final Battlefield BATTLEFIELD;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Sector(Battlefield battlefield, Vector2 field1Vector, Vector2 field2Vector, Vector2 field3Vector,
			Vector2 quarterFieldVector)
	{
		super();
		BATTLEFIELD = battlefield;
		FIELDS.add(new Field(this, quarterFieldVector));
		FIELDS.add(new LeadField(this, field1Vector));
		FIELDS.add(new EntryField(this, field2Vector));
		FIELDS.add(new EndField(this, field3Vector));
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

	public ArrayList<Field> giveFields()
	{
		return new ArrayList<Field>(FIELDS);
	}

	public Sector givePreviousSector()
	{
		return BATTLEFIELD.givePreviousSectorOf(this);
	}

	public Sector giveNextSector()
	{
		return BATTLEFIELD.giveNextSectorOf(this);
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

	public Field giveQuarterField()
	{
		return FIELDS.get(0);
	}

	public LeadField giveLeadField()
	{
		return (LeadField)FIELDS.get(1);
	}

	public EntryField giveEntryField()
	{
		return (EntryField)FIELDS.get(2);
	}

	public EndField giveEndField()
	{
		return (EndField)FIELDS.get(3);
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
