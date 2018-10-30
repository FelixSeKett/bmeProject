
package com.bmeproject.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Figur extends Actor
{
	// ATTRIBUTES

	Sprite sprite;

	// CONSTRUCTOR

	public Figur()
	{
		sprite = new Sprite(new Texture("core/assets/visuals/card.png"));
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());

	}

	// METHODS

	@Override protected void positionChanged()
	{
		sprite.setPosition(getX(), getY());
	}

	@Override protected void rotationChanged()
	{
		sprite.setRotation(getRotation());
	}

	@Override public void draw(Batch batch, float parentAlpha)
	{
		sprite.draw(batch);
	}
}
