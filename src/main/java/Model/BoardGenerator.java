package Model;

import java.util.Random;

public class BoardGenerator {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    private final int[][] board;
    private final int[][] solution;
    private final Random random;

    public BoardGenerator() {
        board = new int[SIZE][SIZE];
        solution = new int[SIZE][SIZE];
        random = new Random();
    }

    public int[][] generateBoard() {
        generateSolution();
        removeCells();
        return board;
    }

    private void generateSolution() {
        if (solve()) {
            copySolutionToBoard();
        }
    }

    private boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (solution[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidPlacement(row, col, num)) {
                            solution[row][col] = num;
                            if (solve()) {
                                return true;
                            }
                            solution[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(int row, int col, int num) {
        return !usedInRow(row, num) &&
                !usedInColumn(col, num) &&
                !usedInSubgrid(row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (solution[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (solution[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInSubgrid(int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (solution[startRow + row][startCol + col] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private void copySolutionToBoard() {
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(solution[row], 0, board[row], 0, SIZE);
        }
    }

    private void removeCells() {
        int numCellsToRemove = random.nextInt(21) + 40; // Randomly remove 40-60 cells
        while (numCellsToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col] != EMPTY) {
                board[row][col] = EMPTY;
                numCellsToRemove--;
            }
        }
    }

    public int[][] GetSolution() {
        return solution;
    }
}
