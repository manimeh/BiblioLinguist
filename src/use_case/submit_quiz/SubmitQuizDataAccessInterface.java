package use_case.submit_quiz;

import entity.user.User;

public interface SubmitQuizDataAccessInterface {
    void saveScore(Float score, User user);
}
