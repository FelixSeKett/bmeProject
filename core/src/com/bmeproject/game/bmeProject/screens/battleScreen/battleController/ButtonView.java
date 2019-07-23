package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
		final Texture     BUTTON_RED_DOWN = new Texture("core/assets/visuals/buttons/3_rotbuttonSmall_down.png");
		final ImageButton RED_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_RED)), new TextureRegionDrawable(new TextureRegion(BUTTON_RED_DOWN)));
		addAntialiasing(BUTTON_RED);
		addAntialiasing(BUTTON_RED_DOWN);
		RED_BUTTON.setPosition(X, Y - 350);
		BATTLE_CONTROLLER.giveStage().addActor(RED_BUTTON);
		RED_BUTTON.addListener(createRedButtonListener());
		return RED_BUTTON;
	}

	private ImageButton generateGreenButton()
	{
		final Texture     BUTTON_GREEN = new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall.png");
		final Texture     BUTTON_GREEN_DOWN = new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall_down.png");
		addAntialiasing(BUTTON_GREEN);
		addAntialiasing(BUTTON_GREEN_DOWN);
		final ImageButton GREEN_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_GREEN)), new TextureRegionDrawable(new TextureRegion(BUTTON_GREEN_DOWN)));
		GREEN_BUTTON.setPosition(X, Y - 480);
		BATTLE_CONTROLLER.giveStage().addActor(GREEN_BUTTON);
		GREEN_BUTTON.addListener(createGreenButtonListener());
		return GREEN_BUTTON;
	}

	private ImageButton generateBlueButton()
	{
		final Texture     BUTTON_BLUE = new Texture("core/assets/visuals/buttons/3_blaubuttonSmall.png");
		final Texture     BUTTON_BLUE_DOWN = new Texture("core/assets/visuals/buttons/3_blaubuttonSmall_down.png");
		addAntialiasing(BUTTON_BLUE);
		addAntialiasing(BUTTON_BLUE_DOWN);
		final ImageButton BLUE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_BLUE)), new TextureRegionDrawable(new TextureRegion(BUTTON_BLUE_DOWN)));
		BLUE_BUTTON.setPosition(X, Y - 605);
		BATTLE_CONTROLLER.giveStage().addActor(BLUE_BUTTON);
		BLUE_BUTTON.addListener(createBlueButtonListener());
		return BLUE_BUTTON;
	}

	private void initButtons(Stage stage)
	{
		final Texture     BUTTON_ZONE = new Texture("core/assets/visuals/buttons/3_kompassbuttonSmall.png");
		final Texture BUTTON_ZONE_DOWN = new Texture ("core/assets/visuals/buttons/3_kompassbuttonSmall_down.png");
		addAntialiasing(BUTTON_ZONE);
		addAntialiasing(BUTTON_ZONE_DOWN);
		final ImageButton ZONE_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_ZONE)), new TextureRegionDrawable(new TextureRegion(BUTTON_ZONE_DOWN)));
		ZONE_BUTTON.setPosition(X, Y);
		START_BUTTONS.addActor(ZONE_BUTTON);
		ZONE_BUTTON.addListener(createZoneButtonListener());

		final Texture     BUTTON_STREAM = new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall.png");
		final Texture BUTTON_STREAM_DOWN = new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall_down.png");
		addAntialiasing(BUTTON_STREAM);
		addAntialiasing(BUTTON_STREAM_DOWN);
		final ImageButton STREAM_BUTTON = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_STREAM)), new TextureRegionDrawable(new TextureRegion (BUTTON_STREAM_DOWN)));
		STREAM_BUTTON.setPosition(X, Y - 130);
		START_BUTTONS.addActor(STREAM_BUTTON);
		STREAM_BUTTON.addListener(createStreamButtonListener());

		final Texture     BUTTON_FINISH = new Texture("core/assets/visuals/buttons/3_zubeendenSmall.png");
		final Texture     BUTTON_FINISH_DOWN = new Texture("core/assets/visuals/buttons/3_zubeendenSmall_down.png");
		addAntialiasing(BUTTON_FINISH);
		addAntialiasing(BUTTON_FINISH_DOWN);
		final ImageButton finishButton  = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_FINISH)), new TextureRegionDrawable(new TextureRegion(BUTTON_FINISH_DOWN)));
		finishButton.setPosition(X + 7, 60);
		stage.addActor(finishButton);
		finishButton.addListener(createFinishButtonListener());
	}

    private InputListener createEndOfGameButtonTitle() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				BMEProject.buttonSound.play(0.6f);
                BATTLE_CONTROLLER.getProject().activateTitleScreen();
                return true;
            }
        };
    }

    private InputListener createEndofGAmeButtonNewGame() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				BMEProject.buttonSound.play(0.6f);
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
						BMEProject.streamChangeSound.play(0.5f);
			            BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.proceedStartSector();
		            }
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Drehe den Kompass um die Sektoren mit Strahlung auszusetzten. Die verschiedenen Strahlungen beeinflussen die Einheiten auf unterschiedliche Weise");
			}
        };
    }

    private InputListener createStreamButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!BATTLE_CONTROLLER.hasTurnStarted()) {
						BMEProject.streamSound.play(0.2f);
			            BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.toggleStream();
		            }
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Die Strömung ändert die Kampfreihenfolge der Einheiten");
			}
        };
    }

    private InputListener createRedButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.RED.isActivated()) {
						BMEProject.buttonSound.play(0.6f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.RED);
		            }
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
					BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Deine Kreaturen und Phänomene werden durch die rote Strahlung zu wahren Kriegsmaschinen");
			}
        };
    }

    private InputListener createGreenButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.GREEN.isActivated()) {
						BMEProject.buttonSound.play(0.6f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.GREEN);
		            }
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Die Grüne Strahlung schafft dir mehr Raum um neue Einheiten in den Kampf zu senden");
			}
        };
    }

    private InputListener createBlueButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
		            if (!Zone.BLUE.isActivated()) {
						BMEProject.buttonSound.play(0.6f);
			            BATTLE_CONTROLLER.BATTLEFIELD.activateZone(Zone.BLUE);
		            }
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Deine Kreaturen und Phänomene werden durch die blaue Strahlung zu wahren Kriegsmaschinen");
			}
        };
    }

    private InputListener createFinishButtonListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            if (BATTLE_CONTROLLER.isGoodToGo()) {
					BMEProject.buttonSound.play(0.6f);
		            BATTLE_CONTROLLER.changeActivePlayer();
	            }
	            return true;
            }
			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				BATTLE_CONTROLLER.DETAIL_VIEW.setUserMessage("Beende deinen Zug");
			}
        };
    }

    private void addAntialiasing(Texture texture) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
}
