package com.bmeproject.game.bmeProject.screens.testScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.util.CameraHelper;

import java.util.HashMap;

public class TestController extends InputAdapter {

    public Sprite[] testSprites;
    public HashMap<Integer, Card> allCards;
    public int selectedCard;
    public CameraHelper cameraHelper;

    public TestController(HashMap allCards){
        this.allCards = allCards;
        init();
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        initObjects();
    }

    private void initObjects() {
        testSprites = new Sprite[allCards.size()];
        for(int key : allCards.keySet())
        {
            Sprite spr = allCards.get(key).getSprite();
            spr.setSize(1,1);
            spr.setOrigin(spr.getWidth()/2.0f, spr.getHeight() / 2.0f);
            float randomX = MathUtils.random(-2.0f, 2.0f);
            float randomY = MathUtils.random(-2.0f, 2.0f);
            spr.setPosition(randomX, randomY);
            testSprites[key-1] = spr;
        }
    }

    public void update(float deltaTimes){
        cameraHelper.update(deltaTimes);
    }

    //initObjects + updateObjects + Controls
}
