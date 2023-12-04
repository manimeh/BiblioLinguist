package use_case;

import entity.user.User;
import use_case.submit_quiz.SubmitQuizDataAccessInterface;
import use_case.view_scores.ViewScoresDataAccessInterface;

import java.util.ArrayList;

public class InMemoryUserScoresDataAccessObject implements ViewScoresDataAccessInterface, SubmitQuizDataAccessInterface {
    private ArrayList<Float> lastTenScores = new ArrayList<>();

    public InMemoryUserScoresDataAccessObject(ArrayList<Float> lastTenScores)
    {
        this.lastTenScores = lastTenScores;
    }

    public InMemoryUserScoresDataAccessObject() {}

    @Override
    public ArrayList<Float> getLastTenScores() {
        return lastTenScores;
    }

    @Override
    public void saveScore(Float score, User user) {
        lastTenScores.add(score);
    }
}