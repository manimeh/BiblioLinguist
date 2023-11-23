package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public class StartNewGameInteractor implements StartNewGameInputBoundary
{
    private final StartNewGameOutputBoundary presenter;

    public StartNewGameInteractor(StartNewGameOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute()
    {
        StartNewGameOutputData outputData = new StartNewGameOutputData(Language.FRENCH, DifficultyLevel.BEGINNER,
                ReadingType.NEWS);
        presenter.prepareSuccessView(outputData);
    }
}
