package com.bmeproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bmeproject.game.BMEProject;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 450;
		new LwjglApplication(new BMEProject(), config);
	}
}
