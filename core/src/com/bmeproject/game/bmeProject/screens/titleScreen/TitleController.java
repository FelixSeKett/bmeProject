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

	private Texture startDuellButton = new Texture("core/assets/Duell-Basis.png");
    private Texture startDuellButtonClicked = new Texture("core/assets/Duell-OnClick.png");
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
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
	}

	public void startDuellBtn(){
        ImageButton btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(startDuellButton)),
                new TextureRegionDrawable(new TextureRegion(startDuellButtonClicked)));
		startDuellButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		startDuellButtonClicked.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



		btn.setPosition(500,350);
        stage.addActor(btn);

        btnClicked(btn);
    }

    public void btnClicked (ImageButton btnClick){
	    btnClick.addListener(new InputListener(){

	        @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				titleScreen.BME_PROJECT.activateBattleScreen();
                return true;
            }
		});
    }
}
