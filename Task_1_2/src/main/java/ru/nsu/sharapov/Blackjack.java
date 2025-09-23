package ru.nsu.sharapov;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Enum of game endings.
 */
enum GameEndings {
    PLAYER_WON("Player won"),
    DEALER_WON("Dealer won"),
    DRAW("Draw");
    final String message;

    GameEndings(String msg) {
        message = msg;
    }
}


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
     * Конструктор для тестов.
     *
     * @param deck      Кастомная колода теста
     * @param decisions Итератор ходов игрока
     */
    public Blackjack(CardDeck deck, TestDecisions decisions) {
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
     * @return String "Player won" / "Dealer won" / "Draw"
     */
    public String startRound(int round_num) {
        deck.reset();
        player.reset(deck);
        dealer.reset(deck);
        System.out.printf("Раунд %d\n", round_num);
        System.out.print("Дилер раздал карты\n");
        System.out.printf("Ваши карты: %s\n", player);
        System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.get(0));
        if (player.sum == maxSum) {
            handleWin(player, "Вы выиграли раунд! Счет %d:%d");
            return GameEndings.PLAYER_WON.message;
        }

        System.out.print("Ваш ход\n" + "-------\n");
        while (decisions.askTakeCard() == 1) {
            Card card = player.getCard(deck);
            System.out.printf("Вы открыли карту %s\n", card);
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.get(0));
            if (player.sum > maxSum) {
                handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
                return GameEndings.DEALER_WON.message;
            }
        }

        System.out.print("Ход дилера\n" + "-------\n");
        System.out.printf("Дилер открывает закрытую карту %s\n",
            dealer.hand.get(dealer.hand.size() - 1));
        System.out.printf("Ваши карты: %s\n", player);
        System.out.printf("Карты дилера: %s\n\n", dealer);
        if (dealer.sum == maxSum) {
            handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
            return GameEndings.DEALER_WON.message;
        }
        while (dealer.sum < dealerMinSum) {
            Card card = dealer.getCard(deck);
            System.out.printf("Дилер открывает карту %s\n", card);
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: %s\n\n", dealer);
            if (dealer.sum > maxSum) {
                handleWin(player, "Вы выиграли раунд! Счет %d:%d");
                return GameEndings.PLAYER_WON.message;
            }
        }

        if (player.sum > dealer.sum) {
            handleWin(player, "Вы выиграли раунд! Счет %d:%d");
            return GameEndings.PLAYER_WON.message;
        } else if (player.sum < dealer.sum) {
            handleWin(dealer, "Вы проиграли раунд! Счет %d:%d");
            return GameEndings.DEALER_WON.message;
        } else {
            System.out.print("Ровно!\n");
            return GameEndings.DRAW.message;
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
     * @return true если запуск в режиме тестирования, false иначе
     */
    private boolean handleWin(Player winner, String message) {
        winner.score += 1;
        System.out.printf(message, player.score, dealer.score);

        if (player.score > dealer.score) {
            System.out.println(" в вашу пользу.\n");
        } else if (player.score < dealer.score) {
            System.out.println(" в пользу дилера.\n");
        } else {
            System.out.println(".\n");
        }

        return decisions instanceof TestDecisions;
    }
}