package use_case.submit_quiz;

import entity.quiz.SubmittedQuizDisplay;

public record SubmitQuizOutputData(SubmittedQuizDisplay quizDisplay, boolean useCaseFailed) {}
