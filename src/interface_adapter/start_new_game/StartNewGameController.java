package interface_adapter.start_new_game;

import use_case.start_new_game.StartNewGameInputBoundary;

public class StartNewGameController
{

    private final StartNewGameInputBoundary interactor;

    public StartNewGameController(StartNewGameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute()
    {
        interactor.execute();
    }
}
