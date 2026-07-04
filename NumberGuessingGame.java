import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        // Generate a random number between 1 and 100
        int randomNumber = random.nextInt(100) + 1;

        int userGuess;
        int attempts = 0;

        System.out.println("===== Number Guessing Game =====");
        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("Try to guess it!");

        do {
            System.out.print("Enter your guess: ");
            userGuess = sc.nextInt();
            attempts++;

            if (userGuess > randomNumber) {
                System.out.println("Too High! Try Again.");
            } 
            else if (userGuess < randomNumber) {
                System.out.println("Too Low! Try Again.");
            } 
            else {
                System.out.println("Congratulations! You guessed the correct number.");
                System.out.println("Number: " + randomNumber);
                System.out.println("Attempts: " + attempts);
            }

        } while (userGuess != randomNumber);

        sc.close();
    }
}