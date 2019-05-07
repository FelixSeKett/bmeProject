package com.bmeproject.game.bmeProject.screens.loadingScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;
import com.bmeproject.game.bmeProject.screens.Controller;

public class LoadingScreen extends AbstractScreen
{

	public LoadingScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	@Override protected Controller createController()
	{
		return new LoadingController();
	}
}
