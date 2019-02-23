package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.bmeproject.game.bmeProject.dataAccess.CardGenerator;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleScreen;
import com.bmeproject.game.bmeProject.screens.deckScreen.DeckScreen;
import com.bmeproject.game.bmeProject.screens.testScreen.TestScreen;
import com.bmeproject.game.bmeProject.screens.titleScreen.TitleScreen;
import com.bmeproject.game.bmeProject.userData.Profile;

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

	private TitleScreen titleScreen;
	private BattleScreen battleScreen;
	private DeckScreen deckScreen;
	private TestScreen testScreen;
	private Profile profile;
	public static HashMap<Integer, Card> allCards;


	// ===================================
	// METHODS
	// ===================================

	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
		initializeScreens();
		profile = new Profile(this);
		//initializeEntities();
	}

	private void initObjects(){
		CardGenerator cardgen = new CardGenerator("core/src/com/bmeproject/game/bmeProject/dataAccess/CardsXML.xml");
		allCards = cardgen.createAllCards();
	}

	public HashMap getAllCards() {
		return allCards;
	}

	private void initializeScreens()
	{
		allCards = new HashMap();
		initObjects();
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckScreen = new DeckScreen(this);
		testScreen = new TestScreen(this);
		profile = new Profile(this);



		setScreen(testScreen);
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

	public void activateTestScreen()
	{
		setScreen(testScreen);
	}

	public Profile getProfile()
	{
		return profile;
	}
}
