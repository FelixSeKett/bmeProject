package com.bmeproject.game.bmeProject.gameObjects;

public class CreatureCard extends Card {

    // default constructor
    public CreatureCard() {}

    @Override
    public void activate() {
        System.out.println("Ich bin eine Kreatur und greife an!");
    }
}
