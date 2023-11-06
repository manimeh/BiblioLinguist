package interface_adapter.view_scores;

import interface_adapter.ViewManagerModel;
import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

public class ViewViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewViewScoresPresenter(ViewManagerModel viewManagerModel, ViewScoresViewModel viewScoresViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewScoresViewModel = viewScoresViewModel;
    }

    @Override
    public void prepareSuccessView(ViewScoresOutputData outputData) {
        ViewScoresState viewScoresState = viewScoresViewModel.getState();
        // Change state
        this.viewScoresViewModel.setState(viewScoresState);
        viewScoresViewModel.firePropertyChanged();
    }
}
