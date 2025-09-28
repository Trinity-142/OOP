package ru.nsu.sharapov;

/**
 * Enum of card suits.
 */
public enum Suits {
    SPADES("Пики"),
    HEARTS("Червы"),
    CLUBS("Трефы"),
    DIAMONDS("Бубны");
    final String suit;

    Suits(String suit) {
        this.suit = suit;
    }
}
