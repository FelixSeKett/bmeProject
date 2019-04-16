package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.dataAccess.CardGenerator;
import com.bmeproject.game.bmeProject.dataAccess.XMLReader;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DeckScreen extends AbstractScreen {

	//ToDo: for dynamic Deck output use variable fields
	private int DeckX = 300;
	private int DeckY = 200;
	private int DeckHeight = 100;
	private int DeckWidth = 100;
	private SpriteBatch batch;
	private Stage stage;
	private Skin skin;
	private Deck savedDeck;
	private Card savedCard;
	ShapeRenderer shape;
	public static HashMap<Integer, Card> allCards;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckScreen(BMEProject bmeProject) {
		super(bmeProject);
		//build all cards


		//generate new Deck
		savedDeck = new Deck();
	}

	@Override
	public void show() {
		super.show();
		//some UI stuff
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		shape = new ShapeRenderer();
		//generate my deck information with generated Deck
		buildDeckArea(savedDeck);

		/*


		Table table = new Table(skin);
		table.setFillParent(true);
		stage.addActor(table);

		table.setWidth(stage.getWidth());
		table.align(Align.center | Align.top);

		table.setPosition(0, Gdx.graphics.getHeight());

		Card card1 = new Card();
		//card1.initialize(2);

		Card card2 = new Card();
		//card2.initialize(3);

		TextButton btn = new TextButton("Click me!", skin, "default");

		String str1 = "LALALA";
		String str2 = "TRATATA";

		table.padTop(30);
		table.add(btn).padBottom(30);
		table.row();
		table.add(str2);
		*/
	}

	public void buildDeckArea(Deck savedDeck){

		//save card ids from generated deck into int array
		int[] getDeck = savedDeck.getDeck();
		String cardIDs = Arrays.toString(getDeck);
		int cardAmount = getDeck.length;

		//card array with information based on their card id
		Card[] myCardsInMyDeck = new Card[getDeck.length];
		String[] myCardsInMyDeckTypes = new String[getDeck.length];

		for (int i = 0; i < getDeck.length; i++){
			int CardID = getDeck[i];
			myCardsInMyDeck[i] = allCards.get(CardID);

			myCardsInMyDeckTypes[i] = myCardsInMyDeck[i].getCardType().toString();
		}
		//System.out.println(myCardsInMyDeckTypes[0]);

		ArrayList<String> cardsOfTypeBase = new ArrayList<String>();
		for (int i = 0; i<myCardsInMyDeck.length; i++){
			if (myCardsInMyDeck[i].getCardType().toString() == "BASE"){
				cardsOfTypeBase.add(myCardsInMyDeck[i].toString());
			}
		}
		//System.out.println(cardsOfTypeBase.size());

		Label LCardIDs = new Label("Deck Karten:  " + cardIDs, skin);
		LCardIDs.setPosition(100, 200);
		stage.addActor(LCardIDs);

		Label LCardAmount = new Label("Anzahl der Karten:  " + cardAmount, skin);
		LCardAmount.setPosition(100, 170);
		stage.addActor(LCardAmount);

		Label LTypeBaseAmount = new Label("Anzahl Basen:  " + cardsOfTypeBase.size(), skin);
		LTypeBaseAmount.setPosition(100, 140);
		stage.addActor(LTypeBaseAmount);

		TextButton transparentButton = new TextButton("bla", skin);
		transparentButton.setPosition(80, 70);
		transparentButton.setColor(1, 1, 1, 0);
		transparentButton.setHeight(200);
		transparentButton.setWidth(200);
		stage.addActor(transparentButton);

		//XMLReader xmlReader = new XMLReader("/Users/Manu/bmeProject/core/src/com/bmeproject/game/bmeProject/dataAccess/CardsXML.xml");
		//System.out.println(xmlReader.initCards());


		Gdx.input.setInputProcessor(stage);

		transparentButton.addListener( new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				System.out.println("Chose Deck");
			}
		});
	}

	@Override public void render (float delta)
	{
		Gdx.gl.glClearColor(4/255f, 204/255f, 188/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();

		buildDeckBackground();

		//stage adding text
		stage.act(delta);
		stage.draw();
	}

	public void buildDeckBackground()
	{
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.rect(80, 70, 200, 200);
		shape.end();
	}

}
