package use_case.view_scores;

import interface_adapter.view_scores.ViewScoresController;
import interface_adapter.view_scores.ViewScoresViewModel;
import interface_adapter.generate_quiz.GenQuizViewModel;
import interface_adapter.generate_quiz.GenQuizController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


//I think all of this code is in the HomePageView class now. So this class is not needed now, right?
public class QuizHomeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final GenQuizController genQuizController;
    private final ViewScoresController viewScoresController;
    // Button for creating a quiz
    private final JButton createQuizButton;
    // Button for opening up a JPanel that looks at scores
    private final JButton viewScoresButton;


    /**
     * React to a button click that results in evt.
     */

    public QuizHomeView(GenQuizController genQuizController, GenQuizViewModel genQuizViewModel, ViewScoresController viewScoresController) {
        this.genQuizController = genQuizController;
        this.viewScoresController = viewScoresController;

        JPanel buttons = new JPanel();
        createQuizButton = new JButton(GenQuizViewModel.CREATE_QUIZ_BUTTON_LABEL);
        buttons.add(createQuizButton);
        viewScoresButton = new JButton(GenQuizViewModel.VIEW_SCORES_BUTTON_LABEL);
        buttons.add(createQuizButton);

        // Create an action listener for the viewScoresButton
        viewScoresButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(viewScoresButton)) {
                            viewScoresController.execute();
                            //JOptionPane.showMessageDialog(); //TODO: fill out message dialog
                        }
                    }
                }
        );
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // This method gets called when a bound property change. A PropertyChangeEvent describes the event source
    // and the property that was changed.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
