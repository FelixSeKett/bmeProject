package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.screens.battleScreen.Player;

public class PhenomenonCard extends BattleCard {

    // default constructor
    public PhenomenonCard(Player owner, Card card)
    {
        super(owner, card);
    }

    @Override
    public void activate() {
        System.out.println("Ich bin ein Phänomen und gehöre zu den coolen Kids!");
    }
}
