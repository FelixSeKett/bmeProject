package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;

import java.util.ArrayList;

public class Battlefield
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final  BattleController  BATTLE_CONTROLLER;
	public final  Compass           COMPASS;
	private final ArrayList<Sector> SECTORS; // Muss aus Kapselungsgründen private bleiben!

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Battlefield(BattleController battleController)
	{
		BATTLE_CONTROLLER = battleController;
		SECTORS = new ArrayList<Sector>();
		SECTORS.add(new Sector(this, new Vector2(258f, 167f), new Vector2(294f, 167f), new Vector2(294f, 118f),
				new Vector2(336f, 175f)));
		SECTORS.add(new Sector(this, new Vector2(350f, 84f), new Vector2(385f, 90f), new Vector2(420f, 84f),
				new Vector2(385f, 147f)));
		SECTORS.add(new Sector(this, new Vector2(476f, 118), new Vector2(476f, 167f), new Vector2(512f, 167f),
				new Vector2(433f, 175f)));
		SECTORS.add(new Sector(this, new Vector2(512f, 241f), new Vector2(476f, 241f), new Vector2(476f, 290f),
				new Vector2(433f, 232f)));
		SECTORS.add(new Sector(this, new Vector2(420f, 324f), new Vector2(385f, 317f), new Vector2(350f, 324f),
				new Vector2(385f, 261f)));
		SECTORS.add(new Sector(this, new Vector2(294f, 290f), new Vector2(294f, 241f), new Vector2(258f, 241f),
				new Vector2(336f, 232f)));
		COMPASS = new Compass(this);
	}

	// ===================================
	// METHODS
	// ===================================

	public int giveIndexOfSector(Sector sector)
	{
		return SECTORS.indexOf(sector);
	}

	public Sector giveSectorOfIndex(int index)
	{
		return SECTORS.get(index);
	}

	public Zone giveZoneOfSector(Sector sectorToGiveZoneOf)
	{
		int index = giveIndexOfSector(COMPASS.giveStartSector());
		for (int i = 0; i < SECTORS.size(); i++) {
			int colorIndex = index + i;
			if (SECTORS.get(colorIndex) == sectorToGiveZoneOf) {
				return Zone.giveZoneByColorIndex(colorIndex);
			}
		}
		return null;
	}

	public Field giveCurrentFieldOfBattleCard(BattleCard battleCard)
	{
		for (Sector sector : SECTORS) {
			Field field = sector.giveCurrentFieldOfBattleCard(battleCard);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

	public Sector giveCurrentSectorOfBattleCard(BattleCard battleCard)
	{
		for (Sector sector : SECTORS) {
			if (sector.hasBattleCard(battleCard)) {
				return sector;
			}
		}
		return null;
	}

	/*
	TODO: Vor dem Aufruf dieser Methode (Button-Klick) muss gecheckt werden, ob die Zone aktiviert werden kann (nicht,
	 wenn sie im gleichen Spielzug schon einmal aktiviert wurde) und ob der aktivierende Spieler auch der aktive
	 Spieler ist
	*/
	private void activateZone(Zone zoneToActivate)
	{
		// Erstelle eine nach Strömungsregeln sortierte ArrayList mit Sektoren, die zur aktivierten Zone gehören
		ArrayList<Sector> activeSectors = giveZonedSectors(zoneToActivate);

		// Erstelle eine nach Strömungsregeln sortierte ArrayList aus zu aktivierenden Karten aus der Sektoren-Liste,
		// wobei die äußeren Felder mit Kreaturen und Phänomenen zuerst abgearbeitet werden...
		ArrayList<BattleCard> battleCardsToActivate = new ArrayList<BattleCard>();
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveCurrentStream())) {
				if (battleCard.giveActivatingZones().contains(zoneToActivate)) {
					battleCardsToActivate.add(battleCard);
				}
			}
		}
		// ... und anschließend die inneren Felder mit Quartieren
		for (Sector sector : activeSectors) {
			battleCardsToActivate.add(sector.giveQuarter());
		}

		// aktiviere jede Karte der zuvor erstellen Liste nach Listenreihenfolge
		for (BattleCard battleCard : battleCardsToActivate) {
			if (battleCard.isOnBattlefield()) {
				battleCard.activate();
			}
		}

		// setze die HitPoints der BattleCards in dieser Zone zurück
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveCurrentStream())) {
				battleCard.resetHitPoints();
			}
		}
		for (Sector sector : activeSectors) {
			sector.giveQuarter().resetHitPoints();
		}

		// Setze die Zone als aktiviert
		zoneToActivate.activate();
	}

	// TODO: Die Reihenfolge der Einträge der zurückgegebenen ArrayList soll entsprechend der Strömung sortiert sein
	public ArrayList<Sector> giveZonedSectors(Zone zone)
	{
		// Bereitet eine Liste mit Sektoren vor, die zurückgegeben werden soll
		ArrayList<Sector> zonedSectors = new ArrayList<Sector>();

		// Speichert den Index des im Kompass hinterlegten Startsektors
		int index = SECTORS.indexOf(COMPASS.giveStartSector());

		// Fügt den Sektor hinzu, dessen Index der Differenz aus dem Startsektor und dem ColorIndex der gesuchten
		// Zone entspricht
		index -= zone.ordinal() * 2;
		if (index < 0) {
			index += 6;
		}
		zonedSectors.add(SECTORS.get(index));

		// Fügt den Sektor hinzu, dessen Index der Differenz aus dem Startsektor und dem ColorIndex der gesuchten
		// Zone +1 entspricht
		index--;
		if (index < 0) {
			index += 6;
		}
		zonedSectors.add(SECTORS.get(index));

		return zonedSectors;
	}
}
