package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.testScreen.TestCard;

/**
 * Nur für Testzwecke.
 */
public class TestScreen extends TheatricalScreen
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private TestCard testCard;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public TestScreen(BMEProject bmeProject)
	{
		super(bmeProject);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void show()
	{
		super.show();
		System.out.println("TEST SCREEN SHOWN");

		testCard = new TestCard();
		testCard.initialize();
		stage.addActor(testCard);
	}
}
