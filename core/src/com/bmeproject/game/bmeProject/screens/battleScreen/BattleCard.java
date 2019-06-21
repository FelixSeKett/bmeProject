package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.gameObjects.Card;

public abstract class BattleCard extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public static final int    WIDTH  = 30;
	public static final int    HEIGHT = 44;
	private final       Card   CARD;
	private final       Player OWNER;
	private final       Sprite SPRITE;
	private             Player commander;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleCard(Player owner, Card card)
	{
		CARD = card;
		OWNER = owner;
		commander = owner;
		SPRITE = new Sprite(new Texture(CARD.ILLUSTRATION_FILE_PATH));
		setBounds(0, 0, 30, 44);
		setOrigin(getWidth() / 2, getHeight() / 2);
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

	public void setCommander(Player commander)
	{
		this.commander = commander;
	}

	public String giveName()
	{
		return CARD.NAME;
	}

	public void takeRotation()
	{
		setRotation(commander.giveRotation());
	}
}
