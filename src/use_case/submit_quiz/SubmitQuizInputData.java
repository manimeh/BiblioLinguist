package use_case.submit_quiz;

import entity.quiz.MCQuiz;
import entity.reading.Reading;
import entity.user.User;

public record SubmitQuizInputData(MCQuiz quiz, Reading reading, User user) {
    public MCQuiz getQuiz() {
        return this.quiz;
    }
    public Reading getReading() {
        return this.reading;
    }
    public User getUser() {
        return this.user;
    }
}
