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
	private       Stream      stream;
	private       Sector      startSector;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Compass(Battlefield battlefield)
	{
		BATTLEFIELD = battlefield;
		stream = Stream.CLOCKWISE;
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
