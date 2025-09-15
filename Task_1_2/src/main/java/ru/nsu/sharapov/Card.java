package ru.nsu.sharapov;



public class Card {
    String suit;
    String rank;
    Integer value;
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = CardDeck.VALUE_MAP.get(rank);
    }


    public String toString() {
        return suit + " " + rank + " (" + value + ")";
    }
}
