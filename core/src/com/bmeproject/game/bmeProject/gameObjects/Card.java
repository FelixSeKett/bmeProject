package com.bmeproject.game.bmeProject.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

/**
 * Basis aller auf einem Screen darstellbaren Karten. Stellt alle Attribute und Funktionen bereit, die zur formellen
 * Darstellung (Grafik, Akustik) nötig sind. Werden durch

 * {@link DeckCard} für die jeweiligen screens erweitert.
 */
public class Card extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private static final Texture       TEXTURES     = new Texture("core/assets/visuals/texture_atlas.png");
	private static final TextureRegion TEXTURE_BACK = new TextureRegion(TEXTURES, 0, 0, 204, 300);
	protected Sprite sprite;
	protected int CardId;
	protected Vector2 CardPosition;
	protected Type CardType;
	protected String CardName;
	protected int Strengh;
	protected String Decsription;
	protected String illustrationFilePath;




	// ===================================
	// CONSTRUCTOR
	// ===================================

	public Card()
	{
	}

	public void initialize(int id, String cardName, int cardStrengh, String cardIlluFilePath, Type cType)
	{
		//String path = BMEProject.Cards.get().getIllustrationFilePath();
		initializeVisuals(cardIlluFilePath);
		initializeControls();
		initializeData(id, cardName, cardStrengh, cType);

	}

	public void initializeData(int Id, String cardName, int cardStrengh, Type cType)
	{
		this.CardId = Id;
		this.CardName = cardName;
		this.Strengh = cardStrengh;
		this.CardType = cType;
	}

	private void initializeVisuals(String cardIlluFilePath)
	{
		this.illustrationFilePath = cardIlluFilePath;

		sprite = new Sprite(new Texture(illustrationFilePath));
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		sprite.setOrigin(sprite.getX(), sprite.getY());
	}

	private void initializeControls()
	{
		InputListener inputListener = new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				float duration = 0.5f;

				MoveToAction moveToAction = new MoveToAction();
				moveToAction.setPosition(getX() + 100, getY() + 100);
				moveToAction.setDuration(duration);

				RotateByAction rotateByAction = new RotateByAction();
				rotateByAction.setAmount(90);
				rotateByAction.setDuration(duration);

				ParallelAction parallelAction = new ParallelAction(moveToAction, rotateByAction);

				addAction(parallelAction);

				return true;
			}
		};
		addListener(inputListener);
	}

	public int getCardId()
	{
		return this.CardId;
	}

	public String getCardName() { return this.CardName; }

	public Type getCardType() { return this.CardType; }

	/**
	 * Wird jedes Mal aufgerufen, wenn die Position (x- / y-Koordinaten) einer Instanz dieser Klasse geändert wird,
	 * indem die dafür vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Position
	 * des dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void positionChanged()
	{
		sprite.setPosition(getX(), getY());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn die Rotation einer Instanz dieser Klasse geändert wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Gleicht dann die Rotation des
	 * dazugehörigen Sprites an die der Instanz dieser Klasse an.
	 */
	@Override protected void rotationChanged()
	{
		sprite.setRotation(getRotation());
	}

	/**
	 * Wird jedes Mal aufgerufen, wenn eine Instanz dieser Klasse von der Stage gezeichnet wird, indem die dafür
	 * vorgesehene, gleichnamige Methode der Superklasse überschrieben wird. Zeichnet dann auch
	 * den Sprite der Instanz dieser Klasse.
	 *
	 * @param batch
	 * @param parentAlpha
	 */
	@Override public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}
}
