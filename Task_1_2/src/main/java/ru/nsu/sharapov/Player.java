package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс игрока в блекджек.
 */
public class Player {

    static final int max_sum = 21;
    public int sum;
    ArrayList<Card> hand = new ArrayList<>();
    int aces;
    int score;

    /**
     * Взять карту из колоды.
     *
     * @param deck Колода карт
     * @return Взятая карта
     */
    public Card getCard(CardDeck deck) {
        Card card = deck.giveCard();
        hand.add(card);
        sum += card.value;
        if (Objects.equals(card.rank, "Туз")) {
            aces += 1;
        }
        while (sum > max_sum && aces > 0) {
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
        return hand.toString() + " => " + sum;
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
