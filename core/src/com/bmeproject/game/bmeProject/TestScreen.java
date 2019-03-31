package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.profile.Deck;
import com.bmeproject.game.bmeProject.testScreen.TestCard;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends TheatricalScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;
	private Deck deck;

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

		//BUTTON TO GET BACK TO TITLESCREEN
		Skin skin = new Skin (Gdx.files.internal("uiskin.json"));
		TextButton backButton = new TextButton("Back", skin);
		backButton.setPosition(0,830);
		backButton.addListener(
				new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						BME_PROJECT.activateTitleScreen();
					}
				});
		stage.addActor(backButton);

		card = new Card();
		card.initialize(2);
		stage.addActor(card);

		deck = new Deck();
		int[] tempDeck =  deck.getDeck();
		System.out.println("Deck besteht aus " + tempDeck.length + " Karten");
		for (int i = 0; i < tempDeck.length; i++  )
		{
			System.out.println("ID = " + tempDeck[i]);
		}
	}
}
