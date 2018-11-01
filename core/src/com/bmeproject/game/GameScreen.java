package com.bmeproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BMEProject  PROJECT; // aktuell noch nicht in Gebrauch - für später sinnvoll
	private       SpriteBatch batch;
	private       Stage       stage;
	private       Figur       figur;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	GameScreen(BMEProject project)
	{
		PROJECT = project;
	}

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void show()
	{
		initialize();
		stage.setDebugAll(BMEProject.DEBUG);
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
		stage.dispose();
	}

	private void initialize()
	{
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(), batch);
		figur = new Figur();
		figur.setupAttributes();
		stage.addActor(figur);
	}
}
