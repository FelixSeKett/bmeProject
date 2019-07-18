package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public class DetailView
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Image BATTLE_CARD_IMAGE;
	private final Label NAME_LABEL;
	private final Label ACTUAL_NAME;
	private final Label TYPE_LABEL;
	private final Label ACTUAL_TYPE;
	private final Label EFFECT_LABEL;
	private final Label ACTUAL_EFFECT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DetailView(Stage stage)
	{
		final int              X           = 20;
		final Label.LabelStyle LABEL_STYLE = new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY);
		final Label.LabelStyle INFO_STYLE  = new Label.LabelStyle(new BitmapFont(), Color.BLACK);

		BATTLE_CARD_IMAGE = new Image();
		BATTLE_CARD_IMAGE.setBounds(X, 195, 160, 235);
		NAME_LABEL = new Label("", LABEL_STYLE);
		NAME_LABEL.setPosition(X, 180);
		ACTUAL_NAME = new Label("", INFO_STYLE);
		ACTUAL_NAME.setPosition(X, 160);
		TYPE_LABEL = new Label("", LABEL_STYLE);
		TYPE_LABEL.setPosition(X, 130);
		ACTUAL_TYPE = new Label("", INFO_STYLE);
		ACTUAL_TYPE.setPosition(X, 110);
		EFFECT_LABEL = new Label("", LABEL_STYLE);
		EFFECT_LABEL.setPosition(X, 80);
		ACTUAL_EFFECT = new Label("", INFO_STYLE);
		ACTUAL_EFFECT.setPosition(X, 60);

		stage.addActor(BATTLE_CARD_IMAGE);
		stage.addActor(NAME_LABEL);
		stage.addActor(TYPE_LABEL);
		stage.addActor(EFFECT_LABEL);
		stage.addActor(ACTUAL_NAME);
		stage.addActor(ACTUAL_TYPE);
		stage.addActor(ACTUAL_EFFECT);
	}

	// ===================================
	// METHODS
	// ===================================

	public void update(BattleCard battleCard)
	{
		BATTLE_CARD_IMAGE.setDrawable(new TextureRegionDrawable(battleCard.SPRITE));
		NAME_LABEL.setText("Name:");
		TYPE_LABEL.setText("Type:");
		EFFECT_LABEL.setText("Effect:");
		ACTUAL_NAME.setText(battleCard.giveName());
		ACTUAL_TYPE.setText(battleCard.CARD.TYPE.toString());
		ACTUAL_EFFECT.setText("none");
	}
}
