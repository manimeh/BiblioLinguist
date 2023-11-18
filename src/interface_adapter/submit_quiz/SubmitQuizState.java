package interface_adapter.submit_quiz;

import entity.quiz.MCQuiz;
import entity.user.User;

public class SubmitQuizState {
    private User user;
    private MCQuiz quiz;
    private String submitQuizError = null;

    public SubmitQuizState(User user, MCQuiz quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public SubmitQuizState() {}

    // Getting functions
    public User getUser() {
        return this.user;
    }

    public MCQuiz getQuiz() {
        return this.quiz;
    }

    public String getSubmitQuizError() {
        return this.submitQuizError;
    }

    // Setting functions
    public void setUser(User user) {
        this.user = user;
    }

    public void setQuiz(MCQuiz quiz) {
        this.quiz = quiz;
    }

    public void setSubmitQuizError(String error) {
        this.submitQuizError = error;
    }
}
