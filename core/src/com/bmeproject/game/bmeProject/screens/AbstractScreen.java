package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleScreen;
import com.bmeproject.game.bmeProject.screens.deckScreen.DeckScreen;

/**
 * Elternklasse aller im Spiel verwendeten Screen-Klassen ({@link BattleScreen}, {@link DeckScreen}, etc.), die zur
 * Aktualisierung und Darstellung von Actors verwendet wird und daher auf einen {@link Controller} inklusive
 * {@link Stage} und einen {@link Renderer} inklusive {@link SpriteBatch} angewiesen ist.
 */
public abstract class AbstractScreen implements Screen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	protected final BMEProject BME_PROJECT;
	private         Controller controller;
	private         Renderer   renderer;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public AbstractScreen(BMEProject bmeProject)
	{
		BME_PROJECT = bmeProject;
	}

	// ===================================
	// METHODS
	// ===================================

	/**
	 * Wird aufgerufen, sobald der Screen in {@link BMEProject} per {@link BMEProject#setScreen(Screen)} zum aktiven
	 * Screen gewählt wird und verrichtet alle einmaligen Arbeiten, die im Zuge dessen anfallen. Initialisiert so
	 * unter anderem die Instanzvariablen.
	 */
	@Override public void show()
	{
		SpriteBatch spriteBatch = new SpriteBatch();
		controller = createController(spriteBatch);
		controller.init(spriteBatch);
		renderer = new Renderer(spriteBatch);
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
		controller.update(delta);
		renderer.render(controller.stage);
	}

	/**
	 * Vernichtet bei Aufruf alle hier verwendeten Instanzen, deren Klassen das Interface
	 * {@link com.badlogic.gdx.utils.Disposable} implementieren. Ist nötig, weil jene Klassen trotz abgeschlossener
	 * Verwendung immer noch referenziert und somit nicht vom Garbage Collector aufgeräumt werden.
	 */
	@Override public void dispose()
	{
		controller.dispose();
		renderer.dispose();
	}

	@Override public void resize(int width, int height)
	{
		renderer.resize(width, height);
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

	protected abstract Controller createController(SpriteBatch spriteBatch);
}
