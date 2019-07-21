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
	private final Label ACTUAL_EFFECT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DetailView(Stage stage)
	{
		final float            X          = 60f;
		final float            WIDTH      = 350f;
		final Label.LabelStyle INFO_STYLE = new Label.LabelStyle(new BitmapFont(), Color.BLACK);

		BATTLE_CARD_IMAGE = new Image();
		BATTLE_CARD_IMAGE.setBounds(X, 500, WIDTH, 513);

		ACTUAL_EFFECT = new Label("", INFO_STYLE);
		ACTUAL_EFFECT.setBounds(X, 60, WIDTH, 420);
		ACTUAL_EFFECT.setWrap(true);
		ACTUAL_EFFECT.setFontScale(2f);

		stage.addActor(BATTLE_CARD_IMAGE);
		stage.addActor(ACTUAL_EFFECT);
	}

	// ===================================
	// METHODS
	// ===================================

	public void update(BattleCard battleCard)
	{
		BATTLE_CARD_IMAGE.setDrawable(new TextureRegionDrawable(battleCard.SPRITE));
		ACTUAL_EFFECT.setText(
				"Das hier ist ein Beispieltext. Das hier ist ein Beispieltext. Das hier ist ein Beispieltext. Das " +
						"hier" + " ist ein Beispieltext.");
	}
}
