package com.bmeproject.game.bmeProject.archive;

import com.bmeproject.game.bmeProject.screens.BattleScreen;
import com.bmeproject.game.bmeProject.dataAccess.CardContainer;

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

		ArrayList<CardContainer> entities = BATTLE_SCREEN.BME_PROJECT.getProfile().getDeck().getEntities();
		for (CardContainer cardContainer : entities) { battleCards.add(cardContainer.createBattleCardOfYourself()); }
		return battleCards;
	}
}
