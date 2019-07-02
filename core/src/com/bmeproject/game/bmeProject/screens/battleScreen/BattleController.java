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

import java.util.ArrayList;
import java.util.Random;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player      player1;
	private Player      player2;
	private Battlefield battlefield;
	private Compass     compass;
	private BattleCard  lastClickedBattleCard;
	private Random      random;

	private Player  activePlayer;
	private boolean started;

	private ImageButton zoneButton;
	private ImageButton streamButton;
	private ImageButton greenButton;
	private ImageButton redButton;
	private ImageButton blueButton;
	private ImageButton finishButton;

	// TODO: Das hier muss weg - refactor mit Philadelphia
	private ArrayList<Sector> sectors;

	// ===================================
	// METHODS
	// ===================================

	@Override protected void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		player1 = new Player(this, Party.ALLY);
		player2 = new Player(this, Party.ENEMY);
		battlefield = new Battlefield(this);
		compass = new Compass(this);
		activePlayer = player1;
		Gdx.input.setInputProcessor(stage);
	}

	public void changeActivePlayer()
	{
		Zone.RED.deactivate();
		Zone.GREEN.deactivate();
		Zone.BLUE.deactivate();
		started = false;
		if (activePlayer == player1) {
			activePlayer = player2;
		} else {
			activePlayer = player1;
		}
	}

	private void activate(Zone zone, Player player)
	{
		if (player == activePlayer) {
			if (!zone.isActivated()) {

				// Erstelle eine nach Strömungsregeln sortierte ArrayList mit Sektoren, die zur aktivierten Zone
				// gehören
				ArrayList<Sector> activeSectors = getZone(zone);

				// Erstelle eine nach Strömungsregeln sortierte ArrayList aus Karten aus der Sektoren-Liste
				ArrayList<BattleCard> battleCardsToActivate = new ArrayList<BattleCard>();
				for (Sector sector : activeSectors) {
					for (BattleCard battleCard : sector.giveSortedOuterBattleCards(compass)) {
						if (battleCard.giveActivatingZones().contains(zone)) {
							battleCardsToActivate.add(battleCard);
						}
					}
				}
				for (Sector sector : activeSectors) {
					battleCardsToActivate.add(sector.giveQuarter());
				}

				// aktiviere jede Karte der zuvor erstellen Liste nach Listenreihenfolge
				for (BattleCard battleCard : battleCardsToActivate) {
					if (battleCard.isOnBattlefield()) { // TODO
						battleCard.activate();
					}
				}

				// Setze die Zone als aktiviert
				zone.activate();
			}
		}
	}

	private int spinColorSector()
	{
		sectors = battlefield.getSectors();
		Sector first = compass.getFirstSector();
		int    index = sectors.indexOf(first);
		if (compass.getCurrentStream() == Stream.COUNTERCLOCKWISE) {
			index++;
			if (index >= 6) {
				index = index - 6;
			}
		} else {
			index--;
			if (index < 0) {
				index = index + 6;
			}
		}
		//TODO: Farbrad dreht sich um ein Sector visuel
		compass.setFirstSector(sectors.get(index));

		System.out.println("index von Sector in ArrayList ist = " + index);

		return index;
	}

	// TODO: Die Reihenfolge der Einträge der zurückgegebenen ArrayList soll entsprechend der Strömung sortiert sein
	private ArrayList<Sector> getZone(Zone zone)
	{
		sectors = battlefield.getSectors();
		Sector first   = compass.getFirstSector();
		int    index   = sectors.indexOf(first);
		int    distanz = zone.getColorIndex();
		int    targetSector;
		if (compass.getCurrentStream() == Stream.COUNTERCLOCKWISE) {
			targetSector = (index + distanz);
			if (targetSector >= 6) {
				targetSector = targetSector - 6;
			}
		} else {
			targetSector = (index - distanz);
			if (targetSector < 0) {
				targetSector = targetSector + 6;
			}
		}
		ArrayList<Sector> dist = new ArrayList<Sector>();
		dist.add(sectors.get(targetSector));
		// TODO DODODO ASKING QUESTION: Warum hier incrementiert .. OBOB?
		targetSector++;
		if (targetSector >= 6) {
			targetSector = targetSector - 6;
		}
		dist.add(sectors.get(targetSector));
		return dist;

	}

	/**
	 * Starting Sector is implemented here.
	 * "Starting" means, that this is the first entry of the underlying ArrayList.
	 **/
	public Sector getFirstSector()
	{
		random = new Random();
		sectors = battlefield.getSectors();
		int randomStartingPoint = random.nextInt(6);
		//System.out.println("H(P)immel: " + randomStartingPoint);

		return sectors.get(randomStartingPoint);
	}

	public void setLastClickedBattleCard(BattleCard battleCard)
	{
		lastClickedBattleCard = battleCard;
	}

	public BattleCard giveLastClickedBattleCard()
	{
		return lastClickedBattleCard;
	}

	@Override public void update(float delta)
	{
		super.update(delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			activePlayer.drawTopCard();
		}
		/*
		battlefield.giveSectorOne().giveQuarterField().addListener(new InputListener(){

			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("You clicked it");
				return false;
			}

		});
		*/
	}

	public Player giveOppositePlayerOf(Player player) {
		if(player == player1){
			return player2;
		} else return player1;
	}

	public Field giveCurrentFieldOf(BattleCard cardToAdd) {

		if(battlefield != null) {
			for (Sector sector : battlefield.getSectors()) {
				for (Field field : sector.getFields()) {
					if (field.giveCards().contains(cardToAdd)) {
						return field;
					}

				}
			}
		}

		if(player1 != null) {
			for (Field field : player1.getFIELDS()) {
				if (field.giveCards().contains(cardToAdd)) {
					return field;
				}
			}
		}

		if(player2 != null) {
			for (Field field : player2.getFIELDS()) {
				if (field.giveCards().contains(cardToAdd)) {
					return field;
				}
			}
		}


		return null;

	}
}
