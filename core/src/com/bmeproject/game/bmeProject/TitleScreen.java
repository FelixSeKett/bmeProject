package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
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

	private TextButton firstButton;
	private TextButton secondButton;


	// ===================================
	// CONSTRUCTORS
	// ===================================



	//Hier habe ich die jeweiligen Dateien, welche auf der Startseite zu sehen sind eingefügt.
	//(Buttons, Logo)
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

    //Hier habe ich einen Button implementiert
	private void initButtons() {
		firstButton = new TextButton("Play", skin, "default");
		firstButton.setPosition( stage.getWidth()/positionX,stage.getHeight()/positionYDeck);
		firstButton.setSize(buttonDuel.getWidth(),buttonDuel.getHeight());
		firstButton.setColor(1,1,1,0);

		secondButton = new TextButton("Exit", skin, "default");
		secondButton.setPosition(stage.getHeight()/2,stage.getWidth()/2-100);
		secondButton.setSize(280,60);

		stage.addActor(firstButton);
	//	stage.addActor(secondButton);

		clickButton(firstButton);
	//	exitButton(secondButton);

	}

	//falls wir später mal einen exit button brauchen werden, habe ich diesen auch schonmal programmiet
	/*private void exitButton(TextButton btn){
		btn.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
	}*/

	// hier habe ich die clikButton Methode geschrieben,
	// hierfür gibt es 2 Möglichkeiten, die erste habe ich auskommentiert
	private void clickButton(TextButton btnClick){
	/*	btnClick.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				BME_PROJECT.activateTestScreen();
			}
		});*/

		btnClick.addListener(new ActorGestureListener(){
			@Override
			public void tap(InputEvent event, float x, float y, int count,
			int button) {
				super.tap(event, x, y, count, button);
				BME_PROJECT.activateTestScreen();
				dispose();
			}
		});
	}

	//Hier habe ich alle Bilddateien richtig positioniert und die Hintergrundfarbe angepasst,
	// sodass die Titelseite genauso wie unser Prototyp ausschaut
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
