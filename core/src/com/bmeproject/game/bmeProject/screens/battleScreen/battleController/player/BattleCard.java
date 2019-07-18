package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Zone;

import java.util.ArrayList;

public abstract class BattleCard extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public static final int WIDTH  = 30;
	public static final int HEIGHT = 44;

	protected final Player PLAYER;
	public final    Card   CARD;
	private final   int    DEFAULT_HIT_POINTS;
	public final    Sprite SPRITE;

	protected Player commander;
	protected int    currentHitPoints;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleCard(Player player, Card card, int defaultHitPoints)
	{
		PLAYER = player;
		CARD = card;
		DEFAULT_HIT_POINTS = defaultHitPoints;
		commander = PLAYER;
		currentHitPoints = DEFAULT_HIT_POINTS;
		SPRITE = new Sprite(new Texture(CARD.ILLUSTRATION_FILE_PATH));
		setBounds(0, 0, 30, 44);
		setOrigin(getWidth() / 2, getHeight() / 2);

		addListener(new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				if (PLAYER.isActive()) {
					if (BattleCard.this.isOnHand()) {
						PLAYER.BATTLE_CONTROLLER.takeLastClickedBattleCard(BattleCard.this);
						Gdx.app.log(toString(), giveName() + " selected");
					}
				}
				return true;
			}

			@Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (PLAYER.isActive()) {
					if (BattleCard.this.isOnBattlefield() || BattleCard.this.isOnHand() ||
							BattleCard.this.isOnGraveyard()) {
						PLAYER.BATTLE_CONTROLLER.DETAIL_VIEW.update(BattleCard.this);
					}
				}
			}
		});
	}

	// ===================================
	// METHODS
	// ===================================

	/**
	 * Wird jedes Mal aufgerufen, wenn die Position (x- / y-Koordinaten) einer Instanz dieser Klasse geändert wird,
	 * indem die dafür vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Position
	 * des dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void positionChanged()
	{
		SPRITE.setPosition(getX(), getY());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn die Rotation einer Instanz dieser Klasse geändert wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Rotation des
	 * dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void rotationChanged()
	{
		SPRITE.setRotation(getRotation());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn eine Instanz dieser Klasse von der Stage gezeichnet wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Zeichnet dann auch
	 * den Sprite der Instanz dieser Klasse.
	 *
	 * @param batch
	 * @param parentAlpha
	 */
	@Override public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		SPRITE.draw(batch);
	}

	@Override public void setBounds(float x, float y, float width, float height)
	{
		super.setBounds(x, y, width, height);
		SPRITE.setBounds(x, y, width, height);
	}

	@Override public void setOrigin(float x, float y)
	{
		super.setOrigin(x, y);
		SPRITE.setOrigin(x, y);
	}

	public abstract void activate();

	public String giveName()
	{
		return CARD.NAME;
	}

	public Player giveCommander()
	{
		return commander;
	}

	public boolean isOnHand()
	{
		return PLAYER.giveHand().giveCards().contains(this);
	}

	public boolean isOnGraveyard()
	{
		return PLAYER.giveGraveyard().giveCards().contains(this);
	}

	public void takeRotation()
	{
		setRotation(commander.PARTY.giveRotation());
	}

	public void resetHitPoints()
	{
		currentHitPoints = DEFAULT_HIT_POINTS;
	}

	public void takeDamage()
	{
		currentHitPoints -= 1;
		if (currentHitPoints <= 0) {
			currentHitPoints = 0;
			getDestroyed();
		}
	}

	public boolean isOnBattlefield()
	{
		return PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveCurrentFieldOfBattleCard(this) != null;
	}

	public ArrayList<Zone> giveActivatingZones()
	{
		return CARD.TYPE.giveActivatingZones();
	}

	public abstract void getDestroyed();


}
