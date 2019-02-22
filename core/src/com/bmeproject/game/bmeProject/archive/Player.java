package com.bmeproject.game.bmeProject;

import com.bmeproject.game.bmeProject.Screens.BattleScreen;
import com.bmeproject.game.bmeProject.dataAccess.Entity;

import java.util.ArrayList;

public class Player
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final BattleScreen          BATTLE_SCREEN;
	private      Area                  area;
	private      ArrayList<BattleCard> battleCards;
	private      Event                 event;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Player(BattleScreen battleScreen)
	{
		BATTLE_SCREEN = battleScreen;
	}

	// ===================================
	// METHODS
	// ===================================

	public void initialize()
	{
		area = new Area(this);
		battleCards = transformDeck();
	}

	public void startTurn()
	{
		event = Event.PREPARATION;
	}

	public void manageEvent()
	{
		// TODO
	}

	public Event getEvent()
	{
		return event;
	}

	private ArrayList<BattleCard> transformDeck()
	{
		ArrayList<BattleCard> battleCards = new ArrayList<BattleCard>();

		ArrayList<Entity> entities = BATTLE_SCREEN.BME_PROJECT.getProfile().getDeck().getEntities();
		for (Entity entity : entities) { battleCards.add(entity.createBattleCardOfYourself()); }
		return battleCards;
	}
}
