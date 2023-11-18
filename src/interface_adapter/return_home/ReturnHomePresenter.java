package interface_adapter.return_home;

import interface_adapter.ViewManagerModel;
import use_case.return_home.ReturnHomeOutputBoundary;
import use_case.return_home.ReturnHomeOutputData;

public class ReturnHomePresenter implements ReturnHomeOutputBoundary {
    private final ReturnHomeViewModel returnHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public ReturnHomePresenter(ViewManagerModel viewManagerModel, ReturnHomeViewModel returnHomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.returnHomeViewModel = returnHomeViewModel;
    }

    @Override
    public void prepareSuccessView(ReturnHomeOutputData outputData) {
        ReturnHomeState returnHomeState = returnHomeViewModel.getState();
        // Change state
        this.returnHomeViewModel.setState(returnHomeState);
        returnHomeViewModel.firePropertyChanged();
    }
}
