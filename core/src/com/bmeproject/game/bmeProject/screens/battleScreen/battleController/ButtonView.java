package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

public class ButtonView
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;
	private final Group            START_BUTTONS;
	private final ImageButton      RED_BUTTON;
	private final ImageButton      GREEN_BUTTON;
	private final ImageButton      BLUE_BUTTON;
	private final Interpolation    BUTTON_FADING_INTERPOLATION = Interpolation.sineIn;
	private final int              X                           = 1480;
	private final int              Y                           = 900;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public ButtonView(BattleController battleController)
	{
		BATTLE_CONTROLLER = battleController;
		START_BUTTONS = new Group();
		final Stage STAGE = battleController.giveStage();
		STAGE.addActor(START_BUTTONS);
		initButtons(STAGE);
		RED_BUTTON = generateRedButton();
		GREEN_BUTTON = generateGreenButton();
		BLUE_BUTTON = generateBlueButton();
	}

    public void showEndButtons(Stage stage) {
        ImageButton neuesSpiel;
        ImageButton toStart;

        final Texture BUTTON_NEW = new Texture("core/assets/visuals/buttons/4_neuesSpiel.png");
        final Texture BUTTON_MAIN = new Texture("core/assets/visuals/buttons/4_zumHauptmenue.png");

        neuesSpiel = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_NEW)));
        neuesSpiel.setPosition(1480, 60);
        stage.addActor(neuesSpiel);
        neuesSpiel.setZIndex(300);
        neuesSpiel.addListener(createEndofGAmeButtonNewGame());

        toStart = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_MAIN)));
        toStart.setPosition(69, 60);
        stage.addActor(toStart);
        toStart.setZIndex(300);
        toStart.addListener(createEndOfGameButtonTitle());
    }

    // ===================================
    // METHODS
    // ===================================

	public boolean isGoodToGo()
	{
		return !START_BUTTONS.hasActions();
	}

	public void fadeOutStartButtons()
	{
		START_BUTTONS.addAction(generateFadeOutAction());
	}

	public void fadeInStartButtons()
	{
		START_BUTTONS.addAction(generateFadeInAction());
	}

	public void fadeOutButtonOfZone(Zone zone)
	{
		// Schlechter Code, aber es musste schnell gehen
		if (zone == Zone.RED) {
			RED_BUTTON.addAction(generateFadeOutAction());
		} else if (zone == Zone.GREEN) {
			GREEN_BUTTON.addAction(generateFadeOutAction());
		} else if (zone == Zone.BLUE) {
			BLUE_BUTTON.addAction(generateFadeOutAction());
		}
	}

	public void fadeInZoneButtons()
	{
		RED_BUTTON.addAction(generateFadeInAction());
		GREEN_BUTTON.addAction(generateFadeInAction());
		BLUE_BUTTON.addAction(generateFadeInAction());
	}

	private AlphaAction generateFadeOutAction()
	{
		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(0.3f);
		alphaAction.setDuration(1f);
		alphaAction.setInterpolation(BUTTON_FADING_INTERPOLATION);
		return alphaAction;
	}

	private AlphaAction generateFadeInAction()
	{
		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(1f);
		alphaAction.setDuration(0.3f);
		alphaAction.setInterpolation(BUTTON_FADING_INTERPOLATION);
		return alphaAction;
	}

	private ImageButton generateRedButton()
	{
		final Texture     BUTTON_RED = new Texture("core/assets/visuals/buttons/3_rotbuttonSmall.png");
		addAntialiasing(BUTTON_RED);
		final ImageButton RED_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_RED)));
		RED_BUTTON.setPosition(X, Y - 350);
		BATTLE_CONTROLLER.giveStage().addActor(RED_BUTTON);
		RED_BUTTON.addListener(createRedButtonListener());
		return RED_BUTTON;
	}

	private ImageButton generateGreenButton()
	{
		final Texture     BUTTON_GREEN = new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall.png");
		addAntialiasing(BUTTON_GREEN);
		final ImageButton GREEN_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_GREEN)));
		GREEN_BUTTON.setPosition(X, Y - 480);
		BATTLE_CONTROLLER.giveStage().addActor(GREEN_BUTTON);
		GREEN_BUTTON.addListener(createGreenButtonListener());
		return GREEN_BUTTON;
	}

	private ImageButton generateBlueButton()
	{
		final Texture     BUTTON_BLUE = new Texture("core/assets/visuals/buttons/3_blaubuttonSmall.png");
		addAntialiasing(BUTTON_BLUE);
		final ImageButton BLUE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_BLUE)));
		BLUE_BUTTON.setPosition(X, Y - 605);
		BATTLE_CONTROLLER.giveStage().addActor(BLUE_BUTTON);
		BLUE_BUTTON.addListener(createBlueButtonListener());
		return BLUE_BUTTON;
	}

	private void initButtons(Stage stage)
	{
		final Texture     BUTTON_ZONE = new Texture("core/assets/visuals/buttons/3_kompassbuttonSmall.png");
		addAntialiasing(BUTTON_ZONE);
		final ImageButton ZONE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_ZONE)));
		ZONE_BUTTON.setPosition(X, Y);
		START_BUTTONS.addActor(ZONE_BUTTON);
		ZONE_BUTTON.addListener(createZoneButtonListener());

		final Texture     BUTTON_STREAM = new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall.png");
		addAntialiasing(BUTTON_STREAM);
		final ImageButton STREAM_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_STREAM)));
		STREAM_BUTTON.setPosition(X, Y - 130);
		START_BUTTONS.addActor(STREAM_BUTTON);
		STREAM_BUTTON.addListener(createStreamButtonListener());

		final Texture     BUTTON_FINISH = new Texture("core/assets/visuals/buttons/3_zubeendenSmall.png");
		addAntialiasing(BUTTON_FINISH);
		final ImageButton finishButton  = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_FINISH)));
		finishButton.setPosition(X + 7, 60);
		stage.addActor(finishButton);
		finishButton.addListener(createFinishButtonListener());
	}

    private InputListener createEndOfGameButtonTitle() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				BMEProject.buttonSound.play(0.5f);
                BATTLE_CONTROLLER.getProject().activateTitleScreen();
                return true;
            }
        };
    }

    private InputListener createEndofGAmeButtonNewGame() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				BMEProject.buttonSound.play(0.5f);
                BATTLE_CONTROLLER.getProject().activateBattleScreen();
                return true;
            }
        };
    }


    private InputListener createZoneButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!BATTLE_CONTROLLER.hasTurnStarted()) {
						BMEProject.streamChangeSound.play(1.0f);
			            BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.proceedStartSector();
		            }
	            }
	            return true;
            }
        };
    }

    private InputListener createStreamButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!BATTLE_CONTROLLER.hasTurnStarted()) {
						BMEProject.streamSound.play(0.5f);
			            BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.toggleStream();
		            }
	            }
	            return true;
            }
        };
    }

    private InputListener createRedButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.RED.isActivated()) {
						BMEProject.buttonSound.play(0.5f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.RED);
		            }
	            }
	            return true;
            }
        };
    }

    private InputListener createGreenButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.GREEN.isActivated()) {
						BMEProject.buttonSound.play(0.5f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.GREEN);
		            }
	            }
	            return true;
            }
        };
    }

    private InputListener createBlueButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.BLUE.isActivated()) {
						BMEProject.buttonSound.play(0.5f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.BLUE);
		            }
	            }
	            return true;
            }
        };
    }

    private InputListener createFinishButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
					BMEProject.buttonSound.play(0.5f);
		            BATTLE_CONTROLLER.changeActivePlayer();
	            }
	            return true;
            }
        };
    }

    private void addAntialiasing(Texture texture) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
}
