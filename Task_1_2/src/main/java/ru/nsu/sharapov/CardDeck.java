package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Класс колоды карт.
 */
public class CardDeck {

    private final HashMap<String, Integer> valueMap = new HashMap<>() {{
        put("Двойка", 2);
        put("Тройка", 3);
        put("Четвёрка", 4);
        put("Пятёрка", 5);
        put("Шестёрка", 6);
        put("Семёрка", 7);
        put("Восьмёрка", 8);
        put("Девятка", 9);
        put("Десятка", 10);
        put("Валет", 10);
        put("Дама", 10);
        put("Король", 10);
        put("Туз", 11);
    }};
    private ArrayList<Card> deck = new ArrayList<>();

    /**
     * Конструктор для тестов.
     *
     * @param deck Кастомная колода теста
     */
    public CardDeck(ArrayList<Card> deck) {
        for (Card card : deck) {
            card.value = this.valueMap.get(card.rank);
        }
        this.deck = deck;
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
}
