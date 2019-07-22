package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;

import java.util.ArrayList;

public abstract class FieldUser
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	protected final ArrayList<Field> FIELDS; // Muss aus Kapselungsgründen protected bleiben!

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public FieldUser()
	{
		FIELDS = new ArrayList<Field>();
	}

	// ===================================
	// METHODS
	// ===================================

	public abstract BattleController giveBattleController();

	public abstract Field giveCurrentFieldOfBattleCard(BattleCard battleCard);

	public abstract Player giveCommander();

	/**
	 * Der Spieler soll stattfindende Animationen abwarten müssen, bevor sein Input erneut ausgewertet wird. Das soll
	 * dazu führen, dass sich nicht mehrere Animationen überschneiden; grafische Werte so vielleicht
	 * durcheinanderkommen oder die FrameRate in die Knie geht. Diese Methode gibt an, ob noch Animationen
	 * abgewickelt werden oder das Spiel bereit für die nächste Eingabe samt nächster Animation ist.
	 *
	 * @return Gibt true zurück, wenn keine Animation mehr abgewickelt wird.
	 */
	public boolean isGoodToGo()
	{
		for (Field field : FIELDS) {
			for (BattleCard battleCard : field.giveCards()) {
				if (battleCard.hasActions()) {
					return false;
				}
			}
		}
		return true;
	}
}
