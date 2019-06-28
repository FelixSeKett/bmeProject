package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public interface iFieldable {

    public boolean hasSelectedACard(boolean selected);

    public BattleCard giveLastClickedBattleCard();

    public void drawCardToField();

}
