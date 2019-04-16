package com.bmeproject.game.bmeProject.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.util.Constants;

import java.awt.*;
import java.util.HashMap;

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
	protected int Strength;
	protected String description;
	protected String illustrationFilePathLarge;
	protected String illustrationFilePathSmall;
	private Skin skinLibgdx;

	protected HashMap<Integer, Effect> effectList  = new HashMap<Integer, Effect>();
	protected  BMEProject project;


// ===================================
	// CONSTRUCTOR
	// ===================================

	public Card(int id, String name, Type type, String Effect, String description, BMEProject bmeProject)
	{
		this.CardId = id;
		this.CardName = name;
		this.CardType = type;
		project = bmeProject;
		this.description = description;
		String Path = "core/assets/cards";
		String PathLarge = Path.concat("/large/card_id_");
		String PathSmall = Path.concat("/small/card_id_");
		this.illustrationFilePathLarge = PathLarge.concat( Integer.toString(id)).concat(".png");
		this.illustrationFilePathSmall = PathSmall.concat( Integer.toString(id)).concat(".png");
		buildEffect(Effect);
		getCardEffects();

	}

	public void getCardEffects(){
		for (int i=0; i<=effectList.size()-1; i++){
			System.out.println(effectList.get(i).getEffectDescription());
		}
	}

	private void buildEffect(String Effect){
		String[] effectID = Effect.split(",");
		int counter= 0;

		for (String id: effectID) {
			effectList.put(counter, project.getEffectFromAllEffects(Integer.parseInt(id)));
			counter++;
		}

	}

	public HashMap<Integer,Effect> getAllCardEffects(){
		return effectList;
	}

	public void initialize()
	{
		//String path = BMEProject.Cards.get().getIllustrationFilePath();
		initializeVisuals();
		//initializeControls();
		showDetails();
	}

	private void hideDetails() {
		InputListener inputListener = new InputListener()
		{
			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				initializeVisuals();
				showDetails();
				return true;
			}
		};
		addListener(inputListener);
	}

	private void showDetails() {
		final BitmapFont font = new BitmapFont();
		InputListener inputListener = new InputListener()
		{

			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				sprite = new Sprite(new Texture(illustrationFilePathLarge));
				setWidth(sprite.getWidth());
				setHeight(sprite.getHeight());
				sprite.setOrigin(sprite.getX(), sprite.getY());
				//font.draw(batch, "Beschreibung: "+ decsription,10,10);
				System.out.println("Name: "+ CardName);
				System.out.println("Type: "+ CardType);
				System.out.println("Beschreibung: "+ description);
				skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI),
						new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));
				Table tbl = new Table();
				tbl.pad(10, 10, 0, 10);
				//tbl.add(new Label("Audio", skinLibgdx, "default-font",Color.ORANGE)).colspan(3);
				tbl.row();
				tbl.columnDefaults(0).padRight(10);
				tbl.columnDefaults(1).padRight(10);				hideDetails();

				return true;
			}
		};
		addListener(inputListener);
	}

	private void initializeVisuals()
	{

		sprite = new Sprite(new Texture(illustrationFilePathSmall));
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
