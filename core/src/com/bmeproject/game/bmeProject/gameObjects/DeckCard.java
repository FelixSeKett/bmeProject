package com.bmeproject.game.bmeProject.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.bmeproject.game.bmeProject.gameObjects.Card;

/*
Grafische Darstellung von Karte:

 */
public class DeckCard
{
	// ===================================
	// PROCEDURES
	// ===================================

	public String CardId;


	// ===================================
	// METHODS
	// ===================================


	private void initializeEffects(String CardId)
	{

	}

	//    private void initializeControls()
	//    {
	//        InputListener inputListener = new InputListener()
	//        {
	//            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	//            {
	//                float duration = 0.5f;
	//
	//                MoveToAction moveToAction = new MoveToAction();
	//                moveToAction.setPosition(getX() + 100, getY() + 100);
	//                moveToAction.setDuration(duration);
	//
	//                RotateByAction rotateByAction = new RotateByAction();
	//                rotateByAction.setAmount(90);
	//                rotateByAction.setDuration(duration);
	//
	//                ParallelAction parallelAction = new ParallelAction(moveToAction, rotateByAction);
	//
	//                addAction(parallelAction);
	//
	//                return true;
	//            }
	//        };
	//        addListener(inputListener);
	//    }

}
