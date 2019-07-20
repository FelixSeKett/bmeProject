package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.EntryField;

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
	}

	private EntryField giveCorrespondingEntryField()
	{
		return giveCurrentSector().giveEntryField();
	}
}





