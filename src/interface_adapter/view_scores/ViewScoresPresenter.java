package interface_adapter.view_scores;

import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

public class ViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;

    public ViewScoresPresenter(ViewScoresViewModel viewScoresViewModel) {
        this.viewScoresViewModel = viewScoresViewModel;
    }

    @Override
    public void prepareScoreView(ViewScoresOutputData response) {
        ViewScoresState viewScoresState = viewScoresViewModel.getState();
        viewScoresState.setViewableScores("Your last 10 scores: " + String.join("," response) + "\nAverage from your last 10 scores: ");

    }
}
