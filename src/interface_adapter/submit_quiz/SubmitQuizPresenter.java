package interface_adapter.submit_quiz;

import entity.quiz.SubmittedQuizDisplay;
import entity.user.User;
import interface_adapter.ViewModelManager;
import interface_adapter.return_home.ReturnHomeState;
import interface_adapter.return_home.ReturnHomeViewModel;
import use_case.submit_quiz.SubmitQuizOutputBoundary;
import use_case.submit_quiz.SubmitQuizOutputData;
import view.ResultsView;

public class SubmitQuizPresenter implements SubmitQuizOutputBoundary {
    private final SubmitQuizViewModel quizViewModel;
    private final ReturnHomeViewModel returnHomeViewModel;
    private final ViewModelManager viewManager;

    public SubmitQuizPresenter(ViewModelManager viewManager, SubmitQuizViewModel quizViewModel,
                               ReturnHomeViewModel returnHomeViewModel) {
        this.viewManager = viewManager;
        this.quizViewModel = quizViewModel;
        this.returnHomeViewModel = returnHomeViewModel;
    }

    @Override
    public void prepareSuccessView(SubmitQuizOutputData outputData) {
        SubmitQuizState quizState = quizViewModel.getState();

        // Update state with the given output data
        SubmittedQuizDisplay quizDisplay = outputData.quizDisplay();

        ReturnHomeState returnHomeState = returnHomeViewModel.getState();
        returnHomeState.setQuizDisplay(outputData.quizDisplay());
        returnHomeViewModel.firePropertyChanged();

        viewManager.setActiveView(ResultsView.VIEW_NAME);
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SubmitQuizState submitQuizState = quizViewModel.getState();
        submitQuizState.setSubmitQuizError(error);
        quizViewModel.firePropertyChanged();
    }
}
