package com.bmeproject.game.bmeProject.entity;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.battleScreen.Player;
import com.bmeproject.game.bmeProject.battleScreen.player.Base;
import com.bmeproject.game.bmeProject.battleScreen.player.BattleCard;
import com.bmeproject.game.bmeProject.battleScreen.player.Figure;
import com.bmeproject.game.bmeProject.battleScreen.player.Manipulation;

/**
 * Gibt eine Auswahl aller drei möglichen Kartentypen, die jeweils Koordinaten für die entsprechende TextureRegion
 * der obersten visuellen Ebene einer dargestellten Karte mitliefern.
 */
public enum Type
{
	// ===================================
	// ENTRIES
	// ===================================

	BASE(204, 0)
			{
				@Override public BattleCard createBattleCard(Entity entity, Player player)
				{
					return new Base(entity, player);
				}
			},
	FIGURE(408, 0)
			{
				@Override public BattleCard createBattleCard(Entity entity, Player player)
				{
					return new Figure(entity, player);
				}
			},
	MANIPULATION(612, 0)
			{
				@Override public BattleCard createBattleCard(Entity entity, Player player)
				{
					return new Manipulation(entity, player);
				}
			};

	// ===================================
	// ATTRIBUTES
	// ===================================

	public final int X;
	public final int Y;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Type(int x, int y)
	{
		X = x;
		Y = y;
	}

	// ===================================
	// METHODS
	// ===================================

	public abstract BattleCard createBattleCard(Entity entity, Player player);
}