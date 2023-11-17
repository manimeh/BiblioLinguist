package interface_adapter.view_scores;

import interface_adapter.ViewManagerModel;
import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

public class ViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewScoresPresenter(ViewManagerModel viewManagerModel, ViewScoresViewModel viewScoresViewModel) {
        this.viewScoresViewModel = viewScoresViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareScoreView(ViewScoresOutputData response) {
        if (response.getScoresArray().isEmpty()) {
            ViewScoresState viewScoresState = viewScoresViewModel.getState();
            viewScoresState.setViewScoresMessage("You have no scores!");
        } else {
            Float sumOfScores = 0.0F;
            for (Float i: response.getScoresArray()) {
                sumOfScores += i;
            }
            ViewScoresState viewScoresState = viewScoresViewModel.getState();
            viewScoresState.setViewScoresMessage("Your last 10 scores: " + String.join(",", response.getScoresArray().toString().substring(1, response.getScoresArray().size() - 1)) + "\nAverage from your last 10 scores: ");

        }
    }
}
