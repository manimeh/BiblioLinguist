package view;

import entity.quiz.ActiveQuizDisplay;
import entity.reading.Reading;
import entity.user.User;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizState;
import interface_adapter.submit_quiz.SubmitQuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class GameView extends JSplitPane implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Game View";

    private static final Dimension PREF_SPACE_BETWEEN_QUESTION = new Dimension(0, 15);
    private static final Dimension MAX_SPACE_BETWEEN_QUESTION = new Dimension(0, 20);

    private final SubmitQuizViewModel submitQuizViewModel;

    private final JPanel readingPanel;
    private final JPanel questionsPanel;

    public GameView(SubmitQuizViewModel submitQuizViewModel, SubmitQuizController submitQuizController) {
        this.submitQuizViewModel = submitQuizViewModel;
        this.submitQuizViewModel.addPropertyChangeListener(this);

        // Left half of panel
        JPanel view1 = new JPanel();
        view1.setLayout(new BoxLayout(view1, BoxLayout.Y_AXIS));

        JLabel header1 = new JLabel("Reading");
        header1.setAlignmentX(Component.CENTER_ALIGNMENT);
        header1.setFont(new Font(header1.getFont().getFontName(), Font.PLAIN, 25));
        view1.add(header1);

        readingPanel = new JPanel();
        readingPanel.setLayout(new BoxLayout(readingPanel, BoxLayout.Y_AXIS));
        view1.add(readingPanel);

        // Right half of panel
        JPanel view2 = new JPanel();
        view2.setLayout(new BoxLayout(view2, BoxLayout.Y_AXIS));

        JLabel header2 = new JLabel("Quiz");
        header2.setFont(new Font(header2.getFont().getFontName(), Font.PLAIN, 25));
        JPanel headerPanel = new JPanel();
        headerPanel.add(header2);
        view2.add(headerPanel);

        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        JPanel questionPanelWithSpace = new JPanel();
        questionPanelWithSpace.setLayout(new BoxLayout(questionPanelWithSpace, BoxLayout.X_AXIS));
        questionPanelWithSpace.add(createSpacer(new Dimension(15, 0), new Dimension(40, 0)));
        questionPanelWithSpace.add(questionsPanel);

        JPanel questionsPanelContainer = new JPanel();
        questionsPanelContainer.setLayout(new BorderLayout());
        questionsPanelContainer.add(questionPanelWithSpace, BorderLayout.WEST);
        view2.add(questionsPanelContainer);

        ButtonsPanel buttonsPanel = new ButtonsPanel(BoxLayout.X_AXIS);
        Color buttonBackgroundColor2 = new Color(69, 196, 174);

        // Add submit quiz button to right view
        JButton submitQuiz = buttonsPanel.addGradientButton("Submit", ViewManager.BUTTON_FONT, buttonBackgroundColor2,
                Color.BLACK, ViewManager.BUTTON_CURVATURE);
        submitQuiz.setMaximumSize(new Dimension(100, 30));
        view2.add(buttonsPanel);

        // Handle submit quiz button click
        submitQuiz.addActionListener(
                evt -> {
                    SubmitQuizState submitQuizState = submitQuizViewModel.getState();
                    User user = submitQuizState.getUser();

                    if (evt.getSource().equals(submitQuiz)) {
                        submitQuizController.execute(submitQuizState.getQuiz(), submitQuizState.getAnswers(), user);
                    }
                }
        );

        // Add views to splitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(new JScrollPane(view1));
        setRightComponent(new JScrollPane(view2));
        setResizeWeight(0.5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        SubmitQuizState submitQuizState = (SubmitQuizState) e.getNewValue();

        if (submitQuizState.getSubmitQuizError() != null)
        {
            JOptionPane.showMessageDialog(this,
                    submitQuizState.getSubmitQuizError(), "Error", JOptionPane.ERROR_MESSAGE);
            submitQuizState.setSubmitQuizError(null);
        }
        else
        {
            clearPreviousGame();
            ActiveQuizDisplay quizDisplay = submitQuizState.getQuiz().activeDisplay();
            updateLeftPanel(submitQuizState);
            updateRightPanel(quizDisplay);
        }
    }

    private void updateLeftPanel(SubmitQuizState submitQuizState) {
        Reading reading = submitQuizState.getReading();

        JLabel title = new JLabel("Title: " + reading.display().title());
        JLabel author = new JLabel("Author: " + reading.display().author());
        JTextArea readingText = new JTextArea(reading.display().text());

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setLineWrap(true);
        readingText.setWrapStyleWord(true);
        readingText.setEditable(false);

        readingPanel.add(title);
        readingPanel.add(author);
        readingPanel.add(readingText);
    }

    private void updateRightPanel(ActiveQuizDisplay quizDisplay) {
        String[] questions = quizDisplay.questions();
        String[][] choices = quizDisplay.choices();

        // Add each button group and question label to the view
        for (int i = 0; i < questions.length; i++) {
            QuestionPanel questionPanel = QuestionPanel.Builder.createActiveQuizPanel(questions[i], choices[i],
                    submitQuizViewModel.getState().getAnswers(), i);
            questionsPanel.add(questionPanel);
            questionsPanel.add(Box.createVerticalGlue());
            questionPanel.add(createSpacer(PREF_SPACE_BETWEEN_QUESTION, MAX_SPACE_BETWEEN_QUESTION));
        }
    }

    private void clearPreviousGame()
    {
        questionsPanel.removeAll();
        readingPanel.removeAll();
        revalidate();
        repaint();
    }

    private Box.Filler createSpacer(Dimension prefSize, Dimension maxSize)
    {
        return new Box.Filler(prefSize, prefSize, maxSize);
    }
}
