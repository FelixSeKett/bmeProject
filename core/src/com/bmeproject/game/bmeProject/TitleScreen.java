package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.entity.Background;


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


		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);


		initButtons();
	}




	private void initButtons() {
		firstButton = new TextButton("PlayOne", skin, "default");
		firstButton.setPosition( stage.getHeight()/2,stage.getWidth()/2);
		firstButton.setSize(280,60);

		secondButton = new TextButton("Exit", skin, "default");
		secondButton.setPosition(stage.getHeight()/2,stage.getWidth()/2-100);
		secondButton.setSize(280,60);

		clickButton(firstButton);
		exitButton(secondButton);

		stage.addActor(firstButton);
		stage.addActor(secondButton);
	}

	private void exitButton(TextButton btn){
		btn.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
	}

	private void clickButton(TextButton btnClick){
		btnClick.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				BME_PROJECT.activateTestScreen();
			}
		});
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0x52/255.0f,0x9D/255.0f,0xBF/255.0f,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}
}
