package ru.nsu.sharapov;


import java.util.Objects;

/**
 * Класс игральной карты.
 */
public class Card {

    private final Suits suit;
    private final CardRankValue card;

    /**
     * Constructor.
     *
     * @param card Rank and value in blackjack
     * @param suit Suit
     */
    public Card(CardRankValue card, Suits suit) {
        this.card = card;
        this.suit = suit;
    }

    public CardRankValue getCardRankValue() {
        return this.card;
    }

    /**
     * Equals override to compare cards correctly.
     *
     * @param other the reference object with which to compare.
     * @return equals or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.card == otherCard.card && this.suit == otherCard.suit;
    }

    /**
     * Hash function override for using hashset correctly.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(card, suit);
    }

    /**
     * Возвращает строковое представление объекта. Переопределяет стандартную реализацию метода
     * toString() класса Object.
     *
     * @return Строковое представление объекта в формате: {достоинство} {масть} ({очки в блекджеке})
     */
    public String toString() {
        return card.rank + " " + suit + " (" + card.value + ")";
    }
}
