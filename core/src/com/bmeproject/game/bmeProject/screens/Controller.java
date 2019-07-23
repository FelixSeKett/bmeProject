package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

public class Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	protected Stage stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Controller(SpriteBatch spriteBatch)
	{
		stage = new Stage(new ScreenViewport(), spriteBatch);
		stage.setDebugAll(BMEProject.DEBUG);
		Gdx.input.setInputProcessor(stage);
	}

	// ===================================
	// METHODS
	// ===================================

	protected void init(SpriteBatch spriteBatch)
	{

	}

	public void update(float delta)
	{
		stage.act(delta);
	}

	public void dispose()
	{
		stage.dispose();
	}

	public Stage giveStage()
	{
		return stage;
	}

}
