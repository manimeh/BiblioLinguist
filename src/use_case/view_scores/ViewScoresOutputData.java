package use_case.view_scores;

import java.util.ArrayList;

public class ViewScoresOutputData {
    private final ArrayList<Float> scoresArray;
    public ViewScoresOutputData(ArrayList<Float> scoresArray) {
        this.scoresArray = scoresArray;
    }

    public ArrayList<Float> getScoresArray() {
        return scoresArray;
    }
}
