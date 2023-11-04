package interface_adapter.take_quiz;

import use_case.take_quiz.TakeQuizInputBoundary;

public class TakeQuizController {
    private final TakeQuizInputBoundary takeQuizUseCaseInteractor;

    public TakeQuizController(TakeQuizInputBoundary takeQuizUseCaseInteractor) {
        this.takeQuizUseCaseInteractor = takeQuizUseCaseInteractor;
    }

    public void execute() {
        takeQuizUseCaseInteractor.execute();
    }
}
