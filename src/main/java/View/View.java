package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*the view would display the game board and graphics*/
public class View extends JFrame {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JPanel sudokuGridPanel;
    private JLabel scoreLabel;
    private final JTextField[][] textFields;

    public View() {
        frame = new JFrame("Sudoku");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFields = new JTextField[9][9];
        sudokuGridPanel = new JPanel(new GridLayout(9, 9));
        mainPanel = new JPanel(new BorderLayout());
    }

    public void CreateGUIBoard(Model model) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField textField = new JTextField();
                textField.setFont(new Font("Arial", Font.PLAIN, 25));
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                if (!model.BoardFieldIsEmpty(row, col)) {
                    textField.setText(model.GetBoardFieldString(row, col));
                    textField.setEditable(false);
                } else {
                    int tmpRow = row;
                    int tmpCol = col;
                    textField.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            if (model.UpdateField(textField, tmpRow, tmpCol)) {
                                DisplaySuccess();
                            }
                            DisplayUpdatedScore(model);
                        }
                    });
                }
                textFields[row][col] = textField;
                sudokuGridPanel.add(textField);
            }
        }
        mainPanel.add(sudokuGridPanel, BorderLayout.CENTER);
    }

    private void DisplaySuccess() {
        JOptionPane.showMessageDialog(frame, "Congrats! You've solved this sudoku!");
    }

    private void DisplayUpdatedScore(Model model) {
        scoreLabel.setText("Current score: " + model.GetScore());
    }

    public JButton CreateExitButton(Model model) {
        JButton button = new JButton("Exit");
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.addActionListener(e -> model.ExitButtonAction());
        return button;
    }

    public JButton CreateHighScoresButton(Object[][] highScores) {
        JButton button = new JButton("High Scores");
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.addActionListener(e -> {
            String[] columnNames = {"Number of initially unknown fields", "Highest possible score"};
            JTable table = new JTable(highScores, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(400, 160));
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scrollPane, "Best possible results", JOptionPane.INFORMATION_MESSAGE);
        });
        return button;
    }

    public void AddButtons(JButton button1, JButton button2, JButton button3) {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
        buttonsPanel.add(button1, BorderLayout.SOUTH);
        buttonsPanel.add(button2, BorderLayout.SOUTH);
        buttonsPanel.add(button3, BorderLayout.SOUTH);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void CreateScorePanel() {
        scoreLabel = new JLabel("Current score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(scoreLabel, BorderLayout.NORTH);
    }

    public void DisplayGUI() {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void ResetData() {
        sudokuGridPanel.removeAll();
    }
}
