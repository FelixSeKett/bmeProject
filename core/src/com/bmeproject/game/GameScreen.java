package com.bmeproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen
{
	BMEProject  project;
	SpriteBatch batch;
	Texture     img;
	Stage       stage;
	Figur       figur;

	GameScreen(BMEProject project)
	{
		this.project = project;
		img = new Texture("core/assets/badlogic.jpg");
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(), batch);

		figur = new Figur();
		stage.addActor(figur);
	}


	@Override public void show()
	{
		stage.setDebugAll(project.debug);
		Gdx.input.setInputProcessor(stage);
	}

	@Override public void render(float delta)
	{
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override public void resize(int width, int height)
	{

	}

	@Override public void pause()
	{

	}

	@Override public void resume()
	{

	}

	@Override public void hide()
	{

	}

	@Override public void dispose()
	{
		batch.dispose();
		img.dispose();
		stage.dispose();
	}
}
