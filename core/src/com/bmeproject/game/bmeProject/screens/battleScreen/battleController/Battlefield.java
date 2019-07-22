package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Compass;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

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
		//sektor auf 8 Uhr
		SECTORS.add(new Sector(this, new Vector2(620f, 398f), new Vector2(706f, 398f), new Vector2(706f, 282f),
				new Vector2(808f, 420f)));
		//sektor auf 6 Uhr
		SECTORS.add(new Sector(this, new Vector2(840f, 200f), new Vector2(925f, 215f), new Vector2(1010f, 200f),
				new Vector2(925f, 353f)));
		//sektor auf 4 Uhr
		SECTORS.add(new Sector(this, new Vector2(1145f, 280), new Vector2(1145f, 400f), new Vector2(1230f, 400f),
				new Vector2(1042f, 420f)));
		//sektor auf 2 Uhr
		SECTORS.add(new Sector(this, new Vector2(1230, 578f), new Vector2(1144f, 578f), new Vector2(1144, 695),
				new Vector2(1042f, 555)));
		//sektor auf 12 Uhr
		SECTORS.add(new Sector(this, new Vector2(1010, 775f), new Vector2(925f, 760f), new Vector2(840f, 775f),
				new Vector2(925f, 625f)));
		//sektor auf 10 Uhr
		SECTORS.add(new Sector(this, new Vector2(620f, 578f), new Vector2(706f, 578f), new Vector2(706f, 695f),
				new Vector2(808f, 555f)));
		COMPASS = new Compass(this);
	}

	// ===================================
	// METHODS
	// ===================================

	public ArrayList<Sector> giveSectors()
	{
		return new ArrayList<Sector>(SECTORS);
	}

	public ArrayList<Field> giveAllContainingFields()
	{
		ArrayList<Field> allFields = new ArrayList<Field>();
		for (Sector sector : SECTORS) {
			allFields.addAll(sector.giveFields());
		}
		return allFields;
	}

	public int giveIndexOfSector(Sector sector)
	{
		return SECTORS.indexOf(sector);
	}

	public Sector giveSectorOfIndex(int index)
	{
		return SECTORS.get(index);
	}

	public Field giveNextEmptyQuarterFieldFor(Quarter quarter)
	{
		for (int i = quarter.PLAYER.PARTY.ordinal(); i < SECTORS.size(); i += 2) {
			Field quarterField = SECTORS.get(i).giveQuarterField();
			if (quarterField.giveCards().isEmpty()) {
				return quarterField;
			}
		}
		return null;
	}

	public Sector givePreviousSectorOf(Sector sectorToGivePreviousSectorOf)
	{
		int index = giveIndexOfSector(sectorToGivePreviousSectorOf);
		index -= 1;
		if (index < 0) {
			index += 6;
		}
		return SECTORS.get(index);
	}

	public Sector giveNextSectorOf(Sector sectorToGiveNextSectorOf)
	{
		int index = giveIndexOfSector(sectorToGiveNextSectorOf);
		index += 1;
		if (index > 5) {
			index -= 6;
		}
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
	public void activateZone(Zone zoneToActivate)
	{
		// Erstelle eine nach Strömungsregeln sortierte ArrayList mit Sektoren, die zur aktivierten Zone gehören
		ArrayList<Sector> activeSectors = giveZonedSectors(zoneToActivate);

		// Erstelle eine nach Strömungsregeln sortierte ArrayList aus zu aktivierenden Karten aus der Sektoren-Liste,
		// wobei die äußeren Felder mit Kreaturen und Phänomenen zuerst abgearbeitet werden...
		ArrayList<BattleCard> battleCardsToActivate = new ArrayList<BattleCard>();
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveStream())) {
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
				battleCard.getActivated();
			}
		}

		// setze die HitPoints der BattleCards in dieser Zone zurück
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveStream())) {
				battleCard.resetHitPoints();
			}
		}
		for (Sector sector : activeSectors) {
			sector.giveQuarter().resetHitPoints();
		}

		// Markiere den Spielzug als gestartet
		BATTLE_CONTROLLER.setTurnStarted();

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
