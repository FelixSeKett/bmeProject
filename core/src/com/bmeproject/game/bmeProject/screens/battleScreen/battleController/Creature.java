package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;

import java.util.ArrayList;

public class Creature extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Creature(Player owner, Card card)
	{
		super(owner, card, 2);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void activate()
	{
		// Lege eine Liste mit potenziellen Angriffszielen an
		ArrayList<BattleCard> potentialTargets = new ArrayList<BattleCard>();

		// Hole dir eine Liste mit Sektoren aus der Zone, in der sich diese Karte befindet
		ArrayList<Sector> sectors =
				PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveZonedSectors(giveCurrentSector().giveCurrentZone());

		// Füge der Liste mit potenziellen Zielen alle Karten hinzu, die in der Liste aus Sektoren vorkommen
		for (Sector sector : sectors) {
			potentialTargets.addAll(sector
					.giveSortedOuterBattleCards(PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.COMPASS.giveCurrentStream()));
		}
		for (Sector sector : sectors) {
			potentialTargets.add(sector.giveQuarter());
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
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addCard(this);
	}
}