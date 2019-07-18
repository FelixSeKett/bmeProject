package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.Player;
import com.bmeproject.game.bmeProject.userData.User;



import java.security.acl.Owner;

public class Quarter extends BattleCard
{

	// ===================================
	// CONSTRUCTORS
	// ===================================
private int quarterEnemyCounter;
private int quarterAlleyCounter;

	public Quarter(Player owner, Card card)
	{
		super(owner, card, 3);

	}

	// ===================================
	// METHODS
	// ===================================

	@Override public void activate()
	{
		Gdx.app.log(toString(), "Ich bin ein Quatier und dürfte garnicht reden können!");
	}

	@Override public void getDestroyed()
	{
		commander = PLAYER.BATTLE_CONTROLLER.giveOppositePlayerOf(commander);
		checkForWin();
	}

	private void checkForWin() {
		checkQuarterOwner();
		showWinMessage();
	}

	private void showWinMessage() {
		if (quarterEnemyCounter==6){
			System.out.println("YouLoose");
		}
		if (quarterAlleyCounter==6){
			System.out.println("YouWin");
		}
		else{
			//das spiel geht weiter
		}


	}



	private void checkQuarterOwner() {
			for (int i = 0;  i<=5; i++) {
				if (PLAYER.PARTY.equals("ALLEY")&& CARD.TYPE.equals("QUARTER")){
					quarterAlleyCounter=0;
					quarterAlleyCounter++;
					System.out.println("deine Quartiere"+quarterAlleyCounter);


				}
				else{
					quarterEnemyCounter=0;
					quarterEnemyCounter++;
					System.out.println("Feindliche Quartiere"+quarterEnemyCounter);


				}

			}


	}
	
}





