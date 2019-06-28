package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.bmeProject.screens.Controller;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.*;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.BattleCard;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player.Party;

import java.util.ArrayList;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player      player1;
	private Player      player2;
	private Battlefield battlefield;
	private Compass     compass;
	private BattleCard lastClickedBattleCard;

	private Player  activePlayer;
	private boolean started;

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
				ArrayList<BattleCard> battleCardsToActivate = new ArrayList<>();
				for (Sector sector : activeSectors) {
					for (BattleCard battleCard : sector.giveSortedOuterBattleCards()) {
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
		targetSector++;
		if (targetSector >= 6) {
			targetSector = targetSector - 6;
		}
		dist.add(sectors.get(targetSector));
		return dist;

	}

	public Sector getFirstSector()
	{
		sectors = battlefield.getSectors();
		return sectors.get(0);
	}

	public void setLastClickedBattleCard(BattleCard battleCard){
		lastClickedBattleCard = battleCard;
	}

	public BattleCard giveLastClickedBattleCard(){
		return lastClickedBattleCard;
	}

	@Override
	public void update(float delta)
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
}
