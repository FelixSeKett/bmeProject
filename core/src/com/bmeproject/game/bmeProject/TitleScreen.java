package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

import javax.swing.*;

public class TitleScreen extends TheatricalScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	private Stage stage;
	private Skin skin;

	@Override public void show()
	{
		//super.show();
		System.out.println("TITLE SCREEN SHOWN");
		//BME_PROJECT.activateTitleScreen();

		skin = new Skin (Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());

		final TextButton button = new TextButton("Click me", skin, "default");
		button.setWidth(200);
		button.setHeight(50);
		button.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		stage.addActor(button);

		Gdx.input.setInputProcessor(stage);
	}

	@Override public void render(float delta)
	{
		stage.draw();
	}

}
