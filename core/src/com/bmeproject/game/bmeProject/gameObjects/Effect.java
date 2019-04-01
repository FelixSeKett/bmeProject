package com.bmeproject.game.bmeProject.gameObjects;

public class Effect {
    private int effectId;
    private String effectDescription;

    public Effect(int effectId, String effectDescription) {
        this.effectId = effectId;
        this.effectDescription = effectDescription;
    }

    public int getEffectId() {
        return effectId;
    }

    public String getEffectDescription() {
        return effectDescription;
    }


}


