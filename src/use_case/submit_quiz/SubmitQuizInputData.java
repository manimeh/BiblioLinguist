package use_case.submit_quiz;

import entity.quiz.MCQuiz;
import entity.user.User;

public record SubmitQuizInputData(MCQuiz quiz, Integer[] answers, User user) {}
