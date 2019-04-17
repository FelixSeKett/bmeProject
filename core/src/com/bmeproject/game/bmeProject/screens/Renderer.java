package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Renderer
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final SpriteBatch SPRITE_BATCH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Renderer(SpriteBatch spriteBatch)
	{
		SPRITE_BATCH = spriteBatch;
	}

	// ===================================
	// METHODS
	// ===================================

	void render(Stage stage)
	{
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	public void resize(int width, int height)
	{
	}

	void dispose()
	{
		SPRITE_BATCH.dispose();
	}
}
