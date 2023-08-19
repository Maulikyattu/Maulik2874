import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        Dealer dealer = new Dealer();
        game.addPlayer(player);
        game.setDealer(dealer);
        boolean playAgain = true;
        while (playAgain) {
            game.play();
            System.out.print("Play again? (y/n): ");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("y");
        }
        System.out.println("Thanks for playing!");
    }
}
