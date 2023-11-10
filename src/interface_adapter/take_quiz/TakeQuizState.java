package interface_adapter.take_quiz;

import entity.quiz.MCQuiz;
import entity.user.User;

public class TakeQuizState {
    private User user;
    private MCQuiz quiz;

    public TakeQuizState(User user, MCQuiz quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public TakeQuizState() {}

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
