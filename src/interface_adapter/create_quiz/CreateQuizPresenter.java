package interface_adapter.create_quiz;

import interface_adapter.ViewModelManager;
import interface_adapter.loading_screen.LoadingScreenViewModel;
import interface_adapter.submit_quiz.SubmitQuizState;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import use_case.create_quiz.CreateQuizOutputBoundary;
import use_case.create_quiz.CreateQuizOutputData;

public class CreateQuizPresenter implements CreateQuizOutputBoundary {
    private final CreateQuizViewModel createQuizViewModel;
    private final LoadingScreenViewModel loadingScreenViewModel;
    private final SubmitQuizViewModel submitQuizViewModel;

    private final ViewModelManager viewModelManager;

    public CreateQuizPresenter(ViewModelManager viewModelManager, CreateQuizViewModel createQuizViewModel,
                               LoadingScreenViewModel loadingScreenViewModel, SubmitQuizViewModel submitQuizViewModel) {
        this.viewModelManager = viewModelManager;
        this.createQuizViewModel = createQuizViewModel;
        this.loadingScreenViewModel = loadingScreenViewModel;
        this.submitQuizViewModel = submitQuizViewModel;
    }

    @Override
    public void prepareLoadView() {
        viewModelManager.setActiveView(loadingScreenViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(CreateQuizOutputData outputData) {
        SubmitQuizState submitQuizState = submitQuizViewModel.getState();
        submitQuizState.setQuiz(outputData.mcQuiz());
        submitQuizState.setReading(outputData.reading());
        submitQuizViewModel.firePropertyChanged();

        viewModelManager.setActiveView(submitQuizViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateQuizState createQuizStateState = createQuizViewModel.getState();
        createQuizStateState.setErrorMessage(error);
        createQuizViewModel.firePropertyChanged();

        viewModelManager.setActiveView(createQuizViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }
}
