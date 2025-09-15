package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CardDeck {

    public static final HashMap<String, Integer> VALUE_MAP = new HashMap<>();
    private static final ArrayList<Card> DECK = new ArrayList<>();

    static {
        VALUE_MAP.put("Двойка", 2);
        VALUE_MAP.put("Тройка", 3);
        VALUE_MAP.put("Четвёрка", 4);
        VALUE_MAP.put("Пятёрка", 5);
        VALUE_MAP.put("Шестёрка", 6);
        VALUE_MAP.put("Семёрка", 7);
        VALUE_MAP.put("Восьмёрка", 8);
        VALUE_MAP.put("Девятка", 9);
        VALUE_MAP.put("Десятка", 10);
        VALUE_MAP.put("Валет", 10);
        VALUE_MAP.put("Дама", 10);
        VALUE_MAP.put("Король", 10);
        VALUE_MAP.put("Туз", 11);
        init();
    }
    private static void init() {
        String[] suits = {"Пики", "Червы", "Трефы", "Бубны"};
        String[] ranks = {"Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
            "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
        for (String suit : suits) {
            for (String rank : ranks) {
                DECK.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(DECK);
    }

    public static Card giveCard() {
        return DECK.removeLast();
    }

    public static void reset(){
        DECK.clear();
        init();
    }
}
