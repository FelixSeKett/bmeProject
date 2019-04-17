package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class TitleScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================
	private static final int buttonHeight = 150;
	private static final int buttonWidth = 150;
	private Stage stage;
	private Texture buttonBattle;
	private Texture buttonPressBattle;
	private Texture buttonSettings;
	private Texture buttonDeck;
	private ImageButton imageButtonBattle;
	private ImageButton imageButtonDeck;
	private ImageButton imageButtonSettings;

	// ===================================
	// CONSTRUCTORS
	// ===================================
	public TitleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================
	@Override public void show()
	{
		super.show();

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		buttonBattle = new Texture(Gdx.files.internal("core/assets/ButtonBattle.png"));
		buttonPressBattle = new Texture(Gdx.files.internal("core/assets/ButtonBattleOnClick.png"));
		buttonSettings = new Texture(Gdx.files.internal("core/assets/ButtonSettings.png"));
		buttonDeck = new Texture(Gdx.files.internal("core/assets/ButtonDeckuebersicht.png"));

		initButtons();
	}

	private void initButtons() {
		imageButtonDeck = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonDeck)),
				new TextureRegionDrawable(new TextureRegion(buttonDeck)));
		imageButtonDeck.setPosition(stage.getHeight()/10,stage.getWidth()/2);
		imageButtonDeck.setSize(buttonWidth,buttonHeight);

		imageButtonBattle = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonBattle)),
				new TextureRegionDrawable(new TextureRegion(buttonPressBattle)));
		imageButtonBattle.setPosition(stage.getHeight()/10,stage.getWidth()/4);
		imageButtonBattle.setSize(buttonWidth,buttonHeight);

		imageButtonSettings = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonSettings)),
				new TextureRegionDrawable(new TextureRegion(buttonSettings)));
		imageButtonSettings.setPosition(stage.getHeight()/10,stage.getWidth()/84);
		imageButtonSettings.setSize(buttonWidth,buttonHeight);


		stage.addActor(imageButtonDeck);
		stage.addActor(imageButtonBattle);
		stage.addActor(imageButtonSettings);

	//	clickImageButton(imageButtonBattle);
	}


	/*private void clickImageButton(ImageButton btnCLick){
		btnCLick.addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count,
							int button) {
					BME_PROJECT.activateBattleScreen();
			}
		});
	}*/

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0x52/255.0f,0x9D/255.0f,0xBF/255.0f,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(imageButtonBattle.isPressed()){
			BME_PROJECT.activateBattleScreen();
		}
		if(imageButtonDeck.isPressed())
		{
			BME_PROJECT.activateDeckScreen();
		}
		/*if(imageButtonSettings.isPressed())
		{
			BME_PROJECT.activateSettingsScreen();
		}*/


		stage.draw();
	}
}
