package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public interface iFieldable
{
	BattleController giveBattleController();

	Field giveCurrentFieldOfBattleCard(BattleCard battleCard);
}
