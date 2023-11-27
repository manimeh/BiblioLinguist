package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import use_case.create_quiz.CreateQuizDataAccessInterface;

public class StartNewGameInteractor implements StartNewGameInputBoundary
{
    private final StartNewGameDataAccessInterface dataAccessor;
    private final StartNewGameOutputBoundary presenter;

    public StartNewGameInteractor(StartNewGameDataAccessInterface dataAccessor,
                                  StartNewGameOutputBoundary presenter) {
        this.dataAccessor = dataAccessor;
        this.presenter = presenter;
    }

    @Override
    public void execute()
    {
        StartNewGameOutputData outputData = new StartNewGameOutputData(dataAccessor.getPreferredLanguage(),
                dataAccessor.getPreferredDifficultyLevel(),
                dataAccessor.getPreferredReadingType());
        presenter.prepareSuccessView(outputData);
    }
}
