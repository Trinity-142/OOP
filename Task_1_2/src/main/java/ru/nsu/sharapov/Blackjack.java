package ru.nsu.sharapov;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Blackjack {

    private final CardDeck deck;
    private final Decisions decisions;


    public Blackjack() {
        this.deck = new CardDeck();
        this.decisions = new ScannerDecisions(new Scanner(System.in));
    }

    public Blackjack(CardDeck deck, TestDecisions decisions) {
        this.deck = deck;
        this.decisions = decisions;
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }

    public String play() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Добро пожаловать в Блэкджек!\n");
        int round = 1;
        Player player = new Player();
        Player dealer = new Player();
        roundsLoop:
        while (true) {
            if (this.decisions instanceof ScannerDecisions) this.deck.reset();
            player.reset(this.deck);
            dealer.reset(this.deck);
            System.out.printf("Раунд %d\n", round);
            System.out.print("Дилер раздал карты\n");
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.getFirst());
            if (player.sum == 21) {
                round += 1;
                if (playerWin(player, dealer)) return "Player won";
                continue;
            }

            System.out.print("Ваш ход\n" + "-------\n");
            while (this.decisions.askTakeCard() == 1) {
                Card card = player.getCard(this.deck);
                System.out.printf("Вы открыли карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.getFirst());
                if (player.sum > 21) {
                    round += 1;
                    if (dealerWin(player, dealer)) return "Dealer won";
                    continue roundsLoop;
                }
            }

            System.out.print("Ход дилера\n" + "-------\n");
            System.out.printf("Дилер открывает закрытую карту %s\n", dealer.hand.getLast());
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: %s\n\n", dealer);
            if (dealer.sum == 21) {
                round += 1;
                if (dealerWin(player, dealer)) return "Dealer won";
                continue;
            }
            while (dealer.sum < 17) {
                Card card = dealer.getCard(this.deck);
                System.out.printf("Дилер открывает карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: %s\n\n", dealer);
                if (dealer.sum > 21) {
                    round += 1;
                    if (playerWin(player, dealer)) return "Player won";
                    continue roundsLoop;
                }
            }

            if (player.sum > dealer.sum) {
                if (playerWin(player, dealer)) return "Player won";
            } else if (player.sum < dealer.sum) {
                if (dealerWin(player, dealer)) return "Dealer won";
            } else {
                System.out.print("Ровно!\n");
                if (this.decisions instanceof TestDecisions) return "Draw";
            }
            round += 1;
        }
    }

    public boolean playerWin(Player player, Player dealer) {
        player.score += 1;
        System.out.printf("Вы выиграли раунд! Счет %d:%d", player.score, dealer.score);
        if (player.score > dealer.score) {
            System.out.println(" в вашу пользу.\n");
        } else if (player.score < dealer.score) {
            System.out.println(" в пользу дилера.\n");
        } else {
            System.out.println(".\n");
        }
        return this.decisions instanceof TestDecisions;
    }

    public boolean dealerWin(Player player, Player dealer) {
        dealer.score += 1;
        System.out.printf("Вы проиграли раунд! Счет %d:%d", player.score, dealer.score);
        if (player.score > dealer.score) {
            System.out.println(" в вашу пользу.\n");
        } else if (player.score < dealer.score) {
            System.out.println(" в пользу дилера.\n");
        } else {
            System.out.println(".\n");
        }
        return this.decisions instanceof TestDecisions;
    }
}