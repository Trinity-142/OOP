import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.Card;
import ru.nsu.sharapov.CardDeck;

public class CardDeckTest {

    @Test
    void randomDecks() {
        CardDeck deck1 = new CardDeck();
        CardDeck deck2 = new CardDeck();
        while (!deck1.empty()) {
            Card card1 = deck1.giveCard();
            Card card2 = deck2.giveCard();
            assertNotEquals(card1, card2);
        }
    }

    @Test
    void deckReset() {
        CardDeck deck = new CardDeck();
        Card card1 = deck.giveCard();
        deck.reset();
        Card card2 = deck.giveCard();
        assertNotEquals(card1.toString(), card2.toString());
    }
}
