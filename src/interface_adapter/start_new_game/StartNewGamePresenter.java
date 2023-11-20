package interface_adapter.start_new_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_quiz.CreateQuizState;
import interface_adapter.create_quiz.CreateQuizViewModel;
import use_case.start_new_game.StartNewGameOutputBoundary;
import use_case.start_new_game.StartNewGameOutputData;

public class StartNewGamePresenter implements StartNewGameOutputBoundary
{
    private final StartNewGameViewModel startNewGameViewModel;
    private final CreateQuizViewModel createQuizViewModel;
    private final ViewManagerModel viewManagerModel;

    public StartNewGamePresenter(ViewManagerModel viewManagerModel,
                                 StartNewGameViewModel startNewGameViewModel,
                                 CreateQuizViewModel createQuizViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.startNewGameViewModel = startNewGameViewModel;
        this.createQuizViewModel = createQuizViewModel;
    }

    @Override
    public void prepareSuccessView(StartNewGameOutputData outputData)
    {
        CreateQuizState createQuizStateState = createQuizViewModel.getState();
        createQuizStateState.setReadingLanguage(outputData.defaultLanguage());
        createQuizStateState.setReadingDifficulty(outputData.defaultDifficultyLevel());
        createQuizStateState.setReadingType(outputData.defaultReadingType());
        this.createQuizViewModel.setState(createQuizStateState);
        createQuizViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createQuizViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
