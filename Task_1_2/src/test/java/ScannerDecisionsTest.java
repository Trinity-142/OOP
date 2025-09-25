import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.Blackjack;
import ru.nsu.sharapov.Blackjack.GameEndings;
import ru.nsu.sharapov.Card;
import ru.nsu.sharapov.CardRankValue;
import ru.nsu.sharapov.ScannerDecisions;
import ru.nsu.sharapov.Suits;

public class ScannerDecisionsTest {

    @Test
    void playerWin() {
        Card[] test = {
            new Card(CardRankValue.SEVEN, Suits.CLUBS),
            new Card(CardRankValue.ACE, Suits.HEARTS),
            new Card(CardRankValue.SIX, Suits.DIAMONDS),
            new Card(CardRankValue.TWO, Suits.DIAMONDS),
            new Card(CardRankValue.ACE, Suits.DIAMONDS),
            new Card(CardRankValue.NINE, Suits.SPADES)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        Scanner decisions = new Scanner("1 0");
        Blackjack game = new Blackjack(deck, new ScannerDecisions(decisions));
        assertEquals(GameEndings.PLAYER_WON, game.startRound(1));
    }
}
