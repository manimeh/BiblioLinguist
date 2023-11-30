package interface_adapter.return_home;

import entity.quiz.SubmittedQuizDisplay;

public class ReturnHomeState {
    private SubmittedQuizDisplay quizDisplay;

    public ReturnHomeState() {}

    public SubmittedQuizDisplay getQuizDisplay() {
        return this.quizDisplay;
    }

    public void setQuizDisplay(SubmittedQuizDisplay quizDisplay) {
        this.quizDisplay = quizDisplay;
    }
}
