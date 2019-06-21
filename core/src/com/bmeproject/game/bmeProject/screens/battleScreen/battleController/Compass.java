package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;

public class Compass
{

    private Stream                 currentStream;
    private Sector                 firstSector;
    private final BattleController BATTLECONTROLLER;


    public Compass(BattleController battleController){

        currentStream = Stream.CLOCKWISE;
        BATTLECONTROLLER = battleController;
        firstSector = battleController.getFirstSector();
    }

    public Stream getCurrentStream(){
        return currentStream;
    }

    private void changeStreamRotation(){
        currentStream = currentStream.giveOppositeStream();
    }

    public Sector getFirstSector()
    {
        return firstSector;
    }

    public void setFirstSector(Sector sector)
    {
        firstSector = sector;
    }

}
