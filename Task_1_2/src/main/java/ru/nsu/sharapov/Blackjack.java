package ru.nsu.sharapov;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Класс игры в блекджек.
 */
public class Blackjack {

    private final CardDeck deck;
    private final Decisions decisions;
    private Player player;
    private Player dealer;

    /**
     * Конструктор для запускка в CLI режиме.
     */
    public Blackjack() {
        this.deck = new CardDeck();
        this.decisions = new ScannerDecisions(new Scanner(System.in));
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
     * Метод запуска игры.
     *
     * @return В случае тестирования возвращает строку "Player won" / "Dealer won" / "Draw"
     */
    public String play() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Добро пожаловать в Блэкджек!\n");
        int round = 1;
        this.player = new Player();
        this.dealer = new Player();
        roundsLoop:
        while (true) {
            if (this.decisions instanceof ScannerDecisions) {
                this.deck.reset();
            }
            player.reset(this.deck);
            dealer.reset(this.deck);
            System.out.printf("Раунд %d\n", round);
            System.out.print("Дилер раздал карты\n");
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.get(0));
            if (player.sum == 21) {
                round += 1;
                if (handleWin(player, "Вы выиграли раунд! Счет %d:%d")) {
                    return "Player won";
                }
                continue;
            }

            System.out.print("Ваш ход\n" + "-------\n");
            while (this.decisions.askTakeCard() == 1) {
                Card card = player.getCard(this.deck);
                System.out.printf("Вы открыли карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.get(0));
                if (player.sum > 21) {
                    round += 1;
                    if (handleWin(dealer, "Вы проиграли раунд! Счет %d:%d")) {
                        return "Dealer won";
                    }
                    continue roundsLoop;
                }
            }

            System.out.print("Ход дилера\n" + "-------\n");
            System.out.printf("Дилер открывает закрытую карту %s\n",
                dealer.hand.get(dealer.hand.size() - 1));
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: %s\n\n", dealer);
            if (dealer.sum == 21) {
                round += 1;
                if (handleWin(dealer, "Вы проиграли раунд! Счет %d:%d")) {
                    return "Dealer won";
                }
                continue;
            }
            while (dealer.sum < 17) {
                Card card = dealer.getCard(this.deck);
                System.out.printf("Дилер открывает карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: %s\n\n", dealer);
                if (dealer.sum > 21) {
                    round += 1;
                    if (handleWin(player, "Вы выиграли раунд! Счет %d:%d")) {
                        return "Player won";
                    }
                    continue roundsLoop;
                }
            }

            if (player.sum > dealer.sum) {
                if (handleWin(player, "Вы выиграли раунд! Счет %d:%d")) {
                    return "Player won";
                }
            } else if (player.sum < dealer.sum) {
                if (handleWin(dealer, "Вы проиграли раунд! Счет %d:%d")) {
                    return "Dealer won";
                }
            } else {
                System.out.print("Ровно!\n");
                if (this.decisions instanceof TestDecisions) {
                    return "Draw";
                }
            }
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
        System.out.printf(message, this.player.score, this.dealer.score);

        if (this.player.score > this.dealer.score) {
            System.out.println(" в вашу пользу.\n");
        } else if (this.player.score < this.dealer.score) {
            System.out.println(" в пользу дилера.\n");
        } else {
            System.out.println(".\n");
        }

        return this.decisions instanceof TestDecisions;
    }
}