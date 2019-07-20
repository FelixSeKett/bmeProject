package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

/*
TODO
- Abfrage beim Setzen von Karten auf Eintrittsfeld: Gehört der Sektor auch dem entsprechenden Spieler?
- Buttons fertig machen
- Kartenrückseite / -Rotation
- Quartiere in Decks limitieren
- TitleScreen fertig machen
 */

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private static final Texture BACKGROUND    = new Texture("core/assets/visuals/spielbrettSmall.png");
	private static final Texture BUTTON_BLUE   = new Texture("core/assets/visuals/buttons/3_blaubuttonSmall.png");
	private static final Texture BUTTON_GREEN  = new Texture("core/assets/visuals/buttons/3_gruenbuttonSmall.png");
	private static final Texture BUTTON_RED    = new Texture("core/assets/visuals/buttons/3_rotbuttonSmall.png");
	private static final Texture BUTTON_ZONE   = new Texture("core/assets/visuals/buttons/3_kompassbuttonSmall.png");
	private static final Texture BUTTON_STREAM =
			new Texture("core/assets/visuals/buttons/3_stroemungsbuttonSmall" + ".png");
	private static final Texture BUTTON_FINISH = new Texture("core/assets/visuals/buttons/3_zubeendenSmall.png");

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
		buttons();
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
		activePlayer = giveOppositePlayerOf(activePlayer);
	}

	public void buttons()
	{
		zoneButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_ZONE)));
		zoneButton.setPosition(620, 380);
		stage.addActor(zoneButton);

		streamButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_STREAM)));
		streamButton.setPosition(620, 330);
		stage.addActor(streamButton);

		greenButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_GREEN)));
		greenButton.setPosition(620, 230);
		stage.addActor(greenButton);


		redButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_RED)));
		redButton.setPosition(620, 180);
		stage.addActor(redButton);

		blueButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_BLUE)));
		blueButton.setPosition(620, 130);
		stage.addActor(blueButton);

		finishButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(BUTTON_FINISH)));
		finishButton.setPosition(620, 20);
		stage.addActor(finishButton);


		clickedImageButton(zoneButton);
	}

	public void clickedImageButton(ImageButton btn)
	{

		btn.addListener(new ActorGestureListener()
		{
			@Override public void tap(InputEvent event, float x, float y, int count, int button)
			{
				System.out.println("Zone successfully clicked");
			}
		});

	}

	/**
	 * Markiert für den aktuellen Spielzug den Zeitpunkt, ab dem eine anfängliche Veränderung für Farbzone und
	 * Strömungsrichtung nicht mehr möglich ist. Soll beim ersten Setzen einer Handkarte oder der ersten Aktivierung
	 * einer Farbzone aufgerufen werden.
	 */
	public void getStarted()
	{
		if (!started) {
			started = true;
		}
	}

	// prüft ob jemande bereits 6 Sektoren hat und gibt den Gewinn in der Konsole aus
	//TODO: Label hinzufügen
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
			System.out.println("YouWin");
		}
		if (enemyCounter == 6) {
			System.out.println("YouLoose");
		} else {
			allyCounter = 0;
			enemyCounter = 0;
		}
	}
}
