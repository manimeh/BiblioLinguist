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
    private final String viewName = "Game View";

    private final SubmitQuizViewModel submitQuizViewModel;
    private final SubmitQuizController submitQuizController;

    private final JButton submitQuiz;

    public GameView (SubmitQuizViewModel submitQuizViewModel, SubmitQuizController submitQuizController) {
        this.submitQuizViewModel = submitQuizViewModel;
        this.submitQuizController = submitQuizController;
        this.submitQuizViewModel.addPropertyChangeListener(this);

        ButtonsPanel buttonsPanel = new ButtonsPanel();
        String buttonFont = "SansSerif";
        Color buttonBackgroundColor2 = new Color(69, 196, 174);
        int buttonCurvature = 25;

        submitQuiz = buttonsPanel.addGradientButton("Submit", buttonFont, buttonBackgroundColor2,
                Color.BLACK, buttonCurvature);
        submitQuiz.setMaximumSize(new Dimension(100, 30));

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
        view2.add(header2);


        String[] questions = quizDisplay.questions();
        String[][] choices = quizDisplay.choices();

//        For testing:
//        String[] questions = {
//                "What is the capital of France?",
//                "Which programming language is Java based on?",
//                "What is the largest planet in our solar system?"
//        };
//        String[][] choices = {
//                {"Paris", "Berlin", "London", "Rome"},
//                {"C++", "Python", "JavaScript", "C#"},
//                {"Jupiter", "Saturn", "Mars", "Earth"}
//        };

        // Add each button group and question label to the view
        for (int i = 0; i < questions.length; i++) {
            JLabel questionLabel = new JLabel(questions[i]);
            view2.add(questionLabel);

            ButtonGroup buttonGroup = new ButtonGroup();
            for (int j = 0; j < choices[i].length; j++) {
                JRadioButton choice = new JRadioButton(choices[i][j]);
                buttonGroup.add(choice);
                view2.add(choice);
            }
        }

        view2.add(submitQuiz);


        // Add views to splitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(new JScrollPane(view1));
        setRightComponent(new JScrollPane(view2));
        setResizeWeight(0.5);


        submitQuiz.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitQuiz)) {
                        Integer[] userAnswers = new Integer[questions.length];

                        Component[] components = view2.getComponents();

                        int radioButtonCount = 0;
                        for (Component component : components) {
                            if (component instanceof JRadioButton) {
                                // Check if the radio button is selected
                                if (((JRadioButton) component).isSelected()) {
                                    // Save the user's answer
                                    String answer = ((JRadioButton) component).getText();
                                    for (int i = 0; i < choices[radioButtonCount].length; i++) {
                                        if (choices[radioButtonCount][i].equals(answer)) {
                                            userAnswers[radioButtonCount] = i; // Index of the target string in the array
                                        }
                                    }
                                    radioButtonCount += 1;
                                }
                            } else if (component instanceof JButton && component.equals(submitQuiz)) {
                                // Submit button found, break the loop
                                break;
                            }
                        }

                        if (radioButtonCount == questions.length) {
                            GameView.this.submitQuizController.execute(submitQuizState.getQuiz(), userAnswers, user);
                        }
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Please answer all questions", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
