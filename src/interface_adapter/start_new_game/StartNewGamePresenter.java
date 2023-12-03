package interface_adapter.start_new_game;

import interface_adapter.ViewModelManager;
import interface_adapter.create_quiz.CreateQuizState;
import interface_adapter.create_quiz.CreateQuizViewModel;
import use_case.start_new_game.StartNewGameOutputBoundary;
import use_case.start_new_game.StartNewGameOutputData;

public class StartNewGamePresenter implements StartNewGameOutputBoundary
{
    private final CreateQuizViewModel createQuizViewModel;
    private final ViewModelManager viewManagerModel;

    public StartNewGamePresenter(ViewModelManager viewManagerModel,
                                 CreateQuizViewModel createQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
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

        viewManagerModel.setActiveView(createQuizViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
