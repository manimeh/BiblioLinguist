package interface_adapter;

import entity.quiz.ActiveQuizDisplay;
import entity.quiz.MCQuizInterface;
import entity.quiz.SubmittedQuizDisplay;

public class TestQuiz implements MCQuizInterface {
    @Override
    public ActiveQuizDisplay activeDisplay() {
        return new ActiveQuizDisplay(new String[]{"Test"}, new String[][]{{"Ok"}});
    }

    @Override
    public SubmittedQuizDisplay submittedDisplay() {
        return null;
    }

    @Override
    public boolean submit(Integer[] answers) {
        return false;
    }
}
