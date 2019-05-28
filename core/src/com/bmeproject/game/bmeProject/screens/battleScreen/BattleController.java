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

	private Player  player1;
	private Player  player2;
	private Compass compass;

	// ===================================
	// CONTRUCTORS
	// ===================================

	public BattleController()
	{

	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
	}

	@Override protected void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		player1 = new Player(this, 0f);
		player2 = new Player(this, 180f);
		//compass = new Compass();
	}

	public Stage giveStage()
	{
		return stage;
	}
}
