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
    public final static String VIEW_NAME = "Home Page";

    private final StartNewGameController startNewGameController;
    private final ViewScoresController viewScoresController;
    private final ViewScoresViewModel viewScoresViewModel;

    public HomePageView(StartNewGameViewModel startNewGameViewModel, ViewScoresViewModel viewScoresViewModel,
                        StartNewGameController startNewGameController, ViewScoresController viewScoresController,
                        Image backgroundImage)
    {
        super(backgroundImage);
        this.startNewGameController = startNewGameController;
        this.viewScoresController = viewScoresController;
        this.viewScoresViewModel = viewScoresViewModel;
        startNewGameViewModel.addPropertyChangeListener(this);
        viewScoresViewModel.addPropertyChangeListener(this);

        ButtonsPanel buttonsPanel = new ButtonsPanel(BoxLayout.Y_AXIS);
        Color buttonBackgroundColor1 = new Color(228, 215, 159);
        Color buttonBackgroundColor2 = new Color(215, 159, 228);

        JButton newGame = buttonsPanel.addGradientButton(StartNewGameViewModel.NEW_GAME_BUTTON_LABEL,
                ViewManager.BUTTON_FONT, buttonBackgroundColor1, Color.BLACK, ViewManager.BUTTON_CURVATURE);
        buttonsPanel.addSpacer();
        JButton viewScores = buttonsPanel.addGradientButton(StartNewGameViewModel.VIEW_SCORES_BUTTON_LABEL,
                ViewManager.BUTTON_FONT, buttonBackgroundColor2, Color.BLACK, ViewManager.BUTTON_CURVATURE);

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
                        // JOptionPane.showMessageDialog(this,viewScoresViewModel.getState().getViewScoresMessage());
                    }
                }
        );

        final int ROWS = 5;
        final int COLS = 3;
        final int COMPONENT_ROWS = 4;
        final int COMPONENT_COLS = 2;

        this.setLayout(new GridLayout(ROWS, COLS));
        setupGrid(ROWS, COLS, COMPONENT_ROWS, COMPONENT_COLS, buttonsPanel);
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
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getSource() == viewScoresViewModel)
        {
            viewScoresPropertyChange(evt);
        }
    }

    private void viewScoresPropertyChange(PropertyChangeEvent evt) {
        JOptionPane.showMessageDialog(this, viewScoresViewModel.getState().getViewScoresMessage());
    }
}
