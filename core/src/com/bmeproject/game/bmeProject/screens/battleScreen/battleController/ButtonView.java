package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

public class ButtonView
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public ButtonView(BattleController battleController)
	{
		BATTLE_CONTROLLER = battleController;
		initButtons(battleController.giveStage());
	}

	// ===================================
	// METHODS
	// ===================================

	private void initButtons(Stage stage)
	{
		final int X = 1480;
		final int Y = 900;

		final Texture     BUTTON_ZONE = new Texture("core/assets/visuals/buttons/3_kompassbuttonSmall.png");
		final ImageButton ZONE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_ZONE)));
		ZONE_BUTTON.setPosition(X, Y);
		stage.addActor(ZONE_BUTTON);
		ZONE_BUTTON.addListener(createZoneButtonListener());

		final Texture     BUTTON_STREAM = new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall.png");
		final ImageButton STREAM_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_STREAM)));
		STREAM_BUTTON.setPosition(X, Y - 130);
		stage.addActor(STREAM_BUTTON);
		STREAM_BUTTON.addListener(createStreamButtonListener());

		final Texture     BUTTON_RED = new Texture("core/assets/visuals/buttons/3_rotbuttonSmall.png");
		final ImageButton RED_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_RED)));
		RED_BUTTON.setPosition(X, Y - 480);
		stage.addActor(RED_BUTTON);
		RED_BUTTON.addListener(createRedButtonListener());

		final Texture     BUTTON_GREEN = new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall.png");
		final ImageButton GREEN_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_GREEN)));
		GREEN_BUTTON.setPosition(X, Y - 350);
		stage.addActor(GREEN_BUTTON);
		GREEN_BUTTON.addListener(createGreenButtonListener());

		final Texture     BUTTON_BLUE = new Texture("core/assets/visuals/buttons/3_blaubuttonSmall.png");
		final ImageButton BLUE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_BLUE)));
		BLUE_BUTTON.setPosition(X, Y - 605);
		stage.addActor(BLUE_BUTTON);
		BLUE_BUTTON.addListener(createBlueButtonListener());

		final Texture     BUTTON_FINISH = new Texture("core/assets/visuals/buttons/3_zubeendenSmall.png");
		final ImageButton finishButton  = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_FINISH)));
		finishButton.setPosition(X + 7, 60);
		stage.addActor(finishButton);
		finishButton.addListener(createFinishButtonListener());
	}

	private InputListener createZoneButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (BATTLE_CONTROLLER.hasTurnStarted()) {
					BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.proceedStartSector();
				}
				return true;
			}
		};
	}

	private InputListener createStreamButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (BATTLE_CONTROLLER.hasTurnStarted()) {
					BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.toggleStream();
				}
				return true;
			}
		};
	}

	private InputListener createRedButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (Zone.RED.isActivated()) {
					BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.RED);
				}
				return true;
			}
		};
	}

	private InputListener createGreenButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (Zone.GREEN.isActivated()) {
					BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.GREEN);
				}
				return true;
			}
		};
	}

	private InputListener createBlueButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (Zone.BLUE.isActivated()) {
					BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.BLUE);
				}
				return true;
			}
		};
	}

	private InputListener createFinishButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BATTLE_CONTROLLER.changeActivePlayer();
				return true;
			}
		};
	}
}
