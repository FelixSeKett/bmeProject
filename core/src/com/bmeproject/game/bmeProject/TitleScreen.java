package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

import javax.swing.*;

public class TitleScreen extends TheatricalScreen
{

	private Skin skin;
	private Stage stage;
	private TextButton firstButton;
	private TextButton secondButton;

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

	@Override public void show(){
		super.show();
		System.out.println("TITLE SCREEN SHOWN");
		//BME_PROJECT.activateTestScreen();

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		initButtons();
	}

	private void initButtons() {
		firstButton = new TextButton("PlayOne", skin, "default");
		firstButton.setPosition( stage.getHeight()/2,stage.getWidth()/2);
		firstButton.setSize(280,60);

		stage.addActor(firstButton);
	}

	@Override
	public void render(float delta){
		stage.draw();
	}



}
