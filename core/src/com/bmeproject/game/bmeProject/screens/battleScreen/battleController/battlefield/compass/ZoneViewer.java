package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Compass;

public class ZoneViewer extends Image
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Compass COMPASS;
	private final Sprite  VIEWER;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public ZoneViewer(Compass compass)
	{
		super(new Texture("core/assets/visuals/compass/zone_viewer_lights.png"));
		COMPASS = compass;
		VIEWER = new Sprite(new Texture("core/assets/visuals/compass/zone_view.png"));

		final Stage STAGE    = COMPASS.BATTLEFIELD.BATTLE_CONTROLLER.giveStage();
		final float CENTER_X = STAGE.getWidth() / 2f;
		final float CENTER_Y = STAGE.getHeight() / 2f;

		setPosition(CENTER_X - getWidth() / 2f, CENTER_Y - getHeight() / 2f);
		setOrigin(getWidth() / 2f, getHeight() / 2f);
		setColor(1f, 1f, 1f, 0f);
		STAGE.addActor(this);
		setZIndex(1);

		VIEWER.setPosition(CENTER_X - VIEWER.getWidth() / 2f, CENTER_Y - VIEWER.getHeight() / 2f);
		VIEWER.setOrigin(VIEWER.getWidth() / 2f, VIEWER.getHeight() / 2f);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected void rotationChanged()
	{
		super.rotationChanged();
		VIEWER.setRotation(getRotation());
	}

	@Override public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		VIEWER.draw(batch);
	}

	private float giveZoneViewerRotation()
	{
		return COMPASS.BATTLEFIELD.giveIndexOfSector(COMPASS.giveStartSector()) * 60f;
	}

	public void updateRotation()
	{
		setRotation(giveZoneViewerRotation());
	}

	public void update()
	{
		SequenceAction sequenceAction = new SequenceAction();

		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(1f);
		alphaAction.setDuration(0.5f);
		sequenceAction.addAction(alphaAction);

		RotateToAction rotateToAction = new RotateToAction();
		rotateToAction.setDuration(0.5f);
		rotateToAction.setRotation(giveZoneViewerRotation());
		rotateToAction.setUseShortestDirection(true);
		rotateToAction.setInterpolation(Interpolation.pow3);
		sequenceAction.addAction(rotateToAction);

		AlphaAction alphaAction2 = new AlphaAction();
		alphaAction2.setAlpha(0f);
		alphaAction2.setDuration(0.5f);
		sequenceAction.addAction(alphaAction2);

		addAction(sequenceAction);
	}
}
