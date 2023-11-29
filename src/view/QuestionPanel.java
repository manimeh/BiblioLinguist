package view;

import interface_adapter.submit_quiz.SubmitQuizViewModel;

import javax.swing.*;
import java.util.Arrays;

public class QuestionPanel extends JPanel {

    public QuestionPanel(String question, String[] choices, SubmitQuizViewModel viewModel, Integer questionIndex)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout to vertical

        JLabel questionLabel = new JLabel(question);
        this.add(questionLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        Integer[] answers = viewModel.getState().getAnswers();
        for (int i = 0; i < choices.length; i++) {
            JRadioButton choice = new JRadioButton(choices[i]);

            int finalI = i;
            choice.addActionListener(evt -> {
                if (evt.getSource().equals(choice)) {
                    answers[questionIndex] = finalI;
                    System.out.println(Arrays.toString(answers));
                }
            });
            buttonGroup.add(choice);
            this.add(choice);
        }
    }
}