package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

import java.util.ArrayList;

public class Player implements iFieldable {
    // ===================================
    // ATTRIBUTES
    // ===================================

    public final BattleController BATTLE_CONTROLLER;
    public final Party PARTY;
    private final ArrayList<Field> FIELDS; // Muss aus Kapselungsgründen private bleiben!
    private final ArrayList<BattleCard> BATTLE_CARDS; // Muss aus Kapselungsgründen private bleiben!

    // ===================================
    // CONSTRUCTORS
    // ===================================

    public Player(BattleController battleController, Party party) {
        BATTLE_CONTROLLER = battleController;
        PARTY = party;

        // Alle Fields instanziieren
        FIELDS = new ArrayList<Field>();
        FIELDS.add(new Field(this, PARTY.giveSupplyVector()));
        FIELDS.add(new Field(this, PARTY.giveHandVector(), 35));
        FIELDS.add(new Field(this, PARTY.giveGraveyardVector()));

        // Alle Cards als BattleCards instanziieren
        BATTLE_CARDS = new ArrayList<BattleCard>();
        Stage stage = BATTLE_CONTROLLER.giveStage();
        for (Card card : BMEProject.allCards.values()) {
            Type type = card.TYPE;
            for (int i = 0; i < 2; i++) {
                BattleCard battleCard = type.createBattleCard(this, card);
                BATTLE_CARDS.add(battleCard);
                stage.addActor(battleCard);
            }
        }

        // Alle Cards in den eigenen Supply verschieben
        for (BattleCard battleCard : BATTLE_CARDS) {
            giveSupply().addCard(battleCard);
        }
    }

    // ===================================
    // METHODS
    // ===================================

    @Override
    public BattleController giveBattleController() {
        return BATTLE_CONTROLLER;
    }

    @Override
    public Field giveCurrentFieldOfBattleCard(BattleCard battleCard) {
        for (Field field : FIELDS) {
            if (field.giveCards().contains(battleCard)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public Player giveCommander() {
        return this;
    }

    public Field giveSupply() {
        return FIELDS.get(0);
    }

    public Field giveHand() {
        return FIELDS.get(1);
    }

    public Field giveGraveyard() {
        return FIELDS.get(2);
    }

    public boolean isActive() {
        return BATTLE_CONTROLLER.giveActivePlayer() == this;
    }

    // TODO
    public void beginTurn() {
        drawTopCard();
    }

    // TODO
    public void endTurn() {
        BATTLE_CONTROLLER.changeActivePlayer();
    }

    public void drawTopCard() {
        BattleCard card = giveSupply().pullTopCard();

        giveHand().addCard(card);
    }
}


