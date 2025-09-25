package ru.nsu.sharapov;


/**
 * Класс игральной карты.
 */
public class Card {

    private final Suits suit;
    private final CardRankValue card;

    public CardRankValue getCardRankValue() {
        return this.card;
    }

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
