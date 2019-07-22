package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
		BitmapFont font = new BitmapFont();
		final Label.LabelStyle LABEL_STYLE = new Label.LabelStyle(font, Color.DARK_GRAY);
		final Label.LabelStyle INFO_STYLE  = new Label.LabelStyle(font, Color.BLACK);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



		BATTLE_CARD_IMAGE = new Image();
		BATTLE_CARD_IMAGE.setBounds(X, 75, 347, 513);
		USER_MESSAGE = new Label("", LABEL_STYLE);
		USER_MESSAGE.setPosition(98, 665);

		stage.addActor(BATTLE_CARD_IMAGE);
		stage.addActor(USER_MESSAGE);

	}

	// ===================================
	// METHODS
	// ===================================

	public void update(BattleCard battleCard)
	{
		BATTLE_CARD_IMAGE.setDrawable(new TextureRegionDrawable(battleCard.SPRITE));
		USER_MESSAGE.setText("hier werden Hinweise zum Spielzug angezeigt:");

	}
}
