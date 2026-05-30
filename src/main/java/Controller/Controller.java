package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private final Model model;
    private final View view;

    public Controller() {
        this.model = new Model();
        this.view = new View();
    }

    public void StartGame() {
        model.CreateNewBoard();
        view.CreateGUIBoard(model);
        view.CreateScorePanel();
        CreateButtons();
        view.DisplayGUI();
    }

    private void CreateButtons() {
        JButton button1 = view.CreateExitButton(model);
        JButton button2 = view.CreateHighScoresButton(model.getHighScoresTable());
        JButton button3 = CreateNewGameButton(model);
        view.AddButtons(button1, button2, button3);
    }

    private JButton CreateNewGameButton(Model model) {
        JButton button = new JButton("New Game");
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.addActionListener(e -> {
            ResetGameData();
            model.CreateNewBoard();
            view.CreateGUIBoard(model);
            view.DisplayGUI();
        });
        return button;
    }

    private void ResetGameData() {
        model.ResetData();
        view.ResetData();
    }
}
