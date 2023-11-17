package use_case.view_scores;

public class ViewScoresInteractor implements ViewScoresInputBoundary{
    final ViewScoresDataAccessInterface viewScoresDataAccessObject;
    final ViewScoresOutputBoundary viewScoresPresenter;

    public ViewScoresInteractor(ViewScoresDataAccessInterface viewScoresDataAccessObject, ViewScoresOutputBoundary viewScoresPresenter) {
        this.viewScoresDataAccessObject = viewScoresDataAccessObject;
        this.viewScoresPresenter = viewScoresPresenter;
    }


    @Override
    public void execute() {
        ViewScoresOutputData viewScoresOutputData = new ViewScoresOutputData(viewScoresDataAccessObject.getLastTenScores());
        viewScoresPresenter.prepareScoreView(viewScoresOutputData);
    }
}
