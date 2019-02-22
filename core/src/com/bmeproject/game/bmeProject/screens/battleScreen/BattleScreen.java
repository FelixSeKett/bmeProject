package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.archive.Player;
import com.bmeproject.game.bmeProject.screens.AbstractScreen;

public class BattleScreen extends AbstractScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Player player1;
	private Player player2;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public BattleScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// PROCEDURES
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("BATTLE SCREEN SHOWN");
		initializePlayers();
	}

	@Override public void render(float delta)
	{
		super.render(delta);
		getActivePlayer().manageEvent();
	}

	private void initializePlayers()
	{
		player1 = new Player(this);
		player2 = new Player(this);
		player1.initialize();
		player2.initialize();
		player1.startTurn();
	}

	// ===================================
	// FUNCTIONS
	// ===================================

	private Player getActivePlayer()
	{
		if (player1.getEvent() != null) { return player1; }
		else { return player2; }
	}
}
