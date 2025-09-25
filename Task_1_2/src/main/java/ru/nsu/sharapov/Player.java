package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс игрока в блекджек.
 */
public class Player {

    private static final int blackjackScore = 21;
    private final ArrayList<Card> hand = new ArrayList<>();
    private int sum;
    private int aces;
    private int score;

    /**
     * Player sum getter.
     *
     * @return Player sum
     */
    public int getSum() {
        return this.sum;
    }

    /**
     * Player score getter.
     *
     * @return Player score in rounds
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Player score setter.
     *
     * @param score New player score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Player hand getter.
     *
     * @return Player hand
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /**
     * Взять карту из колоды.
     *
     * @param deck Колода карт
     * @return Взятая карта
     */
    public Card getCard(CardDeck deck) {
        Card card = deck.giveCard();
        hand.add(card);
        sum += card.getCardRankValue().value;
        if (Objects.equals(card.getCardRankValue(), CardRankValue.ACE)) {
            aces += 1;
        }
        while (sum > blackjackScore && aces > 0) {
            aces -= 1;
            sum -= 10;
        }
        return card;
    }

    /**
     * Возвращает строковое представление объекта. Переопределяет стандартную реализацию метода
     * toString() класса Object.
     *
     * @return Строковое представление объекта в формате: [карты на руках игрока] => сумма очков
     */
    public String toString() {
        return hand + " => " + sum;
    }

    /**
     * Раздает новые карты игроку, не обнуляет его счёт по раундам.
     *
     * @param deck Колода карт
     */
    public void reset(CardDeck deck) {
        this.hand.clear();
        this.aces = 0;
        this.sum = 0;
        this.getCard(deck);
        this.getCard(deck);
    }

}
