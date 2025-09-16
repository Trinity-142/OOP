package ru.nsu.sharapov;

import java.util.Scanner;

/**
 * Интерфейс принятия решения о взятии карты или конце хода игрока.
 */
interface Decisions {

    int askTakeCard();
}

/**
 * Реализация для CLI режима со сканером ввода игрока.
 */
public class ScannerDecisions implements Decisions {

    private final Scanner scanner;

    /**
     * Конструктор принимающий сканер.
     *
     * @param scanner Сканер
     */
    public ScannerDecisions(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Переопределение метода для опроса игрока о принятии решения.
     *
     * @return Выбор игрока: 1 или 0 со сканера
     */
    @Override
    public int askTakeCard() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
        return scanner.nextInt();
    }
}
