package interface_adapter.view_scores;

import use_case.view_scores.ScoresInputBoundary;

public class ScoresController {
    final ScoresInputBoundary scoresUseCaseInteractor;

    public ScoresController(ScoresInputBoundary scoresUseCaseInteractor) {
        this.scoresUseCaseInteractor = scoresUseCaseInteractor;
    }

    public void execute() {
        scoresUseCaseInteractor.execute();
    }
}
