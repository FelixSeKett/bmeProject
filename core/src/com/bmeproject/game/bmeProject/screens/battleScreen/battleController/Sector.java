package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Quarter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Achtung: von den insgesamt 4 Fields, die diese Klasse beinhaltet, ist das erste immer das QuarterField.
 */
public class Sector
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
		FIELDS.add(new Field(stage, quarterFieldVector));
		FIELDS.add(new Field(stage, field1Vector));
		FIELDS.add(new Field(stage, field2Vector));
		FIELDS.add(new Field(stage, field3Vector));
	}

	//method for debug
	public Field giveQuarterField()
	{
		return FIELDS.get(0);
	}

	// TODO: Liste ist noch nicht nach Strömungsregeln
	public ArrayList<BattleCard> giveSortedOuterBattleCards(Compass compass)
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();

		for (Field field : FIELDS) {
			// Implementierte Strömungsregel:

			if(compass.getCurrentStream() == Stream.COUNTERCLOCKWISE) {
				battleCards.addAll(field.giveCards());
			} else {
				battleCards.addAll(reverseCardOrder(field.giveCards()));
			}
		}
		return battleCards;
	}

	// Methode, um die Karten in einer Liste basierend auf der Strömungsrichtung vorwärts oder rückwärts anzuordnen.
	private ArrayList<BattleCard> reverseCardOrder(ArrayList<BattleCard> list) {
		Collections.reverse(list);
		return list;
	}

	public BattleCard giveQuarter()
	{
		return FIELDS.get(0).giveCards().get(0);
	}
}
