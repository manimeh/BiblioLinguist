package interface_adapter.submit_quiz;

import entity.quiz.MCQuiz;
import entity.quiz.MCQuizInterface;
import entity.reading.Reading;
import entity.user.User;

public class SubmitQuizState {
    private User user;
    private MCQuizInterface quiz;
    private Reading reading;

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

    public MCQuizInterface getQuiz() {
        return this.quiz;
    }

    public String getSubmitQuizError() {
        return this.submitQuizError;
    }

    public Reading getReading() {
        return reading;
    }

    // Setting functions
    public void setUser(User user) {
        this.user = user;
    }

    public void setQuiz(MCQuizInterface quiz) {
        this.quiz = quiz;
    }


    public void setSubmitQuizError(String error) {
        this.submitQuizError = error;
    }

    public void setReading(Reading reading) {
        this.reading = reading;
    }
}
