package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player      player1;
	private Player      player2;
	private Battlefield battlefield;
	private Compass     compass;

	private Player  activePlayer;
	private boolean red;
	private boolean blue;
	private boolean green;
	private boolean started;

	// ===================================
	// METHODS
	// ===================================

	@Override protected void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		player1 = new Player(this, Party.ALLY);
		player2 = new Player(this, Party.ENEMY);
		battlefield = new Battlefield(this);
		compass = new Compass();
		activePlayer = player1;
		Gdx.input.setInputProcessor(stage);
	}

	public void changeActivePlayer()
	{
		red = false;
		green = false;
		blue = false;
		started = false;
		if (activePlayer == player1) {
			activePlayer = player2;
		} else {
			activePlayer = player1;
		}
	}

	private void activate(Zone zone, Player player)
	{
		if (player == activePlayer) {
			if (!zone.isActivated()) {
				Gdx.app.log(toString(), "Zone activated!!!!");
				zone.activate();
			}
		}
	}

	@Override
	public void update(float delta)
	{
		super.update(delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			activePlayer.drawTopCard();
		}
		/*
		battlefield.giveSectorOne().giveQuarterField().addListener(new InputListener(){

			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("You clicked it");
				return false;
			}

		});
		*/
	}

}
