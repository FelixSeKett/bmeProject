package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.bmeproject.game.bmeProject.BattleScreen;
import com.bmeproject.game.bmeProject.DeckScreen;
import com.bmeproject.game.bmeProject.TitleScreen;

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

	private TitleScreen  titleScreen;
	private BattleScreen battleScreen;
	private DeckScreen   deckScreen;

	// ===================================
	// PROCEDURES
	// ===================================

	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
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
}
