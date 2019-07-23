package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import sun.tools.jstat.Alignment;

public class DetailView
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Image BATTLE_CARD_IMAGE;
	private final Label USER_MESSAGE;


	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DetailView(Stage stage)
	{
		final int              X           = 69;
		//BitmapFont font = new BitmapFont();
		//Gdx.files.internal("core/assets/fonts/LondrinaSolid-Regular.bmp")


		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/fonts/LondrinaSolid-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 25;
		BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose();

		Label.LabelStyle LABEL_STYLE = new Label.LabelStyle(font, Color.DARK_GRAY);

		final Label.LabelStyle INFO_STYLE  = new Label.LabelStyle(font, Color.BLACK);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



		BATTLE_CARD_IMAGE = new Image();
		BATTLE_CARD_IMAGE.setBounds(X, 75, 347, 513);
		stage.addActor(BATTLE_CARD_IMAGE);



		USER_MESSAGE = new Label("", LABEL_STYLE);

		USER_MESSAGE.setPosition(98f, 665f);
		USER_MESSAGE.setWidth(300f);
		USER_MESSAGE.setHeight(216f);
		USER_MESSAGE.setAlignment(Align.center);
		USER_MESSAGE.setWrap(true);
		stage.addActor(USER_MESSAGE);

	}

	// ===================================
	// METHODS
	// ===================================

	public void update(BattleCard battleCard)
	{
		BATTLE_CARD_IMAGE.setDrawable(new TextureRegionDrawable(new TextureRegion(battleCard.FRONT_TEXTURE)));


	}

	public void setUserMessage(String userMessage) {
		USER_MESSAGE.setText(userMessage);

	}
}
