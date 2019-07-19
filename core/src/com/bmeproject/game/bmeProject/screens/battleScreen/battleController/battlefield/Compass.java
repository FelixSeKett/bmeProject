package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield;

import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Battlefield;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;

import java.util.Random;

public class Compass
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Battlefield BATTLEFIELD;
	private       Stream      currentStream;
	private       Sector      startSector;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Compass(Battlefield battlefield)
	{
		BATTLEFIELD = battlefield;
		currentStream = Stream.CLOCKWISE;
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

	// ===================================
	// METHODS
	// ===================================

	public Stream giveCurrentStream()
	{
		return currentStream;
	}

	public Sector giveStartSector()
	{
		return startSector;
	}

	public void takeStartSector(Sector startSectorToTake)
	{
		startSector = startSectorToTake;
	}

	private void changeCurrentStream()
	{
		currentStream = currentStream.giveOppositeStream();
	}

	private void changeStartSector()
	{
		int index = BATTLEFIELD.giveIndexOfSector(startSector) - 1;
		if (index < 0) {
			index += 6;
		}
		startSector = BATTLEFIELD.giveSectorOfIndex(index);
	}
}
