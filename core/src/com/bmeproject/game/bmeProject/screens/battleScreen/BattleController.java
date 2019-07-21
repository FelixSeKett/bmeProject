package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

import java.util.ArrayList;

/*
TODO: Funktionalität
- TitleScreen fertig machen
- Drehung Farbkreis implementieren und animieren
- Gewinndarstellung implementieren

TODO: Debug
- Aktivierung Rote / Blaue Zone debuggen
- Aktivierung Grüne Zone debuggen

TODO: Kosmetik
- Buttons mit Pressed und Hovered Bildern versehen
- Button View vom oberen Rand wegrücken
- Interpolation von Bildern lösen
- Text in der FlavourText-Box der DetailView obenbündig machen und schriftgröße erhöhen, ohne einfach hochzuskalieren
- Texturen in TextureRegions umbauen
 */

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final  DetailView  DETAIL_VIEW;
	public final  Battlefield BATTLEFIELD;
	private final Player      PLAYER_1;
	private final Player      PLAYER_2;

	private Player     activePlayer;
	private boolean    started;
	private BattleCard lastClickedBattleCard;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleController(SpriteBatch spriteBatch)
	{
		super(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		DETAIL_VIEW = new DetailView(stage);
		new ButtonView(this);
		BATTLEFIELD = new Battlefield(this);
		PLAYER_1 = new Player(this, Party.ALLY);
		PLAYER_2 = new Player(this, Party.ENEMY);
		activePlayer = PLAYER_1;
		activePlayer.beginTurn();
		Gdx.input.setInputProcessor(stage);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
		if (BMEProject.DEBUG) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				activePlayer.drawTopCard();
			}
		}
	}

	public Player giveActivePlayer()
	{
		return activePlayer;
	}

	public BattleCard giveLastClickedBattleCard()
	{
		return lastClickedBattleCard;
	}

	public Player giveOppositePlayerOf(Player player)
	{
		if (player == PLAYER_1) {
			return PLAYER_2;
		} else {
			return PLAYER_1;
		}
	}

	public Field giveCurrentFieldOfBattleCard(BattleCard battleCard)
	{
		Field field = null;

		// Checke die Fields der Sektoren des Battlefields nach der BattleCard!
		if (BATTLEFIELD != null) {
			field = BATTLEFIELD.giveCurrentFieldOfBattleCard(battleCard);
		}
		// Wenn die BattleCard auf keinem der soeben gecheckten Fields liegt, dann...
		if (field == null) {
			// ... checke die Fields vom ersten Spieler nach der BattleCard!
			if (PLAYER_1 != null) {
				field = PLAYER_1.giveCurrentFieldOfBattleCard(battleCard);
			}
			// Wenn die BattleCard auf keinem der soeben gecheckten Fields liegt, dann...
			if (field == null) {
				// Checke die Fields vom zweiten Spieler nach der BattleCard!
				if (PLAYER_2 != null) {
					field = PLAYER_2.giveCurrentFieldOfBattleCard(battleCard);
				}
			}
		}

		// Wenn die BattleCard auf keinem der soeben gecheckten Fields liegt, gib null zurück!
		return field;
	}

	public void takeLastClickedBattleCard(BattleCard battleCard)
	{
		if (lastClickedBattleCard != null) {
			lastClickedBattleCard.getUnselected();
		}
		battleCard.getSelected();
		lastClickedBattleCard = battleCard;
	}

	public void resetLastClickedBattleCard()
	{
		if (lastClickedBattleCard != null) {
			lastClickedBattleCard.getUnselected();
			lastClickedBattleCard = null;
		}
	}

	public void changeActivePlayer()
	{
		resetLastClickedBattleCard();
		Zone.RED.deactivate();
		Zone.GREEN.deactivate();
		Zone.BLUE.deactivate();
		started = false;
		Player nextPlayer = giveOppositePlayerOf(activePlayer);
		nextPlayer.beginTurn();
		activePlayer = nextPlayer;
		updateAllFields();
	}

	public void updateAllFields()
	{
		ArrayList<Field> allFields = new ArrayList<Field>();
		allFields.addAll(BATTLEFIELD.giveAllContainingFields());
		allFields.addAll(PLAYER_1.giveFields());
		allFields.addAll(PLAYER_2.giveFields());
		for (Field field : allFields) {
			field.update();
		}
	}

	public boolean hasTurnStarted()
	{
		return started;
	}

	/**
	 * Markiert für den aktuellen Spielzug den Zeitpunkt, ab dem eine anfängliche Veränderung für Farbzone und
	 * Strömungsrichtung nicht mehr möglich ist. Soll beim ersten Setzen einer Handkarte oder der ersten Aktivierung
	 * einer Farbzone aufgerufen werden.
	 */
	public void startTurn()
	{
		if (!started) {
			started = true;
		}
	}

	// prüft ob jemande bereits 6 Sektoren hat und gibt den Gewinn in der Konsole aus
	// TODO: Label hinzufügen
	public void checkForWin()
	{
		int allyCounter  = 0;
		int enemyCounter = 0;

		//prüft die Besitzer der einzelnen Sektoren und zählt
		for (Sector sector : BATTLEFIELD.giveSectors()) {
			if (sector.giveCommander().PARTY == Party.ALLY) {
				allyCounter++;
			} else {
				enemyCounter++;
			}
		}

		if (allyCounter == 6) {
			System.out.println("You win");
		}
		if (enemyCounter == 6) {
			System.out.println("You lose");
		} else {
			allyCounter = 0;
			enemyCounter = 0;
		}
	}
}
