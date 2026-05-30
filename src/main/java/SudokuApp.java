import javax.swing.*;

import Controller.*;

/*  The model would handle the game logic and state, the view would display the game board and graphics,
    and the controller would handle user input and update the model accordingly.*/
public class SudokuApp {
    public static void main(String[] args) {
        Controller controller = new Controller();
        SwingUtilities.invokeLater(controller::StartGame);
    }
}