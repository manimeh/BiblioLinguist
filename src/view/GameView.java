package view;

import entity.quiz.ActiveQuizDisplay;
import entity.reading.Reading;
import entity.user.User;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizState;
import interface_adapter.submit_quiz.SubmitQuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;


public class GameView extends JSplitPane implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Game View";

    private final SubmitQuizViewModel submitQuizViewModel;
    private final SubmitQuizController submitQuizController;

    private JButton submitQuiz = null;


    public GameView(SubmitQuizViewModel submitQuizViewModel, SubmitQuizController submitQuizController) {
        this.submitQuizViewModel = submitQuizViewModel;
        this.submitQuizController = submitQuizController;
        this.submitQuizViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        String buttonFont = "SansSerif";
        Color buttonBackgroundColor2 = new Color(69, 196, 174);
        int buttonCurvature = 25;

        SubmitQuizState submitQuizState = submitQuizViewModel.getState();
        ActiveQuizDisplay quizDisplay = submitQuizState.getQuiz().activeDisplay();
        User user = submitQuizState.getUser();
        Reading reading = submitQuizState.getReading();


        // Left half of panel
        JPanel view1 = new JPanel();
        view1.setLayout(new BoxLayout(view1, BoxLayout.Y_AXIS));

        JLabel header1 = new JLabel("Reading");
        header1.setAlignmentX(Component.CENTER_ALIGNMENT);
        header1.setFont(new Font(header1.getFont().getFontName(), Font.PLAIN, 25));
        view1.add(header1);

        JLabel title = new JLabel("Title: " + reading.display().title());
        JLabel author = new JLabel("Author: " + reading.display().author());
        JTextArea readingText = new JTextArea(reading.display().text());

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setLineWrap(true);
        readingText.setWrapStyleWord(true);
        readingText.setEditable(false);

        view1.add(title);
        view1.add(author);
        view1.add(readingText);


        // Right half of panel
        JPanel view2 = new JPanel();
        view2.setLayout(new BoxLayout(view2, BoxLayout.Y_AXIS));


        JLabel header2 = new JLabel("Quiz");
        header2.setAlignmentX(Component.CENTER_ALIGNMENT);
        header2.setFont(new Font(header2.getFont().getFontName(), Font.PLAIN, 25));
        JPanel headerPanel = new JPanel();
        headerPanel.add(header2);
        view2.add(headerPanel);


        String[] questions = quizDisplay.questions();
        String[][] choices = quizDisplay.choices();

        submitQuizViewModel.getState().setAnswers(new Integer[questions.length]);

        // Add each button group and question label to the view
        for (int i = 0; i < questions.length; i++) {
            QuestionPanel questionPanel = new QuestionPanel(questions[i], choices[i], submitQuizViewModel, i);
            view2.add(questionPanel);
        }


        // Add submit quiz button to right view
        submitQuiz = buttonsPanel.addGradientButton("Submit", buttonFont, buttonBackgroundColor2,
                Color.BLACK, buttonCurvature);
        submitQuiz.setMaximumSize(new Dimension(100, 30));
        submitQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitQuiz);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        view2.add(buttonPanel);


        // Add views to splitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(new JScrollPane(view1));
        setRightComponent(new JScrollPane(view2));
        setResizeWeight(0.5);


        // Handle submit quiz button click
        submitQuiz.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitQuiz)) {
                        if (Arrays.asList(submitQuizViewModel.getState().getAnswers()).contains(null)) {
                            JOptionPane.showMessageDialog(null,
                                    "Please answer all questions", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            submitQuizController.execute(submitQuizState.getQuiz(), submitQuizState.getAnswers(), user);
                        }
                    }
                }
        );
    }
}
