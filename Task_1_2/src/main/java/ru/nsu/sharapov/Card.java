package ru.nsu.sharapov;



public class Card {
    String suit;
    String rank;
    Integer value;
    public Card(String rank, String suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String rank, String suit, Integer value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }




    public String toString() {
        return suit + " " + rank + " (" + value + ")";
    }
}
