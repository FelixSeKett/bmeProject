package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

public class DeckRenderer{

    private final DeckScreen DS;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    public DeckRenderer(DeckScreen ds){
        DS=ds;
    }

    private void init()
    {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton tbtn = new TextButton("bla", skin);
        tbtn.setPosition(100,100);
        stage.addActor(tbtn);
    }

    public void render(float delta){
        batch.begin();
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height){}
}
