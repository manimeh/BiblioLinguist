package use_case.submit_quiz;

public interface SubmitQuizOutputBoundary {
    void prepareSuccessView(SubmitQuizOutputData outputData);

    void prepareFailView(String error);
}
