package com.bmeproject.game.bmeProject.screens.battleScreen;

public class Compass {

    private Stream currentStream;

    public Compass(){
        currentStream = Stream.CLOCKWISE;
    }

    private Stream getCurrentStream(){
        return currentStream;
    }

    private void changeStreamRotation(){
        currentStream = currentStream.giveOppositeStream();
    }

}
