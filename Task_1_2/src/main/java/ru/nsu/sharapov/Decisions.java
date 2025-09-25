package ru.nsu.sharapov;

/**
 * Интерфейс принятия решения о взятии карты или конце хода игрока.
 */
public interface Decisions {

    /**
     * Метод для опроса игрока о принятии решения.
     *
     * @return Выбор игрока: 1 или 0 со сканера
     */
    int askTakeCard();
}
