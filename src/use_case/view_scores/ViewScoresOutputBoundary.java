package use_case.view_scores;

public interface ViewScoresOutputBoundary {
    //The convention is to name this method prepareSuccessView, so it's probably better to stick with that name
    //to avoid confusion
    void prepareSuccessView(ViewScoresOutputData viewScoresOutputData);

}
