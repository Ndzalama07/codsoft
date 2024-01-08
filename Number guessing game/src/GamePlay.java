import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    int guess;
    int turns = 10;
    final Random random;
    Scanner input = new Scanner(System.in);

    public GamePlay() {
        this.random = new Random();
    }

    public void startGame() {
        boolean isAlive = true;

        while (isAlive) {
            int randomNum = this.random.nextInt(10);
            turns = 10;
            
            System.out.println("Welcome to the guessing game. Enter a number between the range 1 - 10 ");

            while (stillHasTurns()) {
                try {
                    guess = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid number: ");
                    continue;  
                }

                if (randomNum == guess) {
                    System.out.println("Correct");
                    break; 
                } else if (guess > randomNum) {
                    System.out.println("Your guess is too high, try again");
                } else {
                    System.out.println("Your guess is too low, try again");
                }

                lostTurn();
            }

            if (turns == 0) {
                System.out.println("You are out of turns. Game Over! The correct number to guess was " + randomNum);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = input.nextLine().toLowerCase();
            isAlive = playAgainInput.equals("yes");
        }
    }

    public void lostTurn() {
        if (stillHasTurns())
            this.turns--;
        System.out.println("You have " + this.turns + " turns remaining");
    }

    public int getTurns() {
        return this.turns;
    }

    public boolean stillHasTurns() {
        return getTurns() > 0;
    }
}
