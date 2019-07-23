package com.bmeproject.game.bmeProject.screens.endOfGameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.ButtonView;

public class EndOfGameController extends Controller {

    public final ButtonView BUTTON_VIEW;


    public EndOfGameController(SpriteBatch spriteBatch, ButtonView buttonView, boolean isWin){
        super(spriteBatch);
        BUTTON_VIEW = buttonView;

        showResultOfGame(isWin);
    }

    public void showResultOfGame(boolean isWin){
        Texture result;

        if(isWin){
            result = new Texture("core/assets/visuals/messages/win.png");
        } else {
            result = new Texture("core/assets/visuals/messages/loose.png");
        }
        result.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image resultImage = new Image(result);
        stage.addActor(resultImage);
        BUTTON_VIEW.showEndButtons(stage);
    }

    @Override public void update(float delta)
    {
        super.update(delta);
    }

}
