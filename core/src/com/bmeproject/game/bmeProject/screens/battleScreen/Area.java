package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.screens.Field;

public class Area implements iFieldable
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final Player PLAYER;
	private final Field  SUPPLY;
	private final Field  GRAVEYARD;
	private final Field  HAND;
	private final Sector SECTOR_LEFT;
	private final Sector SECTOR_CENTER;
	private final Sector SECTOR_RIGHT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Area(Player player)
	{
		PLAYER = player;
		SUPPLY = new Field(this, 542, 41);
		GRAVEYARD = new Field(this, 230, 41);
		HAND = new Field(this, 280, 0);
		SECTOR_LEFT = new Sector(this, new Vector2(259f, 168f), new Vector2(295f, 168f), new Vector2(295f, 119f),
				new Vector2(337f, 176f));
		SECTOR_CENTER = new Sector(this, new Vector2(350f, 85f), new Vector2(386f, 91f), new Vector2(421f, 85f),
				new Vector2(386f, 148f));
		SECTOR_RIGHT = new Sector(this, new Vector2(477f, 119f), new Vector2(477f, 168f), new Vector2(513f, 168f),
				new Vector2(434f, 176f));
	}

	// ===================================
	// METHODS
	// ===================================

	public Stage giveStage()
	{
		return PLAYER.giveStage();
	}

	public float giveRotation()
	{
		return PLAYER.giveRotation();
	}
}
