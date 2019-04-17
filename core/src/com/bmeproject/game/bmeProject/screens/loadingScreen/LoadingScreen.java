package com.bmeproject.game.bmeProject.screens.loadingScreen;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class LoadingScreen extends AbstractScreen {

    private static final String TAG = BMEProject.class.getName();

    public LoadingScreen(BMEProject bmeProject) {
        super(bmeProject);
        Gdx.app.debug(TAG, "Screen initialized");
    }
}
