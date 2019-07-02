package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Zone;

import java.util.ArrayList;

public abstract class BattleCard extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public static final int    WIDTH  = 30;
	public static final int    HEIGHT = 44;
	private final       Player PLAYER;
	private final       Card   CARD;
	private final       int    DEFAULT_HIT_POINTS;
	private final       Sprite SPRITE;
	private             Player commander;
	private             int    currentHitPoints;

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
				PLAYER.setLastClickedBattleCard(BattleCard.this);
				return true;
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

	public void resetHitPoints()
	{
		currentHitPoints = DEFAULT_HIT_POINTS;
	}

	public void takeDamage()
	{
		currentHitPoints -= 1;
		if (currentHitPoints <= 0) {
			// TODO: Gabriels Methode getDestroyed() aufrufen!
		}
	}

	// TODO: Hier prüfen: Ist das gegenwärtige Feld ein Feld des Battlefields? Die Ausgabe des gegenwärtigen Felds
	//  schreibt Gabriel
	public boolean isOnBattlefield()
	{
		return true;
	}

	public ArrayList<Zone> giveActivatingZones()
	{
		return CARD.TYPE.giveActivatingZones();
	}

	public abstract void getDestroyed();


}
