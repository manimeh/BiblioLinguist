package interface_adapter.create_quiz;

import interface_adapter.ViewManagerModel;
import use_case.generate_quiz.GenQuizOutputBoundary;
import use_case.generate_quiz.GenQuizOutputData;

public class CreateQuizPresenter implements GenQuizOutputBoundary {
    private final CreateQuizViewModel createQuizViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateQuizPresenter(ViewManagerModel viewManagerModel, CreateQuizViewModel createQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createQuizViewModel = createQuizViewModel;
    }

    @Override
    public void prepareSuccessView(GenQuizOutputData outputData) {
        CreateQuizState createQuizState = createQuizViewModel.getState();
        // Change state
        this.createQuizViewModel.setState(createQuizState);
        createQuizViewModel.firePropertyChanged();
    }
}
