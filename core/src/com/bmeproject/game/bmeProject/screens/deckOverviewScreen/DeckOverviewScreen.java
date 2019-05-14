package com.bmeproject.game.bmeProject.screens.deckOverviewScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.userData.User;

import com.bmeproject.game.bmeProject.screens.AbstractScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DeckOverviewScreen extends AbstractScreen {

	private DeckBox deckBox;
	private Deck deck;
	private User currentPlayer;
	private ArrayList<DeckBox> deckBoxUIList = new ArrayList<DeckBox>();
	private SpriteBatch batch;
	private Stage stage;
private BMEProject bmeProject;

	public DeckOverviewScreen(BMEProject bmeProject) {
		super(bmeProject);
		currentPlayer = BMEProject.user;
		//new DeckRenderer
		//new DeckController
		batch = new SpriteBatch();

		this.stage = new Stage(new ScreenViewport(), batch);
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	}

	@Override
	public void show() {
		super.show();

		System.out.println(super.toString());
		//hard coded deck:
		currentPlayer.addDeck(generateDeck());
		currentPlayer.addDeck(generateDeck());

		int xPosition = 90;
		int YPosition = 70;
		for (int i=0; i<currentPlayer.getDecks().size(); i++){
			YPosition += 10;
			deckBox = new DeckBox(xPosition, YPosition, stage);
			deckBox.setPosition(80,80);
			deckBoxUIList.add(deckBox);
			System.out.println(YPosition);
			xPosition=70;
		}



		System.out.println("#1 deck starts with: " + currentPlayer.getDeck(1).getCardFromDeck(currentPlayer.getDeck(1).getCardIdFromDeck(0)).getCardName());
		System.out.println("#2 deck starts with: " + currentPlayer.getDeck(2).getCardFromDeck(currentPlayer.getDeck(2).getCardIdFromDeck(0)).getCardName());
    }

    @Override public void render (float delta)
    {
        Gdx.gl.glClearColor(4/255f, 204/255f, 188/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//System.out.println("render");
		deckBoxUIList.get(1).getStage().draw();
		deckBoxUIList.get(0).getStage().draw();
        deckBox.getStage().draw();
    }

	//hard coded deck generator
	public Deck generateDeck() {
		deck = new Deck();

		Random random = new Random();

		for (int i=0; i<5; i++){
			int oneToFive = random.nextInt(4) + 1;
			deck.addCardToDeck(oneToFive);
		}
		return deck;
	}

	public Stage getStage(){
		return this.stage;
	}
}
