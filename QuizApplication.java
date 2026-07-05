import java.util.Scanner;
public class QuizApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Questions //
        String[] questions = {
                "1. What is the capital of India?\nA. Mumbai\nB. Delhi\nC. Chennai\nD. Kolkata",
                "2. Which language is used for Android app development?\nA. Python\nB. Java\nC. C\nD. HTML",
                "3. Which planet is known as the Red Planet?\nA. Earth\nB. Mars\nC. Venus\nD. Jupiter",
                "4. What is the output of 5 + 3 * 2?\nA. 16\nB. 11\nC. 13\nD. 10",
                "5. Which keyword is used to create an object in Java?\nA. class\nB. object\nC. new\nD. this"
        };

        // Correct Answers //
        char[] answers = {'B', 'B', 'B', 'B', 'C'};
        int score = 0;
        System.out.println("===== Welcome to the Java Quiz =====\n");
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Enter your answer (A/B/C/D): ");
            char userAnswer = Character.toUpperCase(sc.next().charAt(0));
            if (userAnswer == answers[i]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong! Correct answer is: " + answers[i] + "\n");
            }
        }

        // Display Result//
        System.out.println("===== Quiz Completed =====");
        System.out.println("Total Questions : " + questions.length);
        System.out.println("Correct Answers : " + score);
        System.out.println("Wrong Answers   : " + (questions.length - score));
        double percentage = (score * 100.0) / questions.length;
        System.out.printf("Percentage      : %.2f%%\n", percentage);

        // #Performance# //
        if (percentage >= 80) {
            System.out.println("Result: Excellent!");
        } else if (percentage >= 60) {
            System.out.println("Result: Good Job!");
        } else if (percentage >= 40) {
            System.out.println("Result: Average.");
        } else {
            System.out.println("Result: Better Luck Next Time!");
        }
        sc.close();
    }
}