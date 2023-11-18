package interface_adapter.submit_quiz;

import entity.quiz.SubmittedQuizDisplay;
import entity.user.User;
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

        // Update state with the given output data
        SubmittedQuizDisplay quizDisplay = outputData.quizDisplay();
        User user = quizState.getUser();

        user.addScore(quizDisplay.score());
        this.quizViewModel.setState(quizState);

        viewManager.setActiveView("Results View");
        quizViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SubmitQuizState submitQuizState = quizViewModel.getState();
        submitQuizState.setSubmitQuizError(error);
        quizViewModel.firePropertyChanged();
    }
}
