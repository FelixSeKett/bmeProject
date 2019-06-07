package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.bmeProject.screens.Controller;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player      player1;
	private Player      player2;

	private Battlefield battlefield;
	private Compass     compass;
	private Player      activePlayer;

	// ===================================
	// CONTRUCTORS
	// ===================================

	public BattleController()
	{
		activePlayer = player1;
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
	}

	public void changeActivePlayer() {

		if(activePlayer == player1) {
			activePlayer = player2;
		} else {
			activePlayer = player1;
		}

	}

	@Override protected void init(SpriteBatch spriteBatch)


	{
		super.init(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		player1 = new Player(this, Party.ALLY);
		player2 = new Player(this, Party.ENEMY);
		battlefield = new Battlefield(this);
		//compass = new Compass();
	}

	public Stage giveStage()
	{
		return stage;
	}
}
