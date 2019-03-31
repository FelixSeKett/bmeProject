package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class DeckScreen extends AbstractScreen {
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

		Stage stage = new Stage(new ScreenViewport());

		Table table = new Table(skin);
		table.setFillParent(true);
		stage.addActor(table);

		table.setWidth(stage.getWidth());
		table.align(Align.center | Align.top);

		table.setPosition(0, Gdx.graphics.getHeight());

		TextButton btn = new TextButton("Click me!", skin, "default");

		String str1 = "LALALA";
		String str2 = "TRATATA";

		table.padTop(30);
		table.add(btn).padBottom(30);
		table.row();
		table.add(str2);



	}

}
