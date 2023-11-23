package view;

import interface_adapter.start_new_game.StartNewGameController;
import interface_adapter.start_new_game.StartNewGameViewModel;
import interface_adapter.view_scores.ViewScoresController;
import interface_adapter.view_scores.ViewScoresViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends BackgroundImagePanel implements ActionListener, PropertyChangeListener {
    public final static String VIEW_NAME = "home page";

    private final StartNewGameViewModel startNewGameViewModel;
    private final ViewScoresViewModel viewScoresViewModel;
    private final StartNewGameController startNewGameController;
    private final ViewScoresController viewScoresController;

    private final JButton newGame;
    private final JButton viewScores;

    public HomePageView(StartNewGameViewModel startNewGameViewModel, ViewScoresViewModel viewScoresViewModel,
                        StartNewGameController startNewGameController, ViewScoresController viewScoresController,
                        Image backgroundImage)
    {
        super(backgroundImage);
        this.startNewGameViewModel = startNewGameViewModel;
        this.viewScoresViewModel = viewScoresViewModel;
        this.startNewGameController = startNewGameController;
        this.viewScoresController = viewScoresController;
        startNewGameViewModel.addPropertyChangeListener(this);
        viewScoresViewModel.addPropertyChangeListener(this);

        ButtonsPanel buttonsPanel = new ButtonsPanel();
        String buttonFont = "SansSerif";
        Color buttonBackgroundColor1 = new Color(228, 215, 159);
        Color buttonBackgroundColor2 = new Color(215, 159, 228);
        int buttonCurvature = 25;

        newGame = buttonsPanel.addGradientButton(StartNewGameViewModel.NEW_GAME_BUTTON_LABEL,
                buttonFont, buttonBackgroundColor1, Color.BLACK, buttonCurvature);
        buttonsPanel.addSpacer();
        viewScores = buttonsPanel.addGradientButton(StartNewGameViewModel.VIEW_SCORES_BUTTON_LABEL,
                buttonFont, buttonBackgroundColor2, Color.BLACK, buttonCurvature);

        newGame.addActionListener(
                evt -> {
                    if (evt.getSource().equals(newGame)) {
                        HomePageView.this.startNewGameController.execute();
                    }
                }
        );

        viewScores.addActionListener(
                evt -> {
                    if (evt.getSource().equals(viewScores)) {
                        HomePageView.this.viewScoresController.execute();
                    }
                }
        );

        this.setLayout(new GridLayout(5, 3));
        setupGrid(5, 3, 4, 2, buttonsPanel);
    }

    private void setupGrid(int rows, int cols, int componentRow, int componentCol, Component component)
    {
        JPanel emptyJpanel;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if((i != componentRow - 1) || (j != componentCol - 1))
                {
                    emptyJpanel = new JPanel();
                    emptyJpanel.setOpaque(false);
                    this.add(emptyJpanel);
                }
                else
                {
                    this.add(component);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "action not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
