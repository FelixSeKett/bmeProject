package com.bmeproject.game.bmeProject.entity;

import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.battleScreen.player.Base;
import com.bmeproject.game.bmeProject.battleScreen.player.BattleCard;
import com.bmeproject.game.bmeProject.battleScreen.player.Figure;
import com.bmeproject.game.bmeProject.battleScreen.player.Manipulation;

/**
 * Gibt eine Auswahl aller drei möglichen Kartentypen, die jeweils ein fertiges Design für die oberste visuelle Ebene
 * einer dargestellten Karte mitliefern.
 */
public enum Type
{
	// ===================================
	// ENTRIES
	// ===================================

	BASE("filepath")
			{
				@Override public BattleCard createBattleCard(Entity entity)
				{
					return new Base(entity);
				}
			},
	FIGURE("filepath")
			{
				@Override public BattleCard createBattleCard(Entity entity)
				{
					return new Figure(entity);
				}
			},
	MANIPULATION("filepath")
			{
				@Override public BattleCard createBattleCard(Entity entity)
				{
					return new Manipulation(entity);
				}
			};

	// ===================================
	// ATTRIBUTES
	// ===================================

	public final String TEXTURE_FILE_PATH;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	Type(String textureFilePath)
	{
		TEXTURE_FILE_PATH = textureFilePath;
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	public abstract BattleCard createBattleCard(Entity entity);
}
