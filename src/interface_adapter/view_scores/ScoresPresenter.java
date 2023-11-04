package interface_adapter.view_scores;

import interface_adapter.ViewManagerModel;
import use_case.view_scores.ScoresOutputBoundary;
import use_case.view_scores.ScoresOutputData;

public class ScoresPresenter implements ScoresOutputBoundary {
    private final ScoresViewModel scoresViewModel;
    private ViewManagerModel viewManagerModel;
    public ScoresPresenter(ViewManagerModel viewManagerModel, ScoresViewModel scoresViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.scoresViewModel = scoresViewModel;
    }

    @Override
    public void prepareSuccessView(ScoresOutputData users) {
        ScoresState scoresState = scoresViewModel.getState();
        // Change state
        this.scoresViewModel.setState(scoresState);
        scoresViewModel.firePropertyChanged();
    }
}
