package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

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

	@Override protected Controller createController()
	{
		return new BattleController();
	}


}
