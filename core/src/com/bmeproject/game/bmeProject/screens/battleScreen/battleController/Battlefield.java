package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Compass;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.compass.Stream;

import java.util.ArrayList;
import java.util.Collections;

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

	/**
	 * Der Spieler soll stattfindende Animationen abwarten müssen, bevor sein Input erneut ausgewertet wird. Das soll
	 * dazu führen, dass sich nicht mehrere Animationen überschneiden; grafische Werte so vielleicht
	 * durcheinanderkommen oder die FrameRate in die Knie geht. Diese Methode gibt an, ob noch Animationen
	 * abgewickelt werden oder das Spiel bereit für die nächste Eingabe samt nächster Animation ist.
	 *
	 * @return Gibt true zurück, wenn keine Animation mehr abgewickelt wird.
	 */
	public boolean isGoodToGo()
	{
		for (Sector sector : SECTORS) {
			if (!sector.isGoodToGo()) {
				return false;
			}
		}
		return !COMPASS.ZONE_VIEWER.hasActions();
	}

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
		int startIndex = giveIndexOfSector(COMPASS.giveStartSector());
		for (int i = 0; i < SECTORS.size(); i++) {
			int colorIndex = startIndex + i;
			if (colorIndex > 5) {
				colorIndex -= 6;
			}
			if (SECTORS.get(colorIndex) == sectorToGiveZoneOf) {
				Gdx.app.log(toString(), "Gefundener Sektor: " + colorIndex);
				Gdx.app.log(toString(), "Differenz zu StartSector: " + i);
				return Zone.giveZoneByColorIndex(i);
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

	public void activateZone(Zone zoneToActivate)
	{
		// Markiere den Spielzug als gestartet und blende in Folge dessen die StartButtons aus
		BATTLE_CONTROLLER.setTurnStarted();

		// Blende den Zonen-Button für diese Zone aus.
		BATTLE_CONTROLLER.BUTTON_VIEW.fadeOutButtonOfZone(zoneToActivate);

		// Erstelle eine nach Strömungsregeln sortierte ArrayList mit Sektoren, die zur aktivierten Zone gehören
		ArrayList<Sector> activeSectors = giveZonedSectors(zoneToActivate);

		// Erstelle eine nach Strömungsregeln sortierte ArrayList aus zu aktivierenden Karten aus der Sektoren-Liste...
		ArrayList<BattleCard> battleCardsToActivate = new ArrayList<BattleCard>();

		Gdx.app.log(toString(), "Kreaturen und Phänomene hinzufgügen");

		// ... wobei die äußeren Ringfelder mit Kreaturen und Phänomenen zuerst abgearbeitet werden...
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveStream())) {
				if (battleCard.giveActivatingZones().contains(zoneToActivate)) {
					battleCardsToActivate.add(battleCard);
					Gdx.app.log(toString(), "Card added: " + battleCard.giveName());
				}
			}
		}

		Gdx.app.log(toString(), "Quartiere hinzufgügen");

		// ... und anschließend die inneren Felder mit Quartieren
		// Achtung: Hier zeitgeschuldet noch kein schöner Code!
		ArrayList<BattleCard> quarters = new ArrayList<BattleCard>();
		for (Sector sector : activeSectors) {
			BattleCard quarter = sector.giveQuarter();
			if (quarter.giveActivatingZones().contains(zoneToActivate)) {
				quarters.add(quarter);
				Gdx.app.log(toString(), "Card added: " + quarter.giveName());
			}
		}
		if (COMPASS.giveStream() == Stream.COUNTERCLOCKWISE) {
			Collections.reverse(quarters);
		}
		battleCardsToActivate.addAll(quarters);

		//Testzwecke:
		for (BattleCard battleCard : battleCardsToActivate) {
			Gdx.app.log(toString(), "Zu aktivieren: " + battleCard.giveName());
		}

		// aktiviere jede Karte der zuvor erstellen Liste nach Listenreihenfolge
		for (BattleCard battleCard : battleCardsToActivate) {
			if (battleCard.isOnBattlefield()) {
				battleCard.getActivated();
			}
		}

		// setze die HitPoints aller BattleCards in dieser Zone zurück
		for (Sector sector : activeSectors) {
			for (BattleCard battleCard : sector.giveSortedOuterBattleCards(COMPASS.giveStream())) {
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

		// Fügt den Sektor hinzu, dessen Index der Differenz aus dem Startsektor und der Nummerierung der gesuchten
		// Zone entspricht
		index += zone.ordinal() * 2;
		if (index > 5) {
			index -= 6;
		}
		zonedSectors.add(SECTORS.get(index));

		// Fügt den Sektor hinzu, dessen Index der Differenz aus dem Startsektor und der Nummerierung der gesuchten
		// Zone +1 entspricht
		index++;
		if (index > 5) {
			index -= 6;
		}
		zonedSectors.add(SECTORS.get(index));

		// Reihenfolge der Sektoren bei Strömung im Uhrzeigersinn umkehren
		// Achtung: Hier zeitgeschuldet noch kein schöner Code!
		if (COMPASS.giveStream() == Stream.CLOCKWISE) {
			Collections.reverse(zonedSectors);
		}

		//Testzwecke:
		for (Sector sector : zonedSectors) {
			Gdx.app.log(toString(), "Sector added: " + giveIndexOfSector(sector));
		}

		return zonedSectors;
	}

}
