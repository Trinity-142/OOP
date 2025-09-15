package ru.nsu.sharapov;


import java.io.PrintStream;
import java.util.Scanner;


public class Blackjack {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Добро пожаловать в Блэкджек!\n");
        int round = 1;
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Player dealer = new Player();
        roundsLoop:
        while (true) {
            CardDeck.reset();
            player.reset();
            dealer.reset();
            System.out.printf("Раунд %d\n", round);
            System.out.print("Дилер раздал карты\n");
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.getFirst());
            if (player.sum == 21) {
                round += 1;
                playerWin(player, dealer);
                continue;
            }

            System.out.print("Ваш ход\n" + "-------\n");
            System.out.print("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            while (scanner.nextInt() == 1) {
                Card card = player.getCard();
                System.out.printf("Вы открыли карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: [%s, <закрытая карта>]\n", dealer.hand.getFirst());
                if (player.sum > 21) {
                    round += 1;
                    dealerWin(player, dealer);
                    continue roundsLoop;
                }
                System.out.print("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
            }

            System.out.print("\nХод дилера\n" + "-------\n");
            System.out.printf("Дилер открывает закрытую карту %s\n", dealer.hand.getLast());
            System.out.printf("Ваши карты: %s\n", player);
            System.out.printf("Карты дилера: %s\n\n", dealer);
            if (dealer.sum == 21) {
                round += 1;
                dealerWin(player, dealer);
                continue;
            }
            while (dealer.sum < 17) {
                Card card = dealer.getCard();
                System.out.printf("Дилер открывает карту %s\n", card);
                System.out.printf("Ваши карты: %s\n", player);
                System.out.printf("Карты дилера: %s\n\n", dealer);
                if (dealer.sum > 21) {
                    round += 1;
                    playerWin(player, dealer);
                    continue roundsLoop;
                }
            }
            if (player.sum > dealer.sum) {
                playerWin(player, dealer);
            }
            else if (player.sum < dealer.sum) {
                dealerWin(player, dealer);
            }
            else System.out.printf("Ровно!\n");
            round += 1;
        }
    }

    public static void playerWin(Player player, Player dealer) {
        player.score += 1;
        System.out.printf("Вы выиграли раунд! Счет %d:%d", player.score, dealer.score);
        if (player.score > dealer.score) System.out.println(" в вашу пользу.\n");
        else if (player.score < dealer.score) System.out.println(" в пользу дилера.\n");
        else System.out.println(".\n");
    }

    public static void dealerWin(Player player, Player dealer) {
        dealer.score += 1;
        System.out.printf("Вы проиграли раунд! Счет %d:%d", player.score, dealer.score);
        if (player.score > dealer.score) System.out.println(" в вашу пользу.\n");
        else if (player.score < dealer.score) System.out.println(" в пользу дилера.\n");
        else System.out.println(".\n");
    }
}