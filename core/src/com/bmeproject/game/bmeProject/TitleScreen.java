package com.bmeproject.game.bmeProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.Timer;
import com.bmeproject.game.BMEProject;

public class TitleScreen extends TheatricalScreen
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

	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;

	private Texture animationTexture;
	private TextureRegion[][] animationTextureRegion;
	private Sprite animationSprite;
	private int frameRow = 0;
	private int frameColumn = 0;
	private float intervalSec;

	private Texture buttonTexture;
	private Texture buttonPressedTexture;

	private Texture cardEditorHoverTexture;
	private Texture cardEditorHoveredTexture;

	private TextureRegion backgroundTextureRegion;

	@Override public void show()
	{
		System.out.println("TITLE SCREEN SHOWN");

		skin = new Skin (Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();

        backgroundTextureRegion = new TextureRegion(new Texture
				("core/assets/background.png"),0,0, 1500,946);

        //TEXTURES
		buttonTexture = new Texture(Gdx.files.internal("core/assets/CardEditor.png"));
		buttonPressedTexture = new Texture(Gdx.files.internal("core/assets/CardEditorHovered.png"));

		cardEditorHoverTexture = new Texture(Gdx.files.internal("core/assets/CardEditor.png"));
		cardEditorHoveredTexture = new Texture(Gdx.files.internal("core/assets/CardEditorHovered.png"));

		//BUTTONS
		final ImageButton pictureClickedButton = new ImageButton(new TextureRegionDrawable(new
				TextureRegion(buttonTexture)), new TextureRegionDrawable(new
				TextureRegion(buttonPressedTexture)));

		final ImageButton pictureHoverButton = new ImageButton(new TextureRegionDrawable(new
				TextureRegion(buttonTexture)), new TextureRegionDrawable(new
				TextureRegion(buttonPressedTexture)));

		final ImageButton pictureHoveredButton = new ImageButton(new TextureRegionDrawable(new
				TextureRegion(buttonPressedTexture)), new TextureRegionDrawable(new
				TextureRegion(buttonPressedTexture)));

		final TextButton textHoverButton = new TextButton("I am changing when hovered", skin);

		final TextButton textClickedButton = new TextButton("I am just a simple Textbutton", skin);

		//BUTTONS CONFIGURATIONS
        textClickedButton.setPosition(230, 790);

		textHoverButton.setPosition(230, 630);
		textHoverButton.setColor(Color.LIGHT_GRAY);

		pictureClickedButton.setHeight(120);
		pictureClickedButton.setPosition(30, 660);

		pictureHoverButton.setPosition(30,500);
		pictureHoveredButton.setPosition(30,500);
		pictureHoverButton.setHeight(120);
        pictureHoveredButton.setHeight(120);

		//BUTTON-LISTENER
        textClickedButton.addListener(
                new ClickListener()
                {
                    @Override
                    public void clicked(InputEvent event, float x, float y)
                    {
                        BME_PROJECT.activateTestScreen();
                    }
                });

        textHoverButton.addListener(
                new ClickListener()
                {
                    @Override
                    public void clicked(InputEvent event, float x, float y)
                    {
                        BME_PROJECT.activateTestScreen();
                    }
                });

		textHoverButton.addListener(
				new InputListener()
                {
					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						textHoverButton.setText("Now you can click me");
                        textHoverButton.setColor(Color.YELLOW);
                        //textHoverButton.setChecked(true);

					}
					@Override
					public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
						textHoverButton.setText("I am changing when hovered");
						textHoverButton.setColor(Color.LIGHT_GRAY);
					}
				});

		pictureHoverButton.addListener(
				new InputListener()
                {
					@Override
					public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
						pictureHoverButton.setColor(1,1,1,0);
						pictureHoveredButton.setColor(1,1,1,1);
						System.out.println("Hovered over Button");
					}
                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                        pictureHoverButton.setColor(1,1,1,1);
                        pictureHoveredButton.setColor(1,1,1,0);
                        System.out.println("Left Buttonarea");
                    }
				});

		pictureHoverButton.addListener(
				new ClickListener()
                {
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						BME_PROJECT.activateTestScreen();
					}
				});

		pictureClickedButton.addListener(
				new ClickListener()
                {
			        @Override
			        public void clicked(InputEvent event, float x, float y)
			        {
				        //System.out.println("Clicked, no action yet");
				        BME_PROJECT.activateTestScreen();
			        }
		        });

        //BUTTONS ADDED TO STAGE
        stage.addActor(textHoverButton);
        stage.addActor(pictureClickedButton);
        stage.addActor(pictureHoveredButton);
        stage.addActor(pictureHoverButton);
        stage.addActor(textClickedButton);

        //important for receiving all input events, called each frame
		Gdx.input.setInputProcessor(stage);

		//SPRITE ANIMATION
		animationTexture = new Texture("core/assets/bubble_animation.png");

		animationTextureRegion = TextureRegion.split(animationTexture, 160, 320);

		intervalSec = 1/6f;
		animationSprite = new Sprite(animationTextureRegion[0][0]);
		Timer.schedule(new Timer.Task(){
			@Override
			public void run() {
				frameRow++;
				if (frameRow > 8) {
					frameRow = 0;
					if (frameColumn == 1) {
						frameColumn = 0;
					} else {
						frameColumn = 1;
					}

				}
				animationSprite.setRegion(animationTextureRegion[frameRow][frameColumn]);
			}

		}, 0, intervalSec);
		animationSprite.setPosition(stage.getWidth()/2 + 220, stage.getHeight()/2 + animationSprite.getHeight()/3);
	}

	@Override public void render(float delta)
	{
		batch.begin();

		batch.draw(backgroundTextureRegion,0,0);

		animationSprite.draw(batch);

		batch.end();

		stage.act(delta);

		stage.draw();
	}
}
