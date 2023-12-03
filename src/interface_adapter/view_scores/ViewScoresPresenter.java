package interface_adapter.view_scores;

import interface_adapter.ViewModelManager;
import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;
    private final ViewModelManager viewManagerModel;

    public ViewScoresPresenter(ViewModelManager viewManagerModel, ViewScoresViewModel viewScoresViewModel) {
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
            viewScoresState.setViewScoresMessage(
                    "Your last 10 scores: " + scoresArray.stream().map(String::valueOf).collect(Collectors.joining("%, "))
                    + "%\nAverage from your last 10 scores: " + String.format("%.1f", sumOfScores/scoresArray.size()) + "%"
                    + "\nYour last score: " + String.format("%.1f", scoresArray.get(0)) + "%"
            );
        }
        viewScoresViewModel.firePropertyChanged();
    }
}
