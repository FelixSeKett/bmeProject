package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.EntryField;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

public class Quarter extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Quarter(Player owner, Card card)
	{
		super(owner, card, 3);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void activate()
	{
		EntryField correspondingEntryField = giveCorrespondingEntryField();
		if (correspondingEntryField.giveCards().size() > 0) {
			correspondingEntryField.moveContentStreamwise();
		}
	}

	@Override public void getDestroyed()
	{
		commander = PLAYER.BATTLE_CONTROLLER.giveOppositePlayerOf(commander);
		PLAYER.BATTLE_CONTROLLER.checkForWin();
	}

	@Override public void prepareForBattle()
	{
		PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveNextEmptyQuarterFieldFor(this).addCard(this);
	}

	private EntryField giveCorrespondingEntryField()
	{
		return giveCurrentSector().giveEntryField();
	}
}





