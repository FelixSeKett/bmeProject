package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private Viewport viewport;
	private Camera camera;
	private Stage stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Renderer(SpriteBatch spriteBatch)
	{
		SPRITE_BATCH = spriteBatch;
		camera = new PerspectiveCamera();
		viewport = new FitViewport(800, 480, camera);
	}

	// ===================================
	// METHODS
	// ===================================

	void render(Stage stageToRender)
	{
		stage = stageToRender;
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	public void resize(int width, int height)
	{
		viewport.update(width, height);

		if(stage != null){
			stage.getViewport().update(width, height, true);
		}
	}

	void dispose()
	{
		SPRITE_BATCH.dispose();
	}
}
