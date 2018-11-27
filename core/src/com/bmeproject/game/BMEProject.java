package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.bmeproject.game.bmeProject.*;

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

	private TitleScreen             titleScreen;
	private BattleScreen            battleScreen;
	private DeckScreen              deckScreen;
	private TestScreen              testScreen;
	private Profile                 profile;
	private HashMap<String, Entity> entities;

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
		initializeEntities();
		setScreen(titleScreen);
	}

	private void initializeScreens()
	{
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckScreen = new DeckScreen(this);
		testScreen = new TestScreen(this);
	}

	private void initializeEntities()
	{
		entities = new HashMap<String, Entity>();
		/*
		TODO
		Hier soll die Hashmap "entities" mit einzelnen Instanzen der Klasse Entity gefüllt werden. Dazu soll ein
		XML-Dokument ausgelesen werden, das die Inhalte einer Entity mit dazugehöriger ID hält. Die ID soll dann als
		Key in der Hashmap hinterlegt werden - nicht als Variable in der Entity-Instanz. Die dazugehörigen Inhalte
		sollen als Entity-Instanz im Value der Hashmap hinterlegt werden. Am Ende soll hier eine Liste stehen, in der
		jede Karte exakt einmal wie in einem Register vertreten und adressierbar ist.
		 */
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
