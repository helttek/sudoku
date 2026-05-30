package Model;

public class Board {
    private int[][] data;
    private int[][] solution;

    public Board() {
        this.data = new int[9][9];
    }

    public void GenerateBoard() {
        BoardGenerator boardGenerator = new BoardGenerator();
        this.data = boardGenerator.generateBoard();
        this.solution = boardGenerator.GetSolution();
    }

    public int GetSolutionValueAt(final int row, final int column) {
        return solution[row][column];
    }

    public int CountUpEmptyFields() {
        int res = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                res += (data[i][j] == 0) ? 1 : 0;
            }
        }
        return res;
    }

    public boolean FieldIsEmpty(final int row, final int column) {
        return this.data[row][column] == 0;
    }

    public String GetFieldString(final int row, final int column) {
        return String.valueOf(this.data[row][column]);
    }
}
