package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
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
		Zone currentZone = giveCurrentZone();
		ArrayList<Field> fields = PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveRingwiseOrderedFieldsOfZone(currentZone);
		for (int i = (fields.indexOf(giveCurrentField()) + 1); i < fields.size(); i++) {
			ArrayList<BattleCard> fieldCards = fields.get(i).giveCards();
			if (fieldCards.size() > 0) {
				BattleCard potentialTarget = fieldCards.get(0);
				if (potentialTarget.giveCommander() != commander) {
					attack(potentialTarget);
					return;
				}
			}
		}
	}

	@Override public void getDestroyed()
	{
		BMEProject.destroySound1.play(0.5f);
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addBattleCard(this);
	}

	private void attack(BattleCard battleCard)
	{
		battleCard.takeDamage();
	}
}