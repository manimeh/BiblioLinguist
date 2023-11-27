package view;

import interface_adapter.submit_quiz.SubmitQuizViewModel;

import javax.swing.*;

public class QuestionPanel extends JPanel {

    public QuestionPanel(String question, String[] choices, SubmitQuizViewModel viewModel, Integer questionIndex)
    {
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
                    System.out.println(answers);
                }
            });
            buttonGroup.add(choice);
            this.add(choice);
        }
    }
}