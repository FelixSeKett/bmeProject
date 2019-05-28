package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.screens.battleScreen.IFieldable;

import java.util.ArrayList;

/**
 * Das Field dient zur Berechnung der Koordinaten einzelner Spielkarten. Es gilt: Jede auf einem Bildschirm
 * dargestellte Karte "befindet" sich zu jedem Zeitpunkt der Laufzeit "auf einem Field". Das Field kennt alle Karten,
 * die sich auf ihm befinden und berechnet mit jedem Aufruf von {@link #update()} deren Bildschirmkoordinaten auf
 * Basis der Attribute, die ihm bei seiner Instanziierung gegeben werden.
 * Ein Field ist immer eine rechteckige Fläche mit einem Ursprung (x, y), einer Breite (width) und einer Höhe
 * (height). Diese vier Attribute hält die Superklasse Actor.
 * <p>
 * Ein Field muss alle enthaltenen Karten in mindestens einem "Häufchen" (Pile) organisieren. Alle Piles haben
 * Ursprungs- ({@param PILE_X}, {@param PILE_Y}) und Versatzangaben ({@param PILE_OFFSET_X}, {@param PILE_OFFSET_Y}).
 */
public class Field extends Actor
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	private final float           PILE_X;
	private final float           PILE_Y;
	private final float           PILE_OFFSET_X;
	private final float           PILE_OFFSET_Y;
	private final float           CARD_OFFSET_X;
	private final float           CARD_OFFSET_Y;
	private final ArrayList<Card> CARDS;
	private final int             PILE_LIMIT;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	/**
	 * @param x           X-Koordinate des Ursprungs das Fields
	 * @param y           Y-Koordinate des Ursprungs das Fields
	 * @param width       Breite des aufgespannten Fields
	 * @param height      Höhe des aufgespannten Fields
	 * @param pileX       X-Versatz des Ursprungs aller Piles von der X-Koordinate des Fields
	 * @param pileY       Y-Versatz des Ursprungs aller Piles von der Y-Koordinate des Fields
	 * @param pileOffsetX Versatz in X-Richtung von einem Pile zum nächsten
	 * @param pileOffsetY Versatz in Y-Richtung von einem Pile zum nächsten
	 * @param cardOffsetX Versatz in X-Richtung von einer Karte eines Piles zur nächsten Karte des gleichen Piles
	 * @param cardOffsetY Versatz in Y-Richtung von einer Karte eines Piles zur nächsten Karte des gleichen Piles
	 * @param cards       Liste aller Karten, die das Field beinhaltet
	 * @param pileLimit   Limit für Karten in einem Pile und damit Indikatorwert für Gruppierung nachfolgender
	 *                    Karten in einem weiteren Pile.
	 */
	public Field(float x, float y, float width, float height, float pileX, float pileY, float pileOffsetX,
			float pileOffsetY, float cardOffsetX, float cardOffsetY, ArrayList<Card> cards, int pileLimit)
	{
		setBounds(x, y, width, height);
		PILE_X = pileX;
		PILE_Y = pileY;
		PILE_OFFSET_X = pileOffsetX;
		PILE_OFFSET_Y = pileOffsetY;
		CARD_OFFSET_X = cardOffsetX;
		CARD_OFFSET_Y = cardOffsetY;
		CARDS = cards;
		PILE_LIMIT = pileLimit;
	}

	public Field(IFieldable fieldable, float x, float y)
	{
		this(x, y, 30, 44, 0, 0, 0, 0, 0, 0, new ArrayList<Card>(), 1);
		Stage stage = fieldable.giveStage();
		setOrigin(stage.getWidth() / 2 - x, stage.getHeight() / 2 - y);
		setRotation(fieldable.giveRotation());
		stage.addActor(this);
	}

	// ===================================
	// METHODS
	// ===================================

	public void update()
	{
		for (int i = 0, j = 0; i < CARDS.size(); i++) {
			if (i % PILE_LIMIT == 0) {
				j++;
			}
			float x = getX() + PILE_X + i * CARD_OFFSET_X + (j - 1) * PILE_OFFSET_X;
			float y = getY() + PILE_Y + i * CARD_OFFSET_Y + (j - 1) * PILE_OFFSET_Y;
			CARDS.get(i).setPosition(x, y);
			// TODO: Rotation setzen
		}
	}

	public void addCard(Card cardToAdd)
	{
		CARDS.add(cardToAdd);
		update();
	}

	public void removeCard(Card cardToRemove)
	{
		CARDS.remove(cardToRemove);
		update();
	}
}
