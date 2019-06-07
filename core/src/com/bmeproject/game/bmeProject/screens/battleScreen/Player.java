package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;


public class Player implements IFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController BATTLE_CONTROLLER;
	private final Party            PARTY;
	private final Field            SUPPLY;
	private final Field            GRAVEYARD;
	private final Field            HAND;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Player(BattleController battleController, Party party)
	{
		BATTLE_CONTROLLER = battleController;
		PARTY = party;
		SUPPLY = new Field(this, 542, 41);
		GRAVEYARD = new Field(this, 230, 41);
		HAND = new Field(this, 280, 0);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public Stage giveStage()
	{
		return BATTLE_CONTROLLER.giveStage();
	}

	@Override public float giveRotation()
	{
		return PARTY.giveRotation();
	}


	public void drawCardFromSupply()
	{
		Card card = SUPPLY.pullTopCard();
		HAND.addCard(card);
	}

	public void endTurn()
	{
		BATTLE_CONTROLLER.changeActivePlayer();
	}


}
