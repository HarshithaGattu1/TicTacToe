import java.util.Scanner;
class TicTacToe {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean playAgain;

        do {
            char[][] board = new char[3][3];

            // Initialize board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }

            int moves = 0;
            boolean currentPlayer;

            System.out.println("Who will start the game?");
            System.out.println("Enter true for Player X, false for Player O:");
            currentPlayer = sc.nextBoolean();
            sc.nextLine(); 

            while (true) {

                printBoard(board);

                if (moves == 9) {
                    System.out.println("DRAW");
                    break;
                }

                char player = currentPlayer ? 'X' : 'O';
                System.out.print("Player " + player + " :  Enter move  in the range (00 to 22): ");
                String move = sc.nextLine();

                if (move.length() != 2 ||
                    !Character.isDigit(move.charAt(0)) ||
                    !Character.isDigit(move.charAt(1))) {

                    System.out.println("Invalid format! Try again. Enter move in the range (00 to 22)");
                    continue;
                }

                int row = move.charAt(0) - '0';
                int col = move.charAt(1) - '0';

                // Validate move
                if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == ' ') {

                    board[row][col] = player;
                    moves++;

                    if (isWinner(board, player)) {
                        printBoard(board);
                        System.out.println("Player " + player + " has WON");
                        break;
                    }

                    currentPlayer = !currentPlayer; 

                } else {
                    System.out.println("Invalid move! try again.");
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String answer = sc.nextLine();
            playAgain = answer.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing");
        sc.close();
    }

    // Print board
    public static void printBoard(char[][] b) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--+---+--");
        }
        System.out.println();
    }

    //checking move is valid or not
    public static boolean isWinner(char[][] b, char p) {

        return
            // Rows
            (b[0][0] == p && b[0][1] == p && b[0][2] == p) ||
            (b[1][0] == p && b[1][1] == p && b[1][2] == p) ||
            (b[2][0] == p && b[2][1] == p && b[2][2] == p) ||

            // Columns
            (b[0][0] == p && b[1][0] == p && b[2][0] == p) ||
            (b[0][1] == p && b[1][1] == p && b[2][1] == p) ||
            (b[0][2] == p && b[1][2] == p && b[2][2] == p) ||

            // Diagonals
            (b[0][0] == p && b[1][1] == p && b[2][2] == p) ||
            (b[0][2] == p && b[1][1] == p && b[2][0] == p);
    }
}
