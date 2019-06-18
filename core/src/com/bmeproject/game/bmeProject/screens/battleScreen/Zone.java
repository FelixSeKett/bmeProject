package com.bmeproject.game.bmeProject.screens.battleScreen;

public enum Zone {

    RED, GREEN, BLUE;

    private boolean activated;

    Zone() {
        activated = false;
    }

    public boolean isActivated(){
        return activated;
    }

    public void activate(){
        activated = true;
    }

    public void reset() {
        activated = false;
    }
}
