package ru.nsu.sharapov;

import java.util.Iterator;
import java.util.List;

/**
 * Реализация для тестирования с заданными ходами игрока.
 */
public class TestDecisions implements Decisions {

    private final Iterator<Integer> decisions;

    /**
     * Конструктор принимающий список ходов.
     *
     * @param decisions Ходы игрока
     */
    public TestDecisions(List<Integer> decisions) {
        this.decisions = decisions.iterator();
    }

    /**
     * Переопределение метода для опроса игрока о принятии решения.
     *
     * @return Выбор игрока: 1 или 0 из итератора ходов.
     */
    @Override
    public int askTakeCard() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        return decisions.next();
    }
}
