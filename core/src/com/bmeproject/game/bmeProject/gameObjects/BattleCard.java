package com.bmeproject.game.bmeProject.gameObjects;


import org.w3c.dom.Element;

public class BattleCard {
    // encapsulated abstract card
    private Card card;

    public BattleCard() {
        this.card = new Card() {

            @Override
            public void activate() {
                // parse card here...
            }
        };
    }
}
