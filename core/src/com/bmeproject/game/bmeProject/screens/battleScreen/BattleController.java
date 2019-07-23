package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;
import com.bmeproject.game.bmeProject.screens.endOfGameScreen.EndOfGameScreen;

import java.util.ArrayList;

/*
TODO: Funktionalität
- Buttons mit Pressed und Hovered Bildern versehen
- Kampfanimation implementieren
- Zone bei Aktivierung aufleuchten lassen

TODO: Debug
-

TODO: Kosmetik
- Texturen in TextureRegions umbauen
- LastClickedBattleCard wieder "entklickbar" machen?
- Wenn eine Karte auf der Hand ausgewählt wurde könnte man die möglichen Eintrittsfelder markieren
- Kartenvorder- und Rückseite mit Rahmen versehen?
- Sektoren visuell besser voneinander abgrenzbar machen?
- Zonen-Buttons in der Reihenfolge anordnen: 1. Rot, 2. Grün, 3. Blau
 */

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final  DetailView  DETAIL_VIEW;
	public final  ButtonView  BUTTON_VIEW;
	public final  Battlefield BATTLEFIELD;
	private final Player      PLAYER_1;
	private final Player      PLAYER_2;
	private final BMEProject  BME_PROJECT;

	private Player     activePlayer;
	private boolean    started;
	private BattleCard lastClickedBattleCard;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleController(SpriteBatch spriteBatch, BMEProject bmeProject)
	{
		super(spriteBatch);
		Texture background = new Texture("core/assets/visuals/spielbrettSmall.png");
		background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		Image backgroundImage = new Image(background);
		BME_PROJECT = bmeProject;

		stage.addActor(backgroundImage);
		DETAIL_VIEW = new DetailView(stage);
		BUTTON_VIEW = new ButtonView(this);
		BATTLEFIELD = new Battlefield(this);
		PLAYER_1 = new Player(this, Party.ALLY);
		PLAYER_2 = new Player(this, Party.ENEMY);
		activePlayer = PLAYER_1;
		activePlayer.beginTurn();
		Gdx.input.setInputProcessor(stage);
		showActivePlayerMessage();
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
		if (Gdx.input.justTouched()) {

			Stage stage = giveStage();
			Vector2 vector = new Vector2(Gdx.input.getX(), Gdx.input.getY());
			stage.screenToStageCoordinates(vector);
			Actor clickedActor = stage.hit(vector.x, vector.y, true);
			if (clickedActor != null){
				if(!(clickedActor instanceof BattleCard)){
					resetLastClickedBattleCard();
				}
			}
		}
	}

	/**
	 * Der Spieler soll stattfindende Animationen abwarten müssen, bevor sein Input erneut ausgewertet wird. Das soll
	 * dazu führen, dass sich nicht mehrere Animationen überschneiden; grafische Werte so vielleicht
	 * durcheinanderkommen oder die FrameRate in die Knie geht. Diese Methode gibt an, ob noch Animationen
	 * abgewickelt werden oder das Spiel bereit für die nächste Eingabe samt nächster Animation ist.
	 *
	 * @return Gibt true zurück, wenn keine Animation mehr abgewickelt wird.
	 */
	public boolean isGoodToGo()
	{
		return BUTTON_VIEW.isGoodToGo() && BATTLEFIELD.isGoodToGo() && PLAYER_1.isGoodToGo() && PLAYER_2.isGoodToGo();
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
		setTurnUnstarted();
		BUTTON_VIEW.fadeInZoneButtons();
		Player nextPlayer = giveOppositePlayerOf(activePlayer);
		nextPlayer.beginTurn();
		activePlayer = nextPlayer;
		updateAllFields();
		showActivePlayerMessage();
	}

	private void showActivePlayerMessage() {
		final Stage STAGE = BATTLEFIELD.BATTLE_CONTROLLER.giveStage();
		String ORDERIMGPATH = activePlayer.PARTY.giveImagePath();
		Texture OrderTexture = new Texture(ORDERIMGPATH);
		final Image ORDER = new Image(OrderTexture);
		OrderTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		ORDER.setPosition(28, 909);
		STAGE.addActor(ORDER);

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
	public void setTurnStarted()
	{
		if (!started) {
			BUTTON_VIEW.fadeOutStartButtons();
			started = true;
		}
	}

	private void setTurnUnstarted()
	{
		if (started) {
			BUTTON_VIEW.fadeInStartButtons();
			started = false;
		}
	}

	// prüft ob jemande bereits 6 Sektoren hat und gibt den Gewinn in der Konsole aus
	public boolean checkForWin()
	{
		EndOfGameScreen endOfGameScreen;
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

        if (allyCounter == 6 ) {
			endOfGameScreen = new EndOfGameScreen(BME_PROJECT, this, true);
			BME_PROJECT.setEndOfGameScreen(endOfGameScreen);
            return true;

        }
        else if (enemyCounter == 6) {
			endOfGameScreen = new EndOfGameScreen(BME_PROJECT, this, false);
			BME_PROJECT.setEndOfGameScreen(endOfGameScreen);
            return true;
        }

        return false;
    }

    public BMEProject getProject(){
        return BME_PROJECT;
    }

}
