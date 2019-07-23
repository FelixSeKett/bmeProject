package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.sector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bmeproject.game.bmeProject.screens.battleScreen.BattleController;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.battlefield.Sector;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.FieldUser;
import com.bmeproject.game.bmeProject.screens.battleScreen.battleController.BattleCard;

public class EntryField extends RingField
{
	public final Texture SHOW_SECTOR = new Texture("core/assets/visuals/card.png");
	public final Sprite SPRITE = new Sprite(SHOW_SECTOR);

	// ===================================
	// CONSTRUCTORS
	// ===================================

	public EntryField(FieldUser fieldable, Vector2 position)
	{
		super(fieldable, position);
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
				SPRITE.setColor(Color.RED);
				SPRITE.setPosition(x, y);
				SPRITE.draw(batch, parentAlpha);
			}
		}
	}


}
