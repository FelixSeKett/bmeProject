package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class DeckScreen extends AbstractScreen {

	private BMEProject bmeProject;
	private Texture backgroundTexture;

	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckScreen(BMEProject bmeProject) {

		super(bmeProject);

	}

	@Override
	public void show() {
		super.show();
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


		//Background
		backgroundTexture = new Texture("core/assets/visuals/deckScreenBgd.png");
		Image background = new Image(backgroundTexture);
		stage.addActor(background);

		//Button
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/visuals/sprites.txt"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		button = new TextButton("BattleScreen", textButtonStyle);
		stage.addActor(button);

		button.addListener(new ChangeListener() {

			public void changed (ChangeListener.ChangeEvent event, Actor actor)
			{
				System.out.println("I was clicked");
				BME_PROJECT.activateBattleScreen();
			}
		});



/*

		Table table = new Table(skin);
		table.setFillParent(true);
		stage.addActor(table);

		table.setWidth(stage.getWidth());
		table.align(Align.center | Align.top);

		table.setPosition(0, Gdx.graphics.getHeight());


		Card card = BMEProject.allCards.get(1);
		stage.addActor(card);


		Card card1 = new Card();
		card1.initialize(2);

		Card card2 = new Card();
		//card2.initialize(3);

		TextButton btn = new TextButton("Click me!", skin, "default");

		String str1 = "LALALA";
		String str2 = "TRATATA";

		table.padTop(30);
		table.add(btn).padBottom(30);
		table.row();
		table.add(str2);

*/

	}

}
