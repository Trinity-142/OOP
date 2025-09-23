package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Number of existing card ranks and their points in the game.
 */
enum Cards {
    TWO("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четвёрка", 4),
    FIVE("Пятёрка", 5),
    SIX("Шестёрка", 6),
    SEVEN("Семёрка", 7),
    EIGHT("Восьмёрка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);
    final String rank;
    final Integer value;

    Cards(String code, Integer value) {
        this.rank = code;
        this.value = value;
    }
}


/**
 * Класс колоды карт.
 */
public class CardDeck {

    final HashMap<String, Integer> valueMap = new HashMap<>();
    ArrayList<Card> deck = new ArrayList<>();

    {
        for (Cards card : Cards.values()) {
            valueMap.put(card.rank, card.value);
        }
    }

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
        String[] suits = {"Пики", "Червы", "Трефы", "Бубны"};
        String[] ranks = {"Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
            "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
        for (String suit : suits) {
            for (String rank : ranks) {
                this.deck.add(new Card(rank, suit, this.valueMap.get(rank)));
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

