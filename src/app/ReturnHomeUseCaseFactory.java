package app;

import interface_adapter.ViewModelManager;
import interface_adapter.return_home.ReturnHomeController;
import interface_adapter.return_home.ReturnHomePresenter;
import interface_adapter.return_home.ReturnHomeViewModel;
import interface_adapter.start_new_game.StartNewGameViewModel;
import use_case.return_home.ReturnHomeInputBoundary;
import use_case.return_home.ReturnHomeInteractor;
import use_case.return_home.ReturnHomeOutputBoundary;
import view.ResultsView;

public class ReturnHomeUseCaseFactory {
    /** Prevent instantiation. */
    private ReturnHomeUseCaseFactory() {}

    public static ResultsView create(
            ViewModelManager viewModelManager, ReturnHomeViewModel returnHomeViewModel,
            StartNewGameViewModel startNewGameViewModel) {

        ReturnHomeController returnHomeController = createReturnHomeUseCase(viewModelManager, startNewGameViewModel);
        return new ResultsView(returnHomeViewModel, returnHomeController);
    }

    private static ReturnHomeController createReturnHomeUseCase(ViewModelManager viewModelManager,
                                                                StartNewGameViewModel startNewGameViewModel)
    {
        ReturnHomeOutputBoundary returnHomeOutputBoundary = new ReturnHomePresenter(viewModelManager, startNewGameViewModel);
        ReturnHomeInputBoundary returnHomeInputBoundary = new ReturnHomeInteractor(returnHomeOutputBoundary);
        return new ReturnHomeController(returnHomeInputBoundary);
    }
}
