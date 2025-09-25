import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.Blackjack;
import ru.nsu.sharapov.Blackjack.GameEndings;
import ru.nsu.sharapov.Card;
import ru.nsu.sharapov.CardDeck;
import ru.nsu.sharapov.CardRankValue;
import ru.nsu.sharapov.Decisions;
import ru.nsu.sharapov.Player;
import ru.nsu.sharapov.Suits;

class TestCardDeck extends CardDeck {

    /**
     * Subclass constructor for tests.
     *
     * @param deck Custom card deck
     */
    public TestCardDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    @Override
    public void reset() {
    }
}

class TestDecisions implements Decisions {

    private final Iterator<Integer> decisions;

    /**
     * Конструктор принимающий список ходов.
     *
     * @param decisions Ходы игрока
     */
    public TestDecisions(List<Integer> decisions) {
        this.decisions = decisions.iterator();
    }

    @Override
    public int askTakeCard() {
        return decisions.next();
    }
}

public class BlackjackTest {

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

        List<Integer> decisions = List.of(1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.PLAYER_WON, game.startRound(1));
    }

    @Test
    void dealerWin() {
        Card[] test = {
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.QUEEN, Suits.HEARTS),
            new Card(CardRankValue.ACE, Suits.DIAMONDS),
            new Card(CardRankValue.FIVE, Suits.DIAMONDS),
            new Card(CardRankValue.ACE, Suits.SPADES)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.DEALER_WON, game.startRound(1));
    }

    @Test
    void draw() {
        Card[] test = {
            new Card(CardRankValue.ACE, Suits.CLUBS),
            new Card(CardRankValue.NINE, Suits.HEARTS),
            new Card(CardRankValue.KING, Suits.DIAMONDS),
            new Card(CardRankValue.JACK, Suits.SPADES)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.DRAW, game.startRound(1));
    }

    @Test
    void playerBust() {
        Card[] test = {
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.NINE, Suits.HEARTS),
            new Card(CardRankValue.KING, Suits.DIAMONDS),
            new Card(CardRankValue.JACK, Suits.SPADES),
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.THREE, Suits.HEARTS),
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.KING, Suits.HEARTS)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 1, 1, 1);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.DEALER_WON, game.startRound(1));
    }

    @Test
    void dealerBust() {
        Card[] test = {
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.NINE, Suits.HEARTS),
            new Card(CardRankValue.KING, Suits.DIAMONDS),
            new Card(CardRankValue.FIVE, Suits.SPADES),
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.THREE, Suits.HEARTS),
            new Card(CardRankValue.TWO, Suits.CLUBS),
            new Card(CardRankValue.TEN, Suits.HEARTS)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 1, 1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.PLAYER_WON, game.startRound(1));
    }

    @Test
    void acesValueDowngrade() {
        Card[] test = {
            new Card(CardRankValue.ACE, Suits.CLUBS),
            new Card(CardRankValue.ACE, Suits.HEARTS),
            new Card(CardRankValue.ACE, Suits.DIAMONDS)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);
        Player player = new Player();
        for (Card card : test) {
            player.getCard(deck);
        }
        assertEquals(13, player.getSum());
    }

    @Test
    void playerBlackjack() {
        Card[] test = {
            new Card(CardRankValue.ACE, Suits.CLUBS),
            new Card(CardRankValue.KING, Suits.HEARTS),
            new Card(CardRankValue.SIX, Suits.DIAMONDS),
            new Card(CardRankValue.TWO, Suits.DIAMONDS)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of();
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.PLAYER_WON, game.startRound(1));
    }

    @Test
    void dealerBlackjack() {
        Card[] test = {
            new Card(CardRankValue.ACE, Suits.CLUBS),
            new Card(CardRankValue.EIGHT, Suits.HEARTS),
            new Card(CardRankValue.TEN, Suits.DIAMONDS),
            new Card(CardRankValue.ACE, Suits.DIAMONDS),
            new Card(CardRankValue.TWO, Suits.DIAMONDS)};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals(GameEndings.DEALER_WON, game.startRound(1));
    }
}
