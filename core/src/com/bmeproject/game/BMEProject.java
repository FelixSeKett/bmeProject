package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.bmeproject.game.bmeProject.dataAccess.CardGenerator;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.screens.TestScreen;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleScreen;
import com.bmeproject.game.bmeProject.screens.deckOverviewScreen.DeckOverviewScreen;
import com.bmeproject.game.bmeProject.screens.titleScreen.TitleScreen;
import com.bmeproject.game.bmeProject.userData.User;

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

	private TitleScreen titleScreen;
	private BattleScreen battleScreen;
	private DeckOverviewScreen deckOverviewScreen;
	private TestScreen testScreen;
	public static User user;
	public static HashMap<Integer, Card> allCards;
	public static ArrayList<Card> Cards;

	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
		user = new User(this, "TestUser");
		initializeScreens();
	}

	private void initObjects(){
		CardGenerator cardgen = new CardGenerator("core/src/com/bmeproject/game/bmeProject/dataAccess/CardsXML.xml");
		allCards = cardgen.createAllCards();

		Deck testDeck;
		for(int i = 0; i <= user.getDecks().size(); i++){
			testDeck = user.getDeck(i);
		}
	}

	private void initializeScreens()
	{
		allCards = new HashMap();
		initObjects();
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckOverviewScreen = new DeckOverviewScreen(this);
		testScreen = new TestScreen(this);

		setScreen(deckOverviewScreen);
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
		setScreen(deckOverviewScreen);
	}

	public void activateTestScreen()
	{
		setScreen(testScreen);
	}



	/* why not working?
	//public User getUser() {return user;}
	*/
}
