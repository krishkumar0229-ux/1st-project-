import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[random.nextInt(3)];
        System.out.println("===== Rock Paper Scissors Game =====");
        System.out.print("Enter Rock, Paper, or Scissors: ");
        String userChoice = sc.nextLine();
        userChoice = userChoice.substring(0, 1).toUpperCase() +
                     userChoice.substring(1).toLowerCase();

        System.out.println("Computer chose: " + computerChoice);
        if (userChoice.equals(computerChoice)) {
            System.out.println("It's a Tie!");
        }
        else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                 (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                 (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            System.out.println("Congratulations! You Win!");
        }
        else if (userChoice.equals("Rock") ||
                 userChoice.equals("Paper") ||
                 userChoice.equals("Scissors")) {
            System.out.println("Computer Wins!");
        }
        else {
            System.out.println("Invalid Input! Please enter Rock, Paper, or Scissors.");
        }
        sc.close();
    }
}