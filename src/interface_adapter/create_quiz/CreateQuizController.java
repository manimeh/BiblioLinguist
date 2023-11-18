package interface_adapter.create_quiz;

import use_case.create_quiz.CreateQuizInputBoundary;

public class CreateQuizController {
    private final CreateQuizInputBoundary createQuizUseCaseInteractor;

    public CreateQuizController(CreateQuizInputBoundary createQuizUseCaseInteractor) {
        this.createQuizUseCaseInteractor = createQuizUseCaseInteractor;
    }

    public void execute() {
        createQuizUseCaseInteractor.execute();
    }
}
