package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.archive.Player;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class BattleScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================
	private Stage stage;
	private Player player1;
	private Player player2;
	private Texture buttonBack;
	private ImageButton imageButtonBack;

	// ===================================
	// CONSTRUCTORS
	// ===================================
	public BattleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void show()
	{
		super.show();
		initializePlayers();

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		buttonBack = new Texture(Gdx.files.internal("core/assets/BackButton.png"));

		initButtons();
	}

	private void initButtons() {
		imageButtonBack = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonBack)),
				new TextureRegionDrawable(new TextureRegion(buttonBack)));
		imageButtonBack.setPosition(stage.getHeight()/20,stage.getWidth()/2);
		imageButtonBack.setSize(100,100);

		stage.addActor(imageButtonBack);
	}

	@Override public void render(float delta)
	{
		super.render(delta);
		getActivePlayer().manageEvent();

		Gdx.gl.glClearColor(0x52/255.0f,0,0,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(imageButtonBack.isPressed()){
			BME_PROJECT.activateTitleScreen();
		}

		stage.draw();
	}

	private void initializePlayers()
	{
		player1 = new Player(this);
		player2 = new Player(this);
		player1.initialize();
		player2.initialize();
		player1.startTurn();
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	private Player getActivePlayer()
	{
		if (player1.getEvent() != null) { return player1; }
		else { return player2; }
	}
}
