package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bmeproject.game.bmeProject.util.Constants;

public class Renderer
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final SpriteBatch SPRITE_BATCH;
	private       Viewport    viewport;
	private       Camera      camera;
	private       Stage       stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Renderer(SpriteBatch spriteBatch)
	{
		SPRITE_BATCH = spriteBatch;
		camera = new OrthographicCamera();
		viewport = new FitViewport(1920f, 1080f, camera);
		camera.position.set(1920 / 2f, 1080 / 2f, 0f);
	}

	// ===================================
	// METHODS
	// ===================================

	void render(Stage stageToRender)
	{
		stage = stageToRender;
		stage.setViewport(viewport);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}

	void dispose()
	{
		SPRITE_BATCH.dispose();
	}
}
