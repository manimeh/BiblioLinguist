package view;

import entity.quiz.SubmittedQuizDisplay;
import interface_adapter.return_home.ReturnHomeController;
import interface_adapter.return_home.ReturnHomeState;
import interface_adapter.return_home.ReturnHomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResultsView extends JScrollPane implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Results";

    private static final Dimension PREF_SPACE_BETWEEN_QUESTION = new Dimension(0, 15);
    private static final Dimension MAX_SPACE_BETWEEN_QUESTION = new Dimension(0, 20);

    private final JPanel questionsPanel;

    public ResultsView(ReturnHomeViewModel returnHomeViewModel, ReturnHomeController returnHomeController)
    {
        JPanel content = new JPanel();
        returnHomeViewModel.addPropertyChangeListener(this);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel header = new JLabel(ReturnHomeViewModel.HEADER_LABEL);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setFont(new Font(header.getFont().getFontName(), Font.PLAIN, 25));
        content.add(header);

        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        JPanel questionPanelWithSpace = new JPanel();
        questionPanelWithSpace.setLayout(new BoxLayout(questionPanelWithSpace, BoxLayout.X_AXIS));
        questionPanelWithSpace.add(createSpacer(new Dimension(15, 0), new Dimension(40, 0)));
        questionPanelWithSpace.add(questionsPanel);

        JPanel questionsPanelContainer = new JPanel();
        questionsPanelContainer.setLayout(new BorderLayout());
        questionsPanelContainer.add(questionPanelWithSpace, BorderLayout.WEST);
        content.add(questionsPanelContainer);

        ButtonsPanel buttonsPanel = new ButtonsPanel(BoxLayout.X_AXIS);
        Color buttonBackgroundColor2 = new Color(17, 61, 145);

        JButton returnHome = buttonsPanel.addGradientButton(ReturnHomeViewModel.RETURN_HOME_BUTTON_LABEL,
                ViewManager.BUTTON_FONT, buttonBackgroundColor2, Color.BLACK, ViewManager.BUTTON_CURVATURE);
        returnHome.setMaximumSize(new Dimension(175, 30));
        content.add(buttonsPanel);

        // Handle return home button click
        returnHome.addActionListener(
                evt -> {
                    if (evt.getSource().equals(returnHome)) {
                        returnHomeController.execute();
                    }
                }
        );

        setViewportView(content);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "action not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ReturnHomeState returnHomeState = (ReturnHomeState) evt.getNewValue();
        SubmittedQuizDisplay quizDisplay = returnHomeState.getQuizDisplay();

        clearPreviousGame();

        // Add each button group and question label to the view
        for (int i = 0; i < quizDisplay.questions().length; i++) {
            QuestionPanel questionPanel = QuestionPanel.Builder.createSubmittedQuizPanel(quizDisplay.questions()[i],
                    quizDisplay.choices()[i], quizDisplay.chosenChoices(), quizDisplay.answers()[i],
                    quizDisplay.explanations()[i], i);
            questionsPanel.add(questionPanel);
            questionsPanel.add(Box.createVerticalGlue());
            questionPanel.add(createSpacer(PREF_SPACE_BETWEEN_QUESTION, MAX_SPACE_BETWEEN_QUESTION));
        }
    }

    private Box.Filler createSpacer(Dimension prefSize, Dimension maxSize)
    {
        return new Box.Filler(prefSize, prefSize, maxSize);
    }

    private void clearPreviousGame()
    {
        questionsPanel.removeAll();
        revalidate();
        repaint();
    }
}
