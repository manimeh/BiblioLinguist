package interface_adapter.submit_quiz;

import use_case.submit_quiz.SubmitQuizInputBoundary;

public class SubmitQuizController {
    private final SubmitQuizInputBoundary takeQuizUseCaseInteractor;

    public SubmitQuizController(SubmitQuizInputBoundary takeQuizUseCaseInteractor) {
        this.takeQuizUseCaseInteractor = takeQuizUseCaseInteractor;
    }

    public void execute() {
        takeQuizUseCaseInteractor.execute();
    }
}
