package com.bmeproject.game.bmeProject.archive;

import com.bmeproject.game.bmeProject.screens.BattleScreen;

public class Player
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final BattleScreen          BATTLE_SCREEN;
	private      Area                  area;

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
	//	battleCards = transformDeck();
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

	//private ArrayList<BattleCard> transformDeck()

}
