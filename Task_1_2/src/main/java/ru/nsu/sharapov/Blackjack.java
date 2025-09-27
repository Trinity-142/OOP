package ru.nsu.sharapov;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * Класс игры в блекджек.
 */
public class Blackjack {

    static final int maxSum = 21;
    static final int dealerMinSum = 17;
    private final CardDeck deck;
    private final Decisions decisions;
    private final Player player = new Player();
    private final Player dealer = new Player();

    /**
     * Конструктор для запускка в CLI режиме.
     */
    public Blackjack() {
        deck = new CardDeck();
        decisions = new ScannerDecisions(new Scanner(System.in));
    }

    /**
     * Constructor with a given deck and user input scanner.
     *
     * @param deck      Кастомная колода теста
     * @param decisions Итератор ходов игрока
     */
    public Blackjack(CardDeck deck, Decisions decisions) {
        this.deck = deck;
        this.decisions = decisions;
    }

    /**
     * Запускает игру в блекджек в CLI режиме.
     *
     * @param args -
     */
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }

    /**
     * Метод запуска раунда игры.
     *
     * @return Element of GameEndings enum
     */
    public GameEndings startRound(int roundNum) {
        deck.reset();
        player.reset(deck);
        dealer.reset(deck);
        System.out.printf("Раунд %d\n", roundNum);
        System.out.print("Дилер раздал карты\n");
        System.out.printf("Ваши карты: %s\n", player);
        System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.getHand().get(0));
        if (player.getSum() == maxSum) {
            handleWin(player, "Вы выиграли раунд! Счет %d:%d");
            return GameEndings.PLAYER_WON;
        }

        System.out.print("Ваш ход\n" + "-------\n");
        while (decisions.askTakeCard() == 1) {
            Card card = player.getCard(deck);
            System.out.printf("Вы открыли карту %s\n", card);
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.getHand().get(0));
            if (player.getSum() > maxSum) {
                handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
                return GameEndings.DEALER_WON;
            }
        }

        System.out.print("Ход дилера\n" + "-------\n");
        System.out.printf("Дилер открывает закрытую карту %s\n",
            dealer.getHand().get(dealer.getHand().size() - 1));
        System.out.printf("Ваши карты: %s\n", player);
        System.out.printf("Карты дилера: %s\n\n", dealer);
        if (dealer.getSum() == maxSum) {
            handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
            return GameEndings.DEALER_WON;
        }
        while (dealer.getSum() < dealerMinSum) {
            Card card = dealer.getCard(deck);
            System.out.printf("Дилер открывает карту %s\n", card);
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: %s\n\n", dealer);
            if (dealer.getSum() > maxSum) {
                handleWin(player, "Вы выиграли раунд! Счет %d:%d");
                return GameEndings.PLAYER_WON;
            }
        }

        if (player.getSum() > dealer.getSum()) {
            handleWin(player, "Вы выиграли раунд! Счет %d:%d");
            return GameEndings.PLAYER_WON;
        } else if (player.getSum() < dealer.getSum()) {
            handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
            return GameEndings.DEALER_WON;
        } else {
            System.out.print("Ровно!\n");
            return GameEndings.DRAW;
        }
    }

    /**
     * Метод запуска игры.
     */
    public void play() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.print("Добро пожаловать в Блэкджек!\n");
        int round = 1;
        while (true) {
            startRound(round);
            round += 1;
        }
    }

    /**
     * Сообщает о победе или поражении игрока, выводит текущий счет.
     *
     * @param winner  Победитель
     * @param message Сообщение о победе или проигрыше
     */
    private void handleWin(Player winner, String message) {
        winner.setScore(winner.getScore() + 1);
        System.out.printf(message, player.getScore(), dealer.getScore());

        if (player.getScore() > dealer.getScore()) {
            System.out.println(" в вашу пользу.\n");
        } else if (player.getScore() < dealer.getScore()) {
            System.out.println(" в пользу дилера.\n");
        } else {
            System.out.println(".\n");
        }
    }

    /**
     * Enum of game endings.
     */
    public enum GameEndings {
        PLAYER_WON("Player won"), DEALER_WON("Dealer won"), DRAW("Draw");
        final String message;

        GameEndings(String msg) {
            message = msg;
        }
    }
}