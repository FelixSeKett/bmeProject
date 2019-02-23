package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.archive.Player;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class BattleScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player player1;
	private Player player2;

	private Texture backgroundTexture;

	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("BATTLE SCREEN SHOWN");
		initializePlayers();

		//Background
		backgroundTexture = new Texture("core/assets/visuals/battleScreenBgd.png");
		Image background = new Image(backgroundTexture);
		stage.addActor(background);

		//Button
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/visuals/sprites.txt"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		button = new TextButton("TestScreen", textButtonStyle);
		stage.addActor(button);

		button.addListener(new ChangeListener() {

			public void changed (ChangeListener.ChangeEvent event, Actor actor)
			{
				System.out.println("I was clicked");
				BME_PROJECT.activateTestScreen();
			}
		});
	}

	@Override public void render(float delta)
	{
		super.render(delta);
		getActivePlayer().manageEvent();
	}

	private void initializePlayers()
	{
		player1 = new Player(this);
		player2 = new Player(this);
		player1.initialize();
		player2.initialize();
		player1.startTurn();
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	private Player getActivePlayer()
	{
		if (player1.getEvent() != null) { return player1; }
		else { return player2; }
	}
}
