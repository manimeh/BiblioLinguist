package interface_adapter.create_quiz;

import use_case.generate_quiz.GenQuizInputBoundary;

public class CreateQuizController {
    private final GenQuizInputBoundary genQuizUseCaseInteractor;

    public CreateQuizController(GenQuizInputBoundary genQuizUseCaseInteractor) {
        this.genQuizUseCaseInteractor = genQuizUseCaseInteractor;
    }

    public void execute() {
        genQuizUseCaseInteractor.execute();
    }
}
