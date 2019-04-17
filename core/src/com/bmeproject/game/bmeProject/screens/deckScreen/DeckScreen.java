package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class DeckScreen extends AbstractScreen {

	// ===================================
	// ATTRIBUTES
	// ===================================
	private Texture buttonBack;
	private Texture buttonDeckeditor;
	private ImageButton imageButtonBack;
	private ImageButton imageButtonDeckeditor;
	private Stage stage;

	// ===================================
	// CONSTRUCTORS
	// ===================================
	public DeckScreen(BMEProject bmeProject) {
		super(bmeProject);
	}

	@Override
	public void show() {
		super.show();


		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		buttonBack = new Texture(Gdx.files.internal("core/assets/BackButton.png"));
		buttonDeckeditor = new Texture(Gdx.files.internal("core/assets/ButtonDeckeditor.png"));

		initButtons();
	}

	private void initButtons() {
		imageButtonBack = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonBack)),
				new TextureRegionDrawable(new TextureRegion(buttonBack)));
		imageButtonBack.setPosition(stage.getHeight()/20,stage.getWidth()/2);
		imageButtonBack.setSize(100,100);

		imageButtonDeckeditor = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonDeckeditor)),
				new TextureRegionDrawable(new TextureRegion(buttonDeckeditor)));
		imageButtonDeckeditor.setPosition(stage.getHeight()/10,stage.getWidth()/84);
		imageButtonDeckeditor.setSize(150,150);

		stage.addActor(imageButtonBack);
		stage.addActor(imageButtonDeckeditor);
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0x52/255.0f,0x9D/255.0f,0,0xff/255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(imageButtonBack.isPressed()){
			BME_PROJECT.activateTitleScreen();
		}
		/*if(imageButtonDeckeditor.isPressed()){
			BME_PROJECT.activateDeckeditorScreen();
		}*/

		stage.draw();
	}
}
