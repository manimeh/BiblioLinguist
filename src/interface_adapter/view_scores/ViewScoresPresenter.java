package interface_adapter.view_scores;

import use_case.view_scores.ViewScoresOutputBoundary;
import use_case.view_scores.ViewScoresOutputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ViewScoresPresenter implements ViewScoresOutputBoundary {
    private final ViewScoresViewModel viewScoresViewModel;

    public ViewScoresPresenter(ViewScoresViewModel viewScoresViewModel) {
        this.viewScoresViewModel = viewScoresViewModel;
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

            viewScoresState.setViewScoresMessage(String.format("""
                            Your last %s scores: %s%%
                            Average from your last %s scores: %s%%
                            Your last score: %s%%""",
                    scoresArray.size(),
                    scoresArray.stream().map(String::valueOf).collect(Collectors.joining("%, ")),
                    String.format("%.1f", sumOfScores/scoresArray.size()),
                    scoresArray.size(),
                    String.format("%.1f", scoresArray.get(0)))
            );
        }
        viewScoresViewModel.firePropertyChanged();
    }
}
