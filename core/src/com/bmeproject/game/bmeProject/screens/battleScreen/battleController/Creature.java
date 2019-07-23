package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

import java.util.ArrayList;

public class Creature extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Creature(Player owner, Card card)
	{
		super(owner, card);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public int giveDefaultHitpoints()
	{
		return 2;
	}

	@Override public void getActivated()
	{
		Zone             currentZone = giveCurrentZone();
		ArrayList<Field> fields      =
				PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveRingwiseOrderedFieldsOfZone(currentZone);

		// Debug
		for (Field field : fields) {
			Gdx.app.log(toString(), "Field " + ((Sector)field.FIELD_USER).FIELDS.indexOf(field) + " in Sektor " +
					PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveIndexOfSector((Sector)field.FIELD_USER));
			if (field.giveCards().size() > 0) {
				Gdx.app.log(toString(), "Karten: " + field.giveCards());
			}
		}

		int index = (fields.indexOf(giveCurrentField()) + 1);
		Gdx.app.log(toString(), "Iterations begin at " + giveName() + ": " + index);
		Gdx.app.log(toString(), "Iterations von " + giveName() + ": " + (fields.size() - index));

		for (int i = index; i < fields.size(); i++) {
			ArrayList<BattleCard> fieldCards = fields.get(i).giveCards();
			if (fieldCards.size() > 0) {
				BattleCard potentialTarget = fieldCards.get(0);
				Gdx.app.log(toString(), "Ziel von " + giveName() + ": " + potentialTarget.giveName());

				if (potentialTarget.giveCommander() != commander) {
					attack(potentialTarget);
					return;
				}
			}
		}
	}

	@Override public void getDestroyed()
	{
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addBattleCard(this);
	}

	private void attack(BattleCard battleCard)
	{
		battleCard.takeDamage();
	}
}