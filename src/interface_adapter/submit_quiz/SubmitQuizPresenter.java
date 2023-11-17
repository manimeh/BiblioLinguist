package interface_adapter.submit_quiz;

import interface_adapter.ViewManagerModel;
import use_case.submit_quiz.SubmitQuizOutputBoundary;
import use_case.submit_quiz.SubmitQuizOutputData;

public class SubmitQuizPresenter implements SubmitQuizOutputBoundary {
    private final SubmitQuizViewModel quizViewModel;
    private final ViewManagerModel viewManager;

    public SubmitQuizPresenter(ViewManagerModel viewManager, SubmitQuizViewModel quizViewModel) {
        this.viewManager = viewManager;
        this.quizViewModel = quizViewModel;
    }

    @Override
    public void prepareSuccessView(SubmitQuizOutputData outputData) {
        SubmitQuizState quizState = quizViewModel.getState();
        // Change state
        this.quizViewModel.setState(quizState);
        quizViewModel.firePropertyChanged();
    }
}
