package use_case;

import use_case.view_scores.ViewScoresDataAccessInterface;

import java.util.ArrayList;

public class InMemoryUserScoresDataAccessObject implements ViewScoresDataAccessInterface {
    private final ArrayList<Float> lastTenScores;

    public InMemoryUserScoresDataAccessObject(ArrayList<Float> lastTenScores)
    {
        this.lastTenScores = lastTenScores;
    }

    @Override
    public ArrayList<Float> getLastTenScores() {
        return lastTenScores;
    }
}