import java.util.*;

class Program {
    public static char[][] board = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    public static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean valid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean game() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
    
        while (!game()) {
            printBoard();
    
            boolean filledCell = false;
    
            for (int i = 0; i < 9 && !filledCell; i++) {
                for (int j = 0; j < 9 && !filledCell; j++) {
                    if (board[i][j] == '.') {
                        boolean validMove = false;
    
                        while (!validMove) {
                            System.out.println("Enter a number (1-9) for cell at row " + i + ", column " + j + ":");
                            char num = sc.next().charAt(0);
    
                            if (num < '1' || num > '9') {
                                System.out.println("❌ Invalid input. Please enter a digit between 1 and 9.");
                                continue;
                            }
    
                            if (valid(board, i, j, num)) {
                                board[i][j] = num;
                                validMove = true;
                                filledCell = true; // break out of the outer loop
                            } else {
                                System.out.println("❌ Invalid move at row " + i + ", column " + j + ". Try a different number.");
                            }
                        }
                    }
                }
            }
        }
    
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
    
        printBoard();
        System.out.println("🎉 You Win!");
        System.out.println("🕒 Time: " + totalTime + " seconds");
        sc.close();
    }
}
    