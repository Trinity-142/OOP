package ru.nsu.sharapov;

import java.util.Iterator;
import java.util.List;

public class TestDecisions implements Decisions {
    private Iterator<Integer> decisions;

    public TestDecisions(List<Integer> decisions) {
        this.decisions = decisions.iterator();
    }

    @Override
    public int askTakeCard() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        return decisions.next();
    }
}
