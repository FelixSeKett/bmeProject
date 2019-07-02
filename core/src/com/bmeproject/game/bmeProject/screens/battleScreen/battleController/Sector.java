package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;
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

	@Override public boolean hasSelectedACard(boolean selected)
	{
		return false;
	}

	@Override public BattleCard giveLastClickedBattleCard()
	{
		return BATTLEFIELD.giveLastClickedBattleCard();
	}

	@Override public void drawCardToField()
	{

	}

	// TODO: Liste ist noch nicht nach Strömungsregeln
	public ArrayList<BattleCard> giveSortedOuterBattleCards(Compass compass)
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();
		for (Field field : FIELDS) {
			// Implementierte Strömungsregel:

			if (compass.getCurrentStream() == Stream.COUNTERCLOCKWISE) {
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
