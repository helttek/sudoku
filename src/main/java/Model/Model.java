package Model;

import javax.swing.*;

public class Model {
    private final Board board;
    private int EmptyFieldsLeft;
    private int score;
    private final int WRONG_ATTEMPT_COEFFICIENT = 1;
    private final int HIGHEST_POSSIBLE_SCORE_FOR_ONE_FIELD = 10;
    private final int[][] attempts;
    private final Object[][] highScores;

    public Model() {
        this.board = new Board();
        this.attempts = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.attempts[i][j] = 0;
            }
        }
        this.highScores = new Object[21][2];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 2; j++) {
                highScores[i][j] = (j == 0) ? 40 + i : 9 * (40 + i);
            }
        }
    }

    public void CreateNewBoard() {
        board.GenerateBoard();
        EmptyFieldsLeft = board.CountUpEmptyFields();
    }

    /*Returns true if all the fields have been guessed correctly*/
    public boolean UpdateField(JTextField textField, int row, int column) {
        String text = textField.getText();
        if (!text.isEmpty()) {
            int tmp;
            try {
                tmp = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input: '" + text + "', try entering a number 1 through 9.");
                textField.setText("");
                return false;
            }
            ++this.attempts[row][column];
            if (board.GetSolutionValueAt(row, column) != tmp) {
                JOptionPane.showMessageDialog(null, "Wrong guess, try again.");
                textField.setText("");
            } else {
                if (textField.isEditable()) {
                    textField.setEditable(false);
                    if (this.attempts[row][column] < 9) {
                        score += HIGHEST_POSSIBLE_SCORE_FOR_ONE_FIELD - WRONG_ATTEMPT_COEFFICIENT * this.attempts[row][column];
                    }
                    return (--EmptyFieldsLeft) <= 0;
                }
            }
        }
        return false;
    }

    public boolean BoardFieldIsEmpty(final int row, final int column) {
        return board.FieldIsEmpty(row, column);
    }

    public String GetBoardFieldString(final int row, final int column) {
        return board.GetFieldString(row, column);
    }

    public int GetScore() {
        return this.score;
    }

    public void ExitButtonAction() {
        System.exit(0);
    }

    public Object[][] getHighScoresTable() {
        return highScores;
    }

    public void ResetData() {
        score = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.attempts[i][j] = 0;
            }
        }
    }
}
