package interface_adapter.view_scores;

import interface_adapter.ViewManagerModel;
import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewScoresPresenter(ViewManagerModel viewManagerModel, ViewScoresViewModel viewScoresViewModel) {
        this.viewScoresViewModel = viewScoresViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewScoresOutputData response) {
        ArrayList<Float> scoresArray = new ArrayList<>(response.scoresArray());
        Collections.reverse(scoresArray);
        ViewScoresState viewScoresState = viewScoresViewModel.getState();

        if (scoresArray.isEmpty()) {
            viewScoresState.setViewScoresMessage("You have no scores!");
        }
        else {
            float sumOfScores = (float) scoresArray.stream().mapToDouble(Float::floatValue).sum();
            viewScoresState.setViewScoresMessage("Your last 10 scores: " +
                    scoresArray.stream().map(String::valueOf).collect(Collectors.joining("%, "))
                    + "%\nAverage from your last 10 scores: " + String.format("%.2f", sumOfScores/scoresArray.size()) + "%");
        }
    }
}
