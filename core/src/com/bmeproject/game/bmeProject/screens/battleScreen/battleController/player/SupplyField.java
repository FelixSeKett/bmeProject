package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;

import java.util.ArrayList;

public class SupplyField extends Field
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public SupplyField(final FieldUser fieldable, final Vector2 position)
	{
		super(fieldable, position.x, position.y, BattleCard.WIDTH, BattleCard.HEIGHT, 0f, 0f, 0f, 0f, 0.25f, 0.25f,
				new ArrayList<BattleCard>(), 1);
		fieldable.giveBattleController().giveStage().addActor(this);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected void updateReadabilityOfBattleCard(BattleCard battleCard)
	{
		battleCard.coverYourself();
	}
}
