import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


class Game {
    private Deck deck;
    private List<Player> players;
    private Dealer dealer;

    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    private void resetGameState() {
        deck = new Deck();
        deck.shuffle();
        for (Player player : players) {
            player.getHand().clear();
        }
        dealer.getHand().clear();
    }

    public void play() {
        resetGameState();
        for (Player player : players) {
            player.addCardToHand(deck.draw());
            player.addCardToHand(deck.draw());
        }
        dealer.addCardToHand(deck.draw());
        dealer.addCardToHand(deck.draw());
        for (Player player : players) {
            System.out.println(player.getName() + "'s hand: " + player.displayHand());
            boolean hit = true;
            while (hit && player.getHandValue() < 21) {
                System.out.print("Hit or stand? (h/s): ");
                Scanner scanner = new Scanner(System.in);
                String hitOrStand = scanner.nextLine();
                if (hitOrStand.equalsIgnoreCase("h")) {
                    player.addCardToHand(deck.draw());
                    System.out.println(player.getName() + "'s hand: " + player.displayHand());
                } else {
                    hit = false;
                }
            }
        }
        while (dealer.getHandValue() < 17) {
            dealer.addCardToHand(deck.draw());
        }
        for (Player player : players) {
            int playerHandValue = player.getHandValue();
            int dealerHandValue = dealer.getHandValue();
            if (playerHandValue > 21) {
                System.out.println(player.getName() + " busts!");
            } else if (dealerHandValue > 21) {
                System.out.println("Dealer busts! " + player.getName() + " wins!");
            } else if (playerHandValue > dealerHandValue) {
                System.out.println(player.getName() + " wins!");
            } else if (playerHandValue == dealerHandValue) {
                System.out.println(player.getName() + " ties with the dealer.");
            } else {
                System.out.println(player.getName() + " loses.");
            }
        }
    }
}
