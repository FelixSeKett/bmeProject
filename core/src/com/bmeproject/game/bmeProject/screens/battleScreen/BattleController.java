package com.bmeproject.game.bmeProject.screens.battleScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.Area;
import com.bmeproject.game.bmeProject.screens.Controller;

import java.util.ArrayList;

public class BattleController extends Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================


	// ===================================
	// METHODS
	// ===================================

	@Override public void update(float delta)
	{
		super.update(delta);
	}

	@Override protected void init(SpriteBatch spriteBatch)
	{
		super.init(spriteBatch);
		Image backgroundImage = new Image(new Texture("core/assets/visuals/spielbrettSmall.png"));
		stage.addActor(backgroundImage);
		initAreas();
	}

	private void initAreas()
	{
		ArrayList<Area> areas     = new ArrayList<>();
		Group           areaGroup = new Group();
		stage.addActor(areaGroup);
		int debug = 450;
		areas.add(new Area(230, debug - 393));
		areas.add(new Area(259, debug - 281));
		areas.add(new Area(295, debug - 281));
		areas.add(new Area(294, debug - 330));
		areas.add(new Area(336, debug - 273));
		areas.add(new Area(351, debug - 365));
		areas.add(new Area(386, debug - 302));
		areas.add(new Area(386, debug - 370));
		areas.add(new Area(422, debug - 364));
		areas.add(new Area(437, debug - 272));
		areas.add(new Area(477, debug - 331));
		areas.add(new Area(477, debug - 282));
		areas.add(new Area(513, debug - 282));
		areas.add(new Area(541, debug - 393));
		for (Area area : areas) {
			areaGroup.addActor(area);
		}

		Card card = BMEProject.allCards.get(1);
		areas.get(0).addCard(card);
		stage.addActor(card);
	}
}
