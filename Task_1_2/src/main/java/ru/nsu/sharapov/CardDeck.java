package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Класс колоды карт.
 */
public class CardDeck {

    protected ArrayList<Card> deck = new ArrayList<>();


    /**
     * Конструктор для создания рандомной колоды из 52 карт.
     */
    public CardDeck() {
        init();
    }

    /**
     * Инициализация колоды.
     */
    private void init() {
        for (Suits s : Suits.values()) {
            for (CardRankValue c : CardRankValue.values()) {
                this.deck.add(new Card(c, s));
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Выдать случайную карту из колоды.
     *
     * @return Возвращает выданную карту
     */
    public Card giveCard() {
        return deck.remove(0);
    }

    /**
     * Пересоздать колоду.
     */
    public void reset() {
        deck.clear();
        init();
    }

    public boolean empty() {
        return deck.isEmpty();
    }
}

