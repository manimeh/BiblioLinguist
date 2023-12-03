package view;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private static final Color RIGHT_ANSWER_TEXT_COLOR = new Color(12, 121, 5);
    private static final Color WRONG_ANSWER_TEXT_COLOR = new Color(152, 9, 45);

    private QuestionPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout to vertical
    }

    private QuestionPanel(String question, String[] choices, Integer[] answers, Integer questionIndex)
    {
        this();

        JLabel questionLabel = new JLabel(question);
        this.add(questionLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < choices.length; i++) {
            JRadioButton choice = new JRadioButton(choices[i]);

            int finalI = i;
            choice.addActionListener(evt -> {
                if (evt.getSource().equals(choice)) {
                    answers[questionIndex] = finalI;
                }
            });

            buttonGroup.add(choice);
            this.add(choice);
        }
    }

    private QuestionPanel(String question, String[] choices, Integer[] answers,
                          Integer rightChoiceIndex, String explanation, Integer questionIndex)
    {
        this();

        boolean answeredRight = rightChoiceIndex.equals(answers[questionIndex]);
        JLabel scoreLabel = new JLabel((answeredRight ? "1" : "0") + "/1");
        JLabel questionLabel = new JLabel(question);
        questionLabel.setForeground(Color.GRAY);
        this.add(scoreLabel);
        this.add(questionLabel);

        for (int i = 0; i < choices.length; i++) {
            JRadioButton choice = new JRadioButton(choices[i]);
            choice.setEnabled(false);
            choice.setForeground(Color.GRAY);

            if (i == answers[questionIndex])
            {
                choice.setSelected(true);
            }

            this.add(choice);
        }

        if(answeredRight)
        {
            scoreLabel.setForeground(RIGHT_ANSWER_TEXT_COLOR);
        }
        else
        {
            scoreLabel.setForeground(WRONG_ANSWER_TEXT_COLOR);

            JLabel correctAnswerLabel = new JLabel("Correct Answer: " + choices[rightChoiceIndex]);
            JLabel explanationLabel = new JLabel("Explanation: " + explanation);

            correctAnswerLabel.setFont(new Font(correctAnswerLabel.getFont().getFontName(), Font.BOLD,
                    correctAnswerLabel.getFont().getSize()));
            explanationLabel.setFont(new Font(explanationLabel.getFont().getFontName(), Font.BOLD,
                    explanationLabel.getFont().getSize()));

            this.add(correctAnswerLabel);
            this.add(explanationLabel);
        }
    }

    public static class Builder
    {
        public static QuestionPanel createActiveQuizPanel(String question, String[] choices, Integer[] answers,
                                                          Integer questionIndex)
        {
            return new QuestionPanel(question, choices, answers, questionIndex);
        }

        public static QuestionPanel createSubmittedQuizPanel(String question, String[] choices, Integer[] answers,
                                                             Integer rightChoiceIndex, String explanation,
                                                             Integer questionIndex)
        {
            return new QuestionPanel(question, choices, answers, rightChoiceIndex, explanation, questionIndex);
        }
    }
}