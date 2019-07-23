package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Battlefield;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.ZoneViewer;

import java.util.Random;

public class Compass
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final Battlefield BATTLEFIELD;
	public final ZoneViewer  ZONE_VIEWER;
	private      Stream      stream;
	private      Sector      startSector;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Compass(Battlefield battlefield)
	{
		BATTLEFIELD = battlefield;
		ZONE_VIEWER = new ZoneViewer(this);
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
		final float HALF_WIDTH  = STREAM_IMAGE.getWidth() / 2f;
		final float HALF_HEIGHT = STREAM_IMAGE.getHeight() / 2f;
		STREAM_IMAGE.setPosition(1920/ 2f - HALF_WIDTH, 1080 /2f - HALF_HEIGHT);
		STREAM_IMAGE.setOrigin(HALF_WIDTH, HALF_HEIGHT);
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
		ZONE_VIEWER.updateRotation();
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
		ZONE_VIEWER.update();
	}
}
