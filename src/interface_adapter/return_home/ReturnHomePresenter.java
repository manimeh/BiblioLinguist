package interface_adapter.return_home;

import interface_adapter.ViewModelManager;
import interface_adapter.start_new_game.StartNewGameViewModel;
import use_case.return_home.ReturnHomeOutputBoundary;

public class ReturnHomePresenter implements ReturnHomeOutputBoundary {
    private final StartNewGameViewModel startNewGameViewModel;
    private final ViewModelManager viewModelManager;

    public ReturnHomePresenter(ViewModelManager viewModelManager, StartNewGameViewModel startNewGameViewModel) {
        this.viewModelManager = viewModelManager;
        this.startNewGameViewModel = startNewGameViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewModelManager.setActiveView(startNewGameViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }
}
