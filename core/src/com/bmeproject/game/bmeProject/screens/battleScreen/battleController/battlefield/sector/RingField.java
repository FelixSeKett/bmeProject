package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;

public abstract class RingField extends Field
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public RingField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
	}

	// ===================================
	// METHODS
	// ===================================

	public abstract RingField givePreviousField();

	public abstract RingField giveNextField();

	/**
	 * Verschiebt all seine Karten auf das je nach Strömungsrichtung nächstgelegene Feld innerhalb des Feldringes
	 */
	public void moveContentStreamwise()
	{
		if (FIELD_USER.giveBattleController().BATTLEFIELD.COMPASS.giveStream() == Stream.CLOCKWISE) {
			RingField previousEmptyField = givePreviousEmptyField();
			if (previousEmptyField != null) {
				previousEmptyField.addBattleCards(giveCards());
			}
		} else {
			RingField nextEmptyField = giveNextEmptyField();
			if (nextEmptyField != null) {
				nextEmptyField.addBattleCards(giveCards());
			}
		}
	}

	private RingField givePreviousEmptyField()
	{
		RingField previousField = givePreviousField();
		if (previousField != this) {
			if (previousField.giveCards().isEmpty()) {
				return previousField;
			} else {
				return previousField.givePreviousEmptyField();
			}
		} else {
			return null;
		}
	}

	private RingField giveNextEmptyField()
	{
		RingField nextField = giveNextField();
		if (nextField != this) {
			if (nextField.giveCards().isEmpty()) {
				return nextField;
			} else {
				return nextField.giveNextEmptyField();
			}
		} else {
			return null;
		}
	}
}
