package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CardDeck {

    private ArrayList<Card> deck = new ArrayList<>();
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


    public CardDeck(ArrayList<Card> deck) {
        for (Card card : deck) {
            card.value = this.valueMap.get(card.rank);
        }
        this.deck = deck;
    }

    public CardDeck() {
        init();
    }

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

    public Card giveCard() {
        return deck.remove(0);
    }

    public void reset(){
        deck.clear();
        init();
    }
}
