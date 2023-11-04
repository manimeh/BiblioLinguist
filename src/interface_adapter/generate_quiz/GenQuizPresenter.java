package interface_adapter.generate_quiz;

import interface_adapter.ViewManagerModel;
import use_case.generate_quiz.GenQuizOutputBoundary;
import use_case.generate_quiz.GenQuizOutputData;

public class GenQuizPresenter implements GenQuizOutputBoundary {
    private final GenQuizViewModel genQuizViewModel;
    private ViewManagerModel viewManagerModel;
    public GenQuizPresenter(ViewManagerModel viewManagerModel, GenQuizViewModel genQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.genQuizViewModel = genQuizViewModel;
    }

    @Override
    public void prepareSuccessView(GenQuizOutputData users) {
        GenQuizState genQuizState = genQuizViewModel.getState();
        // Change state
        this.genQuizViewModel.setState(genQuizState);
        genQuizViewModel.firePropertyChanged();
    }
}
