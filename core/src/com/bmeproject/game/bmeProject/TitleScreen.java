package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;


public class TitleScreen extends TheatricalScreen
{
	private final int positionX = 16;
	private final int postionYlogo = 300;
	private final int positionYDeck = 4;

	private final int postionYDuel = 100 ;
	private final int postionYSettings = 200 ;
	private final int postionXAnimation = 1000;

	private Skin skin;
	private Stage stage;
	SpriteBatch batch;
	private Texture logo;
	private Texture buttonDeck;
	private Texture buttonDuel;
	private Texture buttonSettings;
	private Texture loadAnimation;


	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);

		batch = new SpriteBatch();
		logo= new Texture("core/assets/logo.png");
		buttonDeck = new Texture("core/assets/buttonDeck.png");
		buttonDuel = new Texture("core/assets/buttonDuel.png");
		buttonSettings = new Texture("core/assets/buttonSettings.png");
		loadAnimation = new Texture("core/assets/animation.png");
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
	//	firstButton = new TextButton("PlayOne", skin, "default");
	//	firstButton.setPosition( stage.getHeight()/2,stage.getWidth()/2);
	//	firstButton.setSize(280,60);

	/*	secondButton = new TextButton("Exit", skin, "default");
		secondButton.setPosition(stage.getHeight()/2,stage.getWidth()/2-100);
		secondButton.setSize(280,60);*/

		clickButton(buttonDeck);
	//	exitButton(buttonDuel);

	//	stage.addActor(firstButton);
	//	stage.addActor(secondButton);
	}

	private void exitButton(TextButton btn){
		btn.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
	}

	private void clickButton(Texture btnClick){
		//BME_PROJECT.activateTestScreen();


		/*btnClick.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				BME_PROJECT.activateTestScreen();
			}
		});*/
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0x52/255.0f,0x9D/255.0f,0xBF/255.0f,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.draw(logo,stage.getWidth()/positionX,stage.getHeight()-postionYlogo);
		batch.end();

		batch.begin();
		batch.draw(buttonDeck,stage.getWidth()/positionX,stage.getHeight()/positionYDeck);
		batch.end();

		batch.begin();
		batch.draw(buttonDuel,stage.getWidth()/positionX,stage.getHeight()/positionYDeck-postionYDuel);
		batch.end();

		batch.begin();
		batch.draw(buttonSettings,stage.getWidth()/positionX,stage.getHeight()/positionYDeck-postionYSettings);
		batch.end();

		batch.begin();
		batch.draw(loadAnimation,stage.getWidth()-postionXAnimation,stage.getHeight()/positionYDeck-postionYSettings,840,463);
		batch.end();


		stage.draw();
	}
}
