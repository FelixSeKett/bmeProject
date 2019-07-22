package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

import java.util.ArrayList;

public class Creature extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Creature(Player owner, Card card)
	{
		super(owner, card);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public int giveDefaultHitpoints()
	{
		return 2;
	}

	@Override public void getActivated()
	{
		// Lege eine Liste mit potenziellen Angriffszielen an
		ArrayList<BattleCard> potentialTargets = new ArrayList<BattleCard>();

		// Hole dir eine Liste mit Sektoren aus der Zone, in der sich diese Karte befindet
		Zone currentZone = giveCurrentSector().giveCurrentZone();
		Gdx.app.log(toString(), "Current Zone: " + currentZone);
		ArrayList<Sector> sectors = PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveZonedSectors(currentZone);

		// Füge der Liste mit potenziellen Zielen alle Karten hinzu, die in der Liste aus Sektoren vorkommen
		for (Sector sector : sectors) {
			potentialTargets.addAll(sector
					.giveSortedOuterBattleCards(PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.giveStream()));
		}
		for (Sector sector : sectors) {
			potentialTargets.add(sector.giveQuarter());
		}

		//Testzwecke:
		for (BattleCard battleCard : potentialTargets) {
			Gdx.app.log(toString(), "Potenzielle Ziele: " + battleCard.giveName());
		}

		// Greife die nächste auf diese Karte folgende gegnerische Karte aus der Liste mit potenziellen Zielen an
		int index = potentialTargets.indexOf(this);
		for (int i = index; i < potentialTargets.size() - index; i++) {
			BattleCard target = potentialTargets.get(i);
			if (target.giveCommander() != commander) {
				target.takeDamage();
				return;
			}
		}
	}

	@Override public void getDestroyed()
	{
		BMEProject.destroySound1.play(0.5f);
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addBattleCard(this);
	}
}