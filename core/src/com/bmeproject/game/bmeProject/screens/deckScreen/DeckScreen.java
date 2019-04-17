package com.bmeproject.game.bmeProject.screens.deckScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

public class DeckScreen extends AbstractScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public DeckScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();

		//		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		//
		//		Stage stage = new Stage(new ScreenViewport());
		//
		//		Table table = new Table(skin);
		//		table.setFillParent(true);
		//		stage.addActor(table);
		//
		//		table.setWidth(stage.getWidth());
		//		table.align(Align.center | Align.top);
		//
		//		table.setPosition(0, Gdx.graphics.getHeight());
		//
		//		Card card1 = new Card();
		//		//card1.initialize(2);
		//
		//		Card card2 = new Card();
		//		//card2.initialize(3);
		//
		//		TextButton btn = new TextButton("Click me!", skin, "default");
		//
		//		String str1 = "LALALA";
		//		String str2 = "TRATATA";
		//
		//		table.padTop(30);
		//		table.add(btn).padBottom(30);
		//		table.row();
		//		table.add(str2);
	}

	@Override protected Controller createController()
	{
		return new DeckController();
	}
}
