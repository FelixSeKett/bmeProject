package com.bmeproject.game.bmeProject.testScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.entity.Background;
import com.bmeproject.game.bmeProject.entity.Type;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

public class TestCard extends Card
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TestCard()
	{
		super(new Entity("", 0, "","",null, null));
		super(new Entity("TEST", 10, "TESTIllu","TestilluPath", Type.BASE, Background.GROUND));

	}

	// ===================================
	// METHODS
	// ===================================

	public void initialize()
	{
		initializeVisuals();
		initializeControls();
	}

	private void initializeVisuals()
	{
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		sprite.setOrigin(sprite.getX(), sprite.getY());
	}

	private void initializeControls()
	{
		InputListener inputListener = new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				float duration = 0.5f;

				MoveToAction moveToAction = new MoveToAction();
				moveToAction.setPosition(getX() + 100, getY() + 100);
				moveToAction.setDuration(duration);

				RotateByAction rotateByAction = new RotateByAction();
				rotateByAction.setAmount(90);
				rotateByAction.setDuration(duration);

				ParallelAction parallelAction = new ParallelAction(moveToAction, rotateByAction);

				addAction(parallelAction);

				return true;
			}
		};
		addListener(inputListener);
	}
}
