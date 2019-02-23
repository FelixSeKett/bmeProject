package com.bmeproject.game.bmeProject.screens.testScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends AbstractScreen {
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;
	private Deck deck;
	private BMEProject bmeProject;

	private TestController testController;
	private TestRenderer testRenderer;

	private boolean paused;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	@Override
	public void render(float DeltaTime) {
		testController.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		testRenderer.render();
	}

	public TestScreen(BMEProject bmeProject)
	{
		super(bmeProject);
		this.bmeProject = bmeProject;
		init();
	}

	public void init(){
		testController = new TestController(bmeProject.getAllCards());
		testRenderer = new TestRenderer(testController);

		paused = false;
	}

//	@Override
//	public void create() {
//
//	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show(){}

	@Override public void resize(int width, int height){
		testRenderer.resize(width, height);
	}

	@Override public void pause(){
		paused = true;
	}

	@Override public void resume(){
		paused = false;
	}

	@Override public void dispose(){
		testRenderer.dispose();
	}
}
