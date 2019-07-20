package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

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

	public abstract Field givePreviousField();

	public abstract Field giveNextField();

	/**
	 * Verschiebt all seine Karten auf das je nach Strömungsrichtung nächstgelegene Feld innerhalb des Feldringes
	 */
	public void moveContentStreamwise()
	{
		if (((Sector)FIELD_USER).BATTLEFIELD.COMPASS.giveStream() == Stream.CLOCKWISE) {
			moveContentToField(givePreviousField());
		} else {
			moveContentToField(giveNextField());
		}
	}

	/**
	 * Verschiebt all seine Karten auf das angegebene Field, wenn es sich dabei um ein anderes Field handelt.
	 *
	 * @param field Feld, auf das alle Karten verschoben werden sollen
	 */
	private void moveContentToField(Field field)
	{
		if (field != this) {
			if (field.giveCards().isEmpty()) {
				for (BattleCard battleCard : CARDS) {
					field.addCard(battleCard);
				}
			} else {
				moveContentStreamwise();
			}
		}
	}
}
