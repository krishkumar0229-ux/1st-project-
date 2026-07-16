import java.util.Scanner;
public class TicTacToe {
    static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        char currentPlayer = 'X';
        int moves = 0;
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your position (1-9): ");
            int position = sc.nextInt();
            if (!placeMark(position, currentPlayer)) {
                System.out.println("Invalid move! Try again.\n");
                continue;
            }
            moves++;
            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " Wins!");
                break;
            }
            if (moves == 9) {
                printBoard();
                System.out.println("The game is a Draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    // Display of the board for the game //
    public static void printBoard() {
        System.out.println();
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }

    // Placement of  X or O //
    public static boolean placeMark(int position, char mark) {

        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (position < 1 || position > 9)
            return false;

        if (board[row][col] == 'X' || board[row][col] == 'O')
            return false;

        board[row][col] = mark;
        return true;
    }

    // To Check the Winner //
    public static boolean checkWinner(char player) {

        // #Rows# //
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player)
                return true;
        }

        // #Columns# //
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player)
                return true;
        }

        // #Diagonals# //
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player)
            return true;

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player)
            return true;
        return false;
    }
}