package com.bmeproject.game.bmeProject.gameObjects;


public class BattleCard {
    // encapsulated abstract card
    private Card card;

    public BattleCard() {
        this.card = new Card() {

            @Override
            public void activate() {

            }
        };
    }
}
