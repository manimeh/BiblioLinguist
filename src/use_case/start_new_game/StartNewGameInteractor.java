package use_case.start_new_game;

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
