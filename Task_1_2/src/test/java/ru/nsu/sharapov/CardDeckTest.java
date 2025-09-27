package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void deckReset() {
        CardDeck deck = new CardDeck();
        Card card1 = deck.giveCard();
        assertFalse(deck.deck.contains(card1));
        deck.reset();
        assertTrue(deck.deck.contains(card1));
    }

    @Test
    void unique52Cards() {
        CardDeck deck = new CardDeck();
        HashSet<Card> cardSet = new HashSet<>(deck.deck);
        assertEquals(52, deck.deck.size());
        assertEquals(52, cardSet.size());
    }
}
