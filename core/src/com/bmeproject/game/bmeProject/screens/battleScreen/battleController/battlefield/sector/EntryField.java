package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

public class EntryField extends RingField
{
	public final Texture SHOW_SECTOR = new Texture("core/assets/visuals/card.png");
	public final Sprite SPRITE = new Sprite(SHOW_SECTOR);
	com.badlogic.gdx.scenes.scene2d.ui.Image image1 = new com.badlogic.gdx.scenes.scene2d.ui.Image(SHOW_SECTOR);



	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EntryField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
		//Image image = new Image(SHOW_SECTOR);

		addListener(new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BattleController battleController = FIELD_USER.giveBattleController();
				if (battleController.isGoodToGo()) {
					BattleCard selectedCard = battleController.giveLastClickedBattleCard();
					if (selectedCard != null) {
						if (selectedCard.giveCommander() == FIELD_USER.giveCommander()) {
							addBattleCard(selectedCard);
							battleController.setTurnStarted();
							battleController.resetLastClickedBattleCard();
						}
					}
				}
				return true;
			}
		});
	}

	// ===================================
	// METHODS
	// ===================================

	@Override public RingField givePreviousField()
	{
		return ((Sector)FIELD_USER).giveLeadField();
	}

	@Override public RingField giveNextField()
	{
		return ((Sector)FIELD_USER).giveEndField();
	}

	@Override public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		float x = getX();
		float y = getY();

		BattleController battleController = FIELD_USER.giveBattleController();
		BattleCard selectedCard = battleController.giveLastClickedBattleCard();

		if(selectedCard != null){
			if (selectedCard.giveCommander() == FIELD_USER.giveCommander()) {
			/*	SPRITE.setColor(Color.RED);
				SPRITE.setPosition(x, y);
				SPRITE.draw(batch, parentAlpha);*/
				image1.setPosition(x,y);
				image1.draw(batch,parentAlpha);
				update();
			}
		}
	}

	public void update()
	{
		SequenceAction sequenceAction = new SequenceAction();

		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(1f);
		alphaAction.setDuration(0.6f);
		sequenceAction.addAction(alphaAction);

		AlphaAction alphaAction2 = new AlphaAction();
		alphaAction2.setAlpha(0f);
		alphaAction2.setDuration(0.6f);
		sequenceAction.addAction(alphaAction2);

		RemoveAction removeAction = new RemoveAction();
		image1.removeAction(removeAction);

		image1.addAction(sequenceAction);
		System.out.println("verschwindeeeee");
	}


}
