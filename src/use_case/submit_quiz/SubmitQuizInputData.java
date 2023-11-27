package use_case.submit_quiz;

import entity.quiz.MCQuizInterface;
import entity.user.User;

public record SubmitQuizInputData(MCQuizInterface quiz, Integer[] answers, User user) {}
