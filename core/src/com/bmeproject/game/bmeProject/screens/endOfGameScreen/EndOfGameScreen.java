package com.bmeproject.game.bmeProject.screens.endOfGameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;

public class EndOfGameScreen extends AbstractScreen {

    // ===================================
    // ATTRIBUTES & CONSTANTS
    // ===================================
    private final BMEProject BME_PROJECT;
    private final BattleController BATTLECONTROLLER;
    private final boolean ISWIN;

    // ===================================
    // CONSTRUCTORS
    // ===================================
    public EndOfGameScreen(BMEProject bmeProject, BattleController battleController, boolean isWin){
        super(bmeProject);
        BME_PROJECT = bmeProject;
        BATTLECONTROLLER = battleController;
        ISWIN = isWin;
    }

    @Override public void show()
    {
        super.show();
        System.out.println("ENDOFGAME SCREEN SHOWN");
    }

    @Override public void render(float delta)
    {
        super.render(delta);
    }

    @Override public void resize(int width, int height)
    {
        super.resize(width, height);
    }

    @Override protected Controller createController(SpriteBatch spriteBatch)
    {
        return new EndOfGameController(spriteBatch, BATTLECONTROLLER.BUTTON_VIEW, ISWIN);
    }
}
