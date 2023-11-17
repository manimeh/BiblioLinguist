package interface_adapter.submit_quiz;

import entity.quiz.MCQuiz;
import entity.user.User;

public class SubmitQuizState {
    private User user;
    private MCQuiz quiz;

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

    // Setting functions
    public void setUser(User user) {
        this.user = user;
    }

    public void setQuiz(MCQuiz quiz) {
        this.quiz = quiz;
    }
}
