package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public class EntryField extends Field
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EntryField(iFieldable fieldable, Vector2 position)
	{
		super(fieldable, position);
		addListener(new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BattleController battleController = FIELDABLE.giveBattleController();
				BattleCard       selectedCard     = battleController.giveLastClickedBattleCard();
				if (selectedCard != null) {
					if (selectedCard.giveCommander() == FIELDABLE.giveCommander()) {
						addCard(selectedCard);
						battleController.resetLastClickedBattleCard();
					}
				}
				return true;
			}
		});
	}
}
