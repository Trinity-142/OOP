package ru.nsu.sharapov;


/**
 * Класс игральной карты.
 */
public class Card {

    String suit;
    String rank;
    Integer value;

    /**
     * Конструктор для тестов, без указания value.
     *
     * @param rank Достоинство карты
     * @param suit Масть карты
     */
    public Card(String rank, String suit) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Конструктор, использующийся при создании колоды.
     *
     * @param rank  Достоинство карты
     * @param suit  Масть карты
     * @param value Количество очков в блекджеке
     */
    public Card(String rank, String suit, Integer value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }


    /**
     * Возвращает строковое представление объекта. Переопределяет стандартную реализацию метода
     * toString() класса Object.
     *
     * @return Строковое представление объекта в формате: {достоинство} {масть} ({очки в блекджеке})
     */
    public String toString() {
        return rank + " " + suit + " (" + value + ")";
    }
}
