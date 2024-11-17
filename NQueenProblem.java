import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueenProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N : ");
        int N = scanner.nextInt();

        if (N <= 0) {
            System.out.println("Invalid input! N must be greater than 0.");
            return;
        }

        NQueenProblem solver = new NQueenProblem();
        List<List<String>> solutions = solver.solveNQueens(N);

        System.out.println("Total Solutions: " + solutions.size());
        int displayedSolutions = 0;
        for (List<String> solution : solutions) {
            if (displayedSolutions >= 2) {
                System.out.println("etc....");
                break;
            }
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
            displayedSolutions++;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        solve(0, board, solutions, n);
        return solutions;
    }

    private void solve(int row, char[][] board, List<List<String>> solutions, int n) {
        if (row == n) {
            solutions.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                solve(row + 1, board, solutions, n);
                board[row][col] = '.'; 
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
}
