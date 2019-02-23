package com.bmeproject.game.bmeProject.screens;

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
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.gameObjects.Card;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;
	private Deck deck;
	private BMEProject bmeProject;

	private Texture backgroundTexture;

	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TestScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("TEST SCREEN SHOWN");

		//Background
		backgroundTexture = new Texture("core/assets/visuals/testScreenBgd.png");
		Image background = new Image(backgroundTexture);
		stage.addActor(background);


		card = BMEProject.allCards.get(1);
		stage.addActor(card);

		deck = new Deck();
		int[] tempDeck =  deck.getDeck();
		System.out.println("Deck besteht aus " + tempDeck.length + " Karten");
		for (int i = 0; i < tempDeck.length; i++  )
		{
			System.out.println("ID = " + tempDeck[i]);
		}

		//Button
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/visuals/sprites.txt"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		button = new TextButton("DeckScreen", textButtonStyle);
		stage.addActor(button);

		button.addListener(new ChangeListener() {

			public void changed (ChangeListener.ChangeEvent event, Actor actor)
			{
				System.out.println("I was clicked");
				BME_PROJECT.activateDeckScreen();
			}
		});
	}
}
