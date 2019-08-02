package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;

import java.util.ArrayList;

public class HandField extends Field
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public HandField(FieldUser fieldUser, Vector2 position)
	{
		super(fieldUser, position.x, position.y, BattleCard.WIDTH, BattleCard.HEIGHT, 0f, 0f, 0f, 0f, 50f, 0f,
				new ArrayList<BattleCard>(), 1);
		fieldUser.giveBattleController().giveStage().addActor(this);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected void updateReadabilityOfBattleCard(BattleCard battleCard)
	{
		Player activePlayer = FIELD_USER.giveBattleController().giveActivePlayer();
		if (battleCard.giveCommander() == activePlayer) {
			battleCard.uncoverYourself();
		} else {
			battleCard.coverYourself();
		}
	}
}
