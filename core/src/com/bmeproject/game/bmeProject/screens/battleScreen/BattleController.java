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

	public final  DetailView  DETAIL_VIEW;
	private final Player      PLAYER_1;
	private final Player      PLAYER_2;
	public final  Battlefield BATTLEFIELD;

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
		Image backgroundImage = new Image(BACKGROUND);
		stage.addActor(backgroundImage);
		DETAIL_VIEW = new DetailView(stage);
		BATTLEFIELD = new Battlefield(this);
		PLAYER_1 = new Player(this, Party.ALLY);
		PLAYER_2 = new Player(this, Party.ENEMY);
		activePlayer = PLAYER_1;
		Gdx.input.setInputProcessor(stage);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			activePlayer.drawTopCard();
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
		lastClickedBattleCard = battleCard;
	}

	public void resetLastClickedBattleCard()
	{
		lastClickedBattleCard = null;
	}

	public void changeActivePlayer()
	{
		resetLastClickedBattleCard();
		Zone.RED.deactivate();
		Zone.GREEN.deactivate();
		Zone.BLUE.deactivate();
		started = false;
		activePlayer = giveOppositePlayerOf(activePlayer);
	}
}
