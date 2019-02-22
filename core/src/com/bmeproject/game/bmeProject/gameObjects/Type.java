package com.bmeproject.game.bmeProject.gameObjects;

import com.bmeproject.game.bmeProject.archive.*;
import com.bmeproject.game.bmeProject.dataAccess.CardContainer;

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
				@Override public BattleCard createBattleCard(CardContainer cardContainer, Player player)
				{
					return new Base(cardContainer);
				}
			},
	FIGURE(408, 0)
			{
				@Override public BattleCard createBattleCard(CardContainer cardContainer, Player player)
				{
					return new Figure(cardContainer);
				}
			},
	MANIPULATION(612, 0)
			{
				@Override public BattleCard createBattleCard(CardContainer cardContainer, Player player)
				{
					return new Manipulation(cardContainer);
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

	public abstract BattleCard createBattleCard(CardContainer cardContainer, Player player);
}