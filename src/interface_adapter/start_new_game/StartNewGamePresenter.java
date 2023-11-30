package interface_adapter.start_new_game;

import interface_adapter.ViewModelManager;
import interface_adapter.create_quiz.CreateQuizState;
import interface_adapter.create_quiz.CreateQuizViewModel;
import use_case.start_new_game.StartNewGameOutputBoundary;
import use_case.start_new_game.StartNewGameOutputData;

public class StartNewGamePresenter implements StartNewGameOutputBoundary
{
    private final CreateQuizViewModel createQuizViewModel;
    private final ViewModelManager viewModelManager;

    public StartNewGamePresenter(ViewModelManager viewModelManager,
                                 CreateQuizViewModel createQuizViewModel) {
        this.viewModelManager = viewModelManager;
        this.createQuizViewModel = createQuizViewModel;
    }

    @Override
    public void prepareSuccessView(StartNewGameOutputData outputData)
    {
        CreateQuizState createQuizStateState = createQuizViewModel.getState();
        createQuizStateState.setReadingLanguage(outputData.defaultLanguage());
        createQuizStateState.setReadingDifficulty(outputData.defaultDifficultyLevel());
        createQuizStateState.setReadingType(outputData.defaultReadingType());
        createQuizViewModel.firePropertyChanged();

        viewModelManager.setActiveView(createQuizViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }
}
