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

    private final ViewModelManager viewManagerModel;

    public CreateQuizPresenter(ViewModelManager viewManagerModel, CreateQuizViewModel createQuizViewModel,
                               LoadingScreenViewModel loadingScreenViewModel, SubmitQuizViewModel submitQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createQuizViewModel = createQuizViewModel;
        this.loadingScreenViewModel = loadingScreenViewModel;
        this.submitQuizViewModel = submitQuizViewModel;
    }

    @Override
    public void prepareLoadView() {
        viewManagerModel.setActiveView(loadingScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(CreateQuizOutputData outputData) {
        SubmitQuizState submitQuizState = submitQuizViewModel.getState();
        submitQuizState.setQuiz(outputData.mcQuiz());
        submitQuizState.setReading(outputData.reading());
        submitQuizViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(submitQuizViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateQuizState createQuizStateState = createQuizViewModel.getState();
        createQuizStateState.setErrorMessage(error);
        createQuizViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createQuizViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
