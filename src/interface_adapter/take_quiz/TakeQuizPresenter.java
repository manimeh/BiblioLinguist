package interface_adapter.take_quiz;

import interface_adapter.ViewManagerModel;
import use_case.take_quiz.TakeQuizOutputBoundary;
import use_case.take_quiz.TakeQuizOutputData;

public class TakeQuizPresenter implements TakeQuizOutputBoundary {
    private final TakeQuizViewModel quizViewModel;
    private final ViewManagerModel viewManager;

    public TakeQuizPresenter(ViewManagerModel viewManager, TakeQuizViewModel quizViewModel) {
        this.viewManager = viewManager;
        this.quizViewModel = quizViewModel;
    }

    @Override
    public void prepareSuccessView(TakeQuizOutputData outputData) {
        TakeQuizState quizState = quizViewModel.getState();
        // Change state
        this.quizViewModel.setState(quizState);
        quizViewModel.firePropertyChanged();
    }
}
