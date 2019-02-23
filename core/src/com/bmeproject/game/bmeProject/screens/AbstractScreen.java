package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleScreen;
import com.bmeproject.game.bmeProject.screens.deckScreen.DeckScreen;

/**
 * Elternklasse aller im Spiel verwendeter Screen-Klassen ({@link BattleScreen}, {@link DeckScreen}, etc.), die zur
 * Darstellung von Actors verwendet werden und damit auf eine {@link Stage} inklusive {@link SpriteBatch} angewiesen
 * sind.
 */
public abstract class AbstractScreen implements Screen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final BMEProject  BME_PROJECT;
	private      SpriteBatch spriteBatch;
	public Stage stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public AbstractScreen(BMEProject bmeProject)
	{
		BME_PROJECT = bmeProject;
	}

	// ===================================
	// PROCEDURES
	// ===================================

	/**
	 * Wird aufgerufen, sobald der Screen in {@link BMEProject} per {@link BMEProject#setScreen(Screen)} zum aktiven
	 * Screen gewählt wird und verrichtet alle einmaligen Arbeiten, die im Zuge dessen anfallen. Initialisiert so
	 * unter anderem die Feldvariablen.
	 */
	@Override public void show()
	{
		spriteBatch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(), spriteBatch);
		stage.setDebugAll(BMEProject.DEBUG);
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Wird entsprechend der festgelegten Bildrate mehrmals pro Sekunde aufgerufen und kümmert sich im Zuge dessen um
	 * inhaltliche und formelle Veränderungen, die zur Laufzeit dieses screens stattfinden. Setzt so zunächst das Bild
	 * zurück und fordert die Stage anschließend auf, mit {@link Stage#act(float)} die Spiellogik (Input, Update von
	 * Variablen) und mit {@link Stage#draw()} die Visualisierung zu aktualisieren.
	 *
	 * @param delta Sekunden zwischen zwei Frames (bei 60 Bildern pro Sekunde rund 0.016)
	 */
	@Override public void render(float delta)
	{
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override public void resize(int width, int height)
	{

	}

	@Override public void pause()
	{

	}

	@Override public void resume()
	{

	}

	@Override public void hide()
	{

	}

	/**
	 * Vernichtet bei Aufruf alle hier verwendeten Instanzen, deren Klassen das Interface
	 * {@link com.badlogic.gdx.utils.Disposable} implementieren. Ist nötig, weil jene Klassen trotz abgeschlossener
	 * Verwendung immer noch referenziert und somit nicht vom Garbage Collector aufgeräumt werden.
	 */
	@Override public void dispose()
	{
		spriteBatch.dispose();
		stage.dispose();
	}
}
