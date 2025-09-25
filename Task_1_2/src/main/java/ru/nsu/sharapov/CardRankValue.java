package ru.nsu.sharapov;

/**
 * Enum of card ranks and their values in blackjack.
 */
public enum CardRankValue {
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

    CardRankValue(String rank, Integer value) {
        this.rank = rank;
        this.value = value;
    }
}
