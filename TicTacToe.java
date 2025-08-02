import java.util.*;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            initializeBoard();
            char currentPlayer = PLAYER_X;
            boolean gameWon = false;
            int movesCount = 0;

            System.out.println("Welcome to Tic-Tac-Toe!");
            printBoard();

            while (!gameWon && movesCount < 9) {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1-3): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    movesCount++;
                    printBoard();

                    if (checkWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameWon = true;
                    } else if (movesCount == 9) {
                        System.out.println("It's a draw!");
                    } else {
                        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            System.out.println("Do you want to play again? (true/false): ");
            playAgain = scanner.nextBoolean();

        } while (playAgain);

        scanner.close();
        System.out.println("Thanks for playing!");
    }

    private static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    private static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    private static boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }
}
