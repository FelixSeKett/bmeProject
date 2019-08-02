package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

public class EntryField extends RingField
{
	public final float duration = 0.5f;
	public final Texture SHOW_SECTOR = new Texture("core/assets/glow_border.png");
	Image glowBorder = new Image(SHOW_SECTOR);
	RepeatAction repeatAction = new RepeatAction();



	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EntryField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);


		float x = getX();
		float y = getY();
		int zCor = getZIndex() - 1;

		glowBorder.setPosition(x,y);
		glowBorder.setColor(1,1,1,0);
		glowBorder.setHeight(getHeight());
		glowBorder.setWidth(getWidth());
		getStage().addActor(glowBorder);

		glowBorder.setZIndex(zCor);


		addListener(new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				BattleController battleController = FIELD_USER.giveBattleController();
				if (battleController.isGoodToGo()) {
					BattleCard selectedCard = battleController.giveLastClickedBattleCard();
					if (selectedCard != null) {
						if (selectedCard.giveCommander() == FIELD_USER.giveCommander()) {
							BMEProject.cardSound.play(0.4f);
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
	}

	public void showBorder(){
		glowBorder.getActions().clear();

		repeatAction.setAction(Actions.sequence(Actions.fadeOut(duration), Actions.fadeIn(duration)));
		repeatAction.setCount(-1);

		glowBorder.setColor(1,1,1,1);
		glowBorder.addAction(repeatAction);
	}

	public void hideBorder(){
		glowBorder.getActions().clear();
		glowBorder.setColor(1,1,1,0);
	}

}
