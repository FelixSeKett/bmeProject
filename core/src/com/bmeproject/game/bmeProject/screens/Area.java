package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.ArrayList;

/**
 * Die Area dient zur Berechnung der Koordinaten einzelner Spielkarten. Es gilt: Jede auf einem Bildschirm
 * dargestellte Karte "befindet" sich zu jedem Zeitpunkt der Laufzeit "in einer Area". Die Area kennt alle Karten,
 * die sich auf ihr befinden und berechnet mit jedem Aufruf von {@link #update()} deren Bildschirmkoordinaten auf
 * Basis der Attribute, die ihr bei ihrer eigenen Instanziierung gegeben werden.
 * Eine Area ist immer eine rechteckige Fläche mit einem Ursprung (x, y), einer Breite (width) und einer Höhe
 * (height). Diese vier Attribute hält die Superklasse Actor.
 * <p>
 * Eine Area muss alle enthaltenen Karten in mindestens einem "Häufchen" (Pile) organisieren. Alle Piles haben
 * Ursprungs- ({@param PILE_X}, {@param PILE_Y}) und Versatzangaben ({@param PILE_OFFSET_X},
 * {@param PILE_OFFSET_Y}).
 */
public class Area extends Actor
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
	private final int             CARDS_PER_PILE;

	// ===================================
	// CONSTRUCTORS
	// ===================================

	/**
	 * @param x            X-Koordinate des Ursprungs der Area
	 * @param y            Y-Koordinate des Ursprungs der Area
	 * @param width        Breite der aufgespannten Area
	 * @param height       Höhe der aufgespannten Area
	 * @param pileX        X-Koordinate des Ursprungs aller Piles
	 * @param pileY        Y-Koordinate des Ursprungs aller Piles
	 * @param pileOffsetX  Versatz in X-Richtung von einem Pile zum nächsten
	 * @param pileOffsetY  Versatz in Y-Richtung von einem Pile zum nächsten
	 * @param cardOffsetX  Versatz in X-Richtung von einer Karte eines Piles zur nächsten Karte des gleichen Piles
	 * @param cardOffsetY  Versatz in Y-Richtung von einer Karte eines Piles zur nächsten Karte des gleichen Piles
	 * @param cards        Liste aller Karten, die die Area beinhaltet
	 * @param cardsPerPile Limit für Karten in einem Pile und damit Indikatorwert für Gruppierung nachfolgender
	 *                     Karten in einem weiteren Pile.
	 */
	public Area(float x, float y, float width, float height, float pileX, float pileY, float pileOffsetX,
			float pileOffsetY, float cardOffsetX, float cardOffsetY, ArrayList<Card> cards, int cardsPerPile)
	{
		setBounds(x, y, width, height);
		PILE_X = pileX;
		PILE_Y = pileY;
		PILE_OFFSET_X = pileOffsetX;
		PILE_OFFSET_Y = pileOffsetY;
		CARD_OFFSET_X = cardOffsetX;
		CARD_OFFSET_Y = cardOffsetY;
		CARDS = cards;
		CARDS_PER_PILE = cardsPerPile;
	}

	// ===================================
	// METHODS
	// ===================================

	public void update()
	{
		for (int i = 0, j = 0; i < CARDS.size(); i++) {
			if (i % CARDS_PER_PILE == 0) {
				j++;
			}
			float x = getX() + PILE_X + i * CARD_OFFSET_X + (j - 1) * PILE_OFFSET_X;
			float y = getY() + PILE_Y + i * CARD_OFFSET_Y + (j - 1) * PILE_OFFSET_Y;
			CARDS.get(i).setPosition(x, y);
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
