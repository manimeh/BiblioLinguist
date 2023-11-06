package interface_adapter.generate_quiz;

import use_case.generate_quiz.GenQuizInputBoundary;

public class GenQuizController {
    private final GenQuizInputBoundary genQuizUseCaseInteractor;

    public GenQuizController(GenQuizInputBoundary genQuizUseCaseInteractor) {
        this.genQuizUseCaseInteractor = genQuizUseCaseInteractor;
    }

    public void execute() {
        genQuizUseCaseInteractor.execute();
    }
}
