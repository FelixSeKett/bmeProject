package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;

import java.util.ArrayList;

public class HandField extends Field
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public HandField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position.x, position.y, BattleCard.WIDTH, BattleCard.HEIGHT, 0f, 0f, 0f, 0f, 50f, 0f,
				new ArrayList<BattleCard>(), 1);
		fieldable.giveBattleController().giveStage().addActor(this);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected void updateReadabilityOfBattleCard(BattleCard battleCard)
	{
		if (battleCard.giveCommander() == FIELD_USER.giveBattleController().giveActivePlayer()) {
			battleCard.getUncovered();
		} else {
			battleCard.getCovered();
		}
	}
}
