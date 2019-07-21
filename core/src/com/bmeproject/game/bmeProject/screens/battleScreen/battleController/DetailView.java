package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Color;
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
	private final Label PLAYER;
	private final Label COMMANDER;
	private final Label FLAVOUR_TEXT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DetailView(Stage stage)
	{
		final float            X          = 60f;
		final float            WIDTH      = 350f;
		final float            FONT_SCALE = 2f;
		final Label.LabelStyle INFO_STYLE = new Label.LabelStyle(new BitmapFont(), Color.BLACK);

		BATTLE_CARD_IMAGE = new Image();
		BATTLE_CARD_IMAGE.setBounds(X, 500f, WIDTH, 513f);

		PLAYER = new Label("", INFO_STYLE);
		PLAYER.setBounds(X, 420f, WIDTH, 60f);
		PLAYER.setFontScale(FONT_SCALE);

		COMMANDER = new Label("", INFO_STYLE);
		COMMANDER.setBounds(X, 360f, WIDTH, 60f);
		COMMANDER.setFontScale(FONT_SCALE);

		FLAVOUR_TEXT = new Label("", INFO_STYLE);
		FLAVOUR_TEXT.setBounds(X, 60f, WIDTH, 300f);
		FLAVOUR_TEXT.setFontScale(FONT_SCALE);
		FLAVOUR_TEXT.setWrap(true);

		stage.addActor(BATTLE_CARD_IMAGE);
		stage.addActor(PLAYER);
		stage.addActor(COMMANDER);
		stage.addActor(FLAVOUR_TEXT);
	}

	// ===================================
	// METHODS
	// ===================================

	public void update(BattleCard battleCard)
	{
		BATTLE_CARD_IMAGE.setDrawable(new TextureRegionDrawable(battleCard.SPRITE));
		PLAYER.setText("Besitzer: " + battleCard.PLAYER.PARTY.giveName());
		COMMANDER.setText("Kontrolle: " + battleCard.commander.PARTY.giveName());
		FLAVOUR_TEXT.setText(
				"Das hier ist ein Flavour Text. Das hier ist ein Flavour Text. Das hier ist ein Flavour Text. Das " +
						"hier ist ein Flavour Text.");
	}
}
