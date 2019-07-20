package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

import java.util.ArrayList;

public abstract class BattleCard extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private static final Interpolation ANIMATION_INTERPOLATION = Interpolation.sine;
	private static final float         ANIMATION_SPEED         = 0.5f;
	public static final  int           WIDTH                   = 70;
	public static final  int           HEIGHT                  = 103;

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
		setBounds(0, 0, 70, 103);
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
				if (BattleCard.this.isOnBattlefield() || BattleCard.this.isOnHand() ||
						BattleCard.this.isOnGraveyard()) {
					PLAYER.BATTLE_CONTROLLER.DETAIL_VIEW.update(BattleCard.this);
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

	@Override public void setScale(float scaleX, float scaleY)
	{
		super.setScale(scaleX, scaleY);
		SPRITE.setScale(scaleX, scaleY);
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

	public Sector giveCurrentSector()
	{
		return PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveCurrentSectorOfBattleCard(this);
	}

	public boolean isOnHand()
	{
		return PLAYER.giveHand().giveCards().contains(this);
	}

	public boolean isOnGraveyard()
	{
		return PLAYER.giveGraveyard().giveCards().contains(this);
	}

	public void moveTo(float x, float y)
	{
		MoveToAction moveToAction = new MoveToAction();
		moveToAction.setDuration(ANIMATION_SPEED);
		moveToAction.setInterpolation(ANIMATION_INTERPOLATION);
		moveToAction.setPosition(x, y);
		addAction(moveToAction);
	}

	public void updateRotation()
	{
		RotateToAction rotateToAction = new RotateToAction();
		rotateToAction.setDuration(ANIMATION_SPEED);
		rotateToAction.setInterpolation(ANIMATION_INTERPOLATION);
		rotateToAction.setRotation(commander.PARTY.giveRotation());
		addAction(rotateToAction);
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

	public void getSelected()
	{
		ScaleToAction scaleToAction = new ScaleToAction();
		scaleToAction.setDuration(0.1f);
		scaleToAction.setScale(1.4f);
		addAction(scaleToAction);
	}

	public void getUnselected()
	{
		ScaleToAction scaleToAction = new ScaleToAction();
		scaleToAction.setDuration(0.1f);
		scaleToAction.setScale(1f);
		addAction(scaleToAction);
	}

	public abstract void getDestroyed();

	public void prepareForBattle()
	{
		PLAYER.giveSupply().addCard(this);
	}
}
