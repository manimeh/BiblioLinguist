package interface_adapter.view_scores;

import use_case.view_scores.ViewScoresInputBoundary;

public class ViewScoresController {
    private final ViewScoresInputBoundary viewScoresUseCaseInteractor;

    public ViewScoresController(ViewScoresInputBoundary viewScoresUseCaseInteractor) {
        this.viewScoresUseCaseInteractor = viewScoresUseCaseInteractor;
    }

    public void execute() {
        viewScoresUseCaseInteractor.execute();
    }
}
