package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

public class Renderer
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Controller  CONTROLLER;
	private       SpriteBatch spriteBatch;
	private       Stage       stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Renderer(Controller controller)
	{
		CONTROLLER = controller;
	}

	// ===================================
	// METHODS
	// ===================================

	public void init()
	{
		spriteBatch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(), spriteBatch);
		stage.setDebugAll(BMEProject.DEBUG);
		Gdx.input.setInputProcessor(stage);
	}

	void render(float delta)
	{
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	public void resize(int width, int height)
	{
	}

	void dispose()
	{
		spriteBatch.dispose();
		stage.dispose();
	}
}
