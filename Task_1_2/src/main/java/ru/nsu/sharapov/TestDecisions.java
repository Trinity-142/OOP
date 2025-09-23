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

    @Override
    public int askTakeCard() {
        return decisions.next();
    }
}
