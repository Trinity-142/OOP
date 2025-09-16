package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    public int sum;
    ArrayList<Card> hand = new ArrayList<>();
    int aces;
    int score;

    public Card getCard(CardDeck deck) {
        Card card = deck.giveCard();
        hand.add(card);
        sum += card.value;
        if (Objects.equals(card.rank, "Туз")) {
            aces += 1;
        }
        while (sum > 21 & aces > 0) {
            aces -= 1;
            sum -= 10;
        }
        return card;
    }

    public String toString() {
        return hand.toString() + " => " + sum;
    }

    public void reset(CardDeck deck) {
        this.hand.clear();
        this.aces = 0;
        this.sum = 0;
        this.getCard(deck);
        this.getCard(deck);
    }

}
