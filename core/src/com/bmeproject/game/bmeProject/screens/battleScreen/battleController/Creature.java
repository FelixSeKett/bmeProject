package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.Field;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Zone;

import java.util.ArrayList;

public class Creature extends BattleCard
{
	// ===================================
	// CONSTRUCTORS
	// ===================================

	public Creature(Player owner, Card card)
	{
		super(owner, card);
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public int giveDefaultHitpoints()
	{
		return 2;
	}

	@Override public void getActivated(int delay)
	{
//		SequenceAction sequenceAction = new SequenceAction();
//		DelayAction    delayAction    = new DelayAction();
//		delayAction.setDuration(delay * ACTIVATION_DURATION);
//		sequenceAction.addAction(delayAction);
//		RunnableAction runnableAction = new RunnableAction()
//		{
//			@Override public void run()
//			{
				Zone currentZone = giveCurrentZone();
				ArrayList<Field> fields =
						PLAYER.BATTLE_CONTROLLER.BATTLEFIELD.giveRingwiseOrderedFieldsOfZone(currentZone);
				for (int i = (fields.indexOf(giveCurrentField()) + 1); i < fields.size(); i++) {
					ArrayList<BattleCard> fieldCards = fields.get(i).giveCards();
					if (fieldCards.size() > 0) {
						BattleCard potentialTarget = fieldCards.get(0);
						if (potentialTarget.giveCommander() != commander) {
							attack(potentialTarget);
							return;
						}
					}
				}
//			}
//		};
//		sequenceAction.addAction(runnableAction);
//		addAction(sequenceAction);
	}

	@Override public void getDestroyed()
	{
		BMEProject.destroySound1.play(0.5f);
		Field graveyard = PLAYER.giveGraveyard();
		graveyard.addBattleCard(this);
	}

	private void attack(final BattleCard BATTLE_CARD)
	{
		SequenceAction sequenceAction = new SequenceAction();

		MoveToAction attackMovement = new MoveToAction();
		attackMovement.setPosition(BATTLE_CARD.getX(), BATTLE_CARD.getY());
		attackMovement.setDuration(0.5f);
		attackMovement.setInterpolation(Interpolation.bounceOut);
		sequenceAction.addAction(attackMovement);

		MoveToAction returnMovement = new MoveToAction();
		Field        field          = giveCurrentField();
		returnMovement.setPosition(field.getX(), field.getY());
		returnMovement.setDuration(0.5f);
		returnMovement.setInterpolation(Interpolation.sine);
		sequenceAction.addAction(returnMovement);

		//		ScaleToAction scaleUpAction = new ScaleToAction();
		//		scaleUpAction.setScale();

		RunnableAction runnableAction = new RunnableAction()
		{
			@Override public void run()
			{
				BATTLE_CARD.takeDamage();
			}
		};
		sequenceAction.addAction(runnableAction);
		addAction(sequenceAction);
	}
}