package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.bmeproject.game.bmeProject.screens.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;

import java.util.ArrayList;

public abstract class FieldUser
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	protected final ArrayList<Field> FIELDS; // Muss aus Kapselungsgründen protected bleiben!

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public FieldUser()
	{
		FIELDS = new ArrayList<Field>();
	}

	// ===================================
	// METHODS
	// ===================================

	public abstract BattleController giveBattleController();

	public abstract Field giveCurrentFieldOfBattleCard(BattleCard battleCard);

	public abstract Player giveCommander();
}
