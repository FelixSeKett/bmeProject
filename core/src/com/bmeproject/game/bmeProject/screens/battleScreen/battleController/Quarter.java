package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector.EntryField;

public class Quarter extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Quarter(Player owner, Card card)
	{
		super(owner, card);
		if (BMEProject.DEBUG) {
			addListener(new InputListener()
			{
				@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
				{
					getDestroyed();
					return true;
				}
			});
		}
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public int giveDefaultHitpoints()
	{
		return 3;
	}

	@Override public void getActivated()
	{
		EntryField correspondingEntryField = giveCorrespondingEntryField();
		if (correspondingEntryField.giveCards().size() > 0) {
			correspondingEntryField.moveContentStreamwise();
		}
	}

	@Override public void getDestroyed()
	{
		commander = PLAYER.BATTLE_CONTROLLER.giveOppositePlayerOf(commander);
		Field currentField = giveCurrentField();
		if (currentField != null) {
			currentField.update();
		}
		PLAYER.BATTLE_CONTROLLER.checkForWin();
	}

	@Override protected Field giveStartField()
	{
		return PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveNextEmptyQuarterFieldFor(this);
	}

	private EntryField giveCorrespondingEntryField()
	{
		return giveCurrentSector().giveEntryField();
	}
}





