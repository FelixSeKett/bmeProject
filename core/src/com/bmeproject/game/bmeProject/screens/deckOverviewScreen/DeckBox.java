package com.bmeproject.game.bmeProject.screens.deckOverviewScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.screens.deckOverviewScreen.DeckOverviewScreen;
import com.bmeproject.game.bmeProject.userData.User;
import com.bmeproject.game.BMEProject;

import java.util.ArrayList;

public class DeckBox extends Actor {
    ArrayList<Card> allBaseCards;
    ArrayList<Card> allFigureCards;
    ArrayList<Card> allManipulationCards;
    User currentPlayer;
    //SpriteBatch batch;
    private Stage stage;
    public ImageButton backgroundImageButton;

    // ===================================
    // CONSTRUCTORS
    // ===================================
    public DeckBox(int xPosition, int YPosition, Stage stage) {

        //super(bmeProject);
        //new DeckRenderer
        //new DeckController

        allBaseCards = new ArrayList<Card>();
        allFigureCards = new ArrayList<Card>();
        allManipulationCards = new ArrayList<Card>();
        this.stage=stage;
        //this.setPosition(90,90);

        giveDeckInformation();
        this.setPosition(xPosition, YPosition);
        //this.setBounds(xPosition, YPosition, 0, 0);
    }

    public void giveDeckInformation() {

        currentPlayer = BMEProject.user;
        //player has currently no saved decks

        //------- for schleife if more than one deck ------------
        Deck thisDeck = currentPlayer.getDeck(1);

        for (int i = 0; i < thisDeck.getSize(); i++) {
            int id = thisDeck.getCardIdFromDeck(i);
            //System.out.println("ID = " + thisDeck.getCardIdFromDeck(i));

            String type = thisDeck.getCardFromDeck(id).getCardType().toString();
            //System.out.println("CardType = " + type);

            if (thisDeck.getCardFromDeck(id).getCardType().toString() == "FIGURE") {
                //System.out.println("There´s a Figure!");
                allFigureCards.add(thisDeck.getCardFromDeck(id));
            } else if (thisDeck.getCardFromDeck(id).getCardType().toString() == "BASE") {
                allBaseCards.add(thisDeck.getCardFromDeck(id));
                //System.out.println("There´s a Base!");
            } else if (thisDeck.getCardFromDeck(id).getCardType().toString() == "MANIPULATION") {
                //System.out.println("There´s a Base!");
                allManipulationCards.add(thisDeck.getCardFromDeck(id));
            } else {
                System.out.println("There´s no type!");
            }
        }
        System.out.println("Anzahl der Basekarten: " + allBaseCards.size());
        System.out.println("Anzahl der Figurekarten: " + allFigureCards.size());
        System.out.println("Anzahl der Manipulationskarten: " + allManipulationCards.size());

        //##########UI#############


        //batch = new SpriteBatch();

        //stage = new Stage(new ScreenViewport(), batch);
        //Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Gdx.input.setInputProcessor(this.stage);


        TextureRegion textureRegion = new TextureRegion(new Texture("core/assets/badlogic.jpg"));
        Texture texture = new Texture(Gdx.files.internal("core/assets/DeckOverview_Background.jpg"));
        backgroundImageButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
        backgroundImageButton.setSize(200,200);
        backgroundImageButton.setPosition(0, 0);
        stage.addActor(backgroundImageButton);


        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = Color.WHITE;
        ls.font = new BitmapFont();

        //Label LInformation = new Label("Anzahl der Basekarten: " + allBaseCards.size(), skin);
        Label LInformation = new Label("Anzahl Deckkarten: " + thisDeck.getSize()
                + "\n\nAnzahl Basekarten: " + allBaseCards.size()
                + "\nAnzahl Figurekarten: " + allFigureCards.size()
                + "\nAnzahl Manipulationskarten: " + allManipulationCards.size()
                , ls);
        LInformation.setPosition(0, 0);
        stage.addActor(LInformation);

        backgroundImageButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("clicked");
                return true;
            }
        });
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }
}


