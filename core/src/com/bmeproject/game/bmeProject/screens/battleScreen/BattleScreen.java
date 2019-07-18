package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

public class BattleScreen extends AbstractScreen
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}


	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("BATTLE SCREEN SHOWN");
	}

	@Override public void render(float delta)
	{
		super.render(delta);
	}

	@Override public void resize(int width, int height)
	{
		super.resize(width, height);
	}


	@Override protected Controller createController(SpriteBatch spriteBatch)
	{
		return new BattleController(spriteBatch);
	}


}
