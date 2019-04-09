package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;


public class TitleScreen extends AbstractScreen
{
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

	private Skin skin;
	private Stage stage;
	private TextButton textButton;
	private Texture buttonTexture;
	private Texture buttonPressTexture;

	@Override public void show()
	{
		super.show();
		System.out.println("TITLE SCREEN SHOWN");
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		buttonTexture = new Texture(Gdx.files.internal("core/assets/Button.png"));
		buttonPressTexture = new Texture(Gdx.files.internal("core/assets/ButtononClick.png"));

		initButtons();
	}

	private void initButtons() {
		textButton = new TextButton("PlayOne", skin, "default");
		textButton.setPosition( stage.getHeight()/4,stage.getWidth()/2);
		textButton.setSize(280,60);

		ImageButton imageButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)),
				new TextureRegionDrawable(new TextureRegion(buttonPressTexture)));
		imageButton.setPosition(stage.getHeight()/4,stage.getWidth()/4);

		stage.addActor(imageButton);
		stage.addActor(textButton);

		clickTextButton(textButton);
		clickImageButton(imageButton);
	}

	private void clickTextButton(TextButton btnClick){
		btnClick.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x, float y){
				BME_PROJECT.activateTestScreen();
			}
		});
	}

	private void clickImageButton(ImageButton btnCLick){
		btnCLick.addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count,
							int button) {
				BME_PROJECT.activateTestScreen();
			}
		});
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0x52/255.0f,0x9D/255.0f,0xBF/255.0f,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}
}
