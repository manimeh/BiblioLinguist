package use_case.submit_quiz;

import entity.quiz.MCQuiz;
import entity.user.User;

public interface SubmitQuizDataAccessInterface {
    void addScoresData(Float score, User user);
}
