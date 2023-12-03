package use_case.return_home;

import interface_adapter.ViewModelManager;
import interface_adapter.start_new_game.StartNewGameViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnHomeUseCaseTest {
    @Test
    void successTest() {
        ViewModelManager viewModelManager = new ViewModelManager();
        StartNewGameViewModel startNewGameViewModel = new StartNewGameViewModel();

        ReturnHomePresenter presenter = new ReturnHomePresenter(viewModelManager, startNewGameViewModel);
        ReturnHomeInteractor interactor = new ReturnHomeInteractor(presenter);
        ReturnHomeController controller = new ReturnHomeController(interactor);
        controller.execute();

        assertEquals(viewModelManager.getActiveView(), startNewGameViewModel.getViewName());
    }
}