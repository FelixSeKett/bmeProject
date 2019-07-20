package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.HandField;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.SupplyField;

import java.util.ArrayList;

public class Player extends FieldUser
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final BattleController BATTLE_CONTROLLER;
	public final Party            PARTY;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Player(BattleController battleController, Party party)
	{
		super();

		BATTLE_CONTROLLER = battleController;
		PARTY = party;

		// Alle Fields instanziieren
		FIELDS.add(new SupplyField(this, PARTY.giveSupplyVector()));
		FIELDS.add(new HandField(this, PARTY.giveHandVector()));
		FIELDS.add(new Field(this, PARTY.giveGraveyardVector()));

		// Alle Cards als BattleCards instanziieren und ihren jeweiligen Start-Fields zuweisen
		Stage stage = BATTLE_CONTROLLER.giveStage();
		for (Card card : BMEProject.allCards.values()) {
			Type       type       = card.TYPE;
			BattleCard battleCard = type.createBattleCard(this, card);
			stage.addActor(battleCard);
		}

		giveSupply().shuffle();

		for (int i = 0; i < 5; i++) {
			drawTopCard();
		}
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public BattleController giveBattleController()
	{
		return BATTLE_CONTROLLER;
	}

	@Override public Field giveCurrentFieldOfBattleCard(BattleCard battleCard)
	{
		for (Field field : FIELDS) {
			if (field.giveCards().contains(battleCard)) {
				return field;
			}
		}
		return null;
	}

	@Override public Player giveCommander()
	{
		return this;
	}

	public ArrayList<Field> giveFields()
	{
		return new ArrayList<Field>(FIELDS);
	}

	public Field giveSupply()
	{
		return FIELDS.get(0);
	}

	public Field giveHand()
	{
		return FIELDS.get(1);
	}

	public Field giveGraveyard()
	{
		return FIELDS.get(2);
	}

	public boolean isActive()
	{
		return BATTLE_CONTROLLER.giveActivePlayer() == this;
	}

	public void beginTurn()
	{
		BATTLE_CONTROLLER.BUTTON_VIEW.updateActivePlayerLabel(this);
		drawTopCard();
	}

	// TODO
	public void endTurn()
	{
		BATTLE_CONTROLLER.changeActivePlayer();
	}

	public void drawTopCard() {
		//Prüfung ob Hand mehr als 7 Karten enthält
		if (giveHand().getPileSize() <= 7) {
			BattleCard card = giveSupply().pullTopCard();
			giveHand().addCard(card);
		}
	}
}


