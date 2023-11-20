package use_case.view_scores;

import java.util.ArrayList;

public interface ViewScoresDataAccessInterface {
    ArrayList<Float> getLastTenScores();
}
