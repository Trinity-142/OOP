import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.sharapov.Blackjack;
import ru.nsu.sharapov.Card;
import ru.nsu.sharapov.CardDeck;
import ru.nsu.sharapov.Player;
import ru.nsu.sharapov.TestCardDeck;
import ru.nsu.sharapov.TestDecisions;


public class BlackjackTest {

    @Test
    void playerWin() {
        Card[] test = {
            new Card("Семёрка", "Трефы"),
            new Card("Туз", "Червы"),
            new Card("Шестёрка", "Бубны"),
            new Card("Двойка", "Бубны"),
            new Card("Туз", "Бубны"),
            new Card("Девятка", "Пики")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Player won", game.startRound(1));
    }

    @Test
    void dealerWin() {
        Card[] test = {
            new Card("Двойка", "Трефы"),
            new Card("Дама", "Червы"),
            new Card("Туз", "Бубны"),
            new Card("Пятёрка", "Бубны"),
            new Card("Туз", "Пики")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Dealer won", game.startRound(1));
    }

    @Test
    void draw() {
        Card[] test = {
            new Card("Туз", "Трефы"),
            new Card("Девятка", "Червы"),
            new Card("Король", "Бубны"),
            new Card("Валет", "Пики")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Draw", game.startRound(1));
    }

    @Test
    void playerBust() {
        Card[] test = {
            new Card("Двойка", "Трефы"),
            new Card("Девятка", "Червы"),
            new Card("Король", "Бубны"),
            new Card("Валет", "Пики"),
            new Card("Двойка", "Трефы"),
            new Card("Тройка", "Червы"),
            new Card("Двойка", "Трефы"),
            new Card("Король", "Червы")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 1, 1, 1);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Dealer won", game.startRound(1));
    }

    @Test
    void dealerBust() {
        Card[] test = {
            new Card("Двойка", "Трефы"),
            new Card("Девятка", "Червы"),
            new Card("Король", "Бубны"),
            new Card("Пятёрка", "Пики"),
            new Card("Двойка", "Трефы"),
            new Card("Тройка", "Червы"),
            new Card("Двойка", "Трефы"),
            new Card("Десятка", "Червы")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 1, 1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Player won", game.startRound(1));
    }

    @Test
    void acesValueDowngrade() {
        Card[] test = {
            new Card("Туз", "Трефы"),
            new Card("Туз", "Червы"),
            new Card("Туз", "Бубны")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);
        Player player = new Player();
        for (Card card : test) {
            player.getCard(deck);
        }
        assertEquals(13, player.sum);
    }

    @Test
    void playerBlackjack() {
        Card[] test = {
            new Card("Туз", "Трефы"),
            new Card("Король", "Червы"),
            new Card("Шестёрка", "Бубны"),
            new Card("Двойка", "Бубны")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of();
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Player won", game.startRound(1));
    }

    @Test
    void dealerBlackjack() {
        Card[] test = {
            new Card("Туз", "Трефы"),
            new Card("Восьмёрка", "Червы"),
            new Card("Десятка", "Бубны"),
            new Card("Туз", "Бубны"),
            new Card("Двойка", "Бубны")};
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(test));
        TestCardDeck deck = new TestCardDeck(cards);

        List<Integer> decisions = List.of(1, 0);
        Blackjack game = new Blackjack(deck, new TestDecisions(decisions));
        assertEquals("Dealer won", game.startRound(1));
    }

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
