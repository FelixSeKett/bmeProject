package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class DeckRenderer {

    public DeckRenderer(){}

    private void init() {}

    public void render(){
        DeckController.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        DeckRenderer.render();
    }

    public void resize(int width, int height){}
}
