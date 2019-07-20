package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

public class ButtonView
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;
	private final Label            ACTIVE_PLAYER_LABEL;
	private final int              X = 620;
	private       ImageButton      zoneButton;
	private       ImageButton      streamButton;
	private       ImageButton      greenButton;
	private       ImageButton      redButton;
	private       ImageButton      blueButton;
	private       ImageButton      finishButton;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public ButtonView(BattleController battleController)
	{
		BATTLE_CONTROLLER = battleController;

		Stage stage = battleController.giveStage();

		final Label.LabelStyle ACTIVE_PLAYER_LABEL_STYLE = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
		ACTIVE_PLAYER_LABEL = new Label("", ACTIVE_PLAYER_LABEL_STYLE);
		ACTIVE_PLAYER_LABEL.setPosition(X + 10, 80);
		stage.addActor(ACTIVE_PLAYER_LABEL);

		initButtons(stage);
	}

	// ===================================
	// METHODS
	// ===================================

	public void updateActivePlayerLabel(Player activePlayer)
	{
		ACTIVE_PLAYER_LABEL.setText(activePlayer.PARTY.giveName() + " ist am Zug!");
	}

	private void initButtons(Stage stage)
	{
		zoneButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_kompassbuttonSmall.png"))));
		zoneButton.setPosition(X, 380);
		stage.addActor(zoneButton);
		zoneButton.addListener(createZoneButtonListener());

		streamButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall.png"))));
		streamButton.setPosition(X, 330);
		stage.addActor(streamButton);
		streamButton.addListener(createStreamButtonListener());

		redButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_rotbuttonSmall.png"))));
		redButton.setPosition(X, 180);
		stage.addActor(redButton);
		redButton.addListener(createRedButtonListener());

		greenButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall.png"))));
		greenButton.setPosition(X, 230);
		stage.addActor(greenButton);
		greenButton.addListener(createGreenButtonListener());

		blueButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_blaubuttonSmall.png"))));
		blueButton.setPosition(X, 130);
		stage.addActor(blueButton);
		blueButton.addListener(createBlueButtonListener());

		finishButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture("core/assets/visuals/buttons/3_zubeendenSmall.png"))));
		finishButton.setPosition(X, 20);
		stage.addActor(finishButton);
		finishButton.addListener(createFinishButtonListener());
	}

	private InputListener createZoneButtonListener()
	{
		return new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.proceedStartSector();
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
				BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.toggleStream();
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
				BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.RED);
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
				BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.GREEN);
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
				BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.BLUE);
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
