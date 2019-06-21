package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

import java.util.ArrayList;

public class Player
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final BattleController      BATTLE_CONTROLLER;
	private final Party                 PARTY;
	private final Field                 SUPPLY;
	private final Field                 GRAVEYARD;
	private final Field                 HAND;
	private final ArrayList<BattleCard> CARDS;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Player(BattleController battleController, Party party)
	{
		BATTLE_CONTROLLER = battleController;
		PARTY = party;
		Stage stage = BATTLE_CONTROLLER.giveStage();
		SUPPLY = new Field(stage, PARTY.giveSupplyVector());
		GRAVEYARD = new Field(stage, PARTY.giveGraveyardVector());
		HAND = new Field(stage, PARTY.giveHandVector());
		CARDS = new ArrayList<>();

		for (Card card : BMEProject.allCards.values()) {
			Type type = card.TYPE;
			for (int i = 0; i < 2; i++) {
				BattleCard battleCard = type.createBattleCard(this, card);
				CARDS.add(battleCard);
				stage.addActor(battleCard);
			}
		}
		for (BattleCard battleCard : CARDS) {
			SUPPLY.addCard(battleCard);
		}
	}

	// ===================================
	// METHODS
	// ===================================

	public float giveRotation()
	{
		return PARTY.giveRotation();
	}

	public void beginTurn()
	{
		drawTopCard();
		/* The decision if the Player want to change the direction of the compass. or if the Player want
		 *  to draw a second card. */
	}

	public void drawTopCard()
	{
		BattleCard card = SUPPLY.pullTopCard();
		HAND.addCard(card);
	}

	public void endTurn()
	{
		BATTLE_CONTROLLER.changeActivePlayer();
	}
}
