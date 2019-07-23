package com.bmeproject.game.bmeProject.screens.titleScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.Controller;

public class TitleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Texture TITLESCREEN = new Texture("core/assets/titlescreen_image.png");

	private Texture startDuellButton = new Texture("core/assets/visuals/buttons/1_duellStartenButton.png");
	private Texture deckEditorButton = new Texture("core/assets/visuals/buttons/1_deckEditorButton.png");
	private Texture einstellungenButton = new Texture("core/assets/visuals/buttons/1_einstellungenButton.png");

    private final TitleScreen titleScreen;

	// ===================================
	// METHODS
	// ===================================

	public TitleController(SpriteBatch spriteBatch, TitleScreen titleScreen)
	{
		super(spriteBatch);
		this.titleScreen = titleScreen;
		TITLESCREEN.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		Image backgroundImage = new Image(TITLESCREEN);
		stage.addActor(backgroundImage);
		startDuellBtn();
		deckEditorBtn();
		einstellungenBtn();
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
	}

	public void startDuellBtn(){
        ImageButton btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(startDuellButton)));
		startDuellButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		btn.setPosition(50,519);
        stage.addActor(btn);
        btnClicked(btn);
    }

	public void deckEditorBtn(){
		ImageButton btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(deckEditorButton)));
		deckEditorButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		btn.setPosition(50,350);
		stage.addActor(btn);
		btnClicked(btn);
	}

	public void einstellungenBtn(){
		ImageButton btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(einstellungenButton)));
		einstellungenButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		einstellungenButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		btn.setPosition(50,128);
		stage.addActor(btn);
		btnClicked(btn);
	}

    public void btnClicked (ImageButton btnClick){
	    btnClick.addListener(new InputListener(){

	        @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				BMEProject.buttonSound.play(0.6f);
				titleScreen.BME_PROJECT.activateBattleScreen();
                return true;
            }
		});
    }
}
