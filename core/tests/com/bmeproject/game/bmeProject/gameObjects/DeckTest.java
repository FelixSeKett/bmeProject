package com.bmeproject.game.bmeProject.gameObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void getDeck() {
        ArrayList<Integer> deckB = deck.getDeck();
        assertNotNull(deckB);
    }

    @Test
    void getSize() {
        assertTrue(deck.getSize() > 0);
    }
}