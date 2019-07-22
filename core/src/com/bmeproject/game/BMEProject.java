package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.bmeproject.game.bmeProject.dataAccess.CardGenerator;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleScreen;
import com.bmeproject.game.bmeProject.screens.deckScreen.DeckScreen;
import com.bmeproject.game.bmeProject.screens.titleScreen.TitleScreen;
import com.bmeproject.game.bmeProject.userData.User;
import com.bmeproject.game.bmeProject.util.SaveGameHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Die Wurzel des Code-Stamms und Erweiterung von {@link Game}. Wird das Spiel gestartet, erstellt die jeweilige
 * Launcher-Klasse (Desktop-Launcher, IOS-Launcher, etc.) eine Instanz dieser Klasse per Default-Constructor und ruft
 * anschließend {@link #create} auf.
 */
public class BMEProject extends Game
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public static final boolean DEBUG = true;

	private       TitleScreen            titleScreen;
	private       BattleScreen           battleScreen;
	private       DeckScreen             deckScreen;
	public        User                   user;
	public static HashMap<Integer, Card> allCards;
	public static ArrayList<Card>        Cards;

	public static Music music;
	public static Sound cardSound;
	public static Sound buttonSound;
	public static Sound attackSound;
	public static Sound destroySound1;
	public static Sound destroySound2;
	public static Sound streamSound;
	public static Sound streamChangeSound;


	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
		user = new User(this, "TestUser");

		music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/audio/music.mp3"));
		cardSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/card.ogg"));
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/button1.ogg"));
		attackSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/attack1.ogg"));
		destroySound1 = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/destroy1.ogg"));
		destroySound2 = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/destroy2.ogg"));
		streamSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/stream.ogg"));
		streamChangeSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/audio/activated.ogg"));

		music.setLooping(true);
		music.setVolume(1.0f);
		music.play();

		initializeScreens();
	}

	private void initObjects()
	{
		CardGenerator cardgen = new CardGenerator("core/src/com/bmeproject/game/bmeProject/dataAccess/CardsXML.xml");
		allCards = cardgen.createAllCards();

		Deck testDeck;
		for (int i = 0; i <= user.getDecks().size(); i++) {
			testDeck = user.getDeck(i);
		}

		SaveGameHandler sg = new SaveGameHandler(this);

		sg.saveGame(user);
		user = sg.loadGame(user);
	}



	private void initializeScreens()
	{
		allCards = new HashMap();
		initObjects();
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckScreen = new DeckScreen(this);

		setScreen(titleScreen);
	}

	public void activateTitleScreen()
	{
		setScreen(titleScreen);
	}

	public void activateBattleScreen()
	{
		setScreen(battleScreen);
	}

	public void activateDeckScreen()
	{
		setScreen(deckScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
		cardSound.dispose();
		buttonSound.dispose();
		attackSound.dispose();
		destroySound1.dispose();
		destroySound2.dispose();
		streamSound.dispose();
		streamChangeSound.dispose();
	}
}
