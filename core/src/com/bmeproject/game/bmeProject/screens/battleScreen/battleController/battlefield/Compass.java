package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private       Stream      stream;
	private       Sector      startSector;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Compass(Battlefield battlefield)
	{
		BATTLEFIELD = battlefield;
		stream = Stream.CLOCKWISE;
		initStreamImage();
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

	public void takeStartSector(Sector startSectorToTake)
	{
		startSector = startSectorToTake;
	}

	private void initStreamImage()
	{
		final Image STREAM_IMAGE = new Image(new Texture("core/assets/visuals/stream.png"))
		{
			@Override public void act(float delta)
			{
				super.act(delta);
				rotateBy(1f * stream.giveDirection());
			}
		};
		Stage stage = BATTLEFIELD.BATTLE_CONTROLLER.giveStage();
		STREAM_IMAGE.setPosition(stage.getWidth() / 2f - STREAM_IMAGE.getWidth() / 2f,
				stage.getHeight() / 2f - STREAM_IMAGE.getWidth() / 2f);
		STREAM_IMAGE.setOrigin(STREAM_IMAGE.getWidth() / 2f, STREAM_IMAGE.getHeight() / 2f);
		stage.addActor(STREAM_IMAGE);
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
		int index = BATTLEFIELD.giveIndexOfSector(startSector) - 1;
		if (index < 0) {
			index += 6;
		}
		startSector = BATTLEFIELD.giveSectorOfIndex(index);
	}
}
