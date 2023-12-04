package interface_adapter;

import entity.quiz.ActiveQuizDisplay;
import entity.quiz.MCQuizInterface;
import entity.quiz.SubmittedQuizDisplay;

public class TestQuiz implements MCQuizInterface {
    private final boolean successfulSubmit;
    private final ActiveQuizDisplay activeQuizDisplay;
    private final SubmittedQuizDisplay submittedQuizDisplay;

    public TestQuiz(boolean successfulSubmit, ActiveQuizDisplay activeQuizDisplay, SubmittedQuizDisplay submittedQuizDisplay) {
        this.successfulSubmit = successfulSubmit;
        this.activeQuizDisplay = activeQuizDisplay;
        this.submittedQuizDisplay = submittedQuizDisplay;
    }

    @Override
    public ActiveQuizDisplay activeDisplay() {
        return activeQuizDisplay;
    }

    @Override
    public SubmittedQuizDisplay submittedDisplay() {
        return submittedQuizDisplay;
    }

    @Override
    public boolean submit(Integer[] answers) {
        return successfulSubmit;
    }
}
