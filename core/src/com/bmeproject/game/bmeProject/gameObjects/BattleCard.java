package com.bmeproject.game.bmeProject.gameObjects;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.screens.battleScreen.Player;

public abstract class BattleCard extends Actor
{
    // encapsulated abstract card
    private final Card CARD;
    private final Player OWNER;
    private Player commander;

    public BattleCard(Player owner, Card card)
    {
        CARD = card;
        OWNER = owner;
        commander = owner;

    }
    
    public void setCommander(Player commander)
    {
        this.commander = commander;
    }

    public abstract void activate();
}
