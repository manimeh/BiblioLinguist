package interface_adapter.create_quiz;

import interface_adapter.ViewManagerModel;
import use_case.create_quiz.CreateQuizOutputBoundary;
import use_case.create_quiz.CreateQuizOutputData;

public class CreateQuizPresenter implements CreateQuizOutputBoundary {
    private final CreateQuizViewModel createQuizViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateQuizPresenter(ViewManagerModel viewManagerModel, CreateQuizViewModel createQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createQuizViewModel = createQuizViewModel;
    }

    @Override
    public void prepareSuccessView(CreateQuizOutputData outputData) {
        CreateQuizState createQuizState = createQuizViewModel.getState();
        // Change state
        this.createQuizViewModel.setState(createQuizState);
        createQuizViewModel.firePropertyChanged();
    }
}
