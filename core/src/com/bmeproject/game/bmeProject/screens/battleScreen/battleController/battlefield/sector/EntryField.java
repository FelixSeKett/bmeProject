package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

public class EntryField extends RingField
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EntryField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
		addListener(new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BattleController battleController = FIELD_USER.giveBattleController();
				if (battleController.isGoodToGo()) {
					BattleCard selectedCard = battleController.giveLastClickedBattleCard();
					if (selectedCard != null) {
						if (selectedCard.giveCommander() == FIELD_USER.giveCommander()) {
							addCard(selectedCard);
							battleController.setTurnStarted();
							battleController.resetLastClickedBattleCard();
						}
					}
				}
				return true;
			}
		});
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public Field givePreviousField()
	{
		return ((Sector)FIELD_USER).giveLeadField();
	}

	@Override public Field giveNextField()
	{
		return ((Sector)FIELD_USER).giveEndField();
	}
}
