package interface_adapter.submit_quiz;

import entity.quiz.MCQuiz;
import entity.reading.Reading;
import entity.user.User;
import use_case.submit_quiz.SubmitQuizInputBoundary;
import use_case.submit_quiz.SubmitQuizInputData;

public class SubmitQuizController {
    private final SubmitQuizInputBoundary takeQuizUseCaseInteractor;

    public SubmitQuizController(SubmitQuizInputBoundary takeQuizUseCaseInteractor) {
        this.takeQuizUseCaseInteractor = takeQuizUseCaseInteractor;
    }

    public void execute(MCQuiz quiz, Reading reading, User user) {
        SubmitQuizInputData inputData = new SubmitQuizInputData(quiz, reading, user);
        takeQuizUseCaseInteractor.execute(inputData);
    }
}
