package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Battlefield;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;

import java.util.Random;

public class Compass
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield BATTLEFIELD;
	private final Image       ZONE_VIEWER;
	private       Stream      stream;
	private       Sector      startSector;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Compass(Battlefield battlefield)
	{
		BATTLEFIELD = battlefield;
		ZONE_VIEWER = generateZoneViewer();
		initStream();
		initStartSector();
	}

	// ===================================
	// METHODS
	// ===================================

	public Stream giveStream()
	{
		return stream;
	}

	public Sector giveStartSector()
	{
		return startSector;
	}

	private Image generateZoneViewer()
	{
		final Stage STAGE           = BATTLEFIELD.BATTLE_CONTROLLER.giveStage();
		final Image ZONE_VIEW_IMAGE = new Image(new Texture("core/assets/visuals/compass/zone_view.png"));
		ZONE_VIEW_IMAGE.setPosition(STAGE.getWidth() / 2f - ZONE_VIEW_IMAGE.getWidth() / 2f,
				STAGE.getHeight() / 2f - ZONE_VIEW_IMAGE.getWidth() / 2f);
		ZONE_VIEW_IMAGE.setOrigin(ZONE_VIEW_IMAGE.getWidth() / 2f, ZONE_VIEW_IMAGE.getHeight() / 2f);
		STAGE.addActor(ZONE_VIEW_IMAGE);
		return ZONE_VIEW_IMAGE;
	}

	private void initStream()
	{
		stream = Stream.CLOCKWISE;
		final Stage STAGE = BATTLEFIELD.BATTLE_CONTROLLER.giveStage();
		final Image STREAM_IMAGE = new Image(new Texture("core/assets/visuals/compass/stream.png"))
		{
			@Override public void act(float delta)
			{
				super.act(delta);
				rotateBy(1f * stream.giveDirection());
			}
		};
		STREAM_IMAGE.setPosition(STAGE.getWidth() / 2f - STREAM_IMAGE.getWidth() / 2f,
				STAGE.getHeight() / 2f - STREAM_IMAGE.getWidth() / 2f);
		STREAM_IMAGE.setOrigin(STREAM_IMAGE.getWidth() / 2f, STREAM_IMAGE.getHeight() / 2f);
		STAGE.addActor(STREAM_IMAGE);
		STREAM_IMAGE.setZIndex(0);
	}

	private void initStartSector()
	{
		/*
		 Starting Sector is implemented here.
		 "Starting" means, that this is the first entry of the underlying ArrayList.
		 TODO: Prüfen: sind die folgenden Ints vereinbar mit den Ints der ArrayList aus dem Battlefield? (Startwert:
		  0 oder 1?)
		 */
		Random random              = new Random();
		int    randomStartingPoint = random.nextInt(6);
		startSector = BATTLEFIELD.giveSectorOfIndex(randomStartingPoint);
	}

	public void toggleStream()
	{
		stream = stream.giveOppositeStream();
	}

	public void proceedStartSector()
	{
		int index = BATTLEFIELD.giveIndexOfSector(startSector) + 1;
		if (index > 5) {
			index -= 6;
		}
		startSector = BATTLEFIELD.giveSectorOfIndex(index);
		updateZoneViewer();
	}

	private void updateZoneViewer()
	{
		float          newRotation    = BATTLEFIELD.giveIndexOfSector(startSector) * 60f;
		RotateToAction rotateToAction = new RotateToAction();
		rotateToAction.setDuration(0.5f);
		rotateToAction.setRotation(newRotation);
		rotateToAction.setUseShortestDirection(true);
		ZONE_VIEWER.addAction(rotateToAction);
	}
}
