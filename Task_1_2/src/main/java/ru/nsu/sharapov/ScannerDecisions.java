package ru.nsu.sharapov;

import java.util.Scanner;

interface Decisions {
    int askTakeCard();
}

public class ScannerDecisions implements Decisions {
    private final Scanner scanner;

    public ScannerDecisions(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int askTakeCard() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        return scanner.nextInt();
    }
}
