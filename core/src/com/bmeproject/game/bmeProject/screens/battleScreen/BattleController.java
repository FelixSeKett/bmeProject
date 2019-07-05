package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private static final Texture BACKGROUND = new Texture("core/assets/visuals/spielbrettSmall.png");

	// TODO: Das hier können Konstanten sein - ändern sich während des Spieles nicht
	private      Player      player1;
	private      Player      player2;
	public final Battlefield BATTLEFIELD;

	private Player     activePlayer;
	private boolean    started;
	private BattleCard lastClickedBattleCard;

	// TODO: In ein HUD auslagern
	private ImageButton zoneButton;
	private ImageButton streamButton;
	private ImageButton greenButton;
	private ImageButton redButton;
	private ImageButton blueButton;
	private ImageButton finishButton;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleController(SpriteBatch spriteBatch)
	{
		super(spriteBatch);
		// TODO: Alles, was aus der init-Methode hierhin ausgelagert werden kann hierhin auslagern
		Image backgroundImage = new Image(BACKGROUND);
		stage.addActor(backgroundImage);
		BATTLEFIELD = new Battlefield(this);
		player1 = new Player(this, Party.ALLY);
		player2 = new Player(this, Party.ENEMY);
		activePlayer = player1;
		Gdx.input.setInputProcessor(stage);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override protected void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);

	}

	@Override public void update(float delta)
	{
		super.update(delta);
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			activePlayer.drawTopCard();
		}
	}

	public BattleCard giveLastClickedBattleCard()
	{
		return lastClickedBattleCard;
	}

	public Player giveOppositePlayerOf(Player player)
	{
		if (player == player1) {
			return player2;
		} else {
			return player1;
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
			if (player1 != null) {
				field = player1.giveCurrentFieldOfBattleCard(battleCard);
			}
			// Wenn die BattleCard auf keinem der soeben gecheckten Fields liegt, dann...
			if (field == null) {
				// Checke die Fields vom zweiten Spieler nach der BattleCard!
				if (player2 != null) {
					field = player2.giveCurrentFieldOfBattleCard(battleCard);
				}
			}
		}

		// Wenn die BattleCard auf keinem der soeben gecheckten Fields liegt, gib null zurück!
		return field;
	}

	public void takeLastClickedBattleCard(BattleCard battleCard)
	{
		lastClickedBattleCard = battleCard;
	}

	public void changeActivePlayer()
	{
		Zone.RED.deactivate();
		Zone.GREEN.deactivate();
		Zone.BLUE.deactivate();
		started = false;
		activePlayer = giveOppositePlayerOf(activePlayer);
	}
}
