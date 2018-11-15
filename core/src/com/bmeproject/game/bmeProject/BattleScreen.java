package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.battleScreen.Player;
import com.bmeproject.game.bmeProject.theatricalScreen.Card;

public class BattleScreen extends TheatricalScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private Card card;

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
		card = new Card();
		card.setupAttributes("core/assets/visuals/card.png");
		stage.addActor(card);
	}
}
