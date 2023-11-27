package use_case.create_quiz;

public interface CreateQuizOutputBoundary {
    void prepareLoadView();
    void prepareSuccessView(CreateQuizOutputData outputData);
    void prepareFailView(String error);
}
