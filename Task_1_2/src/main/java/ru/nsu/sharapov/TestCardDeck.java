package ru.nsu.sharapov;

import java.util.ArrayList;

/**
 * Subclass for tests.
 */
public class TestCardDeck extends CardDeck {

    /**
     * Subclass constructor for tests.
     *
     * @param deck Custom card deck
     */
    public TestCardDeck(ArrayList<Card> deck) {
        for (Card card : deck) {
            card.value = this.valueMap.get(card.rank);
        }
        this.deck = deck;
    }

    @Override
    public void reset() {
    }
}
